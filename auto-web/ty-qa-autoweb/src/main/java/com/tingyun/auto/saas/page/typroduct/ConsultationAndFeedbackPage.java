package com.tingyun.auto.saas.page.typroduct;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.utils.StrAndDateUtil;
/**
 * @version: 2015-7-15 下午 17:28:20
 * @author: mabingxue
 * @decription: 咨询与反馈
 */
public class ConsultationAndFeedbackPage extends GlobalPage {
	private static Logger logger = LoggerFactory.getLogger(ConsultationAndFeedbackPage.class);
	
	public ConsultationAndFeedbackPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	/**
	 * @version: 2015-7-16 上午 09:37:30
	 * @author: mabingxue
	 * @decription: 咨询与反馈
	 */
	
	@FindBy(xpath="//*[@id='centerleft']/ul/li[5]")
	public WebElement xpathCliConsultAndfeedback;   //咨询与反馈
	
	@FindBy(xpath="//div[text()='新建咨询 & 反馈']")
	public WebElement xpathCliGoAndAsk;      //新建咨询 & 反馈
	
	@FindBy(id="userTrade")
	public WebElement idTypeCliSelected;  //请选择类型
	
	@FindBy(xpath="//*[@id='userTradeUl']/li[3]")
	public WebElement xpathProductProblem;   //选择产品问题
	
	@FindBy(id="content")
	public WebElement idProblemDescription;   //问题描述
	
	@FindBy(xpath="//div[text()='提交']")   //提交问题
	public WebElement xpathSubgmit;
	
	@FindBy(xpath="//*[@id='saveOk']/div[1] ")
	public WebElement Determine;
	
	@FindBy(xpath="//table/descendant::p[1]")    //验证最新的提交问题是否成功提交
	public WebElement xpathAsserText;
	
	@FindBy(xpath="//table/descendant::a[1]")    //点击查看
	public WebElement xpathView;
	
	@FindBy(xpath="//div[@id='saveOk']/following-sibling::div[1]/div[3]")
	public WebElement xpathClose;                 //点击关闭按钮
	
	@FindBy(xpath="//table/descendant::a[2]")
	public WebElement xpathDelete;            //点击删除
	 
	@FindBy(tagName="tr")
	private List<WebElement> tagNameTr;   //验证是否成功删除
	
	public void saasConsultationAndFeedback(){
		//点击咨询与反馈
		driverBrowser.click(xpathCliConsultAndfeedback);
		driverBrowser.pause(1000);
		//点击新建咨询 & 反馈
		driverBrowser.click(xpathCliGoAndAsk);
		driverBrowser.pause(1000);
		//点击请选择类型
		driverBrowser.click(idTypeCliSelected);
		driverBrowser.pause(1000);
		//选择产品问题
		driverBrowser.click(xpathProductProblem);
		driverBrowser.pause(1000);
		//输入问题描述
		String text = "test咨询"+StrAndDateUtil.randowEightNumbers(1, 10, 8);
		driverBrowser.sendKeys(idProblemDescription, text);
		driverBrowser.pause(1000);
		//点击提交
		driverBrowser.click(xpathSubgmit);
		driverBrowser.pause(1000);
		//点击确定
		driverBrowser.click(Determine);
		driverBrowser.pause(1000);
		//验证最新的提交问题是否成功提交
		Assert.assertEquals(text,xpathAsserText.getText());
		//点击查看
		driverBrowser.click(xpathView);
		driverBrowser.pause(1000);
		//点击关闭
		driverBrowser.click(xpathClose);
		driverBrowser.pause(1000);
		int tagNum1 = driverBrowser.getElementNums(tagNameTr);
		//点击删除
		driverBrowser.click(xpathDelete);
		driverBrowser.pause(1000);
		//点击确定
		driverBrowser.confirmAlert(true);
		driverBrowser.pause(1000);
		//点击确定
		driverBrowser.confirmAlert(true);
		driverBrowser.pause(1000);
		//验证是否成功删除
		int tagNum2 = driverBrowser.getElementNums(tagNameTr);
		if(tagNum1<=2){
		Assert.assertEquals(tagNum1-2,tagNum2);
		logger.info("新建问题后tr标签的数量tagNum1={},删除问题后tr标签的数量tagNum2={}",tagNum1,tagNum2);
		}else{
			Assert.assertEquals(tagNum1-1,tagNum2);
		  logger.info("新建问题后tr标签的数量tagNum1={},删除问题后tr标签的数量tagNum2={}",tagNum1,tagNum2);
		}
	}
	}

