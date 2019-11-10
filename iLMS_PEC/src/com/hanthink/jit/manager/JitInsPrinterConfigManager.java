package com.hanthink.jit.manager;

import java.util.List;

import com.hanthink.jit.model.JitInsPrinterConfigModel;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;

/**
 * @ClassName: JitInsPrinterConfigManager
 * @Description: 拣货工程与打印机配置
 * @author dtp
 * @date 2018年10月25日
 */
public interface JitInsPrinterConfigManager extends Manager<String, JitInsPrinterConfigModel>{

	/**
	 * @Description: 拣货工程与打印机关系查询  
	 * @param: @param model
	 * @param: @param page
	 * @param: @return    
	 * @return: PageList<JitInsPrinterConfigModel>   
	 * @author: dtp
	 * @date: 2018年10月25日
	 */
	PageList<JitInsPrinterConfigModel> queryJitInsPrinterConfigPage(JitInsPrinterConfigModel model, DefaultPage page);

	/**
	 * @Description: 新增拣货工程与打印机关系
	 * @param: @param model    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年10月25日
	 */
	void insertJitInsPrintConfig(JitInsPrinterConfigModel model);

	/**
	 * @Description: 更新拣货工程与打印机关系
	 * @param: @param model    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年10月25日
	 */
	void updateJitInsPrintConfig(JitInsPrinterConfigModel model);

	/**
	 * @Description: 拣货工程与打印机关系查询  excel导出
	 * @param: @param model
	 * @param: @return    
	 * @return: List<JitInsPrinterConfigModel>   
	 * @author: dtp
	 * @date: 2018年10月25日
	 */
	List<JitInsPrinterConfigModel> queryJitInsPrinterConfigList(JitInsPrinterConfigModel model);

	/**
	 * @Description: 删除 拣货工程与打印机关系  
	 * @param: @param models
	 * @param: @param ipAddr    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年10月25日
	 */
	void deleteJitInsPrintConfig(JitInsPrinterConfigModel[] models, String ipAddr);

	/**
	 * @Description: 判断业务主键是否存在 
	 * @param: @param models
	 * @param: @param ipAddr    
	 * @return: void   
	 * @author: dtp
	 * @date: 2018年10月25日
	 */
	List<JitInsPrinterConfigModel> queryPkIsConflict(JitInsPrinterConfigModel model);

}