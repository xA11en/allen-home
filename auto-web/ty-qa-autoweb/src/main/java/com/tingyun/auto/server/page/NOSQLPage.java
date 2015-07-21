package com.tingyun.auto.server.page;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tingyun.auto.framework.browser.DriverBrowser;
/**
* @author :chenjingli 
* @version ：2015-6-11 下午2:29:19 
* @decription: server - NOSQL page
 */
public class NOSQLPage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(NOSQLPage.class);
	public NOSQLPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(css="#topMongodb > div > div >svg")
	public WebElement xpathGetMostRedisMap; //最耗时Redis操作堆叠图

	@FindBy(css="#throughput > div > div >svg")
	public WebElement xpathGetRedisThroughtMap; //Redis吞吐率堆叠图
	
	@FindBy(css="#performance > div > div >svg")
	public WebElement xpathGetRedisRespondMap; //Redis响应时间曲线图
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 最耗时Redis操作堆叠图
	* @return
	 */
	public void validationRedisMap(String str){
		driverBrowser.expectElementExist(xpathGetMostRedisMap,str);
		String js = "return document.getElementById('topMongodb').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		
		if(null!=length && length>0){
			logger.info("最耗时SQL操作堆叠图表显示正常");
			assertTrue(true);
		}else{
			fail("最耗时SQL操作堆叠图表未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 Redis吞吐率堆叠图
	* @return
	 */
	public void valiRedisThroughtMap(String str){
		driverBrowser.expectElementExist(xpathGetRedisThroughtMap,str);
		String js = "return document.getElementById('throughput').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("Redis吞吐率堆叠图显示正常");
			assertTrue(true);
		}else{
			fail("Redis吞吐率堆叠图表未显示");
		}
	}
	/**
	* @author : chenjingli
	* @decriptionTOP5 Redis响应时间曲线图
	* @return
	 */
	public void valiRedisRespondMap(String str){
		driverBrowser.expectElementExist(xpathGetRedisRespondMap,str);
		String js = "return document.getElementById('performance').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("Redis响应时间曲线图显示正常");
			assertTrue(true);
		}else{
			fail("Redis响应时间曲线图表未显示");
		}
	}
}
