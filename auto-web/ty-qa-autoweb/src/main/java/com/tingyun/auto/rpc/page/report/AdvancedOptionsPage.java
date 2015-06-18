package com.tingyun.auto.rpc.page.report;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.tingyun.auto.framework.browser.DriverBrowser;

/**
* @author :chenjingli 
* @version ：2015-5-19 上午11:42:16 
* @decription: 报表高级选项page
 */
public class AdvancedOptionsPage extends CommonPage {
	
	public static Logger logger = LoggerFactory.getLogger(AdvancedOptionsPage.class);
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
	
	@FindBy(xpath="//*[@id='comPerfOption']/descendant::td[3]")
	public WebElement xpathGetSortText;//选则省
	
	@FindBy(id="sortAscendant1")
	public WebElement idCliSortZheng;//选择性能正排序
	
	@FindBy(id="sortBy2")
	public WebElement idCliAble;//选择可用性
	
	@FindBy(id="sortBy3")
	public WebElement idCliYunYingS;//选择按运营商
	
	//显示及排序选项 的性能历史曲线
	@FindBy(id="showPerfValues1")
	public WebElement idCliPerfValues;//选择性能图
	
	@FindBy(id="showAvailValues1")
	public WebElement idCliAvailValues;//选择可用性图图
	
	
	/**
	 * 运营商选项开始*************************************************
	 */
	@FindBy(id="byIsp")
	public WebElement idSelOperator;//选择运营商下拉框
	
	@FindBy(name="ispId")
	public WebElement idSelAnOperator;//选择运营商下拉框
	
	@FindBy(xpath="//*[@id='ispOption']/descendant::select/option[last()-5]")
	public WebElement xpathCliItOper;//选择运营商下拉框
	
	/**
	 * 按性能过滤 选项开始*************************************************
	 */
	@FindBy(id="trimMode1")
	public WebElement idCliClose;//点击关闭按钮
	
	@FindBy(id="autoTrimMode")
	public WebElement idSelAuto;//选择自动按图标维度
	
	@FindBy(id="trimMode2")
	public WebElement idCliAuto ;//选择自动
	
	@FindBy(id="trimMode3")
	public WebElement idCliManual ;//选择手动
	
	@FindBy(id="mannuallyTrimMode")
	public WebElement idSelAutoOut ;//选择手动
	
	@FindBy(xpath="//*[@id='trimOption']/descendant::input[9]")
	public WebElement xpathTypeSeconds ;//输入多少秒
	
	/**
	 * 按监测节点IP/ID过滤选项开始*************************************************
	 */
	@FindBy(id="probeIpFilterBy")
	public WebElement idSelDetectionNode;//选择过滤监测点
	
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
	 * 监测节点排除选项开始*************************************************第二套
	 */
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[1]")
	public WebElement xpathCliOutCpu1;//点击排除cpu使用率
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[2]")
	public WebElement xpathTypeCpu2;//输入cpu使用率
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[3]")
	public WebElement xpathCliAvgMemory;//点击平均内存使用率
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[4]")
	public WebElement xpathTypeAvgMemory ;//输入内存使用率
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[5]")
	public WebElement xpathCliAvgDownLoad2;//点击平均下载速度
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[6]")
	public WebElement xpathCliAvgDownLoadXia ;//输入多少Kb一下
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[7]")
	public WebElement xpathTypeUAvgDownLoadUp ;//输入多少Kb以上
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[8]")
	public WebElement xpathCliTaskOut;//点击排除任务平均下载速度
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[9]")
	public WebElement xpathCliTaskOutXia ;//输入多少Kb一下
	
	@FindBy(xpath="//*[@id='probeTrimOption']/descendant::input[10]")
	public WebElement xpathCliTaskOutUp ;//输入多少Kb以上
	
	
	
	
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
	
	@FindBy(xpath="//*[@id='trimErrorCodeList']/option[1]")
	public WebElement idCliFirst;//点击默认第一个测试数据
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
	
	/**
	 * 时间颗粒度选项
	 */
	@FindBy(id="tmCategorizedBy")
	public WebElement idSelTime;//选择时间粒度
	
	/**
	 * 城市选项
	 */
	@FindBy(xpath="//*[@id='__dselect_tbl_cities']/tbody/tr[1]/td")
	public WebElement xpathCliFirstCity;//城市选项默认选择第一个
	
	@FindBy(xpath="//*[@id='ct_buttons']/ul/li[1]/input")
	public WebElement xpathCliRigth;//城市选项全部城市 →拉
	
	/**
	 * 行业选项
	 */
	@FindBy(xpath="//*[@id='cbcontainer']/div/div[1]/button")
	public WebElement xpathSel;//行业选项默认选择第一个
	
	@FindBy(xpath="//*[@id='cbcontainer']/descendant::li[2]")
	public WebElement xpathCliFirst;//行业选项默认选择第一个
	
	/**
	 * 按特定DNS服务器过滤
	 */
	@FindBy(id="dnsServerIpFilterMode1")
	public WebElement idCliContainsDNS;//点击包含的dns
	
	@FindBy(id="dnsServerIpFilterMode2")
	public WebElement idClioutDNS;//点击排除的dns
	
