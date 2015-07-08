package com.tingyun.auto.common;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.utils.ExcelReader;
import com.tingyun.auto.utils.OperateProperties;
import com.tingyun.auto.utils.RedisManger;
import com.tingyun.auto.utils.StrAndDateUtil;

/**
* @author :chenjingli 
* @version ：2015-5-6 上午10:24:43 
* @decription:系统首页page
 */
public class GlobalPage  {
	
	public static DriverBrowser driverBrowser;
	protected Logger logger;
	protected ExcelReader excelReader;
	protected static final String firePath = System.getProperty("user.dir")+"\\src\\main\\resources\\testdata.xlsx";
	public GlobalPage(DriverBrowser driverBrowser){
		this.driverBrowser = driverBrowser;
		ElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driverBrowser.getWebDriver(), SeleniumSettings.PAGE_FACTORY_TIME_OUT);
		PageFactory.initElements(factory, this);
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
	/**
	* @author : chenjingli
	* @decription
	* @return  读取radis中手机验证码
	 */
	public String getExcelData(String key){
		excelReader = new ExcelReader(firePath,"report");
		return excelReader.getExcelData(key);
	}
	/**
	* @author : chenjingli
	* @decription 通过 redis 手机验证码 规则key 获取 验证码
	* @return String
	 */
	public String getRadisKey(String key,String phone){
		
		if(StrAndDateUtil.isBlank(key) ||StrAndDateUtil.isBlank(phone) ){
			return null;
		}
		return RedisManger.getValue(key, phone);
	}
	
	
	/**
	* @author : chenjingli
	* @decription 通过 redis 手机验证码 规则key 获取 验证码
	* @return String
	 */
	public void setRadisKeyValue(String key,String value){
		
		if(StrAndDateUtil.isBlank(key) ||StrAndDateUtil.isBlank(value) ){
			logger.error("key或value为null");
		}
		RedisManger.setValue(key, value);
	}
	
	
	
}
