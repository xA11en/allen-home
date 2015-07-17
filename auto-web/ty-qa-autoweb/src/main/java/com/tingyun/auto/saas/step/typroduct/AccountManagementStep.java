package com.tingyun.auto.saas.step.typroduct;


import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.saas.page.RegisteredAndLoginPage;
import com.tingyun.auto.saas.page.typroduct.AccountManagementPage;
import com.tingyun.auto.utils.OperateProperties;
/**
* @author :chenjingli 
* @version ：2015-6-30 下午2:28:05 
* @decription: saas  账号管理 step
 */
//@Listeners({ TestResultListener.class})
public class AccountManagementStep extends GlobalStep {
	
	public static final String addZiAccount = "通过企业账号增加子账号-测试用例";
	public static final String editZiAccount = "通过企业账号编辑子账号-测试用例";
	public static final String deleteZiAccount = "通过企业账号删除子账号-测试用例";
	public static final String appRenewal = "续费听云app服务测试用例";
	private static AccountManagementPage managementPage;
	public static DriverBrowser driverBrowser;
	private static RegisteredAndLoginPage loginPage;
	
	@BeforeClass
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		managementPage = new AccountManagementPage(driverBrowser);
		loginPage = new RegisteredAndLoginPage(driverBrowser);
		driverBrowser.open(OperateProperties.readValue("saasLoginURL"));
		loginPage.saasZiAccountLogin();
		driverBrowser.pause(1000);
	}
	/**
	* @author : chenjingli
	* @decription 通过企业账号增加子账号"
	 */
	@Test(description=addZiAccount)
	public void testAddZiAccount(){
	try{
			pinfo(ReportStep.class,addZiAccount+caseStart);
			managementPage.addZiAccountName();
			pinfo(ReportStep.class,addZiAccount+caseEnd);	
	}catch(Error e){
		driverBrowser.failScreenShot("testAddZiAccount");
		fail(addZiAccount+FAIL + e.getMessage(), e);
	}catch (Exception e) {
		driverBrowser.failScreenShot("testAddZiAccount");
		throw new TestNGException(addZiAccount+FAIL + e.getMessage(), e);
	} 
	}
	
	/**
	* @author : chenjingli
	* @decription 通过企业账号编辑子账号
	 */
	@Test(description=editZiAccount)
	public void testEditZiAccount(){
	try{
			driverBrowser.open(OperateProperties.readValue("saasLoginURL"));
			loginPage.saasZiAccountLogin();
			driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/subUserManager/userList");
			pinfo(ReportStep.class,editZiAccount+caseStart);
			managementPage.editZiAccount();
			pinfo(ReportStep.class,editZiAccount+caseEnd);	
	}catch(Error e){
		driverBrowser.failScreenShot("testEditZiAccount");
		fail(editZiAccount+FAIL + e.getMessage(), e);
	}catch (Exception e) {
		driverBrowser.failScreenShot("testEditZiAccount");
		throw new TestNGException(editZiAccount+FAIL + e.getMessage(), e);
	} 
	}
	
	/**
	* @author : chenjingli
	* @decription 通过企业账号删除子账号
	 */
	@Test(description=deleteZiAccount)
	public void testDeleteZiAccount(){
	try{
			driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/subUserManager/userList");
			pinfo(ReportStep.class,deleteZiAccount+caseStart);
			managementPage.delZiAccount();
			pinfo(ReportStep.class,deleteZiAccount+caseEnd);
	}catch(Error e){
		driverBrowser.failScreenShot("testDeleteZiAccount");
		fail(deleteZiAccount+FAIL + e.getMessage(), e);
	}catch (Exception e) {
		driverBrowser.failScreenShot("testDeleteZiAccount");
		throw new TestNGException(deleteZiAccount+FAIL + e.getMessage(), e);
	} 
	}
	
	
	@AfterSuite
	public void down(){
		driverBrowser.quit();
	}
}
