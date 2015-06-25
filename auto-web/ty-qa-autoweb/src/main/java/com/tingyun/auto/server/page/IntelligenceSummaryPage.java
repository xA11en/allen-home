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
* @decription: server - 情报汇总 page
 */
public class IntelligenceSummaryPage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(IntelligenceSummaryPage.class);
	public IntelligenceSummaryPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(css="#httpAndNetwork > div > div >svg")
	public WebElement cssAppRespondTimeMap; //应用服务器响应时间

	@FindBy(css="#apdex > div > div >svg")
	public WebElement cssApdexMap; //Apdex指标
	
	@FindBy(css="#error > div > div >svg")
	public WebElement cssErrorMap; //错误率

	@FindBy(css="#topWebAction > div > div >svg")
	public WebElement csstopWebActionMap; //最耗时Web应用过程（Web Action）图表

	@FindBy(css="#throughput > div > div >svg")
	public WebElement cssThroughtMap; //吞吐率
	
	@FindBy(css="#httpResponseTime > div > div >svg")
	public WebElement cssCPUTimeMap; //服务器资源--cpu

	@FindBy(css="#activeDevice > div > div >svg")
	public WebElement cssMerboryMap; //服务器资源--内存

	@FindBy(id="tablesorter")
	public WebElement idTable; //展现所有的数据 table
	
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 应用服务器响应时间图
	* @return
	 */
	public void cssAppRespondTimeMap(String str)throws Exception{
		driverBrowser.expectElementExist(cssAppRespondTimeMap,str);
		String js = "return document.getElementById('httpAndNetwork').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		
		if(null!=length && length>0){
			logger.info("应用服务器响应时间图表显示正常");
			assertTrue(true);
		}else{
			fail("应用服务器响应时间图表未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 Apdex指标图
	* @return
	 */
	public void cssApdexMap(String str)throws Exception{
		driverBrowser.expectElementExist(cssApdexMap,str);
		String js = "return document.getElementById('apdex').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("Apdex指标图显示正常");
			assertTrue(true);
		}else{
			fail("Apdex指标图表未显示");
		}
	}
	/**
	* @author : chenjingli
	* @decriptionTOP5 错误率图
	* @return
	 */
	public void cssErrorMap(String str)throws Exception{
		driverBrowser.expectElementExist(cssErrorMap,str);
		String js = "return document.getElementById('error').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("错误率图显示正常");
			assertTrue(true);
		}else{
			fail("错误率图表未显示");
		}
	}
	
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 最耗时Web应用过程（Web Action）图表显示正常
	* @return
	 */
	public void csstopWebActionMap(String str)throws Exception{
		driverBrowser.expectElementExist(csstopWebActionMap,str);
		String js = "return document.getElementById('topWebAction').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		
		if(null!=length && length>0){
			logger.info("最耗时Web应用过程（Web Action）图表显示正常");
			assertTrue(true);
		}else{
			fail("最耗时Web应用过程（Web Action）图表显示正常");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 吞吐率图
	* @return
	 */
	public void cssThroughtMap(String str)throws Exception{
		driverBrowser.expectElementExist(cssThroughtMap,str);
		String js = "return document.getElementById('throughput').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("吞吐率图显示正常");
			assertTrue(true);
		}else{
			fail("吞吐率图表未显示");
		}
	}
	/**
	* @author : chenjingli
	* @decriptionTOP5 服务器资源--cpu
	* @return
	 */
	public void cssCPUTimeMap(String str)throws Exception{
		driverBrowser.expectElementExist(cssCPUTimeMap,str);
		String js = "return document.getElementById('httpResponseTime').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("服务器资源--cpu图显示正常");
			assertTrue(true);
		}else{
			fail("服务器资源--cpu图表未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 服务器资源--内存
	* @return
	 */
	public void cssMerboryMap(String str)throws Exception{
		driverBrowser.expectElementExist(cssMerboryMap,str);
		String js = "return document.getElementById('activeDevice').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("服务器资源--内存图显示正常");
			assertTrue(true);
		}else{
			fail("服务器资源--内存图表未显示");
		}
	}
	/**
	* @author : chenjingli
	* @decriptionTOP5  展现所有的数据 table 
	* @return
	 */
	public void idTable(String str)throws Exception{
		driverBrowser.expectElementExist(idTable,str);
//		String js = "return document.getElementById('tablesorter').length";
//		Long length = (Long) driverBrowser.executeScript(js);
//		if(null!=length && length >0){
//			logger.info("展现所有的数据 table显示正常");
//			assertTrue(true);
//		}else{
//			fail("展现所有的数据 table表未显示");
//		}
	}
}
