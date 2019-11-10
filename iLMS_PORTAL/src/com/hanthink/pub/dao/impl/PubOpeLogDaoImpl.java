package com.hanthink.pub.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hanthink.pub.dao.PubOpeLogDao;
import com.hanthink.pub.model.PubOpeLogModel;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.base.db.mybatis.domain.DefaultPage;


/**
 * 
 * <pre> 
 * 描述：BOM基础数据查询 DAO实现类
 * 构建组：x5-bpmx-platform
 * 作者:linzhuo
 * 邮箱:zhuo.lin@hotent.com
 * 日期:2018-09-29 10:39:09
 * 版权：汉思信息技术有限公司
 * </pre>
 */
@Repository
public class PubOpeLogDaoImpl extends MyBatisDaoImpl<String, PubOpeLogModel> implements PubOpeLogDao{

    @Override
    public String getNamespace() {
        return PubOpeLogModel.class.getName();
    }

    /**
     * 执行分页查询
     */
	@Override
	public List<PubOpeLogModel> queryPubOpeLogForPage(PubOpeLogModel model, DefaultPage p) {
		
		return this.getByKey("queryPubOpeLogForPage", model, p);
	}
	
}
