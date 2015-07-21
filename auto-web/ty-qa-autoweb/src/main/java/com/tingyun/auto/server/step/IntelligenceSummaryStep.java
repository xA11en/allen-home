package com.tingyun.auto.server.step;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.server.page.IntelligenceSummaryPage;

/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - 情报汇总 step
 */
@Listeners({ TestResultListener.class })
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
			pinfo(ReportStep.class,cssAppRespondTimeMap+caseStart);
			summaryPage.cssAppRespondTimeMap(cssAppRespondTimeMap);
			pinfo(ReportStep.class,cssAppRespondTimeMap+caseEnd);	
	}

	/**
	* @author : chenjingli
	* @decription Apdex指标
	 */
	@Test(description=cssApdexMap)
	public void testApdexMapp (){
			pinfo(ReportStep.class,cssApdexMap+caseStart);
			summaryPage.cssApdexMap(cssApdexMap);
			pinfo(ReportStep.class,cssApdexMap+caseEnd);	
	}

	/**
	* @author : chenjingli
	* @decription 错误率
	 */
	@Test(description=cssErrorMap)
	public void testErrorMapp(){
			pinfo(ReportStep.class,cssErrorMap+caseStart);
			summaryPage.cssErrorMap(cssErrorMap);
	}
	/**
	* @author : chenjingli
	* @decription 最耗时Web应用过程（Web Action）图表--展现测试用例
	 */
	@Test(description=csstopWebActionMap)
	public void testTopWebActionMap(){
			pinfo(ReportStep.class,csstopWebActionMap+caseStart);
			summaryPage.csstopWebActionMap(csstopWebActionMap);
			pinfo(ReportStep.class,csstopWebActionMap+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription 吞吐率--展现测试用例
	 */
	@Test(description=cssThroughtMap)
	public void testThroughtMap(){
			pinfo(ReportStep.class,cssThroughtMap+caseStart);
			summaryPage.cssThroughtMap(cssThroughtMap);
			pinfo(ReportStep.class,cssThroughtMap+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription 服务器资源CPU
	 */
	@Test(description=cssCPUTimeMap)
	public void testCPUTimeMap(){
			pinfo(ReportStep.class,cssCPUTimeMap+caseStart);
			summaryPage.cssCPUTimeMap(cssCPUTimeMap);
			pinfo(ReportStep.class,cssCPUTimeMap+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription 服务器资源内存
	 */
	@Test(description=cssMerboryMap)
	public void testMerboryMap(){
			pinfo(ReportStep.class,cssMerboryMap+caseStart);
			summaryPage.cssMerboryMap(cssMerboryMap);
			pinfo(ReportStep.class,cssMerboryMap+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 所有指标显示Table
	 */
	@Test(description=idTable)
	public void testTable(){
			pinfo(ReportStep.class,idTable+caseStart);
			summaryPage.idTable(idTable);
			pinfo(ReportStep.class,idTable+caseEnd);	
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