	@FindBy(id="dnsServerIp")
	public WebElement idSendDnsAdress;//send dns
	
	/**
	 * 显示数据点类型
	 */
	@FindBy(id="showType")
	public WebElement idSelectShowType;//显示数据点类型select
	
	/**
	 * 散点图选项
	 */
	@FindBy(id="showScatterPlot1")
	public WebElement idShowPlot;//点击显示散点图
	
	@FindBy(id="showSmoke1")
	public WebElement idShowSmoke;//点击显示smoke
	
	@FindBy(id="smokeCategorizedBy")
	public WebElement idSelGroup;//select smoke图分组区间
	
	//**********************************************************
	
	@FindBy(xpath="//*[@id='overlayer_advopt']/div[3]/input[1]")
	public WebElement xpathCliSure;//点击确定
	
	@FindBy(xpath="//*[@id='chinamap']/embed")
	public WebElement booleanMapIsExist;//用来判断地图是否出现
	
	@FindBy(css="#taskMap44335 > div.jvectormap-container > svg")
	public WebElement booleanWordMapIsExist;//用来判断世界地图是否出现
	
	@FindBy(xpath="//*[@id='btnOverlayAdvance']/a")
	public WebElement xpathCliAdvancedOptions ;//点击高级选项
	
	@FindBy(xpath="//*[@id='nbs2']/div/table/tbody")
	public WebElement booleanTableIsExist;//用来判断表格是否出现
	
	@FindBy(id="chartTitle")
	public WebElement booleanTitle;//用来地图标题
	

	@FindBy(tagName="h1")
	public WebElement booleanTitle2;//用来地图标题
	
