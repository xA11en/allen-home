package com.tingyun.auto.rpc.step.report.singletask;

import static org.testng.Assert.fail;

import org.testng.Reporter;
import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.page.report.singletask.GeneralizPerforPage;
import com.tingyun.auto.rpc.page.report.singletask.ReportPage;

/**
* @author :chenjingli 
* @version ：2015-5-19 下午5:19:19 
* @decription: rpc报表测试用例
 */
@Listeners({ TestResultListener.class })
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
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.ChinaMap();
		pinfo(ReportStep.class,DESCHINAMAP+caseEnd);	
	}
	//单任务--世界地图
	@Test(description=DESWORDMAP)
	public void testWordMap(){
		pinfo(ReportStep.class,DESWORDMAP+caseStart);
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.wordMap();
		pinfo(ReportStep.class,DESWORDMAP+caseEnd);	
	}
	@Test(description=DESXINGNENGMAP)
	public void testXingNengMap(){
		pinfo(ReportStep.class,DESXINGNENGMAP+caseStart);
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.xingNengMap();
		pinfo(ReportStep.class,DESXINGNENGMAP+caseEnd);	
	}
	@Test(description=DESYUNXINGSHANGMAP)
	public void testYunYingShangMap(){
		pinfo(ReportStep.class,DESYUNXINGSHANGMAP+caseStart);
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.yunYingShangMap();
		pinfo(ReportStep.class,DESYUNXINGSHANGMAP+caseEnd);	
	}
	@Test(description=DESCITYGMAP)
	public void testCityMap(){
		pinfo(ReportStep.class,DESCITYGMAP+caseStart);
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.cityMap();
		pinfo(ReportStep.class,DESCITYGMAP+caseEnd);	
	}
	@Test(description=DESCOMMUNICATIONMAP)
	public void testCityCommunication(){
		pinfo(ReportStep.class,DESCOMMUNICATIONMAP+caseStart);
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.cityCommunication();
		pinfo(ReportStep.class,DESCOMMUNICATIONMAP+caseEnd);	
	}
	@Test(description=DESYUNXINGSXINGNENGMAP)
	public void testYunXingSXingNengMap(){
		pinfo(ReportStep.class,DESYUNXINGSXINGNENGMAP+caseStart);
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.YunXingSXingNengMap();
		pinfo(ReportStep.class,DESYUNXINGSXINGNENGMAP+caseEnd);	
	}
	
	@Test(description=DESPROVINCEXINGNENGMAP)
	public void testProvinceXingNengMap(){
			pinfo(ReportStep.class,DESPROVINCEXINGNENGMAP+caseStart);
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.provinceXingNengMap();
			pinfo(ReportStep.class,DESPROVINCEXINGNENGMAP+caseEnd);	
		}
	@Test(description=DESPCITYXINGNENGMAP)
	public void testCityXingNengMap(){
			pinfo(ReportStep.class,DESPCITYXINGNENGMAP+caseStart);
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.cityXingNengMap();
			pinfo(ReportStep.class,DESPCITYXINGNENGMAP+caseEnd);	
		}
	@Test(description=DESSANDIANMAP)
	public void testSanDianMap(){
			pinfo(ReportStep.class,DESSANDIANMAP+caseStart);
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.scatterDiagramMap();
			pinfo(ReportStep.class,DESSANDIANMAP+caseEnd);	
		}
	@Test(description=DESXINGNENGFENBUMAP)
	public void testDistributionHistogramMap(){
			pinfo(ReportStep.class,DESXINGNENGFENBUMAP+caseStart);
				ReportPage reportPage = new ReportPage(driverBrowser);
				reportPage.distributionHistogramMap();
			pinfo(ReportStep.class,DESXINGNENGFENBUMAP+caseEnd);	
		}
	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
