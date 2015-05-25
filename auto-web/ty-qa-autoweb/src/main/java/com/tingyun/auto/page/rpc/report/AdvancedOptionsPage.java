package com.tingyun.auto.page.rpc.report;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.page.GlobalPage;
import com.tingyun.auto.page.rpc.RpcIndexPage;

/**
* @author :chenjingli 
* @version ：2015-5-19 上午11:42:16 
* @decription: 报表高级选项page
 */
public class AdvancedOptionsPage extends GlobalPage {
	
	
	public AdvancedOptionsPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	public static StringBuffer sb = new StringBuffer();
	/**
	 * 高级选项之---时间匹配模式开始***********************************************
	 */
	@FindBy(id="allHours2")
	public WebElement idCliHours;//选则具体小时
	
	@FindBy(id="allHours1")
	public WebElement idCliAllHour;//选则全部小时
	
	@FindBy(id="hourFrom")
	public WebElement idSelHour;//选择10:00
	
	@FindBy(id="dayFrom")
	public WebElement idSelDay;//选择具体天 星期三
	
	/**
	 * 显示及排序选项开始*************************************************
	 */
	@FindBy(id="sortBy2")
	public WebElement idCliProvince;//选则省
	
	@FindBy(id="sortAscendant1")
	public WebElement idCliSortZheng;//选择性能正排序
	
	/**
	 * 运营商选项开始*************************************************
	 */
	@FindBy(id="byIsp")
	public WebElement idSelOperator;//选择运营商下拉框
	
	@FindBy(name="ispId")
	public WebElement idSelAnOperator;//选择运营商下拉框
	
	/**
	 * 按性能过滤 选项开始*************************************************
	 */
	@FindBy(id="trimMode1")
	public WebElement idCliClose;//点击关闭按钮
	
	@FindBy(id="autoTrimMode")
	public WebElement idSelAuto;//选择自动按图标维度
	
	@FindBy(id="trimMode3")
	public WebElement idCliManual ;//选择手动
	
	@FindBy(xpath="//*[@id='trimOption']/descendant::input[9]")
	public WebElement xpathTypeSeconds ;//输入多少秒
	
	/**
	 * 按监测节点IP/ID过滤选项开始*************************************************
	 */
	@FindBy(id="probeIpFilterBy")
	public WebElement idSelDetectionNode;//点击关闭按钮
	
	@FindBy(id="probeIpFilterMode1")
	public WebElement idCliContaisRadio;//选择包含的IP
	
	@FindBy(id="probeIpFilterMode2")
	public WebElement idCliRuleOutRadio;//选择排除的IP
	
	@FindBy(id="probeIp")
	public WebElement idTypeIpAdress ;//输入ip地址
	
	/**
	 * 按目标主机IP过滤过滤选项开始*************************************************
	 */
//	@FindBy(id="probeIpFilterBy")
//	public WebElement idSelDetectionNode;//点击关闭按钮
	
	/**
	 * 监测节点排除选项开始*************************************************
	 */
	@FindBy(xpath="//*[@id='probeTrim2Option']/descendant::li[1]/input[1]")
	public WebElement xpathCliOutCpu;//点击排除cpu使用率
	
	@FindBy(xpath="//*[@id='probeTrim2Option']/descendant::li[1]/input[2]")
	public WebElement xpathTypeCpu;//输入cpu使用率
	
	@FindBy(xpath="//*[@id='probeTrim2Option']/descendant::li[2]/input[1]")
	public WebElement xpathCliOutMemory;//点击排除内存使用率
	
	@FindBy(xpath="//*[@id='probeTrim2Option']/descendant::li[2]/input[2]")
	public WebElement xpathTypeMemory ;//输入内存使用率
	
	@FindBy(xpath="//*[@id='probeTrim2Option']/descendant::li[3]/input[1]")
	public WebElement xpathCliAvgDownLoad;//点击平均下载速度
	
	@FindBy(xpath="//*[@id='probeTrim2Option']/descendant::li[3]/input[2]")
	public WebElement xpathTypeDownKB ;//输入多少Kb一下
	
	@FindBy(xpath="//*[@id='probeTrim2Option']/descendant::li[3]/input[3]")
	public WebElement xpathTypeUpKB ;//输入多少Kb以上
	
	
	/**
	 * 错误排除选项开始*************************************************
	 */
	@FindBy(id="countCEAsPEId")
	public WebElement idCliErrorType;//点击排除下列错误类型
	
