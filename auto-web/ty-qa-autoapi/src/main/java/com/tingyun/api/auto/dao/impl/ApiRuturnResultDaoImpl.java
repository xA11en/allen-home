package com.tingyun.api.auto.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tingyun.api.auto.dao.ApiRuturnResultDao;
import com.tingyun.api.auto.dao.DBUtilsDAO;
import com.tingyun.api.auto.entity.ApiRuturnResultBean;
import com.tingyun.api.auto.entity.ApiRuturnResultSql;
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
		 List<ApiRuturnResultBean> list =  dbUtilsDAO.query(ApiRuturnResultBean.class, ApiRuturnResultSql.SELECT_ALL, null);
		 return list;
	}

	@Override
	public List<ApiRuturnResultBean> selectByAppId(int id) throws Exception {
		// TODO Auto-generated method stub
		List<ApiRuturnResultBean> list =  dbUtilsDAO.query(ApiRuturnResultBean.class, ApiRuturnResultSql.SELECT_JSON_BY_APPID, id);
		 return list;
	}

	@Override
	public List<Map<String, Object>> selectByerrorCount() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mapList = dbUtilsDAO.queryMore(ApiRuturnResultSql.SELECT_APP_API_ERROR_SQL, null);
		return mapList;
	}

}
