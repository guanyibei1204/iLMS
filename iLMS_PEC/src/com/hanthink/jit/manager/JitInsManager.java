package com.hanthink.jit.manager;

import java.util.List;

import com.hanthink.jit.model.JitInsModel;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;

/**
 * @ClassName: JitInsManager
 * @Description: 配送单查询
 * @author dtp
 * @date 2018年10月8日
 */
public interface JitInsManager extends Manager<String, JitInsModel>{

	/**
	 * @Description: 配送单查询   
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JitInsModel>   
	 * @author: dtp
	 * @date: 2018年10月8日
	 */
	PageList<JitInsModel> queryJitInsPage(JitInsModel model, DefaultPage page);

	/**
	 * @Description: 配送单导出
	 * @param: @param model
	 * @param: @return    
	 * @return: List<JitInsModel>   
	 * @author: dtp
	 * @date: 2018年10月8日
	 */
	List<JitInsModel> queryJitInsList(JitInsModel model);

	/**
	 * @Description: 配送单明细查询
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JitInsModel>   
	 * @author: dtp
	 * @date: 2018年10月8日
	 */
	PageList<JitInsModel> queryJitInsDetailPage(JitInsModel model, DefaultPage page);

	/**
	 * @Description: 查询配送单明细(配送单打印)
	 * @param: @param model_q
	 * @param: @return    
	 * @return: List<JitInsModel>   
	 * @author: dtp
	 * @date: 2018年10月9日
	 */
	List<JitInsModel> queryJitInsDetailList(JitInsModel model_q);

	/**
	 * @Description: 更新配送单打印状态
	 * @param: @param list_printInfo    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年10月9日
	 */
	void updatePrintInfo(List<JitInsModel> list_printInfo);

	/**
	 * @Description: 配送单明细导出
	 * @param: @param model
	 * @param: @return    
	 * @return: List<JitInsModel>   
	 * @author: dtp
	 * @date: 2019年5月6日
	 */
	List<JitInsModel> downloadJitInsDetail(JitInsModel model);


}