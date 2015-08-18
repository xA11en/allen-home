package com.tingyun.api.auto.utils;

import com.tingyun.api.auto.dao.impl.ReportApiDaoImpl;

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
