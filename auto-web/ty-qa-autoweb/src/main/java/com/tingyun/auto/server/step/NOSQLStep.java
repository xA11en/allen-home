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
import com.tingyun.auto.server.page.NOSQLPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - web应用过程 step
 */
@Listeners({ TestResultListener.class })
public class NOSQLStep extends GlobalStep{
	
	private static final String Redis = "最耗时Redis操作堆叠图--展现测试用例";
	private static final String RedisAndThrougth = "Redis吞吐率堆叠图--展现测试用例";
	private static final String RedisRespond = "Redis响应时间曲线图--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static NOSQLPage nosqlPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		nosqlPage = new NOSQLPage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/actions");
		driverBrowser.click(nosqlPage.nameCliNoSQL);
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	 * @throws Exception 
	* @decription 最耗时Redis操作堆叠图
	 */
	@Test(description=Redis)
	public void testOperationRedisMap(){
			pinfo(ReportStep.class,Redis+caseStart);
			nosqlPage.validationRedisMap(Redis);
			pinfo(ReportStep.class,Redis+caseEnd);	
	}

	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	@Test(description=RedisAndThrougth)
	public void testRedisThoughputMap (){
			pinfo(ReportStep.class,RedisAndThrougth+caseStart);
			nosqlPage.valiRedisThroughtMap(RedisAndThrougth);
			pinfo(ReportStep.class,RedisAndThrougth+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	@Test(description=RedisRespond)
	public void testRedisRespondMap(){
			pinfo(ReportStep.class,RedisRespond+caseStart);
			nosqlPage.valiRedisRespondMap(RedisRespond);
			pinfo(ReportStep.class,RedisRespond+caseEnd);	
	}
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
