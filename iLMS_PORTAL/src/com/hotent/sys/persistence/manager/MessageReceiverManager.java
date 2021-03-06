package com.hotent.sys.persistence.manager;

import java.util.Map;

import com.hotent.base.manager.api.Manager;
import com.hotent.sys.persistence.model.MessageReceiver;

/**
 * 
 * <pre> 
 * 描述：sys_msg_receiver 处理接口
 * 构建组：x5-bpmx-platform
 * 作者:hugh
 * 邮箱:zxh@jee-soft.cn
 * 日期:2014-11-17 17:49:50
 * 版权：广州宏天软件有限公司
 * </pre>
 */
public interface MessageReceiverManager extends Manager<String, MessageReceiver>{

	/*Map<String, String> getMsgType();*/

	void updateReadStatus(String[] lAryId);

	
}
