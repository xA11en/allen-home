package com.tingyun.auto.server.step;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.server.page.DataSourcePage;
import com.tingyun.auto.server.page.IntelligenceSummaryPage;
import com.tingyun.auto.server.page.WebApplicationPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - 情报汇总 step
 */
public class IntelligenceSummaryStep extends GlobalStep{
	
	private static final String cssAppRespondTimeMap = "应用服务器响应时间--展现测试用例";
	private static final String cssApdexMap = "Apdex指标--展现测试用例";
	private static final String cssErrorMap = "错误率--展现测试用例";
	private static final String csstopWebActionMap = "最耗时Web应用过程（Web Action）图表--展现测试用例";
	private static final String cssThroughtMap = "吞吐率--展现测试用例";
	private static final String cssCPUTimeMap = "服务器资源CPU--展现测试sdsdsd用例";
	private static final String cssMerboryMap = "服务器资源内存--展现测试用ddddddddddddaaa例";
	private static final String idTable = "所有指标显示Table--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static IntelligenceSummaryPage summaryPage ;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		summaryPage = new IntelligenceSummaryPage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/overview");
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	* @decription 应用服务器响应时间
	 */
	@Test(description=cssAppRespondTimeMap)
	public void testAppRespondTimeMap(){
		try {
			pinfo(ReportStep.class,cssAppRespondTimeMap+caseStart);
			summaryPage.cssAppRespondTimeMap(cssAppRespondTimeMap);
			pinfo(ReportStep.class,cssAppRespondTimeMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testAppRespondTimeMap");
			fail(cssAppRespondTimeMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testAppRespondTimeMap");
			throw new TestNGException(cssAppRespondTimeMap+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription Apdex指标
	 */
	@Test(description=cssApdexMap)
	public void testApdexMapp (){
		try {
			pinfo(ReportStep.class,cssApdexMap+caseStart);
			summaryPage.cssApdexMap(cssApdexMap);
			pinfo(ReportStep.class,cssApdexMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testApdexMapp");
			fail(cssApdexMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testApdexMapp");
			throw new TestNGException(cssApdexMap+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription 错误率
	 */
	@Test(description=cssErrorMap)
	public void testErrorMapp(){
		try {
			pinfo(ReportStep.class,cssErrorMap+caseStart);
			summaryPage.cssErrorMap(cssErrorMap);
			pinfo(ReportStep.class,cssErrorMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testErrorMapp");
			fail(cssErrorMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testErrorMapp");
			throw new TestNGException(cssErrorMap+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 最耗时Web应用过程（Web Action）图表--展现测试用例
	 */
	@Test(description=csstopWebActionMap)
	public void testTopWebActionMap(){
		try {
			pinfo(ReportStep.class,csstopWebActionMap+caseStart);
			summaryPage.csstopWebActionMap(csstopWebActionMap);
			pinfo(ReportStep.class,csstopWebActionMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testTopWebActionMap");
			fail(csstopWebActionMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testTopWebActionMap");
			throw new TestNGException(csstopWebActionMap+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription 吞吐率--展现测试用例
	 */
	@Test(description=cssThroughtMap)
	public void testThroughtMap(){
		try {
			pinfo(ReportStep.class,cssThroughtMap+caseStart);
			summaryPage.cssThroughtMap(cssThroughtMap);
			pinfo(ReportStep.class,cssThroughtMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testThroughtMap");
			fail(cssThroughtMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testThroughtMap");
			throw new TestNGException(cssThroughtMap+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription 服务器资源CPU
	 */
	@Test(description=cssCPUTimeMap)
	public void testCPUTimeMap(){
		try {
			pinfo(ReportStep.class,cssCPUTimeMap+caseStart);
			summaryPage.cssCPUTimeMap(cssCPUTimeMap);
			pinfo(ReportStep.class,cssCPUTimeMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testCPUTimeMap");
			fail(cssCPUTimeMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testCPUTimeMap");
			throw new TestNGException(cssCPUTimeMap+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription 服务器资源内存
	 */
	@Test(description=cssMerboryMap)
	public void testMerboryMap(){
		try {
			pinfo(ReportStep.class,cssMerboryMap+caseStart);
			summaryPage.cssMerboryMap(cssMerboryMap);
			pinfo(ReportStep.class,cssMerboryMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testMerboryMap");
			fail(cssMerboryMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testMerboryMap");
			throw new TestNGException(cssMerboryMap+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 所有指标显示Table
	 */
	@Test(description=idTable)
	public void testTable(){
		try {
			pinfo(ReportStep.class,idTable+caseStart);
			summaryPage.idTable(idTable);
			pinfo(ReportStep.class,idTable+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testTable");
			fail(idTable+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testTable");
			throw new TestNGException(idTable+"" + e.getMessage(), e);
		} 
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
