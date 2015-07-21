package com.tingyun.auto.saas.page.typroduct;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.saas.page.CommonPage;
import com.tingyun.auto.utils.StrAndDateUtil;
/**
 * @version: 2015-7-15 下午 17:28:20
 * @author: mabingxue
 * @decription: 咨询与反馈
 */
public class ConsultationAndFeedbackPage extends CommonPage {
	private static Logger logger = LoggerFactory.getLogger(ConsultationAndFeedbackPage.class);
	
	public ConsultationAndFeedbackPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	/**
	 * @version: 2015-7-16 上午 09:37:30
	 * @author: mabingxue
	 * @decription: 咨询与反馈
	 */
	
	@FindBy(xpath="//div[text()='新建咨询 & 反馈']")
	public WebElement xpathCliGoAndAsk;      //新建咨询 & 反馈
	
	@FindBy(id="userTrade")
	public WebElement idTypeCliSelected;  //请选择类型
   
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
	 
	@FindBy(xpath="//tr[@class='feedback_tab1_tr1']/following-sibling::tr")
	private List<WebElement> xpathCell;   //验证是否成功删除
	/**
	 * 咨询与反馈的新建、查看、删除咨询的测试用例
	 */
	public void saasConsultationAndFeedback(){
		//点击咨询与反馈
		driverBrowser.click(linCliFeedback);
		driverBrowser.pause(1000);
		//点击新建咨询 & 反馈
		driverBrowser.click(xpathCliGoAndAsk);
		driverBrowser.pause(1000);
		//点击请选择类型
		driverBrowser.click(idTypeCliSelected);
		driverBrowser.pause(1000);
		//选择问题
		String n = StrAndDateUtil.randowNumbers(1, 7, 1);
		String tmp= "//*[@id='userTradeUl']/li["+n+"]";
		DriverBrowser.getWebDriver().findElement(By.xpath(tmp)).click();
		driverBrowser.pause(1000);
	     
		//输入问题描述
		String text = "test咨询"+StrAndDateUtil.randowNumbers(1, 10, 8);
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
		int cellNum1 = driverBrowser.getElementNums(xpathCell);
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
		
		int cellNum2 = driverBrowser.getElementNums(xpathCell);
		if(cellNum1<=1){
		Assert.assertEquals(cellNum1-1,cellNum2);
		logger.info("新建问题后cell的数量cellNum1={},删除问题后表格的数量cellNum2={}",cellNum1,cellNum2);
		}else{
			Assert.assertEquals(cellNum1-1,cellNum2);
		  logger.info("新建问题后cell的数量cellNum1={},删除问题后tr标签的数量cellNum2={}",cellNum1,cellNum2);
		}
	}
	}

