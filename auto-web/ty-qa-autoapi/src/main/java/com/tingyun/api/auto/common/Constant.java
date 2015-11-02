package com.tingyun.api.auto.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author chenjingli
 *
 */
public class Constant {
	public static Map<String, String> MAP = new HashMap<String, String>();
	public static ArrayList<String> suites = new ArrayList<String>();

	/**
	 * system
	 */
	public final static String TESTNGLISTENER = "TestNGListener";
	
	
	//测试报告 失败状态
	public static final String REPORT_FAIL_STATUS = "Fail";
	
	//标示接口返回成功springmvc
	public static final String SUCCESS_STATUS = "success";
	
	//登陆标识
	public static final String LOGIN_SUCCESS_STATUS = "login_success";
}
