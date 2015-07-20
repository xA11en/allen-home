package com.tingyun.auto.saas.step;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.saas.page.FindPasswordBackPage;
import com.tingyun.auto.utils.OperateProperties;

@Listeners({ TestResultListener.class })
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
			pinfo(ReportStep.class,PhFindPw+caseStart);
			findPage.PhFindPassword();
			pinfo(ReportStep.class,PhFindPw+caseEnd);	
	}
	@Test(description=EmFindPw)
	public void testEmFindPassword(){
			pinfo(ReportStep.class,EmFindPw+caseStart);
			findPage.PhFindPassword();
			pinfo(ReportStep.class,EmFindPw+caseEnd);	
	}
	@AfterMethod
	public void down(){
		driverBrowser.quit();
	}
}
