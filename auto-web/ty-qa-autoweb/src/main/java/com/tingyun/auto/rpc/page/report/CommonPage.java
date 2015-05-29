package com.tingyun.auto.rpc.page.report;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;

public class CommonPage extends GlobalPage {
	
	
	public CommonPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}

	/**
	 * 判断性能历史曲线 图和表格
	 */
	@FindBy(xpath="//*[@id='perfByTime']/img")
	public WebElement booleanMapXingNeng;//用来判断性能历史曲线性能
	
	@FindBy(xpath="//*[@id='availByTime']/img")
	public WebElement booleanMapKeYongXing;//用来判断性能历史曲线性能可用性
	
	@FindBy(xpath="//*[@id='dataList']/tbody")
	public WebElement booleanTableXingNeng;//用来判断性能历史曲线性能可用性\
	
	/**
	 * 判断运营商性能曲线图
	 */
	@FindBy(xpath="//*[@id='ispByTime']/img")
	public WebElement booleanMapYunYingShang;//运营商性能曲线图
	
	@FindBy(xpath="//*[@id='availIspByTime']/img")//运营商性能曲线图2
	public WebElement booleanMapYunYingShang2;
	
	@FindBy(xpath="//*[@id='nbs4']/div/table/tbody")//运营商性能表格
	public WebElement booleanTableYunYingShang;
	
	/**
	 * 城市性能曲线图
	 */
	@FindBy(xpath="//*[@id='locationByTime']/img")
	public WebElement booleanMapCity;//城市性能曲线图
	
	@FindBy(xpath="//*[@id='availLocationByTime']/img")//城市性能曲线图
	public WebElement booleanMapCity2;
	
	/**
	 * 城市运营商性能曲线图
	 */
	@FindBy(xpath="//*[@id='locationIspByTime']/img")
	public WebElement booleanMapCityXingN;//城市运营商性能曲线图
	
	@FindBy(xpath="//*[@id='nbs4']/div/table/tbody")//城市运营商性能table
	public WebElement booleanTableCityXingN;
	
	
	/**
	 * 运营商性能图
	 */
	@FindBy(xpath="//*[@id='perfByIsp']/img")
	public WebElement booleanMapComXing;//运营商性能图
	
	@FindBy(xpath="//*[@id='availByIsp']/img")//运营商性能图可用性
	public WebElement booleanMapAbale;
}