	@FindBy(id="trimErrorCodeListCK")
	public WebElement idCliAllChose;//点击全选按钮
	
	@FindBy(xpath="//*[@id='otherOption']/descendant::input[3]")
	public WebElement xpathCliRight;//点击右拉箭头按钮
	
	@FindBy(xpath="//*[@id='otherOption']/descendant::input[4]")
	public WebElement xpathCliLeft ;//点击←拉箭头按钮
	
	@FindBy(xpath="trimErrorCodeCK")
	public WebElement idCliRightAllChose;//点击右全选
	

	/**
	 * 操作系统选项开始*************************************************
	 */
	@FindBy(xpath="//*[@id='osOption']/descendant::input[4]")
	public WebElement xpathCliAllChose;//点击全选按钮
	
	@FindBy(xpath="//*[@id='osOption']/descendant::select/option[1]")
	public WebElement idCliAndroid;//点击ios
	
	@FindBy(xpath="//*[@id='osOption']/descendant::input[1]")
	public WebElement xpathCliRightSystem;//点击右拉箭头按钮
	
	/**
	 * 浏览器选项开始*************************************************
	 */
	@FindBy(xpath="//*[@id='bsVerOption']/descendant::input[4]")
	public WebElement xpathCliAllChoseLiu;//点击全选按钮
	
	@FindBy(xpath="//*[@id='bsVerOption']/descendant::select/option[1]")
	public WebElement idCliIE11;//点击ie11
	
	@FindBy(xpath="//*[@id='bsVerOption']/descendant::input[1]")
	public WebElement xpathCliRightLiu;//点击右拉箭头按钮
	
	//**********************************************************
	
	@FindBy(xpath="//*[@id='overlayer_advopt']/div[3]/input[1]")
	public WebElement xpathCliSure;//点击确定
	
	@FindBy(xpath="//*[@id='chinamap']/embed")
	public WebElement booleanMapIsExist;//用来判断地图是否出现
	
