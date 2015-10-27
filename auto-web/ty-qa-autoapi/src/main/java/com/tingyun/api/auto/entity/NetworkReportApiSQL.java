package com.tingyun.api.auto.entity;

public class NetworkReportApiSQL {
	
	
	public static final String FIND_ALL_SQL = "select * from TEST_NETWORK_API order by id desc";
	
	public static final String DELETE_SQL = "delete from TEST_NETWORK_API where id=?";
	
	public static final String SAVE_SQL = "insert into TEST_NETWORK_API(caseName,authKey,parameter,url,json) values(?,?,?,?,?)";
	
	public static final String UPDATE_SQL = "update TEST_NETWORK_API set caseName=?,authKey=?,parameter=?,url=? where id=?";
	
	public static final String COUNT_SQL = "select count(*) from TEST_NETWORK_API";
	
	public static final String FIND_XML_JSON_SQL = "select xml,json from TEST_NETWORK_API where id=?";
	
	public static final String PAGE_SQL =FIND_ALL_SQL+ " limit ?,?";
	
}
