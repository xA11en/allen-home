package com.tingyun.auto.rpc.step.report.singletask;

import static org.testng.Assert.fail;

import org.testng.Reporter;
import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.page.report.singletask.GeneralizPerforPage;
import com.tingyun.auto.rpc.page.report.singletask.ReportPage;

/**
* @author :chenjingli 
* @version ：2015-5-19 下午5:19:19 
* @decription: rpc报表测试用例
 */
public class ReportStep extends GlobalStep {
	public static final String DESCHINAMAP = "性能概括-中国地图测试用例";
	public static final String DESWORDMAP = "性能概括-世界地图测试用例";
	public static final String DESXINGNENGMAP = "性能概括-性能历史曲线图图测试用例";
	public static final String DESYUNXINGSHANGMAP = "性能概括-运营商性能曲线图测试用例";
	public static final String DESCITYGMAP = "性能概括-城市性能曲线图测试用例";
	public static final String DESCOMMUNICATIONMAP = "性能概括-城市运营商性能曲线图测试用例";
	public static final String DESYUNXINGSXINGNENGMAP = "性能概括-运营商性能图测试用例";
	public static final String DESPROVINCEXINGNENGMAP = "性能概括-省份性能图测试用例";
	public static final String DESPCITYXINGNENGMAP = "性能概括-城市性能图测试用例";
	public static final String DESSANDIANMAP = "性能概括-散点图测试用例";
	public static final String DESXINGNENGFENBUMAP = "性能概括-性能分布直方图测试用例";
	public static final String rpcDesLogin = "rpc系统登录测试用例";
	private static DriverBrowser driverBrowser;
	@BeforeMethod(alwaysRun=true)
	public void testRpcLogin(){
		pinfo(ReportStep.class,rpcDesLogin+caseStart);
		try {
			driverBrowser = new DriverBrowser(BrowserType.Chrome);
			driverBrowser.open(SeleniumSettings.URL);
			RpcLoginPage reportPage = new RpcLoginPage(driverBrowser);
			reportPage.RpcLogin();
		}catch(Error e){
			driverBrowser.failScreenShot("testRpcLogin");
			fail(rpcDesLogin+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRpcLogin");
			throw new TestNGException(rpcDesLogin+FAIL + e.getMessage(), e);
		} 
	}
	//单任务--中国地图
	@Test(description=DESCHINAMAP)
	public void testChinaMap(){
		pinfo(ReportStep.class,DESCHINAMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.ChinaMap();
		pinfo(ReportStep.class,DESCHINAMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testChinaMap");
			fail(DESCHINAMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testChinaMap");
			throw new TestNGException(DESCHINAMAP+"" + e.getMessage(), e);
		} 
	}
	//单任务--世界地图
	@Test(description=DESWORDMAP)
	public void testWordMap(){
		pinfo(ReportStep.class,DESWORDMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.wordMap();
		pinfo(ReportStep.class,DESWORDMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testWordMap");
			fail(DESWORDMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testWordMap");
			throw new TestNGException(DESWORDMAP+"" + e.getMessage(), e);
		} 
	}
	@Test(description=DESXINGNENGMAP)
	public void testXingNengMap(){
		pinfo(ReportStep.class,DESXINGNENGMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.xingNengMap();
		pinfo(ReportStep.class,DESXINGNENGMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testXingNengMap");
			fail(DESXINGNENGMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testXingNengMap");
			throw new TestNGException(DESXINGNENGMAP+"" + e.getMessage(), e);
		} 
	}
	@Test(description=DESYUNXINGSHANGMAP)
	public void testYunYingShangMap(){
		pinfo(ReportStep.class,DESYUNXINGSHANGMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.yunYingShangMap();
		pinfo(ReportStep.class,DESYUNXINGSHANGMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testYunYingShangMap");
			fail(DESYUNXINGSHANGMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testYunYingShangMap");
			throw new TestNGException(DESYUNXINGSHANGMAP+"" + e.getMessage(), e);
		} 
	}
	@Test(description=DESCITYGMAP)
	public void testCityMap(){
		pinfo(ReportStep.class,DESCITYGMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.cityMap();
		pinfo(ReportStep.class,DESCITYGMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testCityMap");
			fail(DESCITYGMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testCityMap");
			throw new TestNGException(DESCITYGMAP+"" + e.getMessage(), e);
		} 
	}
	@Test(description=DESCOMMUNICATIONMAP)
	public void testCityCommunication(){
		pinfo(ReportStep.class,DESCOMMUNICATIONMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.cityCommunication();
		pinfo(ReportStep.class,DESCOMMUNICATIONMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testCityCommunication");
			fail(DESCOMMUNICATIONMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testCityCommunication");
			throw new TestNGException(DESCOMMUNICATIONMAP+"" + e.getMessage(), e);
		} 
	}
	@Test(description=DESYUNXINGSXINGNENGMAP)
	public void testYunXingSXingNengMap(){
		pinfo(ReportStep.class,DESYUNXINGSXINGNENGMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.YunXingSXingNengMap();
		pinfo(ReportStep.class,DESYUNXINGSXINGNENGMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testYunXingSXingNengMap");
			fail(DESYUNXINGSXINGNENGMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testYunXingSXingNengMap");
			throw new TestNGException(DESYUNXINGSXINGNENGMAP+"" + e.getMessage(), e);
		} 
	}
	
	@Test(description=DESPROVINCEXINGNENGMAP)
	public void testProvinceXingNengMap(){
			pinfo(ReportStep.class,DESPROVINCEXINGNENGMAP+caseStart);
			try {
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.provinceXingNengMap();
			pinfo(ReportStep.class,DESPROVINCEXINGNENGMAP+caseEnd);	
			}catch(Error e){
				driverBrowser.failScreenShot("testProvinceXingNengMap");
				fail(DESPROVINCEXINGNENGMAP+FAIL + e.getMessage(), e);
			}catch (Exception e) {
				driverBrowser.failScreenShot("testProvinceXingNengMap");
				throw new TestNGException(DESPROVINCEXINGNENGMAP+"" + e.getMessage(), e);
			} 
		}
	@Test(description=DESPCITYXINGNENGMAP)
	public void testCityXingNengMap(){
			pinfo(ReportStep.class,DESPCITYXINGNENGMAP+caseStart);
			try {
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.cityXingNengMap();
			pinfo(ReportStep.class,DESPCITYXINGNENGMAP+caseEnd);	
			}catch(Error e){
				driverBrowser.failScreenShot("testCityXingNengMap");
				fail(DESPCITYXINGNENGMAP+FAIL + e.getMessage(), e);
			}catch (Exception e) {
				driverBrowser.failScreenShot("testCityXingNengMap");
				throw new TestNGException(DESPCITYXINGNENGMAP+"" + e.getMessage(), e);
			} 
		}
	@Test(description=DESSANDIANMAP)
	public void testSanDianMap(){
			pinfo(ReportStep.class,DESSANDIANMAP+caseStart);
			try {
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.scatterDiagramMap();
			pinfo(ReportStep.class,DESSANDIANMAP+caseEnd);	
			}catch(Error e){
				driverBrowser.failScreenShot("testSanDianMap");
				fail(DESSANDIANMAP+FAIL + e.getMessage(), e);
			}catch (Exception e) {
				driverBrowser.failScreenShot("testSanDianMap");
				throw new TestNGException(DESSANDIANMAP+"" + e.getMessage(), e);
			} 
		}
	@Test(description=DESXINGNENGFENBUMAP)
	public void testDistributionHistogramMap(){
			pinfo(ReportStep.class,DESXINGNENGFENBUMAP+caseStart);
			try {
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.distributionHistogramMap();
			pinfo(ReportStep.class,DESXINGNENGFENBUMAP+caseEnd);	
			}catch(Error e){
				driverBrowser.failScreenShot("testDistributionHistogramMap");
				fail(DESXINGNENGFENBUMAP+FAIL + e.getMessage(), e);
			}catch (Exception e) {
				driverBrowser.failScreenShot("testDistributionHistogramMap");
				throw new TestNGException(DESXINGNENGFENBUMAP+"" + e.getMessage(), e);
			} 
		}
	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
