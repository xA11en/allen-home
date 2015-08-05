package com.tingyun.api.auto.utils;

import com.tingyun.api.auto.dao.impl.ReportApiDaoImpl;

/**
* @author :chenjingli 
* @version ：2015-7-29 上午11:17:45 
* @decription:  dao  工厂
 */
public class Factory {
	public static Object getInstance(String type){
		Object obj = null;
		if("ReportApiDao".equals(type)){
			obj = new ReportApiDaoImpl();
			//obj = new EmployeeDAOHibernateImpl();
		}
		return obj;
	}
}
