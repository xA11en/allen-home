package com.tingyun.api.auto.entity;

public class ApiRuturnResultSql {
	
	public static final String INSERT_RESULT_TO_ARETURN = "insert into API_RETURN_RESULT(app_id,app_api_result,timestamp,errorCount) values(?,?,?,?)";
	
	
	public static final String SELECT_ALL = "select * from API_RETURN_RESULT order by id desc";
	
	public static final String SELECT_JSON_BY_APPID = "select * from API_RETURN_RESULT where app_id =?";
	
	//查询app接口错误表
	public static final String SELECT_APP_API_ERROR_SQL = "select * from API_RETURN_RESULT order by id desc";
}
