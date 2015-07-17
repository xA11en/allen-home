package com.tingyun.auto.saas.step.typroduct;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.saas.page.RegisteredAndLoginPage;
import com.tingyun.auto.saas.page.typroduct.ConsultationAndFeedbackPage;
import com.tingyun.auto.utils.OperateProperties;
/**
* @author :mabingxie 
* @version :2015-7-16 上午10:05:37 
* @decription: Consultation And Feedback  page
 */
public class ConsultationAndFeedbackStep extends GlobalStep{
	public static final String ConsuAndFeedB = "saas咨询与反馈";
	private static DriverBrowser driverBrowser;
	private static ConsultationAndFeedbackPage ConFeedPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		ConFeedPage= new ConsultationAndFeedbackPage(driverBrowser);
		RegisteredAndLoginPage login = new RegisteredAndLoginPage(driverBrowser);
		driverBrowser.open(OperateProperties.readValue("saasConsultationAndFeedbackUrl"));
		driverBrowser.pause(1000);
		login.saasLogin();
	}
	
	@Test(description=ConsuAndFeedB)
	public void testSaasConsultationAndFeedback(){
		try {
			pinfo(ReportStep.class,ConsuAndFeedB+caseStart);
			ConFeedPage.saasConsultationAndFeedback();
		    pinfo(ReportStep.class,ConsuAndFeedB+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testSaasConsultationAndFeedback");
			fail(ConsuAndFeedB+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testSaasConsultationAndFeedback");
			throw new TestNGException(ConsuAndFeedB+"" + e.getMessage(), e);
		} 
		
	}
	@AfterMethod
	public void down(){
		driverBrowser.quit();
	}
}
