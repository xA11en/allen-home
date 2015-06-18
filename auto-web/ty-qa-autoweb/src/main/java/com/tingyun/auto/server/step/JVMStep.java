package com.tingyun.auto.server.step;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.server.page.JVMPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - web应用过程 step
 */
public class JVMStep extends GlobalStep{
	
	private static final String heapMemoryUsage = "内存使用量--展现测试用例";
	private static final String codeCache = "代码缓存--展现测试用例";
	private static final String parSurvivorSpace = "Par Survivor Space 曲线图--展现测试用例";
	private static final String parEdenSpace = "Par Eden Space 曲线图--展现测试用例";
	private static final String cmsOldGen = "CMS Old Gen 曲线图--展现测试用例";
	private static final String cmsPermGen = "CMS Perm Gen 曲线图--展现测试用例";
	private static final String garbageCollCpuTime = "Garbage collection CPU time 曲线图--展现测试用例";
	private static final String classCount = " Class Count  曲线图--展现测试用例";
	private static final String threadCount = "thread  Count 曲线图--展现测试用例";
	private static final String ajp = "ajp-18009   曲线图--展现测试用例";
	private static final String HTTP = "http-18080   曲线图--展现测试用例";
	private static final String manager = "manager   曲线图--展现测试用例";
	private static final String hostManager = "host-manager   曲线图--展现测试用例";
	private static final String javaDemo = "java_demo   曲线图--展现测试用例";
	private static final String examples = "examples   曲线图--展现测试用例";
	private static final String docs = "docs   曲线图--展现测试用例";
	private static final String fanXieGang = "fanXieGang   曲线图--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static JVMPage jvmPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		jvmPage = new JVMPage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/jvm");
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	* @decription 内存使用量
	 */
	@Test(description=heapMemoryUsage)
	public void testHeapMemoryUsageMap(){
		try {
			pinfo(ReportStep.class,heapMemoryUsage+caseStart);
			jvmPage.validationCommonJvmMap(heapMemoryUsage);
			pinfo(ReportStep.class,heapMemoryUsage+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testHeapMemoryUsageMap");
			fail(heapMemoryUsage+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testHeapMemoryUsageMap");
			throw new TestNGException(heapMemoryUsage+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription   代码缓存 
	 */
	@Test(description=codeCache)
	public void testCodeCache(){
		try {
			pinfo(ReportStep.class,codeCache+caseStart);
			jvmPage.validationCommonJvmMap(codeCache);
			pinfo(ReportStep.class,codeCache+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testDataSourceThroughputMap");
			fail(codeCache+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testDataSourceThroughputMap");
			throw new TestNGException(codeCache+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription Par Survivor Space
	 */
	@Test(description=parSurvivorSpace)
	public void testDataSourceResponseTimeMap(){
		try {
			pinfo(ReportStep.class,parSurvivorSpace+caseStart);
			jvmPage.validationCommonJvmMap(parSurvivorSpace);
			pinfo(ReportStep.class,parSurvivorSpace+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testDataSourceResponseTimeMap");
			fail(parSurvivorSpace+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testDataSourceResponseTimeMap");
			throw new TestNGException(parSurvivorSpace+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription par  Eden  Space
	 */
	@Test(description=parEdenSpace)
	public void testEdenSpaceMap(){
		try {
			pinfo(ReportStep.class,parEdenSpace+caseStart);
			jvmPage.validationCommonJvmMap(parEdenSpace);
			pinfo(ReportStep.class,parEdenSpace+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testEdenSpaceMap");
			fail(parEdenSpace+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testEdenSpaceMap");
			throw new TestNGException(parEdenSpace+"" + e.getMessage(), e);
		} 
	}
	
	
	/**
	* @author : chenjingli
	* @decription  cms Old  Gen  
	 */
	@Test(description=cmsOldGen)
	public void testCmsOldGenMap(){
		try {
			pinfo(ReportStep.class,cmsOldGen+caseStart);
			jvmPage.validationCommonJvmMap(cmsOldGen);
			pinfo(ReportStep.class,cmsOldGen+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testcmsOldGenMap");
			fail(cmsOldGen+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testcmsOldGenMap");
			throw new TestNGException(cmsOldGen+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription par  per  Space
	 */
	@Test(description=cmsPermGen)
	public void testCmsPermGenMap(){
		try {
			pinfo(ReportStep.class,cmsPermGen+caseStart);
			jvmPage.validationCommonJvmMap(cmsPermGen);
			pinfo(ReportStep.class,cmsPermGen+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testCmsPermGenMap");
			fail(cmsPermGen+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testCmsPermGenMap");
			throw new TestNGException(cmsPermGen+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription garbage  Coll   Cpu  Time
	 */
	@Test(description=garbageCollCpuTime)
	public void testgarbageCollectionCpuTimeMap(){
		try {
			pinfo(ReportStep.class,garbageCollCpuTime+caseStart);
			jvmPage.valiGCMap(garbageCollCpuTime);
			pinfo(ReportStep.class,garbageCollCpuTime+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testgarbageCollectionCpuTimeMap");
			fail(garbageCollCpuTime+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testgarbageCollectionCpuTimeMap");
			throw new TestNGException(garbageCollCpuTime+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription garbage  Coll   Cpu  Time
	 */
	@Test(description=classCount)
	public void testClassCountMap(){
		try {
			pinfo(ReportStep.class,classCount+caseStart);
			jvmPage.valiJvmClassMap(classCount);
			pinfo(ReportStep.class,classCount+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testgarbageCollectionCpuTimeMap");
			fail(classCount+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testgarbageCollectionCpuTimeMap");
			throw new TestNGException(classCount+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription thread  Count
	 */
	@Test(description=threadCount)
	public void testThreadCountMap(){
		try {
			pinfo(ReportStep.class,threadCount+caseStart);
			jvmPage.valiThreadCountMap(threadCount);
			pinfo(ReportStep.class,threadCount+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testThreadCountMap");
			fail(threadCount+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testThreadCountMap");
			throw new TestNGException(threadCount+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  Ajp  Map
	 */
	@Test(description=ajp)
	public void testAjpMap(){
		try {
			pinfo(ReportStep.class,ajp+caseStart);
			jvmPage.validationCommonJvmMap(ajp);
			pinfo(ReportStep.class,ajp+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testAjpMap");
			fail(ajp+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testAjpMap");
			throw new TestNGException(ajp+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  Ajp  Map
	 */
	@Test(description=HTTP)
	public void testHttpMap(){
		try {
			pinfo(ReportStep.class,ajp+caseStart);
			jvmPage.validationCommonJvmMap(HTTP);
			pinfo(ReportStep.class,HTTP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testHttpMap");
			fail(HTTP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testHttpMap");
			throw new TestNGException(HTTP+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  manager  Map
	 */
	@Test(description=manager)
	public void testManagerMap(){
		try {
			pinfo(ReportStep.class,manager+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,manager+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testManagerMap");
			fail(manager+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testManagerMap");
			throw new TestNGException(manager+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  manager  Map
	 */
	@Test(description=hostManager)
	public void testHostManagerMap(){
		try {
			pinfo(ReportStep.class,hostManager+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,hostManager+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testHostManagerMap");
			fail(hostManager+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testHostManagerMap");
			throw new TestNGException(hostManager+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  javaDemo  Map
	 */
	@Test(description=javaDemo)
	public void testJavaDemoMap(){
		try {
			pinfo(ReportStep.class,javaDemo+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,javaDemo+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testJavaDemoMap");
			fail(javaDemo+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testJavaDemoMap");
			throw new TestNGException(javaDemo+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  examples  Map
	 */
	@Test(description=examples)
	public void testExamplesMap(){
		try {
			pinfo(ReportStep.class,examples+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,examples+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testExamplesMap");
			fail(examples+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testExamplesMap");
			throw new TestNGException(examples+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  docs  Map
	 */
	@Test(description=docs)
	public void testDocsMap(){
		try {
			pinfo(ReportStep.class,docs+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,docs+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testDocsMap");
			fail(docs+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testDocsMap");
			throw new TestNGException(docs+"" + e.getMessage(), e);
		} 
	}
	
	/**
	* @author : chenjingli
	* @decription test  /  Map
	 */
	@Test(description=fanXieGang)
	public void testFanXieGangMap(){
		try {
			pinfo(ReportStep.class,fanXieGang+caseStart);
			jvmPage.validationCommonJvmMap(fanXieGang);
			pinfo(ReportStep.class,fanXieGang+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testFanXieGangMap");
			fail(fanXieGang+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testFanXieGangMap");
			throw new TestNGException(fanXieGang+"" + e.getMessage(), e);
		} 
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
