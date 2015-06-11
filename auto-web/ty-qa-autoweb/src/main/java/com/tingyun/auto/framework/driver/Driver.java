package com.tingyun.auto.framework.driver;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
* @author :chenjingli 
* @version ：2015-5-11 下午2:14:18 
* @decription:
 */
public abstract class Driver {
	
    protected RemoteWebDriver     remWebDriver;
    
    protected WebDriver webDriver;
    

    
    /**
     * 获取webDriver
     * @ClassName  : getWebDriver
     * @return
     * @throws IOException 
     * RemoteWebDriver
     */
	public abstract  RemoteWebDriver getRemWebDriver() throws MalformedURLException;
	public abstract WebDriver getwebDriver();
	
	
}
