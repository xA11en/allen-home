package com.tingyun.auto.page;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;



import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.utils.OperateProperties;

/**
* @author :chenjingli 
* @version ：2015-5-6 上午10:24:43 
* @decription:系统首页page
 */
public class GlobalPage  {
	
	public static DriverBrowser driverBrowser;
	protected Logger logger;
	
	
	public GlobalPage(DriverBrowser driverBrowser){
		this.driverBrowser = driverBrowser;
		ElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driverBrowser.getWebDriver(), SeleniumSettings.PAGE_FACTORY_TIME_OUT);
		PageFactory.initElements(factory, this);
	}
	
	public void pinfo(Class<?> clazz,String info){
		logger = LoggerFactory.getLogger(clazz);
		logger.info(info);
	}
	public void perror(Class<?> clazz,String error){
		logger = LoggerFactory.getLogger(clazz);
		logger.error(error);
	}
	public void pdebug(Class<?> clazz,String debug){
		logger = LoggerFactory.getLogger(clazz);
		logger.debug(debug);
	}
	
	/**
	* @author : chenjingli
	* @decription 通过key读pro文件
	* @return
	 */
	public String readValue(String key){
		return OperateProperties.readValue(key);
	}
	/**
	* @author : chenjingli
	* @decription 写入pro文件 key and value
	* @return
	 */
	public void writeProperties(String keyname,String keyvalue){
		 OperateProperties.writeProperties(keyname, keyvalue);
	}
	/**
	* @author : chenjingli
	* @decription 更新pro文件 key and value
	* @return
	 */
	public void updateProperties(String keyname,String keyvalue){
		 OperateProperties.updateProperties(keyname, keyvalue);
	}
	
}
