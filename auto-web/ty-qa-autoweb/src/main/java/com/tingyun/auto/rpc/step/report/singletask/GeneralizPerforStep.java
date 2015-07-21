package com.tingyun.auto.rpc.step.report.singletask;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
/**
* @author :chenjingli 
* @version ：2015-6-4 下午4:17:00 
* @decription: 性能指标
 */
@Listeners({ TestResultListener.class})
public class GeneralizPerforStep extends GlobalStep{

	private static DriverBrowser driverBrowser;
	private static 	GeneralizPerforPage genPage;
	@BeforeClass
	public void testRpcLogin(){
		pinfo(ReportStep.class,rpcDesLogin+caseStart);
		try {
			driverBrowser = new DriverBrowser(BrowserType.Chrome);
			genPage = new GeneralizPerforPage(driverBrowser);
			RpcLoginPage reportPage = new RpcLoginPage(driverBrowser);
			driverBrowser.open(SeleniumSettings.URL);
			reportPage.RpcLogin();
			driverBrowser.pause(1000);
			//genPage.clickGenPerfor();
		}catch(Error e){
			driverBrowser.failScreenShot("testRpcLogin");
			fail(rpcDesLogin+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRpcLogin");
			throw new TestNGException(rpcDesLogin+FAIL + e.getMessage(), e);
		} 
	}
	
	//性能指标-历史曲线图测试用例
	//@Test(description=DESHISTORYMAP)
	public void testHistoryMap(){
		pinfo(ReportStep.class,DESHISTORYMAP+caseStart);
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.historyCurveMap();
		pinfo(ReportStep.class,DESHISTORYMAP+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 运营商曲线图
	* @return
	 */
	//@Test(description=DESOPERATORMAP)
	public void testOperatorMap(){
		pinfo(ReportStep.class,DESOPERATORMAP+caseStart);
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.operatorMap();
		pinfo(ReportStep.class,DESOPERATORMAP+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 省份曲线图
	* @return
	 */
	//@Test(description=DESPROVINCESMAP)
	public void testprovincesMap(){
		pinfo(ReportStep.class,DESPROVINCESMAP+caseStart);
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.provincesMap();
		pinfo(ReportStep.class,DESPROVINCESMAP+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 城市曲线图
	* @return
	 */
	//@Test(description=DESCCITYMAP)
	public void testCityMap(){
		pinfo(ReportStep.class,DESCCITYMAP+caseStart);
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.cityMap();
		pinfo(ReportStep.class,DESCCITYMAP+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 省份运营商曲线图
	* @return
	 */
	//@Test(description=DESPROVINCESOPERATORMAP)
	public void testProvincesOperatorMap(){
		pinfo(ReportStep.class,DESPROVINCESOPERATORMAP+caseStart);
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.provincesOperatorMap();
		pinfo(ReportStep.class,DESPROVINCESOPERATORMAP+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 城市运营商曲线图
	* @return
	 */
	//@Test(description=DESCITYOPERATORMAP)
	public void testCityOperatorMap(){
		pinfo(ReportStep.class,DESCITYOPERATORMAP+caseStart);
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.cityOperatorMap();
		pinfo(ReportStep.class,DESCITYOPERATORMAP+caseEnd);	
	}
	/**
	* @author : chenjingli
	* @decription 环比曲线图
	* @return
	 */
	//@Test(description=DESSEQUENTIALCURVEMAP)
	public void testSequentialCurveMapMap(){
		pinfo(ReportStep.class,DESSEQUENTIALCURVEMAP+caseStart);
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.sequentialCurveMap();
		pinfo(ReportStep.class,DESSEQUENTIALCURVEMAP+caseEnd);	
	}
//	@Test(description=DESCONTRASTCURVEMAP)
	public void testContrastCurveMap(){
		pinfo(ReportStep.class,DESCONTRASTCURVEMAP+caseStart);
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.contrastCurveMap();
		pinfo(ReportStep.class,DESCONTRASTCURVEMAP+caseEnd);	
	}
	
	//@Test(description=operatorPerformanceMap)
	public void testOperatorPerformanceMap(){
		pinfo(ReportStep.class,operatorPerformanceMap+caseStart);
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.operatorPerformanceMap();
		pinfo(ReportStep.class,operatorPerformanceMap+caseEnd);	
	}
	
	//省份性能图
	//@Test(description=provincesPerformanceMap)
	public void testProvincesPerformanceMap(){
		pinfo(ReportStep.class,provincesPerformanceMap+caseStart);
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.provincesPerformanceMap();
		pinfo(ReportStep.class,provincesPerformanceMap+caseEnd);	
	}
	//城市性能图
	//@Test(description=cityPerformanceMap)
	public void testCityPerformanceMap(){
		pinfo(ReportStep.class,cityPerformanceMap+caseStart);
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.cityPerformanceMap();
		pinfo(ReportStep.class,cityPerformanceMap+caseEnd);	
	}
	//@Test(description=provinceOperatorsMap)
	public void testProvinceOperatorsMap(){
		pinfo(ReportStep.class,provinceOperatorsMap+caseStart);
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.provinceOperatorsMap();
		pinfo(ReportStep.class,provinceOperatorsMap+caseEnd);	
	}
	//城市运营商性能图
	//@Test(description=cityOperatorsMap)
	public void testCityOperatorsMap(){
			pinfo(ReportStep.class,cityOperatorsMap+caseStart);
				GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
				openUrl();
				genPage.cityOperatorsMap();
			pinfo(ReportStep.class,cityOperatorsMap+caseEnd);	
		}
	
	//城市运营商性能图
	@Test(description=sumUpMap)
	public void testSumUpMap(){
			pinfo(ReportStep.class,sumUpMap+caseStart);
				GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
				openUrl();
				genPage.sumUpMap();
			pinfo(ReportStep.class,sumUpMap+caseEnd);	
		}
	void openUrl(){
		driverBrowser.open("http://rpc.dev.networkbench.com/rpc/home.do");
		genPage.clickGenPerfor();
	}
	
	@AfterClass
	public void afterClass(){
		driverBrowser.quit();
	}
}
