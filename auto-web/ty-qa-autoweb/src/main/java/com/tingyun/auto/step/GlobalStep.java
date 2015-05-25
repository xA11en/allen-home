package com.tingyun.auto.step;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.page.rpc.RpcLoginPage;

/**
* @author :chenjingli 
* @version ：2015-5-12 下午2:09:07 
* @decription: 初始化 before 或者 after 的一些信息
 */
public class GlobalStep{
	
	protected DriverBrowser driverBrowser;
	
	protected Logger logger;
	
	protected static final String caseStart ="执行开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	protected static final String caseEnd ="执行结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	protected static final String FAIL ="失败 >> >>";
	
//	@BeforeClass
//	public void beforeClass() {
//		driverBrowser = new DriverBrowser(BrowserType.Firefox);
//		driverBrowser.open(SeleniumSettings.URL);
//	}
//	
//	@BeforeMethod
//	public void beforeMethod(){
//		
//	}
//	
//	@AfterMethod
//	public void afterMethod(){
//		
//	}
//	
//	@BeforeTest
//	public void beforeTest(){
//		
//	}
//	
//	@AfterTest
//	public void afterTest(){
//		
//	}
//	@AfterClass
//	public void afterClass(){
//		driverBrowser.quit();
//	}
	public void pinfo(Class<?> clazz,String info){
		logger = LoggerFactory.getLogger(clazz);
		logger.info(info);
	}
	public void perror(Class<?> clazz,String error,Throwable e){
		logger = LoggerFactory.getLogger(clazz);
		logger.error(error);
	}
	public void pdebug(Class<?> clazz,String debug){
		logger = LoggerFactory.getLogger(clazz);
		logger.debug(debug);
	}
}
