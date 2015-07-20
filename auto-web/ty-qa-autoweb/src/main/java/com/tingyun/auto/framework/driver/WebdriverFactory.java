package com.tingyun.auto.framework.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.framework.browser.BrowserType;


/**
 * 
 * @author chenjingli
 *
 */
public class WebdriverFactory {

	/**
	 * slf4j logback
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(WebdriverFactory.class);
	
	public final  static String packageName = "com.tingyun.auto.framework.driver.";
	
	
	/**
	* @decription 通过反向代理生成webdriver
	* @return
	 */
	  public static Driver createWebDriver(final BrowserType type) {
		  
	        try {
	        	return (Driver) Class.forName(packageName + type.toString()).newInstance();
	        } catch (Exception e) {
	        	logger.error("通过反向代理生成webdriver异常{}",e);
	            throw new IllegalArgumentException("Unable to instantiate " ,e);
	        }
	        
	    }
	
	
}
