package com.hanthink.mon.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hanthink.base.manager.impl.AbstractManagerImpl;
import com.hanthink.base.model.DictVO;
import com.hanthink.base.model.TableColumnVO;
import com.hanthink.base.model.TableOpeLogVO;
import com.hanthink.mon.dao.MonKbDao;
import com.hanthink.mon.manager.MonKbManager;
import com.hanthink.mon.model.BigStockKbModel;
import com.hanthink.mon.model.MonKbModel;
import com.hotent.base.api.Page;
import com.hotent.base.core.util.string.StringUtil;
import com.hotent.base.db.api.Dao;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.sys.util.ContextUtil;
/**
 * @ClassName: MonKbManager
 * @Description: 物流看板信息
 * @author luocc
 * @date 2018年11月3日
 */
@Service("MonKbManager")
public class MonKbManagerImpl extends AbstractManagerImpl<String,MonKbModel> implements MonKbManager{
	@Resource
	private MonKbDao monKbDao;
	
	@Override
	protected Dao<String, MonKbModel> getDao() {
		return monKbDao;
	}
	/**
	 * @Description:基础看板信息分页查询 
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<MonKbModel>   
	 * @author: luocc
	 * @date: 2018年11月3日
	 */
	@Override
	public PageList<MonKbModel> queryMonBaseKbPage(MonKbModel model, DefaultPage page) {
		model.setFactoryCode(ContextUtil.getCurrentUser().getCurFactoryCode());
		return monKbDao.queryMonBaseKbPage(model, page);
	}
	
	/**
	 * @Description:基础看板信息单条新增
	 * @param: @param model
	 * @param: @return    
	 * @return: String
	 * @author: luocc
	 * @date: 2018年11月3日
	 */
	@Override
	public String addMonBaseKbOne(MonKbModel model) {
		String resultCode="0";
		if(monKbDao.checkUnique(model)==0) {
			monKbDao.addMonBaseKbOne(model);
			resultCode="1";
		}
		return resultCode;
	}
	
	/**
	 * @Description:基础看板信息单条修改
	 * @param: @param model
	 * @param: @return    
	 * @return: String
	 * @author: luocc
	 * @date: 2018年11月3日
	 */
	@Override
	public void modifyMonBaseKbOne(MonKbModel model,String ipAddr) {
		//日志记录
		TableOpeLogVO logVO = new TableOpeLogVO();
		logVO.setOpeUserName(ContextUtil.getCurrentUser().getAccount());
		logVO.setOpeIp(ipAddr); 
		logVO.setFromName("界面更新");
		logVO.setOpeType(TableOpeLogVO.OPE_TYPE_MODIFY);
		logVO.setTableName("MM_MON_KB");
		TableColumnVO pkColumnVO = new TableColumnVO();
		pkColumnVO.setColumnName("ID");
		pkColumnVO.setColumnVal(model.getId());
		this.tableDataLogDao.logOpeTableData(logVO, pkColumnVO);
		
		monKbDao.modifyMonBaseKbOne(model);
	}
	/**
	 * @Description:基础看板信息删除
	 * @param: @param ids
	 * @param: @return    
	 * @return: String
	 * @author: luocc
	 * @date: 2018年11月3日
	 */
	@Override
	public void removeByIds(String[] ids, String ipAddr) {
		//日志记录
		//看板删除记录
		TableOpeLogVO logVO = new TableOpeLogVO();
		logVO.setOpeUserName(ContextUtil.getCurrentUser().getAccount());
		logVO.setOpeIp(ipAddr); 
		logVO.setFromName("数据删除");
		logVO.setOpeType(TableOpeLogVO.OPE_TYPE_DELETE);
		logVO.setTableName("MM_MON_KB");
		TableColumnVO pkColumnVO = new TableColumnVO();
		pkColumnVO.setColumnName("ID");
		pkColumnVO.setColumnValArr(ids);
		this.tableDataLogDao.logOpeTableDataBatch(logVO, pkColumnVO);
		//看板明细删除记录
		TableOpeLogVO logVO1 = new TableOpeLogVO();
		logVO1.setOpeUserName(ContextUtil.getCurrentUser().getAccount());
		logVO1.setOpeIp(ipAddr); 
		logVO1.setFromName("数据删除");
		logVO1.setOpeType(TableOpeLogVO.OPE_TYPE_DELETE);
		logVO1.setTableName("MM_MON_KB_DETAIL");
		TableColumnVO pkColumnVO1 = new TableColumnVO();
		pkColumnVO1.setColumnName("KB_ID");
		pkColumnVO1.setColumnValArr(ids);
		this.tableDataLogDao.logOpeTableDataBatch(logVO1, pkColumnVO1);
		//批量删除
		List<MonKbModel> ms=new ArrayList<MonKbModel>();
		for(String id:ids) {
	        MonKbModel m=new MonKbModel();
	        m.setId(id);
	        ms.add(m);
		}
		monKbDao.removeByKbIds(ms);
		
	}
	

