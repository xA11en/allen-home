package com.tingyun.api.auto.dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tingyun.api.auto.dao.ApiRuturnResultDao;
import com.tingyun.api.auto.dao.DBUtilsDAO;
import com.tingyun.api.auto.entity.ApiRuturnResultBean;
import com.tingyun.api.auto.entity.ApiRuturnResultSql;
import com.tingyun.api.auto.utils.DBUtils;
/**
* @author :chenjingli 
* @version ：2015-9-23 下午2:33:44 
* @decription: 
 */
@Repository
@Transactional
public class ApiRuturnResultDaoImpl implements ApiRuturnResultDao {
	
	@Autowired DBUtilsDAO dbUtilsDAO;
	
	@Override
	public void saveAppApiResult(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		dbUtilsDAO.update(ApiRuturnResultSql.INSERT_RESULT_TO_ARETURN, params);
	}

	@Override
	public List<ApiRuturnResultBean> selectAll() throws Exception{
		// TODO Auto-generated method stub
//		 Statement stmt = DBUtils.getConnection().createStatement();
//		 ResultSet result = stmt.executeQuery(ApiRuturnResultSql.SELECT_ALL);
//		 List<ApiRuturnResultBean> resultBeans = new ArrayList<ApiRuturnResultBean>();
//		 while(result.next()){//判断是否还有下一行  
//			 	ApiRuturnResultBean apiRuturnResultBean = new ApiRuturnResultBean();
//	            apiRuturnResultBean.setId(result.getInt("id"));
//	            apiRuturnResultBean.setAppId(result.getInt("app_id"));
//	            apiRuturnResultBean.setAppJson(result.getString("app_api_result"));
//	            resultBeans.add(apiRuturnResultBean);
//	        }
		 List<ApiRuturnResultBean> list =  dbUtilsDAO.query(ApiRuturnResultBean.class, ApiRuturnResultSql.SELECT_ALL, null);
		 return list;
	}



}
