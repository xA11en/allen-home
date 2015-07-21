package com.tingyun.auto.saas.page.typroduct;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.saas.page.RegisteredAndLoginPage;
import com.tingyun.auto.utils.StrAndDateUtil;

/**
* @author :chenjingli 
* @version ：2015-7-16 下午2:48:40 
* @decription: saas-账号管理（增加，删除子账号）
 */
public class AccountManagementPage extends RegisteredAndLoginPage {
	
	static Logger logger = LoggerFactory.getLogger(AccountManagementPage.class);
	static final String REGIST_EMAIL= "ZiregistEmail";
	static final String ZI_ACCOUNUT_NAME="子账号"+StrAndDateUtil.randowNumbers(1, 10, 5);
	public AccountManagementPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	/**
	 * 子账号增加page
	 */
	@FindBy(xpath="//a[contains(text(),'创建')]")
	public WebElement xpathCliCreate;//判断或者点击“立即创建按钮是否存在”
	
	@FindBy(id="userName")
	public WebElement idTypeUserName;//输入用户名称
	
	@FindBy(id="userEmail")
	public WebElement idTypeUserEmail;//输入userEmail
	
	@FindBy(id="userMobile")
	public WebElement idTypeUserMobile;//输入userMobile
	
	@FindBy(id="roleImg")
	public WebElement idSelectRoleImg;//选择roleImg
	
	@FindBy(xpath="//*[@id='roleUl']/li[1]")
	public WebElement xpathSelAppAdmin;//输入应用管理员
	
	@FindBy(xpath="//*[@id='roleUl']/li[2]")
	public WebElement xpathSelReportAdmin;//选择报表管理员
	
	@FindBy(id="pwd")
	public WebElement idTypePwd;//输入pwd
	
	@FindBy(id="pwdConfirm")
	public WebElement idTypePwdConfirm;//输入pwdConfirm
	
	@FindBy(id="userTradeImg")
	public WebElement idSelsndustry;//输入行业
	
	@FindBy(xpath="//*[@id='userTradeUl']/li[1]")
	public WebElement xpathSelTourismTravel;//输入行业  旅游出行
	
	@FindBy(id="userDepartment")
	public WebElement idTypeUserDepartment;//输入旅游行业
	
	@FindBy(id="positionImg")
	public WebElement idSelJobs;//选择岗位
	
	@FindBy(xpath="//*[@id='positionUl']/li[1]")
	public WebElement idSelJobTest;//选择测试研发工程师
	
	@FindBy(id="company")
	public WebElement idTypeCompanyName;//输入公司name
	
	@FindBy(id="companyAddress")
	public WebElement idTypeCompanyAddress;//输入公司地址
	
	@FindBy(xpath="//div[contains(text(),'保存')]")
	public WebElement xpathCliSave;//点击保存按钮
	
	/**
	 * 子账号生成后的列表page
	 */
	@FindBy(xpath="//table/descendant::tr[1]/td[2]/a")
	public List<WebElement> xpathGetNames;//获得子账号姓名列表
	
	@FindBy(id="addBtn")
	public WebElement idCliCreateZiAccount;//点击 “创建子账号按钮”
	
	@FindBy(xpath="//*[@id='page_1']/div[1]/descendant::a[3]")
	public WebElement xpathCliDelFirstAccount;//删除第一个账号 点击第一个删除按钮

	@FindBy(xpath="//*[@id='page_1']/div[1]/descendant::a[2]")
	public WebElement xpathEditFirstAccount;//编辑第一个账号 编辑第一个删除按钮
	
	@FindBy(xpath="//*[@id='page_1']/div[1]/descendant::a[1]")
	public WebElement xpathGetFirstAccountName;//获得第一个account name
	/**
	 * 权限相关的元素page
	 */
	@FindBy(xpath="//*[@id='page_1']/div[1]//a[text()='权限']")
	public WebElement xpathCliRight;//权限
	
	@FindBy(xpath="//*[@id='rumselect_1_rumselect_left_1_check']")
	public WebElement xpathCliBox;//选择应用
	
	@FindBy(className="rumselect-to-right-button")
	public WebElement CliSelectRightBut;//分配应用
	
	@FindBy(xpath="//*[@id='filter_ok']")
	public WebElement xpathCliSSave;//保存
	
