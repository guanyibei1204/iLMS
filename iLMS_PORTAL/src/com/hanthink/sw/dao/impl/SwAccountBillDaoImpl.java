package com.hanthink.sw.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hanthink.sw.dao.SwAccountBillDao;
import com.hanthink.sw.model.SwAccountBillModel;
import com.hanthink.sw.model.SwDemandForecastModel;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
/**
 * 
* <p>Title: SwAccountBillDaoImpl</p>  
* <p>Description: 发票对账管理daoImpl</p> 
* <p>Company: hanthink</p>
* @author luoxq  
* @date 2018年10月22日 下午4:36:44
 */
@Repository
public class SwAccountBillDaoImpl extends MyBatisDaoImpl<String, SwAccountBillModel>
implements SwAccountBillDao{

	@Override
	public String getNamespace() {
		
		return SwAccountBillModel.class.getName();
	}

	/**
	 * 
	 * <p>Title: queryJisoAccountBillPage</p>  
	 * <p>Description: 发票对账管理，分页查询界面</p>  
	 * @param model
	 * @param p
	 * @return  
	 * @authoer luoxq
	 * @time 2018年10月22日 下午4:39:31
	 */
	@Override
	public PageList<SwAccountBillModel> queryJisoAccountBillPage(SwAccountBillModel model, DefaultPage p) {
		
		return (PageList<SwAccountBillModel>) this.getList("queryJisoAccountBillPage", model, p);
	}

	/**
	 * 
	 * <p>Title: queryJisoAccountBillDetailPage</p>  
	 * <p>Description: 发票对账管理，明细查看功能</p>  
	 * @param model
	 * @param p
	 * @return  
	 * @authoer luoxq
	 * @time 2018年10月22日 下午5:05:46
	 */
	@Override
	public PageList<SwAccountBillModel> queryJisoAccountBillDetailPage(SwAccountBillModel model, DefaultPage p) {
		
		return (PageList<SwAccountBillModel>) this.getList("queryJisoAccountBillDetailPage", model, p);
	}

	/**
	 * 
	 * <p>Title: billFeedback</p>  
	 * <p>Description: 发票对账管理，反馈功能</p>  
	 * @param model  
	 * @authoer luoxq
	 * @time 2018年10月22日 下午5:44:30
	 */
	@Override
	public void billFeedback(SwAccountBillModel model) {
		this.insertByKey("billFeedback", model);
	}

	/**
	 * 
	 * <p>Title: querySwAccountBillByKey</p>  
	 * <p>Description: 发票对账管理界面导出功能</p>  
	 * @param model
	 * @return  
	 * @authoer luoxq
	 * @time 2018年10月22日 下午6:06:59
	 */
	@Override
	public List<SwAccountBillModel> querySwAccountBillByKey(SwAccountBillModel model) {
		return (List<SwAccountBillModel>) this.getList("queryJisoAccountBillPage", model);
	}

	/**
	 * 
	 * <p>Title: querySwAccountDetailBillByKey</p>  
	 * <p>Description: 发票对账管理明细查看，导出功能</p>  
	 * @param model
	 * @return  
	 * @authoer luoxq
	 * @time 2018年10月22日 下午6:13:21
	 */
	@Override
	public List<SwAccountBillModel> querySwAccountDetailBillByKey(SwAccountBillModel model) {
		return (List<SwAccountBillModel>) this.getList("queryJisoAccountBillDetailPage", model);
	}

	/**
	 * 
	 * @Description: 发票反馈，把数据写入到接口表结算对账单发票反馈中间表
	 * @param @param model   
	 * @return void  
	 * @throws  
	 * @author luoxq
	 * @date 2018年12月14日 上午12:43:30
	 */
	@Override
	public void insertIfAccountInv(SwAccountBillModel model) {
		this.insertByKey("insertIfAccountInv", model);
		
	}

	/**
	 * 
	 * @Description: 发票反馈，把数据写入到接口表结算对账单发票反馈明细中间表
	 * @param @param model   
	 * @return void  
	 * @throws  
	 * @author luoxq
	 * @date 2018年12月14日 上午12:43:52
	 */
	@Override
	public void insertIfAccountInvDetail(SwAccountBillModel model) {
		this.insertByKey("insertIfAccountInvDetail", model);
		
	}

	/**
	 * 
	 * @Description: 根据对账单号，发票号，发票代码判断发票是否已存在
	 * @param @param model
	 * @param @return   
	 * @return Integer  
	 * @throws  
	 * @author luoxq
	 * @date 2018年12月19日 下午9:29:26
	 */
	@Override
	public Integer getCountBill(SwAccountBillModel model) {
		
		return this.sqlSessionTemplate.selectOne("getCountBill",model);
	}

	/**
	 * 
	 * @Description: 查看发票反馈情况
	 * @param @param model
	 * @param @param p
	 * @param @return   
	 * @return PageList<SwAccountBillModel>  
	 * @throws  
	 * @author luoxq
	 * @date 2019年1月3日 下午2:02:28
	 */
	@Override
	public PageList<SwAccountBillModel> queryJisoAccountInvoicePage(SwAccountBillModel model, DefaultPage p) {
		
		return (PageList<SwAccountBillModel>) this.getByKey("queryJisoAccountInvoicePage", model, p);
	}

	/**
	 * 
	 * @Description: 发票反馈提交后修改主表中提交标识
	 * @param @param billNoArr
	 * @param @param submitStatusYes   
	 * @return void  
	 * @throws  
	 * @author luoxq
	 * @date 2019年1月3日 下午4:10:11
	 */
	@Override
	public void updateSubmitStatus(String[] billNoArr, String submitStatusYes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("billNoArr", billNoArr);
		map.put("submitStatusYes", submitStatusYes);
		this.updateByKey("updateSubmitStatus", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageList<SwAccountBillModel> queryJisoAccountBillSearchPage(SwAccountBillModel model, DefaultPage p) {
		
		return (PageList<SwAccountBillModel>) this.getList("queryJisoAccountBillSearchPage", model, p);
	}

	@Override
	public List<SwAccountBillModel> querySwAccountBillSearchByKey(SwAccountBillModel model) {
		
		return this.getByKey("queryJisoAccountBillSearchPage", model);
	}

	@Override
	public List<SwAccountBillModel> downloadSwAccountBillInvoiceModel(SwAccountBillModel model) {
		
		return this.getByKey("downloadSwAccountBillInvoiceModel", model);
	}

}
