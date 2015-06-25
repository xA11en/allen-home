package com.tingyun.auto.rpc.step.report.singletask;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.page.report.singletask.GeneralizPerforPage;
/**
* @author :chenjingli 
* @version ：2015-6-4 下午4:17:00 
* @decription: 性能指标
 */
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
		try {
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.historyCurveMap();
		pinfo(ReportStep.class,DESHISTORYMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testHistoryMap");
			fail(DESHISTORYMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testHistoryMap");
			throw new TestNGException(DESHISTORYMAP+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 运营商曲线图
	* @return
	 */
	//@Test(description=DESOPERATORMAP)
	public void testOperatorMap(){
		pinfo(ReportStep.class,DESOPERATORMAP+caseStart);
		try {
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.operatorMap();
		pinfo(ReportStep.class,DESOPERATORMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testOperatorMap");
			fail(DESOPERATORMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testOperatorMap");
			throw new TestNGException(DESOPERATORMAP+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 省份曲线图
	* @return
	 */
	//@Test(description=DESPROVINCESMAP)
	public void testprovincesMap(){
		pinfo(ReportStep.class,DESPROVINCESMAP+caseStart);
		try {
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.provincesMap();
		pinfo(ReportStep.class,DESPROVINCESMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testprovincesMap");
			fail(DESPROVINCESMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testprovincesMap");
			throw new TestNGException(DESPROVINCESMAP+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 城市曲线图
	* @return
	 */
	//@Test(description=DESCCITYMAP)
	public void testCityMap(){
		pinfo(ReportStep.class,DESCCITYMAP+caseStart);
		try {
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.cityMap();
		pinfo(ReportStep.class,DESCCITYMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testCityMap");
			fail(DESCCITYMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testCityMap");
			throw new TestNGException(DESCCITYMAP+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 省份运营商曲线图
	* @return
	 */
	//@Test(description=DESPROVINCESOPERATORMAP)
	public void testProvincesOperatorMap(){
		pinfo(ReportStep.class,DESPROVINCESOPERATORMAP+caseStart);
		try {
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.provincesOperatorMap();
		pinfo(ReportStep.class,DESPROVINCESOPERATORMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testProvincesOperatorMap");
			fail(DESPROVINCESOPERATORMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testProvincesOperatorMap");
			throw new TestNGException(DESPROVINCESOPERATORMAP+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 城市运营商曲线图
	* @return
	 */
	//@Test(description=DESCITYOPERATORMAP)
	public void testCityOperatorMap(){
		pinfo(ReportStep.class,DESCITYOPERATORMAP+caseStart);
		try {
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.cityOperatorMap();
		pinfo(ReportStep.class,DESCITYOPERATORMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testCityOperatorMap");
			fail(DESCITYOPERATORMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testCityOperatorMap");
			throw new TestNGException(DESCITYOPERATORMAP+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 环比曲线图
	* @return
	 */
	//@Test(description=DESSEQUENTIALCURVEMAP)
	public void testSequentialCurveMapMap(){
		pinfo(ReportStep.class,DESSEQUENTIALCURVEMAP+caseStart);
		try {
			openUrl();
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.sequentialCurveMap();
		pinfo(ReportStep.class,DESSEQUENTIALCURVEMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testSequentialCurveMapMap");
			fail(DESSEQUENTIALCURVEMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testSequentialCurveMapMap");
			throw new TestNGException(DESSEQUENTIALCURVEMAP+"" + e.getMessage(), e);
		} 
	}
//	@Test(description=DESCONTRASTCURVEMAP)
	public void testContrastCurveMap(){
		pinfo(ReportStep.class,DESCONTRASTCURVEMAP+caseStart);
		try {
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.contrastCurveMap();
		pinfo(ReportStep.class,DESCONTRASTCURVEMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testContrastCurveMap");
			fail(DESCONTRASTCURVEMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testContrastCurveMap");
			throw new TestNGException(DESCONTRASTCURVEMAP+"" + e.getMessage(), e);
		} 
	}
	
	//@Test(description=operatorPerformanceMap)
	public void testOperatorPerformanceMap(){
		pinfo(ReportStep.class,operatorPerformanceMap+caseStart);
		try {
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.operatorPerformanceMap();
		pinfo(ReportStep.class,operatorPerformanceMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testOperatorPerformanceMap");
			fail(operatorPerformanceMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testOperatorPerformanceMap");
			throw new TestNGException(operatorPerformanceMap+"" + e.getMessage(), e);
		} 
	}
	
	//省份性能图
	//@Test(description=provincesPerformanceMap)
	public void testProvincesPerformanceMap(){
		pinfo(ReportStep.class,provincesPerformanceMap+caseStart);
		try {
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.provincesPerformanceMap();
		pinfo(ReportStep.class,provincesPerformanceMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testProvincesPerformanceMap");
			fail(provincesPerformanceMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testProvincesPerformanceMap");
			throw new TestNGException(provincesPerformanceMap+"" + e.getMessage(), e);
		} 
	}
	//城市性能图
	//@Test(description=cityPerformanceMap)
	public void testCityPerformanceMap(){
		pinfo(ReportStep.class,cityPerformanceMap+caseStart);
		try {
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.cityPerformanceMap();
		pinfo(ReportStep.class,cityPerformanceMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testCityPerformanceMap");
			fail(cityPerformanceMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testCityPerformanceMap");
			throw new TestNGException(cityPerformanceMap+"" + e.getMessage(), e);
		} 
	}
	//@Test(description=provinceOperatorsMap)
	public void testProvinceOperatorsMap(){
		pinfo(ReportStep.class,provinceOperatorsMap+caseStart);
		try {
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			openUrl();
			genPage.provinceOperatorsMap();
		pinfo(ReportStep.class,provinceOperatorsMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testProvinceOperatorsMap");
			fail(provinceOperatorsMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testProvinceOperatorsMap");
			throw new TestNGException(provinceOperatorsMap+"" + e.getMessage(), e);
		} 
	}
	//城市运营商性能图
	//@Test(description=cityOperatorsMap)
	public void testCityOperatorsMap(){
			pinfo(ReportStep.class,cityOperatorsMap+caseStart);
			try {
				GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
				openUrl();
				genPage.cityOperatorsMap();
			pinfo(ReportStep.class,cityOperatorsMap+caseEnd);	
			}catch(Error e){
				driverBrowser.failScreenShot("testCityOperatorsMap");
				fail(cityOperatorsMap+FAIL + e.getMessage(), e);
			}catch (Exception e) {
				driverBrowser.failScreenShot("testCityOperatorsMap");
				throw new TestNGException(cityOperatorsMap+"" + e.getMessage(), e);
			} 
		}
	
	//城市运营商性能图
	@Test(description=sumUpMap)
	public void testSumUpMap(){
			pinfo(ReportStep.class,sumUpMap+caseStart);
			try {
				GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
				openUrl();
				genPage.sumUpMap();
			pinfo(ReportStep.class,sumUpMap+caseEnd);	
			}catch(Error e){
				driverBrowser.failScreenShot("testSumUpMap");
				fail(sumUpMap+FAIL + e.getMessage(), e);
			}catch (Exception e) {
				driverBrowser.failScreenShot("testSumUpMap");
				throw new TestNGException(sumUpMap+"" + e.getMessage(), e);
			} 
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
