package com.tingyun.auto.rpc.page.report.singletask;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.utils.StrAndDateUtil;
/**
* @author :chenjingli 
* @version ：2015-6-4 下午2:07:00 
* @decription: 性能概括相关用例
 */
public class GeneralizPerforPage extends ReportPage{
	
	private static Logger logger = LoggerFactory.getLogger(GeneralizPerforPage.class);
	public GeneralizPerforPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 点击性能概括
	 */
	public void clickGenPerfor(){
		driverBrowser.click(xpathCliGenPerfor);
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-历史曲线图
	 */
	public void historyCurveMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[2]/a")).click();
		this.testPublich();
	}
	/**
	 * 单任务检测-性能概括-点击性能概括-运营商曲线图
	 */
	public void operatorMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[3]/a")).click();
		this.testPublich();
	}
	/**
	 * 单任务检测-性能概括-点击性能概括-省份曲线图
	 */
	public void provincesMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[4]/a")).click();
		this.testPublich();
	}
	/**
	 * 单任务检测-性能概括-点击性能概括-城市曲线图
	 */
	public void cityMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[5]/a")).click();
		this.testPublich();
	}
	/**
	 * 单任务检测-性能概括-点击性能概括-省份运营商曲线图
	 */
	public void provincesOperatorMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[6]/a")).click();
		this.testPublich();
	}
	/**
	 * 单任务检测-性能概括-点击性能概括-城市运营商曲线图
	 */
	public void cityOperatorMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[7]/a")).click();
		this.testPublich();
	}
	/**
	 * 单任务检测-性能概括-点击性能概括-对比曲线图
	 */
	public void contrastCurveMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[8]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-环比曲线图
	 */
	public void sequentialCurveMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[9]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-运营商性能图
	 */
	public void operatorPerformanceMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[11]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-省份性能图
	 */
	public void provincesPerformanceMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[12]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-城市性能图
	 */
	public void cityPerformanceMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[13]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-省份运营商性能图
	 */
	public void provinceOperatorsMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[14]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-城市运营商性能图
	 */
	public void cityOperatorsMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[15]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单任务检测-性能概括-点击性能概括-汇总概况图
	 */
	public void sumUpMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='componentChart']/li[17]/a")).click();
		this.testPublich();
	}
	
	public void testPublich(){
		int count = 0;
		int count4=0;
		//判断地图和图标是否存在
		try{
			//判断地图是否存在 表格
			this.tryCatch();
		}catch(Exception e){
			Reporter.log("/n"+sb.toString()+"失败");
			sb.delete(0, sb.length());
			count++;
		}
		//判断使用哪个高级选项
		count4 = this.highOptionGenPer();
		logger.info("高级选项循环的错误次数为：count========================================{}",count4);
		if(count4!=0){
			count = count4+count;
		}
		//相对时间
		for (int i = 0; i < 2; i++) {
				logger.info("快捷选项===》select相对时间为：1天");
				sb.append("快捷选项===》select相对时间为：1天");
				//选择相对时间为1天开始循环性能指标
				if(i==0){
					int count3 = PerIndexCycle();
					if(count3!=0){
						count = count3+count;
					}
					sb.delete(0, sb.length());//清楚stringbuffer内容
				}
				
				//选择相对时间为2周开始循环性能指标
				if(i==1){
					
					driverBrowser.pause(1000);
					logger.info("快捷选项===》select相对时间为：2周");
					sb.append("快捷选项===》select相对时间为：2周");
					driverBrowser.select(nameSelRelativeTime, "20160", "value");//选择相对时间两周
					try{
						//判断地图是否存在 表格
						this.tryCatch();
					}catch(Exception e){
						Reporter.log("/n"+sb.toString()+"失败");
						sb.delete(0, sb.length());
						count++;
					}
					
					//性能指标循环
					int count3 = PerIndexCycle();
					if(count3!=0){
						count = count3+count;
					}
					driverBrowser.pause(1000);
					sb.delete(0, sb.length());//清楚stringbuffer内容
				}
			}
			
		
		//绝对时间
		for (int i = 0; i < 2; i++) {
			
			//选择绝对时间为1天
			if(i==0){
				logger.info("快捷选项===》select绝对时间为：1天");
				sb.append("快捷选项===》select绝对时间为：1天");
				driverBrowser.sendKeys(idTypeStartTime, StrAndDateUtil.randowStringTime("start", 1));//选择绝对时间1天
				driverBrowser.pause(1000);
				try{
					//判断地图是否存在 表格
					this.tryCatch();
				}catch(Exception e){
					Reporter.log("/n"+sb.toString()+"失败");
					sb.delete(0, sb.length());
					count++;
				}
				//性能指标循环
				int count3 = PerIndexCycle();
				if(count3!=0){
					count = count3+count;
				}
				sb.delete(0, sb.length());//清楚stringbuffer内容
			}
			//选择绝对时间为2周
			if(i==1){
				driverBrowser.pause(1000);
				logger.info("快捷选项===》select绝对时间为：2周");
				sb.append("快捷选项===》select绝对时间为：2周");
				driverBrowser.sendKeys(idTypeEndTime, StrAndDateUtil.randowStringTime("end", 14));//选择绝对时间两周
				try{
					//判断地图是否存在
					this.tryCatch();
				}catch(Exception e){
					Reporter.log("/n"+sb.toString()+"失败");
					sb.delete(0, sb.length());
					count++;
				}
				}
				int count3 = PerIndexCycle();
				if(count3!=0){
					count = count3+count;
				}
				
				sb.delete(0, sb.length());//清楚stringbuffer内容
				//性能指标循环选择
			}
			
		logger.info("测试用例共未找到地图或表格的次数为：{}",count);
		if(count !=0){
			Reporter.log("测试用例共未找到地图或表格的次数为:"+count+"次");
			fail("中国地图用例测试失败");
		}
		
	}
	
	/**
	* @author : chenjingli
	* @decription 判断太多导致脚本会卡在摘取一个循环次数为6次显示及排序选项
	* @return
	 */
	public int highOptionGenPer(){ 
		logger.info("****************开始进入高级选项循环**********************");
		//点击高级选项
		driverBrowser.click(xpathCliAdvancedOptions);
		//获取图标名称
		String title =driverBrowser.getPageText(booleanTitle);
		int a = 0;
		for (int i = 0; i < 6; i++) {
			if(i==0){
				driverBrowser.pause(1);
				logger.info("默认高级选项执行查询");
				sb.append("打开高级选项默认情况执行查询");
				int number = tryCatchGenPor(i);
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
				
				//选择城市选项
				logger.info("城市选项===》select{默认选择第一个城市}");
				sb.append("城市选项===》select{默认选择第一个城市}");
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliFirstCity);
				driverBrowser.click(xpathCliFirstCity);
				
				//显示及排序选项
				if(driverBrowser.getPageText(booleanTitle).contains("运营商性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("省份性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("城市性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("省份运营商性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("城市运营商性能图")){
					
					logger.info("显示及排序选项===》click{正排序}");
					sb.append("显示及排序选项===》click{正排序}");
					driverBrowser.pause(1);
					driverBrowser.click(idCliSortZheng);
				}		  
				
				if(driverBrowser.getPageText(booleanTitle).contains("历史曲线图")||
				   driverBrowser.getPageText(booleanTitle).contains("运营商曲线图")||
				   driverBrowser.getPageText(booleanTitle).contains("省份曲线图")||
				   driverBrowser.getPageText(booleanTitle).contains("城市曲线图")||
				   driverBrowser.getPageText(booleanTitle).contains("省份运营商曲线图")||
				   driverBrowser.getPageText(booleanTitle).contains("城市运营商曲线图")||
				   driverBrowser.getPageText(booleanTitle).contains("对比曲线图")||
				   driverBrowser.getPageText(booleanTitle).contains("环比曲线图")){
					//选择时间粒度
					logger.info("时间粒度选项===》select{默认选择时间:30分钟}");
					sb.append("时间粒度选项===》select{默认选择时间：30分钟}");
					driverBrowser.pause(1);
					driverBrowser.select(idSelTime, "30", "value");
				}
				
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
				
				//错误排除选项 选择一个测试数据
				logger.info("错误排除选项===》click{第一个测试数据点击→拉倒右边}");
				sb.append("错误排除选项===》click{第一个测试数据点击→拉倒右边}");
				driverBrowser.click(idCliFirst);
				driverBrowser.click(xpathCliRight);
				
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
				driverBrowser.click(xpathCliOutCpu1);
				driverBrowser.sendKeys(xpathTypeCpu2, "10");
				//包含dns
				logger.info("按特定DNS服务器过滤选项===》过滤DNS服务器:click{包含}，send{192.168.1.1}");
				sb.append("按特定DNS服务器过滤选项===》过滤DNS服务器:click{包含}，send{192.168.1.1}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliContainsDNS);
				driverBrowser.clear(idSendDnsAdress);
				driverBrowser.sendKeys(idSendDnsAdress, "192.168.1.1");
				
				if(title.equals("历史曲线图")){
					//行业选项
					logger.info("行业选项===》click{默认第一个行业}");
					sb.append("行业选项===》click{默认第一个行业}");
					driverBrowser.pause(1);
					driverBrowser.click(xpathSel);
					driverBrowser.click(xpathCliFirst);
				}
				
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
				
				int number = tryCatchGenPor(i);
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
				
				//显示及排序选项
				if(driverBrowser.getPageText(booleanTitle).contains("运营商性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("省份性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("城市性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("省份运营商性能图")||
				   driverBrowser.getPageText(booleanTitle).contains("城市运营商性能图")){
					
					if(driverBrowser.getPageText(booleanTitle).contains("运营商")){
						logger.info("显示及排序选项===》click{按运营商}");
						sb.append("显示及排序选项===》click{按运营商}");
					}else{
						logger.info("显示及排序选项===》click{按城市}");
						sb.append("显示及排序选项===》click{按城市}");
					}
					driverBrowser.pause(1);
					driverBrowser.click(idCliAble);
				}
				
				//按监测节点IP/ID过滤 
				logger.info("按监测节点IP/ID过滤===》select{按ip 不包含ip，send ip:192.168.9.1}");
				sb.append("按监测节点IP/ID过滤===》select{按ip 不包含ip，send ip:192.168.9.1}");
				driverBrowser.pause(1);
				driverBrowser.click(idCliRuleOutRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress,"192.168.9.1");
				
				logger.info("按特定DNS服务器过滤选项===》过滤DNS服务器:click{排除}，send{192.168.1.1}");
				sb.append("按特定DNS服务器过滤选项===》过滤DNS服务器:click{排除}，send{192.168.1.1}");
				driverBrowser.pause(1000);
				driverBrowser.click(idClioutDNS);
				driverBrowser.clear(idSendDnsAdress);
				driverBrowser.sendKeys(idSendDnsAdress, "192.168.1.1");
				
				//错误排除选项
				logger.info("错误排除选项===》click{左侧全选按钮，点击→}");
				sb.append("错误排除选项===》click{左侧全选按钮，点击→}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAllChose);
				driverBrowser.pause(1000);
				driverBrowser.click(xpathCliRight);
				
				//按性能过滤
				logger.info("按性能过滤选项===》click{手动,总下载时间}，send{'10'秒以下的监测数据 }");
				sb.append("按性能过滤选项===》click{手动，总下载时间}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliManual);
				driverBrowser.pause(1);
				logger.info("按性能过滤选项===》send{'10'秒以下的监测数据 }");
				driverBrowser.sendKeys(xpathTypeSeconds,"10");
				
				//监测节点排除选项
				logger.info("监测节点排除选项===》click{排除平均内存使用率超过'10'%的监测点}");
				sb.append("监测节点排除选项===》click{排除平均内存使用率超过'10'%的监测点}");
				driverBrowser.pause(1000);
				driverBrowser.click(xpathCliAvgMemory);
				driverBrowser.sendKeys(xpathTypeAvgMemory, "10");
				
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
				
				int number = tryCatchGenPor(i);
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
				
				//按监测节点IP/ID过滤
				logger.info("按监测节点IP/ID过滤===》select{按节点ID},click{包含节点为：12}");
				sb.append("按监测节点IP/ID过滤===》select{按节点ID},click{包含节点为：12}");
				driverBrowser.select(idSelDetectionNode, "按节点ID", "text");
				driverBrowser.click(idCliContaisRadio);
				driverBrowser.clear(idTypeIpAdress);
				driverBrowser.sendKeys(idTypeIpAdress, "12");
				driverBrowser.pause(1);
				
				//错误排除选项
				logger.info("错误排除选项===》click{右侧全选按钮，点击←}");
				sb.append("错误排除选项===》click{右侧全选按钮，点击←}");
				driverBrowser.pause(1000);
				driverBrowser.click(idCliAllChose);
				driverBrowser.pause(1);
				driverBrowser.click(xpathCliLeft);
				
				//监测节点排除选项
				logger.info("监测节点排除选项===》click{排除平均下载速度},send{10kb以下}，{60kb以上}");
				sb.append("监测节点排除选项===》click{排除平均下载速度},send{10kb以下}，{60kb以上}");
				driverBrowser.pause(1000);
				driverBrowser.click(xpathCliAvgDownLoad2);
				driverBrowser.sendKeys(xpathCliAvgDownLoadXia, "10");
				driverBrowser.sendKeys(xpathTypeUAvgDownLoadUp, "60");
				//对比曲线图不点击首屏时间
				if(driverBrowser.getPageText(booleanTitle).contains("汇总概况图")||
				   driverBrowser.getPageText(booleanTitle).contains("对比曲线图")){
					 driverBrowser.click(idSelAutoOut);
				}else{
					//按性能过滤
					logger.info("按性能过滤选项===》click{手动,select首屏时间}，send{'10'秒以下的监测数据 }");
					sb.append("按性能过滤选项===》click{手动，select首屏时间}，send{'10'秒以下的监测数据 }");
					driverBrowser.pause(1000);
					driverBrowser.select(idSelAutoOut, "首屏时间", "text");
					
				}
				int number = tryCatchGenPor(i);
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
				driverBrowser.click(xpathCliTaskOut);
				driverBrowser.sendKeys(xpathCliTaskOutXia, "10");
				driverBrowser.sendKeys(xpathCliTaskOutUp, "60");
				
				int number = tryCatchGenPor(i);
				if(number!=0){
					a = number+a;
				}
			}
		}
		logger.info("********************高级选项循环完成  end********************");
		return a;
		
	}
	
	public int tryCatchGenPor(int i){
		logger.info("*********************开始进入第{}次循环*****************",i);
		int count = 0;
		try{
			driverBrowser.pause(1);
			logger.info("点击确定提交按钮！");
			driverBrowser.click(xpathCliSure);
			driverBrowser.pause(2500);
			if(i!=4){ 
				//点击高级选项
				driverBrowser.click(xpathCliAdvancedOptions);
			}	
			this.assEqualPerIndicators();
			sb.delete(0, sb.length());
		}catch(Exception e){
			Reporter.log("/n"+sb.toString()+"失败");
			sb.delete(0, sb.length());
			count++;
		}
		logger.info("高级选项循环到的错误次数为===============：{}",count);
		return count;
	}
	
	
}	
