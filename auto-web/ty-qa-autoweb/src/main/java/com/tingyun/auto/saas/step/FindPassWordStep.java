package com.tingyun.auto.saas.step;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.saas.page.FindPasswordBackPage;
import com.tingyun.auto.utils.OperateProperties;


public class FindPassWordStep extends GlobalStep{

	public static final String PhFindPw = "saas手机找回密码";
	public static final String EmFindPw = "saas邮箱找回密码";
	private static DriverBrowser driverBrowser;
	private static FindPasswordBackPage findPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		findPage = new FindPasswordBackPage(driverBrowser);
		driverBrowser.open(OperateProperties.readValue("saasFindPasswordUrl"));
		driverBrowser.pause(1000);
	}
	
	@Test(description=PhFindPw)
	public void testPhFindPassword(){
		try {
			pinfo(ReportStep.class,PhFindPw+caseStart);
			findPage.PhFindPassword();
			pinfo(ReportStep.class,PhFindPw+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testPhFindPassword");
			fail(PhFindPw+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testPhFindPassword");
			throw new TestNGException(PhFindPw+"" + e.getMessage(), e);
		} 
	}
	@Test(description=EmFindPw)
	public void testEmFindPassword(){
		try {
			pinfo(ReportStep.class,EmFindPw+caseStart);
			findPage.PhFindPassword();
			pinfo(ReportStep.class,EmFindPw+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testEmFindPassword");
			fail(EmFindPw+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testEmFindPassword");
			throw new TestNGException(EmFindPw+"" + e.getMessage(), e);
		} 
	}
	@AfterMethod
	public void down(){
		driverBrowser.quit();
	}
}
