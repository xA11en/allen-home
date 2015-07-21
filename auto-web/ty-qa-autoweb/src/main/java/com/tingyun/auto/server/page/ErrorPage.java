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
* @decription: server - error page
 */
public class ErrorPage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(ErrorPage.class);
	public ErrorPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(css="#applicationErrors > div > div >svg")
	public WebElement xpathAppErrorMap; //应用错误率

	/**
	* @author : chenjingli
	* @decriptionTOP5  应用错误率
	* @return
	 */
	public void validationAppErrorMap(String str){
		driverBrowser.expectElementExist(xpathAppErrorMap,str);
		String js = "return document.getElementById('applicationErrors').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		
		if(null!=length && length>0){
			logger.info("应用错误率显示正常");
			assertTrue(true);
		}else{
			fail("应用错误率图表未显示");
		}
	}
}