	@FindBy(linkText="自动化saas测试专用授权测试数据1勿删")
	public WebElement AssertText;//验证元素
	/**
	 *  子账号增加 测试用例
	 */
	public void addZiAccountName(){
		//点击账号管理
		driverBrowser.click(linCliAccountManager);
		//判断是否已经有账号列表
		try{
			if(xpathCliCreate.isDisplayed()){
				driverBrowser.click(xpathCliCreate);
			}
		}catch(Exception e){
			driverBrowser.click(idCliCreateZiAccount);
		}
		//输入姓名
		driverBrowser.sendKeys(idTypeUserName,ZI_ACCOUNUT_NAME );
		//输入邮箱
		String email = StrAndDateUtil.randowNumbers(1, 10, 8)+"@qq.com";
		driverBrowser.sendKeys(idTypeUserEmail, email);
		
		logger.info("随机生成的子账号名称为-----》：{}",ZI_ACCOUNUT_NAME);
		//邮箱存入redis
		setRadisKeyValue(REGIST_EMAIL, email);
		//输入手机
		driverBrowser.sendKeys(idTypeUserMobile, "131"+StrAndDateUtil.randowNumbers(1, 10, 8));
		//选择角色
		driverBrowser.click(idSelectRoleImg);
		//点击选择的角色
		driverBrowser.click(xpathSelReportAdmin);
		//输入登陆密码
		driverBrowser.sendKeys(idTypePwd, readValue("saasLoginPwd"));
		//再次输入登陆密码
		driverBrowser.sendKeys(idTypePwdConfirm, readValue("saasLoginPwd"));
		//选择行业
		driverBrowser.click(idSelsndustry);
		//选择旅游出行
		driverBrowser.click(xpathSelTourismTravel);
		//输入部门
		driverBrowser.sendKeys(idTypeUserDepartment, "部门"+StrAndDateUtil.randowNumbers(1, 10, 8));
		//选择岗位
		driverBrowser.click(idSelJobs);
		//选择研发测试岗位
		driverBrowser.click(idSelJobTest);
		//输入公司名称
		driverBrowser.sendKeys(idTypeCompanyName,"公司名称"+StrAndDateUtil.randowNumbers(1, 10, 8));
		//输入公司地址
		driverBrowser.sendKeys(idTypeCompanyAddress, "公司地址"+StrAndDateUtil.randowNumbers(1, 10, 8));
		//点击保存
		driverBrowser.click(xpathCliSave);
		driverBrowser.pause(1500);
		driverBrowser.delCookie("username");
		driverBrowser.delCookie("password");
		//进入iframe
		driverBrowser.enterFrame(xpathIntoIframe);
		//点击退出
		driverBrowser.click(xpathCliLoginOut);
		//打开登陆页验证登陆
		driverBrowser.open(readValue("saasLoginURL"));
		this.saasLogin();
	}
	
	/**
	 * 编辑子账号信息
	 */
	public void editZiAccount(){
		//点击第一个子账号 编辑按钮
		driverBrowser.click(xpathEditFirstAccount);
		//输入姓名
		driverBrowser.clear(idTypeUserName);
		driverBrowser.sendKeys(idTypeUserName,ZI_ACCOUNUT_NAME);
		//选择角色
		driverBrowser.click(idSelectRoleImg);
		//点击选择的角色报表查看员
		driverBrowser.click(xpathSelAppAdmin);
		//选择行业
		driverBrowser.click(idSelsndustry);
		//选择旅游出行
		driverBrowser.click(xpathSelTourismTravel);
		//输入部门
		driverBrowser.clear(idTypeUserDepartment);
	    driverBrowser.sendKeys(idTypeUserDepartment, "部门"+StrAndDateUtil.randowNumbers(1, 10, 8));
	    //选择岗位
	  	driverBrowser.click(idSelJobs);
	  	//选择研发测试岗位
	  	driverBrowser.click(idSelJobTest);
	  	//输入公司名称
		driverBrowser.clear(idTypeCompanyName);
	  	driverBrowser.sendKeys(idTypeCompanyName,"公司名称"+StrAndDateUtil.randowNumbers(1, 10, 8));
	  	//输入公司地址
	  	driverBrowser.clear(idTypeCompanyAddress);
	  	driverBrowser.sendKeys(idTypeCompanyAddress, "公司地址"+StrAndDateUtil.randowNumbers(1, 10, 8));
	    //点击保存
	  	driverBrowser.click(xpathCliSave);
	  	driverBrowser.pause(1500);
	  		
	  	Assert.assertEquals(driverBrowser.getPageText(xpathGetFirstAccountName), ZI_ACCOUNUT_NAME);
	}
	/**
	 * 权限分配
	 */
	public void AllocatRight(){
		//点击权限
		driverBrowser.click(xpathCliRight);
		//点击选择方框
		driverBrowser.click(xpathCliBox);
		//点击向右选择
		driverBrowser.click(CliSelectRightBut);
		//点击保存
		driverBrowser.click(xpathCliSSave);
	}
	/**
	 * 删除子账号信息
	 */
	public void delZiAccount(){
		int beforeNumber = driverBrowser.getElementNums(xpathGetNames);
		//点击第一个子账号 删除按钮
		driverBrowser.click(xpathCliDelFirstAccount);
		//cli弹出框中确定
		driverBrowser.confirmAlert(true);
		//again click
		driverBrowser.pause(1500);
		driverBrowser.confirmAlert(true);
		if(beforeNumber ==1){
			Assert.assertEquals(true, xpathCliCreate.isDisplayed());
		}else{
			int afterNumber = driverBrowser.getElementNums(xpathGetNames);
			Assert.assertEquals(beforeNumber-1,afterNumber);
		}
		
	}
	
	/**
	* @author : chenjingli
	* @decription :  saas login
	* @return
	 */
	public void saasLogin(){
		//输入用户名  从redis
		driverBrowser.sendKeys(idTypeUsername, getRadisKey2(REGIST_EMAIL));
		//输入密码
		driverBrowser.sendKeys(idTypePassword, readValue("saasLoginPwd"));
		//点击登陆按钮
		driverBrowser.click(xpathCliLogin);
		//进入iframe
		driverBrowser.enterFrame(xpathIntoIframe);
		//点击退出
		driverBrowser.click(xpathCliLoginOut);
	} 
	
}
