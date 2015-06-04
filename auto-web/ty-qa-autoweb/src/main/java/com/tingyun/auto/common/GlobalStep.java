package com.tingyun.auto.common;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.page.report.singletask.GeneralizPerforPage;

/**
* @author :chenjingli 
* @version ：2015-5-12 下午2:09:07 
* @decription: 初始化 before 或者 after 的一些信息
 */
public class GlobalStep{
	
	protected DriverBrowser driverBrowser;
	
	protected Logger logger;
	
	protected static 	RpcLoginPage reportPage;
	protected static 	GeneralizPerforPage genPage;
	
	protected static final String caseStart ="执行开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	protected static final String caseEnd ="执行结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	protected static final String FAIL ="失败 >> >>";
	public GlobalStep() {
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		 reportPage = new RpcLoginPage(driverBrowser);
		 genPage = new GeneralizPerforPage(driverBrowser);
	}
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