	@FindBy(xpath="//*[@id='btnOverlayAdvance']/a")
	public WebElement xpathCliAdvancedOptions ;//点击高级选项
	/**
	 * 高级选项循环提交
	 */
	public void highOption(){
		int count=0;
		int count1 = 0;
		for (int i = 0; i < 4; i++) {
			if(i==0){
				System.out.println("时间匹配模式选项===》click{全部小时，全部天}");
				sb.append("时间匹配模式选项===》click{全部小时，全部天}");
			}
			if(i==1){
				System.out.println("时间匹配模式选项===》select{10:00，全部天}");
				sb.append("时间匹配模式选项===》select{10:00，全部天}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelHour, "10","value");
			}
			if(i==2){
				System.out.println("时间匹配模式选项===》select{10:0}");
				sb.append("时间匹配模式选项===》select{10:00，星期三}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelHour, "10","value");
				driverBrowser.pause(1);
				System.out.println("时间匹配模式选项===》select{星期三}");
				driverBrowser.select(idSelDay, "4","value");
			}
			if(i==3){
				System.out.println("时间匹配模式选项===》select{全部小时，星期三}");
				sb.append("时间匹配模式选项===》select{全部小时，星期三}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelDay, "4","value");
			}
			driverBrowser.pause(2);
			for (int j = 0; j < 3; j++) {
				if(j==0){
					System.out.println("显示及排序选项===》click{性能，倒排序}");
					sb.append("显示及排序选项===》click{性能，倒排序}");
				}
				if(j==1){
					System.out.println("显示及排序选项===》click{性能，正排序}");
					sb.append("显示及排序选项===》click{性能，正排序}");
					driverBrowser.pause(1);
					driverBrowser.click(idCliSortZheng);
				}
				if(j==2000){
					System.out.println("显示及排序选项===》click{省}");
					sb.append("显示及排序选项===》click{省}");
					driverBrowser.pause(1);
					driverBrowser.click(idCliProvince);
				}
				driverBrowser.pause(2000);
				for (int k = 0; k < 4; k++) {
					if(k==0){
						System.out.println("运营商选项===》select{全部}");
						sb.append("运营商选项===》select{全部}");
					}
					if(k==1){
						System.out.println("运营商选项===》select{节点组中的运营商}");
						sb.append("运营商选项===》select{节点组中的运营商}");
						driverBrowser.pause(1);
						driverBrowser.select(idSelOperator, "0","value");
					}
					if(k==2){
						System.out.println("运营商选项===》select{主要网络运营商}");
						sb.append("运营商选项===》select{主要网络运营商}");
						driverBrowser.pause(1);
						driverBrowser.select(idSelOperator, "2","value");
					}
					if(k==3){
						System.out.println("运营商选项===》select{主要网络运营商}");
						sb.append("运营商选项===》select{主要网络运营商}");
						driverBrowser.pause(2000);
						driverBrowser.select(idSelOperator, "-1","value");
						driverBrowser.pause(1);
						driverBrowser.select(idSelAnOperator, "16","value");
					}
					driverBrowser.pause(2);
					for (int l = 0; l < 4; l++) {
						if(l==0){
							System.out.println("运营商选项===》select{主要网络运营商}");
							sb.append("按性能过滤选项===》select{自动，按图标维度}");
						}
						if(l==1){
							System.out.println("按性能过滤选项===》select{自动，按总体}");
							sb.append("按性能过滤选项===》select{自动，按总体}");
							driverBrowser.pause(1);
							driverBrowser.select(idSelAuto, "0","value");
						}
						if(l==2){
							System.out.println("按性能过滤选项===》click{手动}");
							sb.append("按性能过滤选项===》click{手动}");
							driverBrowser.pause(2000);
							driverBrowser.click(idCliManual);
							driverBrowser.pause(1);
							System.out.println("按性能过滤选项===》send{'10'秒以下的监测数据 }");
							driverBrowser.sendKeys(xpathTypeSeconds,"10");
						}
						if(l==3){
							System.out.println("按性能过滤选项===》click{关闭}");
							sb.append("按性能过滤选项===》click{关闭}");
							driverBrowser.pause(1);
							driverBrowser.click(idCliClose);
						}
						driverBrowser.pause(2000);
						//***********************后续加东西
						for (int h = 0; h < 1; h++) {
							if(h==0){
								System.out.println("按监测节点IP/ID过滤选项===》select{*IP*}");
								sb.append("按监测节点IP/ID过滤选项===》select{*IP*}");
							}
							driverBrowser.pause(2000);
							//***********************后续加东西
							for (int m = 0; m < 1; m++) {
								if(m==0){
									System.out.println("按目标主机IP过滤选项===》select{*全部*}");
									sb.append("按目标主机IP过滤选项===》select{*全部*}");
								}
								driverBrowser.pause(2000);
								for (int g = 0; g <3 ; g++) {
									if(g==0){
										System.out.println("监测节点排除选项===》click{排除平均CPU使用率}");
										sb.append("监测节点排除选项===》click{排除平均CPU使用率超过'10'%的监测点}");
										if(!xpathCliOutCpu.isSelected()){
											driverBrowser.click(xpathCliOutCpu);
											driverBrowser.pause(1000);
											System.out.println("监测节点排除选项===》send{超过'10'%的监测点}");
											driverBrowser.sendKeys(xpathTypeCpu, "10");
										}else{
											driverBrowser.click(xpathCliOutCpu);
											driverBrowser.pause(1000);
											driverBrowser.click(xpathCliOutCpu);
										}
									}
									if(g==1){
										System.out.println("监测节点排除选项===》click{排除平均内存使用率超过}");
										sb.append("监测节点排除选项===》click{排除平均内存使用率超过'10'%的监测点}");
										driverBrowser.pause(2000);
										if(!xpathCliOutMemory.isSelected()){
											driverBrowser.click(xpathCliOutMemory);
											driverBrowser.pause(1000);
											System.out.println("监测节点排除选项===》send{超过'10'%的监测点}");
											driverBrowser.sendKeys(xpathTypeMemory, "10");
										}else{
											driverBrowser.click(xpathCliOutMemory);
											driverBrowser.pause(1);
											driverBrowser.click(xpathCliOutMemory);
										}
									}
									if(g==2){
										System.out.println("监测节点排除选项===》click{排除平均下载速度在'10'KB/s以下或'60'KB/s以上的节点}");
										sb.append("监测节点排除选项===》click{排除平均下载速度在'10'KB/s以下或'60'KB/s以上的节点}");
										driverBrowser.pause(2000);
										if(!xpathCliAvgDownLoad.isSelected()){
											driverBrowser.click(xpathCliAvgDownLoad);
											driverBrowser.pause(2000);
											System.out.println("监测节点排除选项===》send{'10'KB/s以下}");
											driverBrowser.sendKeys(xpathTypeDownKB, "10");
											driverBrowser.pause(1);
											System.out.println("监测节点排除选项===》send{60'KB/s以上的节点}");
											driverBrowser.sendKeys(xpathTypeUpKB, "60");
										}else{
											driverBrowser.click(xpathCliAvgDownLoad);
											driverBrowser.pause(1);
											driverBrowser.click(xpathCliAvgDownLoad);
										}
									}
									driverBrowser.pause(2000);
									for (int n = 0; n < 3; n++) {
										if(n==0){
											System.out.println("错误排除选项===》click{默认点击：排除下列错误类型}");
											sb.append("错误排除选项===》click{默认点击：排除下列错误类型}");
										}
										if(n==1){
											System.out.println("错误排除选项===》click{左侧全选按钮，点击→}");
											sb.append("错误排除选项===》click{左侧全选按钮，点击→}");
											driverBrowser.pause(2000);
											driverBrowser.click(idCliAllChose);
											driverBrowser.pause(1000);
											driverBrowser.click(xpathCliRight);
										}
										if(n==2){
											System.out.println("错误排除选项===》click{右侧全选按钮，点击←}");
											sb.append("错误排除选项===》click{右侧全选按钮，点击←}");
											driverBrowser.pause(2000);
											driverBrowser.click(idCliAllChose);
											driverBrowser.pause(1);
											driverBrowser.click(xpathCliLeft);
										}
										driverBrowser.pause(2000);
										for (int p = 0; p < 2; p++) {
											if(p==0){
												System.out.println("操作系统选项===》click{全部操作系统}click{→}");
												sb.append("操作系统选项===》click{全部操作系统}click{→}");
												driverBrowser.pause(2);
												driverBrowser.click(xpathCliAllChose);
												driverBrowser.pause(1);
												driverBrowser.click(xpathCliRightSystem);
											}
											if(p==1){
												System.out.println("操作系统选项===》click{指定操作系统 Android}");
												sb.append("操作系统选项===》click{指定操作系统 Android}");
												driverBrowser.pause(2000);
												driverBrowser.click(idCliAndroid);
												driverBrowser.pause(1);
												driverBrowser.click(idCliAndroid);
											}
											driverBrowser.pause(2000);
											for (int u = 0; u < 2; u++) {
												if(u==0){
													System.out.println("浏览器选项===》click{全部浏览器}click{→}");
													sb.append("浏览器选项===》click{全部浏览器}click{→}");
													driverBrowser.pause(2);
													driverBrowser.click(xpathCliAllChoseLiu);
													driverBrowser.pause(1);
													driverBrowser.click(xpathCliRightLiu);
												}
												if(u==1){
													System.out.println("浏览器选项===》click{指定浏览器 ie11}");
													sb.append("浏览器选项===》click{指定浏览器 ie11}");
													driverBrowser.pause(2);
													driverBrowser.click(idCliIE11);
													driverBrowser.pause(1);
													driverBrowser.click(idCliIE11);
												}
												try{
													System.out
													.println("点击提交按钮"+count++); 
//													count = i*j*k*l*m*h*g*n*p*u;
//													logger.info("高级选项一共循环了{}次",count);
													driverBrowser.pause(2);
//													System.out
//															.println("点击提交按钮"); 
													driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='overlayer_advopt']/div[3]/input[1]")).click();//点击提交的确定按钮
													System.out
															.println("判断地图");
													driverBrowser.pause(3000);
													assertEquals(true, booleanMapIsExist.isDisplayed());
													System.out.println("pass");
													driverBrowser.pause(2000);
													System.out.println("开始点击高级选项");
													driverBrowser.click(xpathCliAdvancedOptions);//点击高级选项
												}catch (Exception e1) {
//													count1 = count++;
//													count1++;
													Reporter.log("/n"+sb.toString()+"失败");
													sb.delete(0, sb.length());
//													if(count==count1){
//													fail("高级选项提交后地图没有出现");
//												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
}