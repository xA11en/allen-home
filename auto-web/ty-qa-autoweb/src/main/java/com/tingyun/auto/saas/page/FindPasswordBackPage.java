package com.tingyun.auto.saas.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.framework.browser.DriverBrowser;
/**
 * @version: 2015-7-15   下午 15:51:16
 * @author: mabingxue
 * @decription: 找回密码的两种方式
 */
public class FindPasswordBackPage extends CommonPage {

	public FindPasswordBackPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
  /**
   * common page
   */
	
	@FindBy(xpath="//div[text()='下一步']")
	public static WebElement xpathNextStep;   //下一步
	
	
	@FindBy(id="inputRandom")
	public static WebElement idSecurityCode;   //验证码
	/**
	 * 手机找回密码
	 */
	@FindBy(xpath="//input[@value='phone']")
	public WebElement xpathPhChecked;  //选择手机找回方式
	
	@FindBy(id="inputPhone")
	public WebElement idPhoneNum;  //手机号码
	
	@FindBy(id="btnPhone")
	public WebElement idPSendvericode;  //发送验证码
	
	/**
	 * 邮箱找回密码
	 */
	@FindBy(xpath="//input[@value='email']")
	public WebElement xpathEmChecked;   //选择邮件找回方式
	
	@FindBy(id="inputEmail")    
	public WebElement idEmailAddres; //邮箱地址
	
	@FindBy(id="btnEmail")
	public WebElement idESendvericode;  //发送验证码
	/**
	 * common ways
	 */
	public static void SendCodeAndNextStep(){
        //单击输入验证码
		driverBrowser.sendKeys(idSecurityCode, "");
		driverBrowser.pause(1000);
		//driverBrowser.sendKeys(idSecurityCode, getRadisKey(readValue("redisPhoneKey"),phone));
		//点击下一步
		driverBrowser.click(xpathNextStep);
		driverBrowser.pause(1000);
}
	/**
	 * 手机找回密码的方法
	 */
	public void PhFindPassword(){
		//点击手机找回
		driverBrowser.click(xpathPhChecked);
		driverBrowser.pause(1000);
		//输入手机号码
		driverBrowser.sendKeys(idPhoneNum, "");
		driverBrowser.pause(1000);
		//单击发送验证码
		driverBrowser.click(idPSendvericode);
		driverBrowser.pause(1000);
		FindPasswordBackPage.SendCodeAndNextStep();
	}
	public void EmFindPassword(){
		//点击邮箱找回
		driverBrowser.click(xpathEmChecked);
		driverBrowser.pause(1000);
		//输入邮箱地址
		driverBrowser.sendKeys(idEmailAddres, "");
		driverBrowser.pause(1000);
		//单击发送验证码
		driverBrowser.click(idESendvericode);
		driverBrowser.pause(1000);
		FindPasswordBackPage.SendCodeAndNextStep();
	}
}
