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
* @decription: server - JVM page
 */
public class JVMPage extends CommonPage{
	
	private static Logger logger = LoggerFactory.getLogger(JVMPage.class);
	public JVMPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	

	@FindBy(linkText="内存使用量")
	public WebElement linCliMemoryUsed; //cli 内存使用量
	
	@FindBy(xpath="//div[@class='submenu_tab2']/descendant::div[2]")
	public WebElement xpathCliThread; //cli 线程
	
	@FindBy(linkText="Http会话")
	public WebElement xpathCliSession; //cli Http会话
	
	/**
	 * 内存使用量
	 */
	@FindBy(css="#heapMemory > div > div >svg")
	public WebElement xpathGetHeapMemoryMap; // jvm common webelement

	@FindBy(css="#gc > div > div >svg")
	public WebElement xpathGetgcMap; // Garbage collection CPU time 
	
	@FindBy(css="#jvmclass > div > div >svg")
	public WebElement xpathGetJvmClassMap; // Class Count 
	
	
	/**
	 * 线程
	 */
	@FindBy(css="#threadCount > div > div >svg")
	public WebElement xpathGetThreadCountMap; //  Thread Count 
	
	
	/**
	* @author : chenjingli
	* @return
	* @description 判断图片个数 如果少于14个说明有未显示图标存在 错误会截图
	 */
	public void validationCommonJvmMap(String str){
		
		String js = "return document.getElementById('heapMemory').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length<14){
			logger.info(str+"显示正常");
			assertTrue(true);
		}else{
			fail(str+"未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 Garbage collection CPU time 
	* @return
	 */
	public void valiGCMap(String str){
		driverBrowser.expectElementExist(xpathGetgcMap,str);
		String js = "return document.getElementById('gc').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("Garbage collection CPU time 图显示正常");
			assertTrue(true);
		}else{
			fail("Garbage collection CPU time 图表未显示");
		}
	}
	/**
	* @author : chenjingli
	* @decriptionTOP5 Class Count 
	* @return
	 */
	public void valiJvmClassMap(String str){
		driverBrowser.expectElementExist(xpathGetgcMap,str);
		String js = "return document.getElementById('jvmclass').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("Class Count 图显示正常");
			assertTrue(true);
		}else{
			fail("Class Count 图表未显示");
		}
	}
	
	/**
	* @author : chenjingli
	* @decriptionTOP5 Class Count 
	* @return
	 */
	public void valiThreadCountMap(String str){
		//点击线程按钮
		driverBrowser.click(xpathCliThread);
		//判断图标是否真正常展现
		driverBrowser.expectElementExist(xpathGetThreadCountMap,str);
		String js = "return document.getElementById('threadCount').getElementsByTagName('svg').length";
		Long length = (Long) driverBrowser.executeScript(js);
		if(null!=length && length >0){
			logger.info("Thread Count  图显示正常");
			assertTrue(true);
		}else{
			fail("Thread Count  图表未显示");
		}
	}
}
