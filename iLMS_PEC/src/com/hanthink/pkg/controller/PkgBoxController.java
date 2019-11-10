package com.hanthink.pkg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanthink.pkg.manager.PkgBoxManager;
import com.hanthink.pkg.model.PkgBoxModel;
import com.hanthink.pkg.model.PkgProposalModel;
import com.hanthink.util.excel.ExcelUtil;
import com.hotent.base.api.model.ResultMessage;
import com.hotent.base.core.util.string.StringUtil;
import com.hotent.base.core.web.GenericController;
import com.hotent.base.core.web.RequestUtil;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageJson;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.api.model.IUser;
import com.hotent.sys.util.ContextUtil;
import com.hotent.sys.util.SysPropertyUtil;

/**
* <p>Title: PkgBoxController.java<／p>
* <p>Description: <／p>
* <p>Company: hanthink<／p>
* @author luoxq
* @date 2018年9月25日
*/
@Controller
@RequestMapping("/pkg/pkgBox")
public class PkgBoxController extends GenericController{

	@Resource
	private PkgBoxManager pkgBoxManager;
	
	private static Logger log = LoggerFactory.getLogger(PkgBoxController.class);
	
	/**
	 * 
	* @Title: curdlistJson 
	* @Description: 分页查询箱种维护信息 
	* @param @param request
	* @param @param reponse
	* @param @param model
	* @param @return
	* @param @throws Exception    
	* @return PageJson 
	* @throws
	 */
	@RequestMapping("curdlistJson")
    public @ResponseBody PageJson curdlistJson(HttpServletRequest request,
            HttpServletResponse reponse,
            @ModelAttribute("model") PkgBoxModel model) throws Exception{
        DefaultPage p=new DefaultPage(new RowBounds(getQueryFilter(request).getPage().getStartIndex(), getQueryFilter(request).getPage().getPageSize()));
        IUser user = ContextUtil.getCurrentUser();
        model.setFactoryCode(user.getCurFactoryCode());
        
        PageList<PkgBoxModel> pageList = (PageList<PkgBoxModel>) pkgBoxManager.queryPkgBoxForPage(model, p);
        return new PageJson(pageList);
    }
	
	/**
	 * 
	* @Title: save 
	* @Description: 修改和新增箱种维护信息 
	* @param @param request
	* @param @param response
	* @param @param pkgBoxModel
	* @param @throws Exception
	* @return void
	* @throws
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response,@RequestBody PkgBoxModel pkgBoxModel) throws Exception{
		String resultMsg = null;
		String id = pkgBoxModel.getId();
		IUser user = ContextUtil.getCurrentUser();
		
		try {
			if(StringUtil.isEmpty(id)){
				String boxCode=pkgBoxModel.getBoxCode();
				PkgBoxModel p= pkgBoxManager.getByCode(boxCode);
				if (null != p) {
					resultMsg="箱COD已存在";
					writeResultMessage(response.getWriter(),resultMsg,ResultMessage.FAIL);
					return;
				}else {
					pkgBoxModel.setCreateUser(user.getAccount()); //记录创建人
					pkgBoxModel.setFactoryCode(user.getCurFactoryCode());
					pkgBoxManager.create(pkgBoxModel);
					resultMsg="添加成功";
				}
			}else{
				pkgBoxModel.setLastModifiedUser(user.getAccount());//记录修改人
				pkgBoxManager.updateAndLog(pkgBoxModel, RequestUtil.getIpAddr(request));
				resultMsg="更新成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			throw new Exception("系统错误,请联系管理员");
//			resultMsg="操作失败";
//			writeResultMessage(response.getWriter(),resultMsg,e.getMessage(),ResultMessage.FAIL);
		}
	}
	
	/**
	 * 
	* @Title: 获取包装管理信息明细
	* @Description:  
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws Exception    
	* @return PkgBoxModel 
	* @throws
	 */
	@RequestMapping("curdgetJson")
	public @ResponseBody PkgBoxModel curdgetJson(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String id = RequestUtil.getString(request, "id");
		if(StringUtil.isEmpty(id)){
			return new PkgBoxModel();
		}
		return pkgBoxManager.get(id);
	}
	
