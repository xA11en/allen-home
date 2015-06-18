package com.tingyun.auto.server.page;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;
/**
* @author :chenjingli 
* @version ：2015-6-11 下午2:29:19 
* @decription: server - web应用过程 page
 */
public class WebApplicationPage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(WebApplicationPage.class);
	public WebApplicationPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(css="#top5action > div > div >svg")
	public WebElement xpathGetMostWebAppMap; //TOP5 最耗时Web应用过程(墙钟时间比)堆叠图
	
	@FindBy(css="#throughputAction > div > div >svg")
	public WebElement xpathGetTimeMap; //响应时间和吞吐率
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 最耗时Web应用过程(墙钟时间比)堆叠图
	* @return
	 */
	public void validationWebMap(String str)throws Exception{
		driverBrowser.expectElementExist(xpathGetMostWebAppMap,str);
		String js = "return document.getElementById('top5action').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		
		if(null!=length && length>0){
			logger.info("响应时间和吞吐率图表显示正常");
			assertTrue(true);
		}else{
			fail("最耗时Web应用过程(墙钟时间比)堆叠图的数据图表未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 响应时间和吞吐率
	* @return
	 */
	public void valiTimeAndThroughput(String str)throws Exception{
		driverBrowser.expectElementExist(xpathGetTimeMap,str);
		String js = "return document.getElementById('throughputAction').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("响应时间和吞吐率的数据图显示正常");
			assertTrue(true);
		}else{
			fail("响应时间和吞吐率的数据图表未显示");
		}
	}
}
