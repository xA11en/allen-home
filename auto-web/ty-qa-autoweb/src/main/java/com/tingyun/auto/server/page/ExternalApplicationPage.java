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
* @decription: server - 外部应用 page
 */
public class ExternalApplicationPage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(ExternalApplicationPage.class);
	public ExternalApplicationPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(css="#topSql > div > div >svg")
	public WebElement xpathGetMostSlowWebAPPMap; //总耗时最慢的外部应用曲线图

	@FindBy(css="#throughput > div > div >svg")
	public WebElement xpathGetWebAppThroughtMap; //外部应用吞吐率曲线图
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 总耗时最慢的外部应用曲线图
	* @return
	 */
	public void validationMostSlowWebAPPMap(String str){
		driverBrowser.expectElementExist(xpathGetMostSlowWebAPPMap,str);
		String js = "return document.getElementById('topSql').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		
		if(null!=length && length>0){
			logger.info("总耗时最慢的外部应用曲线图显示正常");
			assertTrue(true);
		}else{
			fail("总耗时最慢的外部应用曲线图表未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 外部应用吞吐率曲线图
	* @return
	 */
	public void valiWebAppThroughtMap(String str){
		driverBrowser.expectElementExist(xpathGetWebAppThroughtMap,str);
		String js = "return document.getElementById('throughput').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("外部应用吞吐率曲线图显示正常");
			assertTrue(true);
		}else{
			fail("外部应用吞吐率曲线图表未显示");
		}
	}
}
