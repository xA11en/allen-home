package com.tingyun.auto.server.page;

import org.openqa.jetty.jetty.servlet.WebApplicationContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午10:04:54 
* @decription: server app sys共同page
 */
public class CommonPage extends GlobalPage {

	public CommonPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(name="Web应用过程")
	public WebElement   nameCliWebAplication  ;//cli Web应用过程
	
	@FindBy(name="数据库")
	public WebElement  nameCliDatabase;//点击数据库
	
	@FindBy(name="NoSQL")
	public WebElement   nameCliNoSQL  ;//cli nosql
	
	@FindBy(name="JVM")
	public WebElement   nameCliJVM  ;//cli JVM
	
	@FindBy(id="rumselect_1_show_timePeriod")
	public WebElement   idGetTime  ;// 获得时间显示
	
	@FindBy(id="time_meun")
	public WebElement   idCliTime  ;// cli TIME 
	
	@FindBy(xpath="//*[@id='time_panel']/descendant::li[1]/span[2]")
	public WebElement  xpathCli30Mins  ;// cli 30 分钟
	
	@FindBy(id="time_ok")
	public WebElement  idCliSetting   ;// 点击设置
	
//	@FindBy()
//	public WebElement     ;//
//	
//	@FindBy()
//	public WebElement     ;//
//	
//	@FindBy()
//	public WebElement     ;//
//	
//	@FindBy()
//	public WebElement     ;//
	
}
