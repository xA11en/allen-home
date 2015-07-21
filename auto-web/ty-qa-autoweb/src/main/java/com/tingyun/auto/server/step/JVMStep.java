package com.tingyun.auto.server.step;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.server.page.JVMPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - web应用过程 step
 */
@Listeners({ TestResultListener.class })
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
			pinfo(ReportStep.class,heapMemoryUsage+caseStart);
			jvmPage.validationCommonJvmMap(heapMemoryUsage);
			pinfo(ReportStep.class,heapMemoryUsage+caseEnd);	
	}

	/**
	* @author : chenjingli
	* @decription   代码缓存 
	 */
	@Test(description=codeCache)
	public void testCodeCache(){
			pinfo(ReportStep.class,codeCache+caseStart);
			jvmPage.validationCommonJvmMap(codeCache);
			pinfo(ReportStep.class,codeCache+caseEnd);	
	}

	/**
	* @author : chenjingli
	* @decription Par Survivor Space
	 */
	@Test(description=parSurvivorSpace)
	public void testDataSourceResponseTimeMap(){
			pinfo(ReportStep.class,parSurvivorSpace+caseStart);
			jvmPage.validationCommonJvmMap(parSurvivorSpace);
			pinfo(ReportStep.class,parSurvivorSpace+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription par  Eden  Space
	 */
	@Test(description=parEdenSpace)
	public void testEdenSpaceMap(){
			pinfo(ReportStep.class,parEdenSpace+caseStart);
			jvmPage.validationCommonJvmMap(parEdenSpace);
			pinfo(ReportStep.class,parEdenSpace+caseEnd);	
	}
	
	
	/**
	* @author : chenjingli
	* @decription  cms Old  Gen  
	 */
	@Test(description=cmsOldGen)
	public void testCmsOldGenMap(){
			pinfo(ReportStep.class,cmsOldGen+caseStart);
			jvmPage.validationCommonJvmMap(cmsOldGen);
			pinfo(ReportStep.class,cmsOldGen+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription par  per  Space
	 */
	@Test(description=cmsPermGen)
	public void testCmsPermGenMap(){
			pinfo(ReportStep.class,cmsPermGen+caseStart);
			jvmPage.validationCommonJvmMap(cmsPermGen);
			pinfo(ReportStep.class,cmsPermGen+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription garbage  Coll   Cpu  Time
	 */
	@Test(description=garbageCollCpuTime)
	public void testgarbageCollectionCpuTimeMap(){
			pinfo(ReportStep.class,garbageCollCpuTime+caseStart);
			jvmPage.valiGCMap(garbageCollCpuTime);
			pinfo(ReportStep.class,garbageCollCpuTime+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription garbage  Coll   Cpu  Time
	 */
	@Test(description=classCount)
	public void testClassCountMap(){
			pinfo(ReportStep.class,classCount+caseStart);
			jvmPage.valiJvmClassMap(classCount);
			pinfo(ReportStep.class,classCount+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription thread  Count
	 */
	@Test(description=threadCount)
	public void testThreadCountMap(){
			pinfo(ReportStep.class,threadCount+caseStart);
			jvmPage.valiThreadCountMap(threadCount);
			pinfo(ReportStep.class,threadCount+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription test  Ajp  Map
	 */
	@Test(description=ajp)
	public void testAjpMap(){
			pinfo(ReportStep.class,ajp+caseStart);
			jvmPage.validationCommonJvmMap(ajp);
			pinfo(ReportStep.class,ajp+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription test  Ajp  Map
	 */
	@Test(description=HTTP)
	public void testHttpMap(){
			pinfo(ReportStep.class,ajp+caseStart);
			jvmPage.validationCommonJvmMap(HTTP);
	}
	
	/**
	* @author : chenjingli
	* @decription test  manager  Map
	 */
	@Test(description=manager)
	public void testManagerMap(){
			pinfo(ReportStep.class,manager+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,manager+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription test  manager  Map
	 */
	@Test(description=hostManager)
	public void testHostManagerMap(){
			pinfo(ReportStep.class,hostManager+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,hostManager+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription test  javaDemo  Map
	 */
	@Test(description=javaDemo)
	public void testJavaDemoMap(){
			pinfo(ReportStep.class,javaDemo+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,javaDemo+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription test  examples  Map
	 */
	@Test(description=examples)
	public void testExamplesMap(){
			pinfo(ReportStep.class,examples+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,examples+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription test  docs  Map
	 */
	@Test(description=docs)
	public void testDocsMap(){
			pinfo(ReportStep.class,docs+caseStart);
			jvmPage.validationCommonJvmMap(manager);
			pinfo(ReportStep.class,docs+caseEnd);	
	}
	
	/**
	* @author : chenjingli
	* @decription test  /  Map
	 */
	@Test(description=fanXieGang)
	public void testFanXieGangMap(){
			pinfo(ReportStep.class,fanXieGang+caseStart);
			jvmPage.validationCommonJvmMap(fanXieGang);
			pinfo(ReportStep.class,fanXieGang+caseEnd);	
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