	/**
	 * @Description:看板详情信息设置保存
	 * @param: @param model
	 * @param: @return    
	 * @return: String
	 * @author: luocc
	 * @date: 2018年11月8日
	 */
	@Override
	public String saveKbDetail(MonKbModel model) {
		String resultCode="0";
		if(monKbDao.checkKbId(model)==0) {
			monKbDao.addKbDetailOne(model);
			resultCode="1";//新增成功
		}else {
			monKbDao.modifyKbDetailOne(model);
			resultCode="2";//修改成功 
		}
		return resultCode;
	}
	
	/**
	 * @Description:查询看板详情
	 * @param: @param model
	 * @param: @return MonKbModel
	 * @return: 
	 * @author: luocc
	 * @date: 2018年11月8日
	 */
	@Override
	public MonKbModel queryKbDetailOne(MonKbModel model) {
		String factoryCode = ContextUtil.getCurrentUser().getCurFactoryCode();
		MonKbModel resultModel = null;
		if(StringUtils.isEmpty(model.getDistriPerson()) && StringUtil.isNotEmpty(model.getId())) {
			model.setFactoryCode(factoryCode);
			resultModel = monKbDao.queryKbDetailOne(model);
		}else {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("kbIp", model.getIp());
			paramMap.put("kbCode", model.getKbCode());
			paramMap.put("distriPerson", model.getDistriPerson());
			paramMap.put("factoryCode", factoryCode);
			paramMap.put("currBatchNo", model.getCurrbatchNo());
			paramMap.put("batchCycleNum", model.getBatchCycleNum());
			paramMap.put("runProcessNo", model.getCurrprocessNo());
			paramMap.put("kbStatus", model.getKbStatus());
			paramMap.put("runDelayFlag", model.getRunDelayFlag());
			paramMap.put("currbatchseqNo", model.getCurrbatchseqNo());
			try {
				resultModel = monKbDao.queryKbDetailForPage(paramMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultModel;
	}
	/**
	 * @Description:查询供应商看板
	 * @param: @param model
	 * @param: @return MonKbModel
	 * @return: 
	 * @author: luocc
	 * @date: 2018年11月10日
	 */
	@Override
	public MonKbModel selectBatchBySeq(MonKbModel model) {
		model.setFactoryCode(ContextUtil.getCurrentUser().getCurFactoryCode());
		return monKbDao.selectBatchBySeq(model);
	}
	/**
	 * @Description:查询厂内拉动看板
	 * @param: @param model
	 * @param: @return MonKbModel
	 * @return: 
	 * @author: luocc
	 * @date: 2018年11月10日
	 */
	@Override
	public MonKbModel selectBatchBySeqAndSkew(MonKbModel model) {
		return monKbDao.selectBatchBySeqAndSkew(model);
	}
	/**
	 * @Description:设置厂内拉动偏移进度
	 * @param: @param model
	 * @param: @return    
	 * @return: 
	 * @author: luocc
	 * @date: 2018年11月10日
	 */
	@Override
	public void updateSkewProcessNo(MonKbModel model, String ipAddr) {
		//日志记录
		TableOpeLogVO logVO = new TableOpeLogVO();
		logVO.setOpeUserName(ContextUtil.getCurrentUser().getAccount());
		logVO.setOpeIp(ipAddr); 
		logVO.setFromName("厂内拉动看板");
		logVO.setOpeType(TableOpeLogVO.OPE_TYPE_MODIFY);
		logVO.setTableName("MM_MON_KB");
		TableColumnVO pkColumnVO = new TableColumnVO();
		pkColumnVO.setColumnName("ID");
		pkColumnVO.setColumnVal(model.getId());
		this.tableDataLogDao.logOpeTableData(logVO, pkColumnVO);
		
		monKbDao.updateSkewProcessNo(model);	
	}
	/**
	 * @Description:封装下拉框  看板名称
	 * @param: @param model
	 * @param: @return List<DictVO>
	 * @return: 
	 * @author: luocc
	 * @date: 2018年11月8日
	 */
	@Override
	public List<DictVO> queryForMonKbName(MonKbModel model) {
		model.setFactoryCode(ContextUtil.getCurrentUser().getCurFactoryCode());
		return monKbDao.queryForMonKbName(model);
	}
	/**
	 * @Description:封装下拉框  工程
	 * @param: @param model
	 * @param: @return List<DictVO>
	 * @return: 
	 * @author: luocc
	 * @date: 2018年11月8日
	 */
	@Override
	public List<DictVO> queryForDistriPerson(MonKbModel model) {
		return monKbDao.queryForDistriPerson(model);
	}
	/**
	 * @Description:封装下拉框  安灯灯号
	 * @param: @param model
	 * @param: @return List<DictVO>
	 * @return: 
	 * @author: luocc
	 * @date: 2018年11月8日
	 */
	@Override
	public List<DictVO> queryForLampId() {
		return monKbDao.queryForLampId();
	}
	@Override
	public void queryLoguserJurisdiction(Map<String, String> paraMap) throws Exception {
		if (StringUtil.isEmpty(paraMap.get("account"))) {
			throw new Exception("获取当前登录用户信息失败");
		}
//		monKbDao.queryLoguserJurisdiction(paraMap);
	}
	@Override
	public List<BigStockKbModel> queryStockKbDetails(Map<String, String> paramMap) throws Exception {
		return monKbDao.queryStockKbDetails(paramMap);
	}
	@Override
	public PageList<MonKbModel> queryForKbConfig(Page page) throws Exception {
		String factoryCode = ContextUtil.getCurrentUser().getCurFactoryCode();
		return new PageList<MonKbModel>(monKbDao.queryForKbConfig(factoryCode,page));
	}
	@Override
	public MonKbModel getLoactionShowMessage(String ip) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ip", ip);
		paramMap.put("factoryCode", ContextUtil.getCurrentUser().getCurFactoryCode());
		MonKbModel model = new MonKbModel();
		model = monKbDao.getLoactionShowMessage(paramMap);
		model.setIp(ip);
		return model;
	}
	
	@Override
	public void updateCurrentKbStatus(MonKbModel model) throws Exception {
		model.setFactoryCode(ContextUtil.getCurrentUser().getCurFactoryCode());
		monKbDao.updateCurrentKbStatus(model);
	}
	
	/**
	 * 大物备货看板
	 */
	/**
	 * 看板表查询产品流水号
	 */
	@Override
	public List<BigStockKbModel> getBatchNo(String kbCode) {
		return monKbDao.getBatchNo(kbCode);
	}
	
	/**
	 * 根据批次查询单头
	 */
	@Override
	public List<BigStockKbModel> getBigStockKbAC(String distriProductSeqNo,String factoryCode,String workCenter) {
		return monKbDao.getBigStockKbAC(distriProductSeqNo,factoryCode,workCenter);
	}
	
	/**
	 * 根据批次查询单头
	 */
	@Override
	public List<BigStockKbModel> getBigStockKbWC(String distriProductSeqNo,String factoryCode,String workCenter) {
		return monKbDao.getBigStockKbWC(distriProductSeqNo,factoryCode,workCenter);
	}
	
	/**
	 * 根据产品流水号获取批次
	 */
	@Override
	public String getDistriProductSeqNo(String kbId, String currProcessNo) {
		return monKbDao.getDistriProductSeqNo(kbId,currProcessNo);
	}
	@Override
	public List<String> getBigStockKbDetail(String distriPerson) {
		return monKbDao.getBigStockKbDetail(distriPerson);
	}
	@Override
	public List<DictVO> queryDistriPersonForSelect(MonKbModel model) throws Exception {
		return monKbDao.queryDistriPersonForSelect(model);
	}
	@Override
	public String queryWorkCenterByPlanCode(MonKbModel model) throws Exception {
		return monKbDao.queryWorkCenterByPlanCode(model);
	}
	@Override
	public MonKbModel queryForKbConfig(MonKbModel model) throws Exception {
		return monKbDao.queryForKbConfig(model);
	}
	
	/**
	 * @Description: 获取冲压配送指示   
	 * @param: @param model
	 * @param: @return    
	 * @return: List<MonKbModel>   
	 * @author: dtp
	 * @date: 2019年4月22日
	 */
	@Override
	public List<MonKbModel> queryStampKb(MonKbModel model) {
		return monKbDao.queryStampKb(model);
	}
	
	/**
	 * @Description: 获取电池车间指示    
	 * @param: @param model
	 * @param: @return    
	 * @return: List<MonKbModel>   
	 * @author: dtp
	 * @date: 2019年4月22日
	 */
	@Override
	public List<MonKbModel> queryBatteryKb(MonKbModel model) {
		return monKbDao.queryBatteryKb(model);
	}
	
	/**
	 * 用户维护的参数值
	 */
	@Override
	public String selectProductSeqNoByParamCode(String productSeqNo) {
		return monKbDao.selectProductSeqNoByParamCode(productSeqNo);
	}
	
	/**
	 * 通过IP查询车间
	 */
	@Override
	public String selectWorkCenterByIp(String ip) {
		return monKbDao.selectWorkCenterByIp(ip);
	}
	
	/**
	 * 为空处理
	 */
	@Override
	public List<BigStockKbModel> selectBigStockKbByNull() {
		return monKbDao.selectBigStockKbByNull();
	}
	
	/**
	 * 延迟判定
	 */
	@Override
	public List<String> delayDetermination(String currBatchNo) {
		return monKbDao.delayDetermination(currBatchNo);
	}
	
	/**
	 * 查询看板代码
	 */
	@Override
	public String selectKbCodeByIp(Map<String, String> paramMap) {
		return monKbDao.selectKbCodeByIp(paramMap);
	}
	@Override
	public List<String> getBigStockKbDetail(MonKbModel model) {
		return monKbDao.getBigStockKbDetail(model);
	}
	

	
}