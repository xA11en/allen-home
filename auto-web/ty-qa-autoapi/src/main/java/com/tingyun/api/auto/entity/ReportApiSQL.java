package com.tingyun.api.auto.entity;

public class ReportApiSQL {
	
	
	public static final String FIND_ALL_SQL = "select * from TEST_APP_API order by id desc";
	
	public static final String DELETE_SQL = "delete from TEST_APP_API where id=?";
	
	public static final String SAVE_SQL = "insert into TEST_APP_API(caseName,c6nnnfcg,parameter,url) values(?,?,?,?)";
	
	public static final String UPDATE_SQL = "update TEST_APP_API set caseName=?,c6nnnfcg=?,parameter=?,url=? where id=?";
	
	public static final String COUNT_SQL = "select count(*) from TEST_APP_API";
	
	public static final String FIND_XML_JSON_SQL = "select xml,json from TEST_APP_API where id=?";
	
	public static final String PAGE_SQL =FIND_ALL_SQL+ " limit ?,?";
	
}