	/**
	* @author : chenjingli
	* @decription 性能概括判断
	* @return
	 */
	public void assEqual(){
		
		if(driverBrowser.getPageText(booleanTitle2).contains("性能分布直方图")){
			driverBrowser.executeScript("document.getElementsByTagName('h1')[0].setAttribute('id','chartTitle');");
		}
		
			if (driverBrowser.getPageText(booleanTitle).contains("世界地图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanWordMapIsExist));
				assertEquals(true, driverBrowser.isElementPresent(booleanTableIsExist));
			}else if(driverBrowser.getPageText(booleanTitle).contains("中国地图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanMapIsExist));
				assertEquals(true, driverBrowser.isElementPresent(booleanTableIsExist));
			} else if(driverBrowser.getPageText(booleanTitle).contains("性能历史曲线图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanMapKeYongXing));
//				assertEquals(true, driverBrowser.isElementPresent(booleanMapXingNeng));
				assertEquals(true, driverBrowser.isElementPresent(booleanTableXingNeng));
			} else if(driverBrowser.getPageText(booleanTitle).length()==8){
				assertEquals(true, driverBrowser.isElementPresent(booleanMapYunYingShang));
				assertEquals(true, driverBrowser.isElementPresent(booleanTableYunYingShang));
			}else if(driverBrowser.getPageText(booleanTitle).contains("城市性能曲线图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanMapCity));
				assertEquals(true, driverBrowser.isElementPresent(booleanMapCity2));
				assertEquals(true, driverBrowser.isElementPresent(booleanTableYunYingShang));
			}else if(driverBrowser.getPageText(booleanTitle).contains("城市运营商性能曲线图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanMapCityXingN));
				assertEquals(true, driverBrowser.isElementPresent(booleanTableCityXingN));
			}else if(driverBrowser.getPageText(booleanTitle).contains("运营商性能图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanMapAbale));
			}else if(driverBrowser.getPageText(booleanTitle).contains("运营商性能图")||
					driverBrowser.getPageText(booleanTitle).contains("城市性能图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanMapProvinceAbale));
			}else if(driverBrowser.getPageText(booleanTitle).contains("散点图")){
				if(driverBrowser.getElementNums(getMaps)==1){
					assertEquals(true, driverBrowser.isElementPresent(booleanSanDianMap));
				}else{
					assertEquals(true, driverBrowser.isElementPresent(booleanSanDianMap));
					assertEquals(true, driverBrowser.isElementPresent(booleanMapSmoke));
				}	
				
			}else if(driverBrowser.getPageText(booleanTitle2).contains("性能分布直方图")){
				assertEquals(true, driverBrowser.isElementPresent(booDistributionHistogram));
			//以下开始了************性能指标图的判断	
			}else if(driverBrowser.getPageText(booleanTitle2).contains("历史曲线图")){
				assertEquals(true, driverBrowser.isElementPresent(booleanHistoryShouPingMap));
				assertEquals(true, driverBrowser.isElementPresent(booleanHistoryAbleMap));
			}
		
	}
	

	/**
	* @author : chenjingli
	* @decription 性能指标判断
	* @return
	 */
	public void assEqualPerIndicators(){
		if(driverBrowser.getPageText(booleanTitle2).contains("历史曲线图")){
			assertEquals(true, driverBrowser.isElementPresent(booleanHistoryShouPingMap));
			assertEquals(true, driverBrowser.isElementPresent(booleanHistoryAbleMap));
		}else if(driverBrowser.getPageText(booleanTitle).contains("省份曲线图")||
				driverBrowser.getPageText(booleanTitle).contains("城市曲线图")){
			assertEquals(true, driverBrowser.isElementPresent(provincesAndCityShouPingrMap));
			assertEquals(true, driverBrowser.isElementPresent(provincesAndCityAblerMap));
		}else if(driverBrowser.getPageText(booleanTitle2).contains("运营商曲线图")){
			assertEquals(true, driverBrowser.isElementPresent(operatoShouPingrMap));
			assertEquals(true, driverBrowser.isElementPresent(operatoAblerMap));
		}else if(driverBrowser.getPageText(booleanTitle).contains("城市曲线图")||
				driverBrowser.getPageText(booleanTitle).contains("城市运营商曲线图")){
			assertEquals(true, driverBrowser.isElementPresent(provincesShouPingrMap));
			assertEquals(true, driverBrowser.isElementPresent(provincesAblerMap));
		}else if(driverBrowser.getPageText(booleanTitle2).contains("环比曲线图")){
			assertEquals(true, driverBrowser.isElementPresent(sequentialShouPingrMap));
			assertEquals(true, driverBrowser.isElementPresent(sequentialAblerMap));
		}else if(driverBrowser.getPageText(booleanTitle2).contains("对比曲线图")){
			assertEquals(true, driverBrowser.isElementPresent(contrastCurveMap));
		}else if(driverBrowser.getPageText(booleanTitle2).contains("运营商性能图")){
			assertEquals(true, driverBrowser.isElementPresent(operatorPerformanceMap));
		}else if(driverBrowser.getPageText(booleanTitle2).contains("省份性能图")){
			assertEquals(true, driverBrowser.isElementPresent(provincesPerformanceMap));
		}
	}
	public int tryCatch(int i){
		logger.info("*********************开始进入第{}次循环*****************",i);
		int count = 0;
		try{
			driverBrowser.pause(1);
			logger.info("点击确定提交按钮！");
			driverBrowser.click(xpathCliSure);
			driverBrowser.pause(2500);
			
			if(driverBrowser.getPageText(booleanTitle2).contains("性能分布直方图")){
				driverBrowser.executeScript("document.getElementsByTagName('h1')[0].setAttribute('id','chartTitle');");
			}
			
			//循环时候的各种判断啊
				if(driverBrowser.getPageText(booleanTitle).contains("运营商性能图")||
						driverBrowser.getPageText(booleanTitle).contains("省份性能图") ||
						driverBrowser.getPageText(booleanTitle).contains("城市性能图")){
					if(i!=5){
						//点击高级选项
						driverBrowser.click(xpathCliAdvancedOptions);
					}
				}
				else if(driverBrowser.getPageText(booleanTitle).contains("中国地图 ")||
						driverBrowser.getPageText(booleanTitle).contains("世界地图")||
						driverBrowser.getPageText(booleanTitle).contains("性能历史曲线图")||
						driverBrowser.getPageText(booleanTitle).contains("运营商性能曲线图")||
						driverBrowser.getPageText(booleanTitle).contains("城市性能曲线图")||
						driverBrowser.getPageText(booleanTitle).contains("城市运营商性能曲线图")){
					if(i!=4){ 
						//点击高级选项
						driverBrowser.click(xpathCliAdvancedOptions);
					}
				}
				
				else if(driverBrowser.getPageText(booleanTitle).contains("散点图")||
						driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
					if(i!=4){ 
						//点击高级选项
						driverBrowser.click(xpathCliAdvancedOptions);
					}
			}
			this.assEqual();
			sb.delete(0, sb.length());
		}catch(Exception e){
			Reporter.log("/n"+sb.toString()+"失败");
			sb.delete(0, sb.length());
			count++;
		}
		logger.info("高级选项循环到的错误次数为===============：{}",count);
		return count;
	}
	/**
	 * 高级选项循环提交
	 */
	public int highOption(){ 
		logger.info("****************开始进入高级选项循环**********************");
		//点击高级选项
		driverBrowser.click(xpathCliAdvancedOptions);
		//获取图标名称
		String title =driverBrowser.getPageText(booleanTitle)	;
		int a = 0;
		for (int i = 0; i < 5; i++) {
			if(i==0){
				driverBrowser.pause(1);
				logger.info("默认高级选项执行查询");
				sb.append("打开高级选项默认情况执行查询");
				int number = tryCatch(i);
				if(number!=0){
					a = number;
				}
			}
			if(i==1){
				//时间匹配模式选项
				logger.info("时间匹配模式选项===》select{10:00，全部天}");
				sb.append("时间匹配模式选项===》select{10:00，全部天}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelHour, "10","value");
				if(title.contains("性能历史曲线图")){
					if(title.contains("运营商性能曲线图") || title.contains("城市性能曲线图")){
						//选择时间粒度
						logger.info("时间粒度选项===》select{默认选择时间:30分钟}");
						sb.append("时间粒度选项===》select{默认选择时间：30分钟}");
						driverBrowser.pause(1);
						driverBrowser.select(idSelTime, "30", "value");
						
						//选择城市选项
						logger.info("城市选项===》select{默认选择第一个城市}");
						sb.append("城市选项===》select{默认选择第一个城市}");
						driverBrowser.pause(1);
						driverBrowser.click(xpathCliFirstCity);
						driverBrowser.click(xpathCliFirstCity);
						
						//显示及排序选项
						logger.info("显示及排序选项===》click{性能图}");
						sb.append("显示及排序选项===》click{性能图}");
						driverBrowser.pause(1);
						driverBrowser.click(idCliPerfValues);
					}
					//选择时间粒度
					logger.info("时间粒度选项===》select{默认选择时间粒度第一个}");
					sb.append("时间粒度选项===》select{默认选择时间粒度第一个}");
					driverBrowser.pause(1);
					driverBrowser.select(idSelTime, "30", "value");
					
					//选择城市选项
					logger.info("城市选项===》select{默认选择第一个城市}");
					sb.append("城市选项===》select{默认选择第一个城市}");
					driverBrowser.pause(1);
					driverBrowser.click(xpathCliFirstCity);
					driverBrowser.click(xpathCliFirstCity);
					
					//显示及排序选项
					logger.info("显示及排序选项===》click{性能图}");
					sb.append("显示及排序选项===》click{性能图}");
					driverBrowser.pause(1);
					driverBrowser.click(idCliPerfValues);
					
					//行业选项
					logger.info("行业选项===》click{默认第一个行业}");
					sb.append("行业选项===》click{默认第一个行业}");
					driverBrowser.pause(1);
					driverBrowser.click(xpathSel);
					driverBrowser.click(xpathCliFirst);
				}else if(title.contains("中国地图") || title.contains("世界地图")){
					//显示及排序选项
					logger.info("显示及排序选项===》click{性能，正排序}");
					sb.append("显示及排序选项===》click{性能，正排序}");
					driverBrowser.pause(1);
					driverBrowser.click(idCliSortZheng);
				}
				
				
				//运营商选项
				logger.info("运营商选项===》select{节点组中的运营商}");
				sb.append("运营商选项===》select{节点组中的运营商}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelOperator, "0","value");
				
				//按性能过滤
				logger.info("按性能过滤选项===》select{自动，按总体}");
				sb.append("按性能过滤选项===》select{自动，按总体}");
				driverBrowser.click(idCliAuto);
				driverBrowser.pause(1);
				
				//按监测节点IP/ID过滤
				logger.info("按监测节点IP/ID过滤===》select{按ip 包含ip，send ip:192.168.9.1}");
				sb.append("按监测节点IP/ID过滤===》select{按ip 包含ip，send ip:192.168.9.1}");
				driverBrowser.click(idCliContaisRadio);
				driverBrowser.pause(1);
				driverBrowser.sendKeys(idTypeIpAdress,"192.168.9.1");
				
				//按主机ip先不加
				//按检测点排除选项
				logger.info("监测节点排除选项===》click{排除平均CPU使用率}");
				sb.append("监测节点排除选项===》click{排除平均CPU使用率超过'10'%的监测点}");
				driverBrowser.click(xpathCliOutCpu);
				driverBrowser.pause(1000);
				logger.info("监测节点排除选项===》send{超过'10'%的监测点}");
				driverBrowser.sendKeys(xpathTypeCpu, "10");
				
				//错误排除选项 选择一个测试数据
				logger.info("错误排除选项===》click{第一个测试数据点击→拉倒右边}");
				sb.append("错误排除选项===》click{第一个测试数据点击→拉倒右边}");
				driverBrowser.click(idCliFirst);
				driverBrowser.click(xpathCliRight);
				
				//操作系统选项
				logger.info("操作系统选项===》click{指定操作系统 Android}");
				sb.append("操作系统选项===》click{指定操作系统 Android}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAndroid);
				driverBrowser.pause(1);
				driverBrowser.click(idCliAndroid);
				
				//浏览器选项
				logger.info("浏览器选项===》click{指定浏览器 ie11}");
				sb.append("浏览器选项===》click{指定浏览器 ie11}");
				driverBrowser.pause(2);
				driverBrowser.click(idCliIE11);
				driverBrowser.pause(1);
				driverBrowser.click(idCliIE11);
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==2){
				//时间匹配模式选项
				logger.info("时间匹配模式选项===》select{10:0}");
				sb.append("时间匹配模式选项===》select{10:00，星期三}");
				driverBrowser.select(idSelHour, "10","value");
				driverBrowser.pause(1);
				logger.info("时间匹配模式选项===》select{星期三}");
				driverBrowser.select(idSelDay, "4","value");
				
				
				if(title.contains("运营商性能曲线图")){
					
					//选择城市选项
					logger.info("城市选项===》select{选择全部城市}");
					sb.append("城市选项===》select{选择全部城市}");
					driverBrowser.pause(1);
					driverBrowser.click(xpathCliRigth);
					
					//显示及排序
					logger.info("显示及排序选项===》click{可用性图}");
					sb.append("显示及排序选项===》click{可用性图}");
					driverBrowser.pause(1);
					driverBrowser.click(idCliAvailValues);
				}else if(title.contains("中国地图") || title.contains("世界地图")){
					//显示及排序选项
					logger.info("显示及排序选项===》click{性能，正排序}");
					sb.append("显示及排序选项===》click{性能，正排序}");
					driverBrowser.pause(1);
					driverBrowser.click(idCliSortZheng);
					
					//显示及排序选项
					if(driverBrowser.getPageText(xpathGetSortText).contains("省")){
						logger.info("显示及排序选项===》click{省}");
						sb.append("显示及排序选项===》click{省}");
					}else{
						logger.info("显示及排序选项===》click{国家}");
						sb.append("显示及排序选项===》click{国家}");
					}
					driverBrowser.pause(1);
					driverBrowser.click(idCliProvince);
				}
				
				//运营商选项
				logger.info("运营商选项===》select{指定运营商}");
				sb.append("运营商选项===》select{指定运营商}");
				driverBrowser.pause(1000);
				driverBrowser.select(idSelOperator, "主要网络提供商","text");
				driverBrowser.click(xpathCliItOper);
				
				//按性能过滤
				logger.info("按性能过滤选项===》select{自动，按图标维度}");
				sb.append("按性能过滤选项===》select{自动，按图标维度}");
				driverBrowser.select(idSelAuto, "按图表维度","text");
				
				//按监测节点IP/ID过滤 
				logger.info("按监测节点IP/ID过滤===》select{按ip 不包含ip，send ip:192.168.9.1}");
				sb.append("按监测节点IP/ID过滤===》select{按ip 不包含ip，send ip:192.168.9.1}");
				driverBrowser.pause(1);
				driverBrowser.click(idCliRuleOutRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress,"192.168.9.1");
				
				//监测节点排除选项
				logger.info("监测节点排除选项===》send{超过'10'%的监测点}");
				sb.append("监测节点排除选项===》send{超过'10'%的监测点}");
				driverBrowser.click(xpathCliOutMemory);
				driverBrowser.pause(1000);
				driverBrowser.sendKeys(xpathTypeMemory, "10");
				
				//错误排除选项
				logger.info("错误排除选项===》click{左侧全选按钮，点击→}");
				sb.append("错误排除选项===》click{左侧全选按钮，点击→}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAllChose);
				driverBrowser.pause(1000);
				driverBrowser.click(xpathCliRight);
				
				//操作系统选项
				logger.info("操作系统选项===》click{全部操作系统}click{→}");
				sb.append("操作系统选项===》click{全部操作系统}click{→}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliAllChose);
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliRightSystem);
				
				//浏览器选项
				logger.info("浏览器选项===》click{全部浏览器}click{→}");
				sb.append("浏览器选项===》click{全部浏览器}click{→}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliAllChoseLiu);
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliRightLiu);
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==3){
				//时间匹配模式选项
				logger.info("时间匹配模式选项===》select{全部小时，星期三}");
				sb.append("时间匹配模式选项===》select{全部小时，星期三}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelDay, "4","value");
				
				//按性能过滤
				logger.info("按性能过滤选项===》click{手动,总下载时间}，send{'10'秒以下的监测数据 }");
				sb.append("按性能过滤选项===》click{手动，总下载时间}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliManual);
				driverBrowser.pause(1);
				logger.info("按性能过滤选项===》send{'10'秒以下的监测数据 }");
				driverBrowser.sendKeys(xpathTypeSeconds,"10");
				
				//按监测节点IP/ID过滤
				logger.info("按监测节点IP/ID过滤===》select{按节点ID},click{包含节点为：12}");
				sb.append("按监测节点IP/ID过滤===》select{按节点ID},click{包含节点为：12}");
				driverBrowser.select(idSelDetectionNode, "按节点ID", "text");
				driverBrowser.click(idCliContaisRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress, "12");
				driverBrowser.pause(1);
				
				//监测节点排除选项
				driverBrowser.click(xpathCliAvgDownLoad);
				driverBrowser.pause(1000);
				logger.info("监测节点排除选项===》send{'10'KB/s以下}");
				driverBrowser.sendKeys(xpathTypeDownKB, "10");
				driverBrowser.pause(1);
				logger.info("监测节点排除选项===》send{60'KB/s以上的节点}");
				driverBrowser.sendKeys(xpathTypeUpKB, "60");
				
				//错误排除选项
				logger.info("错误排除选项===》click{右侧全选按钮，点击←}");
				sb.append("错误排除选项===》click{右侧全选按钮，点击←}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAllChose);
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliLeft);
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==4){
				if(title.contains("中国地图") || title.contains("世界地图")){
					//按性能过滤
					logger.info("按性能过滤选项===》click{手动,select首屏时间}，send{'10'秒以下的监测数据 }");
					sb.append("按性能过滤选项===》click{手动，select首屏时间}，send{'10'秒以下的监测数据 }");
					driverBrowser.pause(1000);
					driverBrowser.select(idSelAutoOut, "首屏时间", "text");
				}
				//按监测节点IP/ID过滤
				logger.info("按监测节点IP/ID过滤===》select{按节点ID},click{去除节点为：12}");
				sb.append("按监测节点IP/ID过滤===》select{按节点ID},click{去除节点为：12}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliRuleOutRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress, "12");
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
		}
		logger.info("********************高级选项循环完成  end********************");
		return a;
		
		
}
	/**
	* @author : chenjingli
	* @decription 判断太多导致脚本会卡在摘取一个循环次数为6次显示及排序选项
	* @return
	 */
	public int highOptionMropertyMap(){ 
		logger.info("****************开始进入高级选项循环**********************");
		//点击高级选项
		driverBrowser.click(xpathCliAdvancedOptions);
		//获取图标名称
		String title =driverBrowser.getPageText(booleanTitle)	;
		int a = 0;
		for (int i = 0; i < 6; i++) {
			if(i==0){
				driverBrowser.pause(1);
				logger.info("默认高级选项执行查询");
				sb.append("打开高级选项默认情况执行查询");
				int number = tryCatch(i);
				if(number!=0){
					a = number;
				}
			}
			if(i==1){
				//时间匹配模式选项
				logger.info("时间匹配模式选项===》select{10:00，全部天}");
				sb.append("时间匹配模式选项===》select{10:00，全部天}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelHour, "10","value");
				
				//显示及排序选项
				logger.info("显示及排序选项===》click{显示，可用性图}");
				sb.append("显示及排序选项===》click{显示，可用性图}");
				driverBrowser.pause(1);
				driverBrowser.click(idCliPerfValues);
				
				//选择城市选项
				logger.info("城市选项===》select{默认选择第一个城市}");
				sb.append("城市选项===》select{默认选择第一个城市}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliFirstCity);
				driverBrowser.click(xpathCliFirstCity);
				
				//运营商选项
				logger.info("运营商选项===》select{节点组中的运营商}");
				sb.append("运营商选项===》select{节点组中的运营商}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelOperator, "0","value");
				
				//按监测节点IP/ID过滤
				logger.info("按监测节点IP/ID过滤===》select{按ip 包含ip，send ip:192.168.9.1}");
				sb.append("按监测节点IP/ID过滤===》select{按ip 包含ip，send ip:192.168.9.1}");
				driverBrowser.click(idCliContaisRadio);
				driverBrowser.pause(1);
				driverBrowser.sendKeys(idTypeIpAdress,"192.168.9.1");
				
				//按主机ip先不加
				//按检测点排除选项
				logger.info("监测节点排除选项===》click{排除平均CPU使用率}");
				sb.append("监测节点排除选项===》click{排除平均CPU使用率超过'10'%的监测点}");
				driverBrowser.click(xpathCliOutCpu);
				driverBrowser.pause(1000);
				logger.info("监测节点排除选项===》send{超过'10'%的监测点}");
				driverBrowser.sendKeys(xpathTypeCpu, "10");
				
				//错误排除选项 选择一个测试数据
				logger.info("错误排除选项===》click{第一个测试数据点击→拉倒右边}");
				sb.append("错误排除选项===》click{第一个测试数据点击→拉倒右边}");
				driverBrowser.click(idCliFirst);
				driverBrowser.click(xpathCliRight);
				
				//操作系统选项
				logger.info("操作系统选项===》click{指定操作系统 Android}");
				sb.append("操作系统选项===》click{指定操作系统 Android}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAndroid);
				driverBrowser.pause(1);
				driverBrowser.click(idCliAndroid);
				
				//浏览器选项
				logger.info("浏览器选项===》click{指定浏览器 ie11}");
				sb.append("浏览器选项===》click{指定浏览器 ie11}");
				driverBrowser.pause(2);
				driverBrowser.click(idCliIE11);
				driverBrowser.pause(1);
				driverBrowser.click(idCliIE11);
				
				//按性能过滤
				logger.info("按性能过滤选项===》select{自动，按总体}");
				sb.append("按性能过滤选项===》select{自动，按总体}");
				driverBrowser.click(idCliAuto);
				driverBrowser.pause(1);
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==2){
				//时间匹配模式选项
				logger.info("时间匹配模式选项===》select{10:0}");
				sb.append("时间匹配模式选项===》select{10:00，星期三}");
				driverBrowser.select(idSelHour, "10","value");
				driverBrowser.pause(1);
				logger.info("时间匹配模式选项===》select{星期三}");
				driverBrowser.select(idSelDay, "4","value");
				
				//显示及排序
				logger.info("显示及排序选项===》click{排序，可用性}");
				sb.append("显示及排序选项===》click{排序，可用性}");
				driverBrowser.pause(1);
				driverBrowser.click(idCliAble);
				
				//选择城市选项
				logger.info("城市选项===》select{选择全部城市}");
				sb.append("城市选项===》select{选择全部城市}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliRigth);
				
				//运营商选项
				logger.info("运营商选项===》select{指定运营商}");
				sb.append("运营商选项===》select{指定运营商}");
				driverBrowser.pause(1000);
				driverBrowser.select(idSelOperator, "主要网络提供商","text");
				driverBrowser.click(xpathCliItOper);
				
				
				//按监测节点IP/ID过滤 
				logger.info("按监测节点IP/ID过滤===》select{按ip 不包含ip，send ip:192.168.9.1}");
				sb.append("按监测节点IP/ID过滤===》select{按ip 不包含ip，send ip:192.168.9.1}");
				driverBrowser.pause(1);
				driverBrowser.click(idCliRuleOutRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress,"192.168.9.1");
				
				//监测节点排除选项
				logger.info("监测节点排除选项===》send{超过'10'%的监测点}");
				sb.append("监测节点排除选项===》send{超过'10'%的监测点}");
				driverBrowser.click(xpathCliOutMemory);
				driverBrowser.pause(1000);
				driverBrowser.sendKeys(xpathTypeMemory, "10");
				
				//错误排除选项
				logger.info("错误排除选项===》click{左侧全选按钮，点击→}");
				sb.append("错误排除选项===》click{左侧全选按钮，点击→}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAllChose);
				driverBrowser.pause(1000);
				driverBrowser.click(xpathCliRight);
				
				//操作系统选项
				logger.info("操作系统选项===》click{全部操作系统}click{→}");
				sb.append("操作系统选项===》click{全部操作系统}click{→}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliAllChose);
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliRightSystem);
				
				//浏览器选项
				logger.info("浏览器选项===》click{全部浏览器}click{→}");
				sb.append("浏览器选项===》click{全部浏览器}click{→}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliAllChoseLiu);
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliRightLiu);
				
				//按性能过滤
				logger.info("按性能过滤选项===》select{自动，按图标维度}");
				sb.append("按性能过滤选项===》select{自动，按图标维度}");
				driverBrowser.select(idSelAuto, "按图表维度","text");
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==3){
				//时间匹配模式选项
				logger.info("时间匹配模式选项===》select{全部小时，星期三}");
				sb.append("时间匹配模式选项===》select{全部小时，星期三}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelDay, "4","value");
				
				//显示及排序
				logger.info("显示及排序选项===》click{顺序，正排序}");
				sb.append("显示及排序选项===》click{顺序，正排序}");
				driverBrowser.pause(1);
				driverBrowser.click(idCliSortZheng);
				
				
				//按监测节点IP/ID过滤
				logger.info("按监测节点IP/ID过滤===》select{按节点ID},click{包含节点为：12}");
				sb.append("按监测节点IP/ID过滤===》select{按节点ID},click{包含节点为：12}");
				driverBrowser.select(idSelDetectionNode, "按节点ID", "text");
				driverBrowser.click(idCliContaisRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress, "12");
				driverBrowser.pause(1);
				
				//监测节点排除选项
				driverBrowser.click(xpathCliAvgDownLoad);
				driverBrowser.pause(1000);
				logger.info("监测节点排除选项===》send{'10'KB/s以下}");
				driverBrowser.sendKeys(xpathTypeDownKB, "10");
				driverBrowser.pause(1);
				logger.info("监测节点排除选项===》send{60'KB/s以上的节点}");
				driverBrowser.sendKeys(xpathTypeUpKB, "60");
				
				//错误排除选项
				logger.info("错误排除选项===》click{右侧全选按钮，点击←}");
				sb.append("错误排除选项===》click{右侧全选按钮，点击←}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAllChose);
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliLeft);
				
				//按性能过滤
				logger.info("按性能过滤选项===》click{手动,总下载时间}，send{'10'秒以下的监测数据 }");
				sb.append("按性能过滤选项===》click{手动，总下载时间}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliManual);
				driverBrowser.pause(1);
				logger.info("按性能过滤选项===》send{'10'秒以下的监测数据 }");
				driverBrowser.sendKeys(xpathTypeSeconds,"10");
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==4){
				
				//显示及排序
				logger.info("显示及排序选项===》条件是{性能——到排序}");
				sb.append("显示及排序选项===》条件是{性能——到排序}");
				driverBrowser.pause(1);
				if(!idCliAvailValues.isSelected()){
					driverBrowser.click(idCliAvailValues);
				}
				
				//按监测节点IP/ID过滤
				logger.info("按监测节点IP/ID过滤===》select{按节点ID},click{去除节点为：12}");
				sb.append("按监测节点IP/ID过滤===》select{按节点ID},click{去除节点为：12}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliRuleOutRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress, "12");
				
//				if(driverBrowser.getPageText(booleanTitle).contains("运营商性能图")){
//					//按性能过滤
//					logger.info("按性能过滤选项===》click{手动,select首屏时间}，send{'10'秒以下的监测数据 }");
//					sb.append("按性能过滤选项===》click{手动，select首屏时间}，send{'10'秒以下的监测数据 }");
//					driverBrowser.pause(1000);
//					driverBrowser.select(idSelAutoOut, "首屏时间", "text");
//				}
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			
			if(i==5){
				
				//显示及排序
				logger.info("显示及排序选项===》条件是{按运营商}");
				sb.append("显示及排序选项===》条件是{按运营商}");
				driverBrowser.pause(1);
				driverBrowser.click(idCliYunYingS);
				
				if(!idCliAvailValues.isSelected()){
					driverBrowser.click(idCliAvailValues);
				}
				if(!idCliPerfValues.isSelected()){
					driverBrowser.click(idCliPerfValues);
				}
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
		}
		logger.info("********************高级选项循环完成  end********************");
		return a;
		
	}
	
	
	
	/**
	* @author : chenjingli
	* @decription 判断太多导致脚本会卡在摘取一个循环次数为6次显示及排序选项
	* @return
	 */
	public int highOptionThree(){ 
		logger.info("****************开始进入高级选项循环**********************");
		//点击高级选项
		driverBrowser.click(xpathCliAdvancedOptions);
		//获取图标名称
		//String title =driverBrowser.getPageText(booleanTitle)	;
		int a = 0;
		for (int i = 0; i < 6; i++) {
			if(i==0){
				driverBrowser.pause(1);
				logger.info("默认高级选项执行查询");
				sb.append("打开高级选项默认情况执行查询");
				int number = tryCatch(i);
				if(number!=0){
					a = number;
				}
			}
			if(i==1){
				
				//选择城市选项
				logger.info("城市选项===》select{默认选择第一个城市}");
				sb.append("城市选项===》select{默认选择第一个城市}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliFirstCity);
				driverBrowser.click(xpathCliFirstCity);
				
				//运营商选项
				logger.info("运营商选项===》select{节点组中的运营商}");
				sb.append("运营商选项===》select{节点组中的运营商}");
				driverBrowser.pause(1);
				driverBrowser.select(idSelOperator, "0","value");
				
				//按性能过滤
				logger.info("按性能过滤选项===》select{自动，按总体}");
				sb.append("按性能过滤选项===》select{自动，按总体}");
				driverBrowser.click(idCliAuto);
				driverBrowser.pause(1);
				
				//按主机ip先不加
				//按检测点排除选项
				logger.info("监测节点排除选项===》click{排除平均CPU使用率}");
				sb.append("监测节点排除选项===》click{排除平均CPU使用率超过'10'%的监测点}");
				driverBrowser.pause(1000);
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[1]")).click();
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[2]")).sendKeys("10");
				
				//操作系统选项
				logger.info("操作系统选项===》click{指定操作系统 Android}");
				sb.append("操作系统选项===》click{指定操作系统 Android}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAndroid);
				driverBrowser.pause(1);
				driverBrowser.click(idCliAndroid);
				
				//浏览器选项
				logger.info("浏览器选项===》click{指定浏览器 ie11}");
				sb.append("浏览器选项===》click{指定浏览器 ie11}");
				driverBrowser.pause(2);
				driverBrowser.click(idCliIE11);
				driverBrowser.pause(1);
				driverBrowser.click(idCliIE11);
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==2){
				
				//选择城市选项
				logger.info("城市选项===》select{选择全部城市}");
				sb.append("城市选项===》select{选择全部城市}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliRigth);
				
				//运营商选项
				logger.info("运营商选项===》select{指定运营商}");
				sb.append("运营商选项===》select{指定运营商}");
				driverBrowser.pause(1000);
				driverBrowser.select(idSelOperator, "主要网络提供商","text");
				driverBrowser.click(xpathCliItOper);
				
				//按性能过滤s
				logger.info("按性能过滤选项===》click{自动，排除95%-5%以下检测数据}");
				sb.append("按性能过滤选项===》click{自动，按图标维度}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAuto);
				
				//监测节点排除选项
				logger.info("监测节点排除选项===》click{排除平均内存使用率超过'10'%的监测点}");
				sb.append("监测节点排除选项===》click{排除平均内存使用率超过'10'%的监测点}");
				driverBrowser.pause(1000);
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[3]")).click();
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[4]")).sendKeys("10");
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==3){
				
				//监测节点排除选项
				logger.info("监测节点排除选项===》click{排除平均下载速度},send{10kb以下}，{60kb以上}");
				sb.append("监测节点排除选项===》click{排除平均下载速度},send{10kb以下}，{60kb以上}");
				driverBrowser.pause(1000);
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[5]")).click();
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[6]")).sendKeys("10");
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[7]")).sendKeys("60");
				
				//按性能过滤
				logger.info("按性能过滤选项===》click{手动,总下载时间}，send{'10'秒以下的监测数据 }");
				sb.append("按性能过滤选项===》click{手动，总下载时间}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliManual);
				driverBrowser.pause(1);
				logger.info("按性能过滤选项===》send{'10'秒以下的监测数据 }");
				driverBrowser.sendKeys(xpathTypeSeconds,"10");
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
			if(i==4){
//				if(driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
//					//按性能过滤
//					logger.info("按性能过滤选项===》click{手动,select首屏时间}，send{'10'秒以下的监测数据 }");
//					sb.append("按性能过滤选项===》click{手动，select首屏时间}，send{'10'秒以下的监测数据 }");
//					driverBrowser.pause(1000);
//					driverBrowser.select(idSelAutoOut, "首屏时间", "text");
//					
//				}
				//监测节点排除选项
				logger.info("监测节点排除选项===》click{排除任务下载速度在},send{10kb以下}，{60kb以上}");
				sb.append("监测节点排除选项===》click{排除任务下载速度在},send{10kb以下}，{60kb以上}");
				driverBrowser.pause(1000);
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[8]")).click();
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[9]")).sendKeys("10");
				driverBrowser.getWebDriver().findElement
				(By.xpath("//*[@id='probeTrimOption']/descendant::input[10]")).sendKeys("60");
				
				int number = tryCatch(i);
				if(number!=0){
					a = number+a;
				}
			}
		}
		logger.info("********************高级选项循环完成  end********************");
		return a;
		
	}
}