	/**
	 * 
	* @Title: remove 
	* @Description: 删除箱种信息 
	* @param @param request
	* @param @param response
	* @param @throws Exception    
	* @return void 
	* @throws 
	* @author luoxq
	* @date   2018年10月9日 下午1:48:05
	 */
	@RequestMapping("remove")
	public void remove(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultMessage message=null;
		try {
			String arryId = RequestUtil.getString(request, "ids");
			String[] aryIds = arryId.split(",");
			pkgBoxManager.removeAndLogByIds(aryIds, RequestUtil.getIpAddr(request));
			message = new ResultMessage(ResultMessage.SUCCESS, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			throw new Exception("系统错误,请联系管理员");
//			message = new ResultMessage(ResultMessage.FAIL, "删除失败");
		}
		writeResultMessage(response.getWriter(), message);
	}
	
	/**
	 * 
	* @Title: getMsgByBoxCode 
	* @Description: 在包装提案界面通过箱code获取长宽高 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws Exception    
	* @return PkgBoxModel 
	* @throws 
	* @author luoxq
	* @date   2018年10月9日 下午1:50:26
	 */
	@RequestMapping("getMsgByBoxCode")
	public @ResponseBody PkgBoxModel getMsgByBoxCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String boxCode = RequestUtil.getString(request, "boxCode");

		if(StringUtil.isEmpty(boxCode)){
			return new PkgBoxModel();
		}
		PkgBoxModel p = pkgBoxManager.getByCode(boxCode);
		HttpSession session = request.getSession();
		if (null != session) {
			session.removeAttribute("pkgBoxModel");
		}
	    if (null != p) {
			session.setAttribute("pkgBoxModel", p);
		}
		return p;
	}
	
	/**
	 * 加载可使用的箱CODE信息,用于下拉框选择使用等
	 * @param request
	 * @param response
	 * @author ZUOSL	
	 * @DATE	2018年11月27日 上午11:44:14
	 */
	@RequestMapping("loadBoxCodeData")
	public @ResponseBody List<PkgBoxModel> loadBoxCodeData(HttpServletRequest request,HttpServletResponse response){
		PkgBoxModel pkgBox = new PkgBoxModel();
		pkgBox.setStatus(PkgBoxModel.BOX_STATUS_YES);
		return pkgBoxManager.loadBoxCodeData(pkgBox);
	}
	
	/**
	 * 
	 * @Description: 获取需要维护的箱种下拉框
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return List<Map<String,Object>>  
	 * @throws  
	 * @author luoxq
	 * @date 2019年1月7日 下午5:38:34
	 */
	@RequestMapping("loadBoxTypeData")
	public @ResponseBody List<PkgBoxModel> loadBoxTypeData(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		IUser user = ContextUtil.getCurrentUser();
		map.put("factoryCode",user.getCurFactoryCode());
		return pkgBoxManager.loadBoxTypeData(map);
	}
	
	/**
	 * 
	 * @Description: 箱种信息导出
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @throws Exception   
	 * @return void  
	 * @throws  
	 * @author luoxq
	 * @date 2019年1月9日 上午9:52:48
	 */
	@RequestMapping("downloadBoxModel")
	public void downloadBoxModel(HttpServletRequest request, HttpServletResponse response, PkgBoxModel model)
			throws Exception {
		try {
			String exportFileName = "箱种信息导出" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			IUser user = ContextUtil.getCurrentUser();
			model.setFactoryCode(user.getCurFactoryCode());

			List<PkgBoxModel> list = pkgBoxManager.queryPkgBoxByKey(model);
			/**
			 * 如果查询记录超过10000条则报错
			 */
			if (0 == list.size()) {
				ExcelUtil.exportNoDataError(request, response);
				return;
			}
			int sysMaxNum = SysPropertyUtil.getIntByAlias("EXCEL_EXPORT_MAX_SIZE", 10000); // 获取系统所允许的最大导出数量
			if (list.size() > sysMaxNum) {
				ExcelUtil.exportNumError(sysMaxNum, request, response);
				return;
			}

			String[] headers = { "箱CODE", "箱种", "包装长(mm)", "包装宽(mm)", "包装高(mm)", "状态"};
			String[] columns = { "boxCode", "boxType", "packLength", "packWidth", "packHeight", "status"};
			int[] widths = { 100, 100, 100, 100, 100, 100};
			ExcelUtil.exportExcel(ExcelUtil.EXCEL_XLSX, request, response, exportFileName, list, headers, widths,
					columns);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			throw new Exception("系统错误,请联系管理员");
		}
	}
}