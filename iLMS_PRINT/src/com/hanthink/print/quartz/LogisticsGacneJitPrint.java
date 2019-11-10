package com.hanthink.print.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hanthink.mes.common.constants.LogLevel;
import com.hanthink.mes.common.utilities.JdbcUtil;
import com.hanthink.mes.common.utilities.LogDBUtil;
import com.hanthink.mes.common.utilities.LogUtility;
import com.hanthink.mes.print.constants.PrintClass;
import com.hanthink.mes.print.constants.PrintType;
import com.hanthink.mes.print.impl.PrintJobWorkerManager;

/**
 * 
 * @Title: LogisticsGacneJitPrint.java
 * @Package: com.hanthink.print.quartz
 * @Description: 广汽新能源拉动指示票打印 
 * @author dtp
 * @date 2018-12-28
 */
public class LogisticsGacneJitPrint implements Job{
	
	private static boolean startAllJobWorker = false;
	private static PrintJobWorkerManager printJobWorkerManager = null;
	private static boolean timerRunning = false;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LogDBUtil.logTimer(jobExecutionContext);
		if(timerRunning) {
			return;
		}
		timerRunning = true;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Run Logistics Jit Print Job Worker : " + sdf.format(new Date()));
		try {
			if(startAllJobWorker == false)
			{
				printJobWorkerManager = new PrintJobWorkerManager(PrintClass.PRINT_CLASS_NETWORK, PrintType.PRINT_TYPE_LOGISTICS_JIT_PRINT);
				printJobWorkerManager.startAllJobWorker();
				startAllJobWorker = true;
				LogUtility.info("[ManifestPrint.execute] start all " + PrintType.PRINT_TYPE_LOGISTICS_JIT_PRINT + "  jobworker successfully .");
			}
		} catch (Exception e) {
			LogUtility.error(e.getLocalizedMessage(), e);
			LogDBUtil.logTransaction(e.getClass().getSimpleName(), e.getLocalizedMessage(), getClass().getName(), "function:execute", LogLevel.ERROR);
		}
		//加载拉动配送单数据
		loadLogicticsInfoCreatJobJit();
        timerRunning = false;
	}
	
	/**
	 * 从 MM_JIT_INS拉动配送单,关联拉动配送单打印机关系配置表(MM_JIT_INS_PRINTER_CONFIG)、 打印机配置表（MM_PR_PRINTER） 
	 * 和打印机任务表（MM_PR_JOB）以及打印机 业务与任务表（MM_PR_JOB_BUSINESS） 
	 * 来获取 (限制条件是未加载的)的 (指示票号，任务名和业务名，是否启用状态 )字段信息。
	 */
	public void loadLogicticsInfoCreatJobJit(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT T.INS_NO,");
		sb.append(" J.JOB_NAME, B.BUSINESS, B.ACTIVE");
		sb.append(" FROM MM_JIT_INS T");
		sb.append(" LEFT JOIN MM_JIT_INS_PRINTER_CONFIG C ON C.PLAN_CODE = T.PLAN_CODE AND C.PREPARE_PERSON = T.PREPARE_PERSON");
		sb.append(" LEFT JOIN MM_PR_PRINTER P ON P.ID = C.PRINTER_ID ");
		sb.append(" LEFT JOIN MM_PR_JOB J ON J.PRINTER_NAME = P.PRINTER_NAME");
		sb.append(" LEFT JOIN MM_PR_JOB_BUSINESS B ON B.JOB_NAME = J.JOB_NAME");
		sb.append(" WHERE J.JOB_TYPE = 'JIT' AND T.IS_LOAD = 0 AND C.IS_AUTO_PRINT = 1");
		sb.append(" ORDER BY T.PREPARE_PRODUCT_SEQNO, T.CREATION_TIME");
		
		Connection conn = null;
		
		try {
        	conn = JdbcUtil.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String business = resultSet.getString("BUSINESS");
				String jobName = resultSet.getString("JOB_NAME");
				String active = resultSet.getString("ACTIVE");
				if(("1").equals(active)==false){
					continue;
				}
				String serialNumber = resultSet.getString("INS_NO");
				if(	(business != null && business.trim().length() > 0) && 
						(jobName != null && jobName.trim().length() > 0) ){
					printJobWorkerManager.createJobQueue(business, jobName, serialNumber, null, null);
				}
				
				String updateSql = "UPDATE MM_JIT_INS T SET T.IS_LOAD = 1,T.LOAD_TIME = SYSDATE WHERE T.INS_NO = ?";
				PreparedStatement ps = conn.prepareStatement(updateSql);
				ps.setString(1, serialNumber);
				ps.execute();
				ps.close();
			}
			resultSet.close();
			preparedStatement.close();
			conn.close();
		} catch (SQLException e) {
			LogUtility.error(e.getLocalizedMessage(), e);
			LogDBUtil.logTransaction(e.getClass().getSimpleName(), e.getLocalizedMessage(), getClass().getName(), "function:loadLogicticsInfoCreateJobQueue", LogLevel.ERROR);
		} catch (Exception e) {
			LogUtility.error(e.getLocalizedMessage(), e);
			LogDBUtil.logTransaction(e.getClass().getSimpleName(), e.getLocalizedMessage(), getClass().getName(), "function:loadLogicticsInfoCreateJobQueue", LogLevel.ERROR);
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LogUtility.error(e.getLocalizedMessage(), e);
					LogDBUtil.logTransaction(e.getClass().getSimpleName(), e.getLocalizedMessage(), getClass().getName(), "function:loadLogicticsInfoCreateJobQueue", LogLevel.ERROR);
				}
			}
		}
		
	}

}