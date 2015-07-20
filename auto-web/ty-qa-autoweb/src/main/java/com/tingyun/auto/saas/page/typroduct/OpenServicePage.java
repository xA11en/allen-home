package com.tingyun.auto.saas.page.typroduct;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.saas.page.CommonPage;

/**
* @author :chenjingli 
* @version ：2015-7-14 上午11:13:31 
* @decription:  登陆后开通ty，app server cdn sys 等服务page
 */
public class OpenServicePage extends CommonPage{
	
	public static Logger logger = LoggerFactory.getLogger(OpenServicePage.class);
	
	public OpenServicePage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(xpath="//div[@class='fuwu_off_box']/div[2]/div[3]/div")
	public WebElement xpathCliOpenApp;//点击立即开通 听云app
	
	@FindBy(xpath="//div[@class='fuwu_off_box']/div[3]/div[3]/div")
	public WebElement xpathCliOpenServer;//点击立即开通 听云server
	
	@FindBy(xpath="//div[@class='fuwu_off_box']/div[4]/div[3]/div")
	public WebElement xpathCliOpenCdn;//点击立即开通 听云cdn
	
	@FindBy(xpath="//div[@class='fuwu_off_box']/div[5]/div[3]/div")
	public WebElement xpathCliOpenSys;//点击立即开通 听云sys
	
	@FindBy(xpath="//div[@class='fuwu_list_on']/descendant::a[text()='升级套餐'][1]")
	public WebElement xpathCliAppUpgradePackage;//点击升级app套餐
	
	@FindBy(xpath="//div[@class='fuwu_list_on']/descendant::a[text()='升级套餐'][2]")
	public WebElement xpathCliServerUpgradePackage;//点击升级server套餐
	/**
	 * 弹出框中的div page
	 */
	@FindBy(xpath="//*[@id='appProductDiv']/descendant::li[1]/div[3]")
	public WebElement xpathCliTanOpenAppBasis;//点击立即开通 听云app
	
	@FindBy(xpath="//*[@id='appProductDiv']/descendant::li[2]/div[3]")
	public WebElement xpathCliTanOpenAppXiaoWei;//点击立即购买 听云小薇版
	
	@FindBy(xpath="//*[@id='serverProductDiv']/descendant::li[1]/div[3]")
	public WebElement xpathCliTanOpenServerBasis;//点击立即开通 听云server
	
	/**
	 * 开通 app server cdn sys服务测试用例
	 */
	public void openService(){
		int count=0;
		//开通app
		driverBrowser.click(xpathCliOpenApp);
		//点击弹出框中的立即考通基础版app
		driverBrowser.click(xpathCliTanOpenAppBasis);
		//判断是否开通成功
		Assert.assertTrue(xpathBoolOpenIsSuccess.isDisplayed());
		//重新打开我的服务页
		driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/userService/myproduct");
		//开通server
		try{
			driverBrowser.pause(2000);
			driverBrowser.click(xpathCliOpenApp);
			//点击弹出框中的立即考通基础版server
			driverBrowser.click(xpathCliTanOpenServerBasis);
			//判断是否开通成功
			Assert.assertTrue(xpathBoolOpenIsSuccess.isDisplayed());
		}catch(Exception e){
			Reporter.log("/n"+"开通server服务未成功！");
			count++;
		}
		//重新打开我的服务页
		driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/userService/myproduct");
		//开通cdn服务
		driverBrowser.pause(2000);
		try{
			driverBrowser.click(xpathCliOpenApp);
			//判断是否开通成功
			Assert.assertTrue(xpathBoolOpenIsSuccess.isDisplayed());
		}catch(Exception e){
			Reporter.log("/n"+"开通cdn服务未成功！");
			count++;
		}
		//重新打开我的服务页
		driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/userService/myproduct");
		//开通sys服务
		try{
			driverBrowser.click(xpathCliOpenApp);
			//判断是否开通成功
			Assert.assertTrue(xpathBoolOpenIsSuccess.isDisplayed());
		}catch(Exception e){
			Reporter.log("/n"+"开通sys服务未成功！");
			count++;
		}
		
		if(count!=0){
			logger.info("错误次数count==========================={}",count);
			Assert.fail("开通服务测试失败！");
		}
		
	}
}
