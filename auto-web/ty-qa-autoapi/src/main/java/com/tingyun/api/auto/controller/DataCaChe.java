package com.tingyun.api.auto.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.tingyun.api.auto.common.Constant;
import com.tingyun.api.auto.entity.ReportApiBean;

/**
* @author :chenjingli 
* @version ：2015-9-15 下午6:12:36 
* @decription:
 */
@Component
public class DataCaChe {
	private static final Logger Log = LoggerFactory.getLogger(DataCaChe.class);
	/**
	 * 
	
	private ServletContext servletContext;  
	private static ConcurrentMap<String, List<ReportApi>> CACHE = new   ConcurrentHashMap<String, List<ReportApi>>();
	//初始化相关数据
	public static void init(){
		List<ReportApi> list = null;
		try {
			QueryRunner runner = new QueryRunner();  
			list =  runner.query(DBUtils.getConnection(),"select * from TEST_APP_API order by id desc", new BeanListHandler<ReportApi>(ReportApi.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Log.info("初始化基本信息,查询的数据个数为:[ {} ]",list.size());
		CACHE.putIfAbsent(Constant.CARCHE_FLAG, list);
	}
	
	//刷新缓存
	public static void reLoadCaeChe(){
		CACHE.clear();
		init();
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		  // 把缓存放入到servletcontext  
		init();
        servletContext.setAttribute(Constant.SERVLETCONTEXT_FLAG, CACHE);
	}
	
	 */
}
