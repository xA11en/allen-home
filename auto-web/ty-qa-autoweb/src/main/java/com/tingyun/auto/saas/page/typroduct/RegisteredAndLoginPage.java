package com.tingyun.auto.saas.page.typroduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.saas.page.CommonPage;
import com.tingyun.auto.utils.StrAndDateUtil;
/**
* @author :chenjingli 
* @version ：2015-6-30 上午11:23:37 
* @decription:  regist and login page
 */
public class RegisteredAndLoginPage extends CommonPage {

	public RegisteredAndLoginPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	/**
	 * 登陆 page
	 */
	@FindBy(linkText="注册")
	public WebElement linCliRegistered;//注册
	
	@FindBy(id="username")
	public WebElement idTypeUsername;//输入用户名
	
	@FindBy(id="password")
	public WebElement idTypePassword;//输入password
	
	@FindBy(xpath="//a[contains(text(),'登')]")
	public WebElement xpathCliLogin;//cli 登陆按钮
	/**
	 * 注册page
	 */
	@FindBy(id="emailId")
	public WebElement idTypeEmail;//注册输入邮箱
	
	@FindBy(id="passwdId")
	public WebElement idTypePwd1;//注册输入pwd
	
	@FindBy(id="passwd2Id")
	public WebElement idTypePwd2;//注册输入确认输入pwd
	
	@FindBy(id="trade")
	public WebElement idSelIndustry;//sel行业
	
	@FindBy(xpath="//*[@id='tradeUl']/ul/li[2]")
	public WebElement xpathCliEleBussiness;// cli 电商购物
	
	@FindBy(id="position")
	public WebElement idSelIndustryJobs;//sel岗位
	
	@FindBy(xpath="//*[@id='positionUl']/ul/li[1]")
	public WebElement xpathCliJob;// cli 研发 或测试工程师
	
	@FindBy(id="mobileId")
	public WebElement idTypeMobleNum;//输入手机号码
	
	@FindBy(id="btnSendMsg")
	public WebElement idCliSendVilCode;//cli发送验证码
	
	@FindBy(id="randomId")
	public WebElement idTypeMessageCode;//输入短信验证码
	
	@FindBy(id="idcType")
	public WebElement idSelIDCType;//sel idc type
	
	@FindBy(xpath="//*[@id='idcTypeUl']/ul/li[1]")
	public WebElement idCliIDCtype;//sel idc alli  cloud
	
	@FindBy(id="btnRegister")
	public WebElement idCliRegistButton;//点击注册按钮
	
	@FindBy(xpath="//span[contains(text(),'恭喜您')]")
	public WebElement boolRegistSuccess;//注册成功判断
	
	/**
	* @author : chenjingli
	* @decription :  saas login
	* @return
	 */
	public void saasLogin(){
		//输入用户名
		driverBrowser.sendKeys(idTypeUsername, "");
		//输入密码
		driverBrowser.sendKeys(idTypePassword,   "" );
		//点击登陆按钮
		driverBrowser.click(xpathCliLogin);
	} 
	
	public void saasRegist(){
		//点击注册按钮
	//	driverBrowser.click(linCliRegistered);
		//切换窗口
		//driverBrowser.selectWindow(driverBrowser.getWebDriver().getWindowHandle());
		//输入邮箱
		String email = StrAndDateUtil.randowEightNumbers(1, 10, 8)+"@qq.com";
		driverBrowser.sendKeys(idTypeEmail,email);
		setRadisKeyValue(readValue("email"), email);
		driverBrowser.pause(1000);
		//输入密码
		driverBrowser.sendKeys(idTypePwd1, readValue("saasLoginPwd"));
		driverBrowser.pause(1000);
		//再次输入密码
		driverBrowser.sendKeys(idTypePwd2, readValue("saasLoginPwd"));
		driverBrowser.pause(1000);
		//点击行业
		driverBrowser.click(idSelIndustry);
		driverBrowser.pause(1000);
		//点击电子商务
		driverBrowser.click(xpathCliEleBussiness);
		driverBrowser.pause(1000);
		//点击选择岗位
		driverBrowser.click(idSelIndustryJobs);
		driverBrowser.pause(1000);
		//点击测试工程师
		driverBrowser.click(xpathCliJob);
		//输入手机号
		String phone =  "131"+StrAndDateUtil.randowEightNumbers(1, 10, 8);
		driverBrowser.sendKeys(idTypeMobleNum,phone);
		driverBrowser.pause(1000);
		//点击发送验证
		driverBrowser.click(idCliSendVilCode);
		driverBrowser.pause(1000);
		//输入短信验证
		driverBrowser.sendKeys(idTypeMessageCode, getRadisKey(readValue("redisPhoneKey"),phone));
		driverBrowser.pause(1000);
		//点击idc类型
		driverBrowser.click(idSelIDCType);
		//点击阿里云
		driverBrowser.click(idCliIDCtype);
		driverBrowser.pause(1000);
		//点击注册按钮
		driverBrowser.click(idCliRegistButton);
		driverBrowser.pause(1000);
		Assert.assertTrue(boolRegistSuccess.isDisplayed());
	}
	
}
