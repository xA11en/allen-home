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
* @decription: server - web应用过程 page
 */
public class DataSourcePage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(DataSourcePage.class);
	public DataSourcePage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(css="#topSql > div > div >svg")
	public WebElement xpathGetMostSQLMap; //最耗时SQL操作堆叠图
	
	@FindBy(css="#throughput > div > div >svg")
	public WebElement xpathGetDataSourceMap; //数据库吞吐率堆叠图
	
	@FindBy(css="#performance > div > div >svg")
	public WebElement xpathGetDataSourceRespondMap; //数据库响应时间曲线图
	/**
	* @author : chenjingli
	* @decriptionTOP5 最耗时SQL操作堆叠图
	* @return
	 */
	public void validationSQLMap(String str)throws Exception{
		driverBrowser.expectElementExist(xpathGetMostSQLMap,str);
		String js = "return document.getElementById('topSql').getElementsByTagName('svg').length";
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
	* @decriptionTOP5 数据库吞吐率堆叠图
	* @return
	 */
	public void valiDataSourceMap(String str)throws Exception{
		driverBrowser.expectElementExist(xpathGetDataSourceMap,str);
		String js = "return document.getElementById('throughput').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("数据库吞吐率堆叠图显示正常");
			assertTrue(true);
		}else{
			fail("数据库吞吐率堆叠图表未显示");
		}
	}
	/**
	* @author : chenjingli
	* @decriptionTOP5 数据库响应时间曲线图
	* @return
	 */
	public void valiDataSourceRespondMap(String str)throws Exception{
		driverBrowser.expectElementExist(xpathGetDataSourceRespondMap,str);
		String js = "return document.getElementById('performance').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("数据库响应时间曲线图显示正常");
			assertTrue(true);
		}else{
			fail("数据库响应时间曲线图表未显示");
		}
	}
}
