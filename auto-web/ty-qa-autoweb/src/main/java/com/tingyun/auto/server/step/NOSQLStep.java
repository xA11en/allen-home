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
import com.tingyun.auto.server.page.NOSQLPage;
import com.tingyun.auto.server.page.WebApplicationPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - web应用过程 step
 */
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
	* @decription 最耗时Redis操作堆叠图
	 */
	@Test(description=Redis)
	public void testOperationRedisMap(){
		try {
			pinfo(ReportStep.class,Redis+caseStart);
			nosqlPage.validationRedisMap(Redis);
			pinfo(ReportStep.class,Redis+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testOperationRedisMap");
			fail(Redis+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testOperationRedisMap");
			throw new TestNGException(Redis+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	@Test(description=RedisAndThrougth)
	public void testRedisThoughputMap (){
		try {
			pinfo(ReportStep.class,RedisAndThrougth+caseStart);
			nosqlPage.valiRedisThroughtMap(RedisAndThrougth);
			pinfo(ReportStep.class,RedisAndThrougth+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testRedisThoughputMap");
			fail(RedisAndThrougth+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRedisThoughputMap");
			throw new TestNGException(RedisAndThrougth+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	@Test(description=RedisRespond)
	public void testRedisRespondMap(){
		try {
			pinfo(ReportStep.class,RedisRespond+caseStart);
			nosqlPage.valiRedisRespondMap(RedisRespond);
			pinfo(ReportStep.class,RedisRespond+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testRedisRespondMap");
			fail(RedisRespond+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRedisRespondMap");
			throw new TestNGException(RedisRespond+"" + e.getMessage(), e);
		} 
	}
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
