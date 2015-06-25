package com.tingyun.auto.server.page;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;
/**
* @author :chenjingli 
* @version ：2015-6-11 下午2:29:19 
* @decription: server - NOSQL page
 */
public class BackgroundTasksPage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(BackgroundTasksPage.class);
	public BackgroundTasksPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(css="#top5bgAction > div > div >svg")
	public WebElement xpathMostTimeConsumingMap; //最耗时后台任务图表

	@FindBy(css="#bgCpu > div > div >svg")
	public WebElement xpathAppCpuMap; //应用CPU使用率图表
	
	@FindBy(css="#bgMem > div > div >svg")
	public WebElement xpathAppMemoryMap; //应用内存使用量图表
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 最耗时后台任务图表
	* @return
	 */
	public void validationTimeConsumingMap(String str)throws Exception{
		driverBrowser.expectElementExist(xpathMostTimeConsumingMap,str);
		String js = "return document.getElementById('top5bgAction').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		
		if(null!=length && length>0){
			logger.info("最耗时后台任务图表显示正常");
			assertTrue(true);
		}else{
			fail("最耗时后台任务图表未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 应用CPU使用率图表
	* @return
	 */
	public void valiAppCpuMap(String str)throws Exception{
		driverBrowser.expectElementExist(xpathAppCpuMap,str);
		String js = "return document.getElementById('bgCpu').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("应用CPU使用率图表显示正常");
			assertTrue(true);
		}else{
			fail("应用CPU使用率图表未显示");
		}
	}
	/**
	* @author : chenjingli
	* @decriptionTOP5 应用内存使用量图表
	* @return
	 */
	public void valiAppMemoryMap(String str)throws Exception{
		driverBrowser.expectElementExist(xpathAppMemoryMap,str);
		String js = "return document.getElementById('bgMem').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("应用内存使用量图表显示正常");
			assertTrue(true);
		}else{
			fail("应用内存使用量图表未显示");
		}
	}
}
