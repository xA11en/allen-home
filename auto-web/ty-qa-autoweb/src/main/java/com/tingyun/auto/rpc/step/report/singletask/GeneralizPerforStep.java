package com.tingyun.auto.rpc.step.report.singletask;

import static org.testng.Assert.fail;

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
/**
* @author :chenjingli 
* @version ：2015-6-4 下午4:17:00 
* @decription: 性能指标
 */
public class GeneralizPerforStep extends GlobalStep{
	public static final String DESHISTORYMAP = "性能指标-历史曲线图测试用例";
	public static final String DESOPERATORMAP = "性能指标-运营商曲线图测试用例";
	public static final String DESPROVINCESMAP = "性能指标-省份曲线图测试用例";
	public static final String DESCCITYMAP = "性能指标-城市曲线图测试用例";
	public static final String DESPROVINCESOPERATORMAP = "性能指标-省份运营商曲线图测试用例";
	public static final String DESCITYOPERATORMAP = "性能指标-城市运营商曲线图测试用例";
	public static final String DESSEQUENTIALCURVEMAP = "性能指标-环比曲线图测试用例";
	public static final String DESCONTRASTCURVEMAP = "性能指标-对比曲线图测试用例";
	public static final String rpcDesLogin = "rpc系统登录测试用例";
	private static DriverBrowser driverBrowser;
	private static 	GeneralizPerforPage genPage;
	@BeforeMethod
	public void testRpcLogin(){
		pinfo(ReportStep.class,rpcDesLogin+caseStart);
		try {
			driverBrowser = new DriverBrowser(BrowserType.Chrome);
			genPage = new GeneralizPerforPage(driverBrowser);
			RpcLoginPage reportPage = new RpcLoginPage(driverBrowser);
			driverBrowser.open(SeleniumSettings.URL);
			reportPage.RpcLogin();
			driverBrowser.pause(1000);
			genPage.clickGenPerfor();
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
	@Test(description=DESCITYOPERATORMAP)
	public void testCityOperatorMap(){
		pinfo(ReportStep.class,DESCITYOPERATORMAP+caseStart);
		try {
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
	@Test(description=DESSEQUENTIALCURVEMAP)
	public void testSequentialCurveMapMap(){
		pinfo(ReportStep.class,DESSEQUENTIALCURVEMAP+caseStart);
		try {
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
	@Test(description=DESCONTRASTCURVEMAP)
	public void testContrastCurveMap(){
		pinfo(ReportStep.class,DESCONTRASTCURVEMAP+caseStart);
		try {
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
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
	@AfterMethod
	public void afterClass(){
		driverBrowser.quit();
	}
}
