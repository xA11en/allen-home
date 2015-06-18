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
import com.tingyun.auto.server.page.WebApplicationPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - web应用过程 step
 */
public class DataSourceStep extends GlobalStep{
	
	private static final String sql = "最耗时SQL操作堆叠图--展现测试用例";
	private static final String dataSourceAndThrougth = "数据库吞吐率堆叠图--展现测试用例";
	private static final String dataSourceRespond = "数据库响应时间曲线图--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static DataSourcePage dataSourcePage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		dataSourcePage = new DataSourcePage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/actions");
		driverBrowser.click(dataSourcePage.nameCliDatabase);
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	* @decription 最耗时SQL操作堆叠图
	 */
	@Test(description=sql)
	public void testTimeConsumingSQLMap(){
		try {
			pinfo(ReportStep.class,sql+caseStart);
			dataSourcePage.validationSQLMap(sql);
			pinfo(ReportStep.class,sql+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testTimeConsumingSQLMap");
			fail(sql+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testTimeConsumingSQLMap");
			throw new TestNGException(sql+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	@Test(description=dataSourceAndThrougth)
	public void testDataSourceThroughputMap (){
		try {
			pinfo(ReportStep.class,dataSourceAndThrougth+caseStart);
			dataSourcePage.valiDataSourceMap(dataSourceAndThrougth);
			pinfo(ReportStep.class,dataSourceAndThrougth+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testDataSourceThroughputMap");
			fail(dataSourceAndThrougth+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testDataSourceThroughputMap");
			throw new TestNGException(dataSourceAndThrougth+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	@Test(description=dataSourceRespond)
	public void testDataSourceResponseTimeMap(){
		try {
			pinfo(ReportStep.class,dataSourceRespond+caseStart);
			dataSourcePage.valiDataSourceRespondMap(dataSourceRespond);
			pinfo(ReportStep.class,dataSourceRespond+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testDataSourceResponseTimeMap");
			fail(dataSourceRespond+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testDataSourceResponseTimeMap");
			throw new TestNGException(dataSourceRespond+"" + e.getMessage(), e);
		} 
	}
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
