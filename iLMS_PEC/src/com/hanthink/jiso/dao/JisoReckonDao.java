package com.hanthink.jiso.dao;

import java.util.List;

import com.hanthink.jiso.model.JisoInsManuDealModel;
import com.hanthink.jiso.model.JisoInsModel;
import com.hanthink.jiso.model.JisoNetReqModel;
import com.hanthink.jiso.model.JisoOrderManuDealModel;
import com.hanthink.jiso.model.JisoPartgroupModel;
import com.hanthink.jiso.model.JisoVehQueueModel;
import com.hanthink.jit.model.JitInsModel;
import com.hanthink.pub.model.PubPlanCodeModel;
import com.hotent.base.db.api.Dao;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;

/**
 * @ClassName: JisoReckonDao
 * @Description: 厂外同步推算控制台
 * @author dtp
 * @date 2018年9月11日
 */
public interface JisoReckonDao extends Dao<String, JisoVehQueueModel>{

	/**
	 * @Description: 厂外同步推算控制台-查询过点车序    
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JisoVehQueueModel>   
	 * @author: dtp
	 * @date: 2018年9月15日
	 */
	PageList<JisoVehQueueModel> queryJisoVehQueuePage(JisoVehQueueModel model, DefaultPage page);

	/**
	 * @Description: 查询待组票净需求-零件组列表   
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: List<JisoPartgroupModel>   
	 * @author: dtp
	 * @date: 2018年9月12日
	 */
	List<JisoPartgroupModel> queryJisoPartGroupPage(JisoPartgroupModel model, DefaultPage page);

	/**
	 * @Description: 厂外同步推算控制台-启停推算 
	 * @param: @param pubPlanCodeModel    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年9月12日
	 */
	int updateJisoExecState(PubPlanCodeModel pubPlanCodeModel);

	/**
	 * @Description: 厂外同步推算控制台-手工组票
	 * @param: @param list    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年9月13日
	 */
	void insertBatchManuDealBill(List<JisoInsManuDealModel> list);

	/**
	 * @Description: 查询未组票零件净需求 
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JisoNetReqModel>   
	 * @author: dtp
	 * @date: 2018年9月13日
	 */
	PageList<JisoNetReqModel> queryRemainByPartgroupNo(JisoNetReqModel model, DefaultPage page);

	/**
	 * @Description: 根据信息点获取推算状态 
	 * @param: @param model
	 * @param: @return    
	 * @return: PubPlanCodeModel   
	 * @author: dtp
	 * @date: 2018年9月13日
	 */
	PubPlanCodeModel queryReckonState(PubPlanCodeModel model);

	/**
	 * @Description: 待组单指示票-查询组单信息  
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JisoOrderManuDealModel>   
	 * @author: dtp
	 * @date: 2018年9月14日
	 */
	PageList<JisoInsModel> queryJisoGroupOrderPage(JisoInsModel model, DefaultPage page);

	/**
	 * @Description: 待组单指示票-查询未组单指示票列表     
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JisoInsModel>   
	 * @author: dtp
	 * @date: 2018年9月15日
	 */
	PageList<JisoInsModel> queryNotGroupBillPage(JisoInsModel model, DefaultPage page);

	/**
	 * @Description: 厂外同步推算控制台-查询过点车序导出  
	 * @param: @param model
	 * @param: @return    
	 * @return: List<JisoVehQueueModel>   
	 * @author: dtp
	 * @date: 2018年9月15日
	 */
	List<JisoVehQueueModel> queryJisoVehQueueList(JisoVehQueueModel model);

	/**
	 * @Description: 获取厂外同步零件组下拉框 
	 * @param: @return    
	 * @return: List<JisoInsModel>   
	 * @author: dtp
	 * @date: 2018年10月2日
	 */
	List<JisoInsModel> queryPartgroupComboData();

	/**
	 * @Description: 手工组单 
	 * @param: @param list    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年10月2日
	 */
	void updateManuDealOrder(List<JisoOrderManuDealModel> list);

	/**
	 * @Description: 手工组单
	 * @param: @param jisoOrderManuDealModel    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年10月3日
	 */
	void updateManuDealOrder(JisoOrderManuDealModel model);

	/**
	 * @Description: 查询MM_JISO_INS_MANU_DEAL是否存在某零件组
	 * @param: @return    
	 * @return: List<JisoInsManuDealModel>   
	 * @author: dtp
	 * @date: 2018年11月14日
	 */
	List<JisoInsManuDealModel> queryJisoInsManuDealList(JisoInsManuDealModel model);

	/**
	 * @Description: 手工组票MM_JISO_INS_MANU_DEAL
	 * @param: @param model    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年11月14日
	 */
	void updateManuDealBill(JisoInsManuDealModel model);

	/**
	 * @Description: 手工组单MM_JISO_ORDER_MANU_DEAL 
	 * @param: @param jisoOrderManuDealModel
	 * @param: @return    
	 * @return: List<JisoOrderManuDealModel>   
	 * @author: dtp
	 * @date: 2018年11月14日
	 */
	List<JisoOrderManuDealModel> queryJisoOrderManuDealList(JisoOrderManuDealModel jisoOrderManuDealModel);

	/**
	 * @Description: 手工组单,存在更新,不存在新增   
	 * @param: @param jisoOrderManuDealModel    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年11月14日
	 */
	void insertManuDealOrder(JisoOrderManuDealModel jisoOrderManuDealModel);


}