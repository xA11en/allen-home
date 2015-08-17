package com.tingyun.auto.saas.page.typroduct;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.saas.page.CommonPage;
import com.tingyun.auto.utils.StrAndDateUtil;

/**
* @author :chenjingli 
* @version ：2015-7-14 下午4:46:35 
* @decription: 产品价格 page
 */
public class ProductPricePage extends CommonPage {
	
	private static Logger logger = LoggerFactory.getLogger(ProductPricePage.class);
	
	public ProductPricePage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	/**
	 * 购买信息填写 div
	 */
	@FindBy(xpath="//div[contains(text(),'立即购买')]")
	public WebElement xpathCliBuy;//产品价格page 点击立即购买
	
	@FindBy(name="name")
	public WebElement nameType;//输入姓名
	
	@FindBy(name="appName")
	public WebElement nameTypeAppName;//输入app name
	
	@FindBy(name="mobile")
	public WebElement nameTypeMobile;//输入 mobile

	@FindBy(name="companyName")
	public WebElement nameTypeCompanyName;//输入 companyName
	
	@FindBy(name="companyEmail")
	public WebElement nameTypeCompanyEmail;//输入 companyEmail
	
	@FindBy(name="companyWeb")
	public WebElement nameTypeCompanyWeb;//输入 companyWeb
	
	@FindBy(id="pro_agreement")
	public WebElement idCliAgreement;//点击 同意
	
	@FindBy(id="btn_submit")
	public WebElement idCliConfirmButton;//点击 确认按钮
	
	
	/**
	 * 续费page
	 */
	@FindBy(xpath="//div[@class='fuwu_list_on']/descendant::div[contains(text(),'续费')][1]")
	public WebElement classCliRenewalButton;//点击 续费按钮
	
	@FindBy(xpath="//div[@class='fuwu_list_on']/descendant::p[3]")
	public WebElement xpathGetStartAndEndTime;//获得开通和结束时间
	
	@FindBy(id="endDate")
	public WebElement idGetEndTime;//获取结束时间
	
	@FindBy(xpath="//div[contains(text(),'去结算')]")
	public WebElement xpathCliToPay;//默认5个月去结算
	/**
	 * 购物车页page
	 */
	@FindBy(id="checkOutBtn")
	public WebElement idCliGoPayButton;//点击 去结算按钮
	
	/**
	 * 支付page
	 */
	@FindBy(xpath="//*[@id='centerleft']/div/div/p[1]")
	public WebElement xpathGetPayOrderNum;//获取订单号
	/**
	 * app购买小薇版服务
	 */
	public void appBuyXiaoWeiService(){
		//产品价格 点击 立即购买
		driverBrowser.click(xpathCliBuy);
		//输入姓名
		driverBrowser.sendKeys(nameType, "testName");
		//输入appName
		driverBrowser.sendKeys(nameTypeAppName, "testAppName");
		//输入moble number
		String phone =  "131"+StrAndDateUtil.randowNumbers(1, 10, 8);
		driverBrowser.sendKeys(nameTypeMobile, phone);
		//输入公司名称
		driverBrowser.sendKeys(nameTypeCompanyName, "testCompanyName"+StrAndDateUtil.randowNumbers(1, 10, 5));
		//输入email
		String email = StrAndDateUtil.randowNumbers(1, 10, 8)+"@qq.com";
		driverBrowser.sendKeys(nameTypeCompanyEmail,email);
		//输入公司web
		driverBrowser.sendKeys(nameTypeCompanyWeb, "www.test.com");
		//点击同意 radio
		driverBrowser.click(idCliAgreement);
		//点击确定按钮
		driverBrowser.click(idCliConfirmButton);
		//默认5个月 点击去结算按钮
		driverBrowser.pause(1000);
		driverBrowser.click(idCliGoPayButton);
		this.payMethod();
	}
	
	/**
	 * app续费 
	 */
	public void appRenewal(){
		//点击续费按钮
		driverBrowser.click(classCliRenewalButton);
		//获得结束日期
		String endTime = idGetEndTime.getText();
		logger.info("获得的服务续费结束的日期是：{}",endTime);
		//点击去结算
		driverBrowser.click(xpathCliToPay);
		this.payMethod();
		driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/userService/myproduct");
		//获得开通和结束时间段
		String[] timeStrings = xpathGetStartAndEndTime.getText().split(" - ")[1].trim().replace("/", "-").split("-");
		StringBuilder sb = new StringBuilder();
		sb.append(timeStrings[0]);
		if(timeStrings[1].startsWith("0")){
			sb.append("-");
			sb.append(timeStrings[1].substring(1, 2));
		}else{
			sb.append("-");
			sb.append(timeStrings[1]);
		}
		if(timeStrings[2].startsWith("0")){
			sb.append("-");
			sb.append(timeStrings[2].substring(1, 2));
		}else{
			sb.append("-");
			sb.append(timeStrings[2]);
		}
		driverBrowser.pause(1000);
		Assert.assertEquals(sb.toString(), endTime);
	}
	
	
	/**
	 * 公共支付方法 购买和续费通用
	 * 获取订单号 ordernumber 准备MD5加密数据 调用encryption（）方法生成md5加密串 然后输入支付ulr回车完成支付
	 */
	void payMethod(){
		//获取订单号
				String orderNumber = StrAndDateUtil.getTextNum(xpathGetPayOrderNum.getText());
				String md5Datas = "buyer_email=994406030@qq.com&" +
						"buyer_id=2088212410355294&exterface=create_direct_pay_by_user&" +
						"is_success=T&notify_time=2015-04-28 11:18:46&" +
						"notify_type=trade_status_sync&" +
						"out_trade_no="+orderNumber+"&" +
						"payment_type=1&seller_email=wangmy@networkbench.com&" +
						"seller_id=2088501946018790&subject=tingyun&" +
						"total_fee=0.10&trade_no=2014061264139729&" +
						"trade_status=TRADE_SUCCESSfznbcgg49s9n6ge1vt7m8tkd41d3fmb0";
				String md5Strings = StrAndDateUtil.encryption(md5Datas);
				logger.info("利用订单号={}生成的md5加密串是：{}",orderNumber,md5Strings);
				String payUrl = "alipay/alipayReturnUrl?buyer_email=994406030%40qq.com&" +
						"buyer_id=2088212410355294&exterface=create_direct_pay_by_user&" +
						"is_success=T&notify_time=2015-04-28+11%3A18%3A46&notify_type=trade_status_sync&" +
						"out_trade_no="+orderNumber+"&payment_type=1&seller_email=wangmy%40networkbench.com&" +
						"seller_id=2088501946018790&subject=tingyun&total_fee=0.10&trade_no=2014061264139729&" +
						"trade_status=TRADE_SUCCESS&" +
						"sign="+md5Strings+"&sign_type=MD5";
				driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/"+payUrl);
				//模拟回车支付
//				Robot robot;
//				try {
//					robot = new Robot();
//					robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
//				} catch (AWTException e) {
//					e.printStackTrace();
//				}
				
				driverBrowser.pause(1000);
				Assert.assertTrue(xpathBoolOpenIsSuccess.isDisplayed());
	}
	
}
