package com.hanthink.jit.dao;

import java.util.List;

import com.hanthink.jit.model.JitLabelModel;
import com.hotent.base.db.api.Dao;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;

/**
 * @ClassName: JitLabelDao
 * @Description: 零件标签查询
 * @author dtp
 * @date 2018年9月29日
 */
public interface JitLabelDao extends Dao<String, JitLabelModel>{

	/**
	 * @Description: 零件标签查询
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JitLabelModel>   
	 * @author: dtp
	 * @date: 2018年9月29日
	 */
	PageList<JitLabelModel> queryJitLabelPage(JitLabelModel model, DefaultPage page);

	/**
	 * @Description: 零件标签查询导出 
	 * @param: @param model
	 * @param: @return    
	 * @return: List<JitLabelModel>   
	 * @author: dtp
	 * @date: 2018年9月29日
	 */
	List<JitLabelModel> queryJitLabelList(JitLabelModel model);

	/**
	 * @Description: 获取标签(标签打印) 
	 * @param: @param model
	 * @param: @return    
	 * @return: JitLabelModel   
	 * @author: dtp
	 * @date: 2018年10月4日
	 */
	JitLabelModel queryJitLabel(JitLabelModel model);

	/**
	 * @Description: 更新打印信息  
	 * @param: @param jitLabelModel    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年12月26日
	 */
	void updateJitLabelPrintInfo(JitLabelModel jitLabelModel);

	/**
	 * @Description:  更新拉动标签uuid
	 * @param: @param jitLabelModel    
	 * @return: void   
	 * @author: dtp
	 * @date: 2019年3月20日
	 */
	void updateLabelUUID(JitLabelModel jitLabelModel);

}