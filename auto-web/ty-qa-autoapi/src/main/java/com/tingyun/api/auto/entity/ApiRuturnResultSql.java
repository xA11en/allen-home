package com.tingyun.api.auto.entity;

public class ApiRuturnResultSql {
	
	public static final String INSERT_RESULT_TO_ARETURN = "insert into API_RETURN_RESULT(app_id,app_api_result,timestamp,errorCount) values(?,?,?,?)";
	
	
	public static final String SELECT_ALL = "select * from API_RETURN_RESULT order by id desc";
}
