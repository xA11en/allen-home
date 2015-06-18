package com.tingyun.auto.rpc.page.report.singletask;
import static org.testng.Assert.fail;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.report.AdvancedOptionsPage;
import com.tingyun.auto.utils.StrAndDateUtil;
/**
* @author :chenjingli 
* @version ：2015-5-19 上午10:41:21 
* @decription: rpc 系统报表page
 */
public class ReportPage extends AdvancedOptionsPage {
	
	
	private static Logger logger = LoggerFactory.getLogger(ReportPage.class);
	protected StringBuffer sb = new StringBuffer();
	public static AdvancedOptionsPage ap;
	public ReportPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
		//引进高级选项page
		ap = new AdvancedOptionsPage(driverBrowser);
	}

	
	public void tryCatch()throws Exception{
		driverBrowser.pause(1000);
		//引用父类的方法
		if(driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[1]/a")).isDisplayed()){
			assEqual();
		}else{
			assEqualPerIndicators();
		}
		sb.delete(0, sb.length());
	}
	
	/**
	 * 单页面检测--单任务--中国地图
	 */
	public void ChinaMap(){
		//点击中国地图
		driverBrowser.click(xpathCliChinaMap);
		this.testPublich();
	}
	/**
	 * 单页面检测--单任务--世界地图
	 */
	public void wordMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[2]/a")).click();
		this.testPublich();
	}
	/**
	 * 单页面检测--单任务--性能历史曲线图
	 */
	public void xingNengMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[3]/a")).click();
		this.testPublich();
		
	}
	/**
	 * 单页面检测--单任务--运营商性能曲线图
	 */
	public void yunYingShangMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[4]/a")).click();
		this.testPublich();
		
	}
	/**
	 * 单页面检测--单任务--城市性能曲线图
	 */
	public void cityMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[5]/a")).click();
		this.testPublich();
		
	}
	/**
	 * 单页面检测--单任务--城市运营商性能曲线图
	 */
	public void cityCommunication(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[6]/a")).click();
		this.testPublich();
	}
	/**
	 * 单页面检测--单任务--运营商性能图
	 */
	public void YunXingSXingNengMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[7]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单页面检测--单任务--省份性能图
	 */
	public void provinceXingNengMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[8]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单页面检测--单任务--城市性能图
	 */
	public void cityXingNengMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[9]/a")).click();
		this.testPublich();
	}
	
	
	/**
	 * 单页面检测--单任务--散点图
	 */
	public void scatterDiagramMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[10]/a")).click();
		this.testPublich();
	}
	
	/**
	 * 单页面检测--单任务--性能分布直方图
	 */
	public void distributionHistogramMap(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[11]/a")).click();
		this.testPublich();
	}
	
	
	
	public void testPublich(){
		if(driverBrowser.getPageText(booleanTitle2).contains("性能分布直方图")){
			driverBrowser.executeScript("document.getElementsByTagName('h1')[0].setAttribute('id','chartTitle');");
		}
		
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
			if(driverBrowser.getPageText(booleanTitle).contains("运营商性能图")|| 
			   driverBrowser.getPageText(booleanTitle).contains("省份性能图") ||
			   driverBrowser.getPageText(booleanTitle).contains("城市性能图")){
				
			    logger.info("************使用highOptionMropertyMap method**********");
				count4 = ap.highOptionMropertyMap();
			}else{
				if(driverBrowser.getPageText(booleanTitle).contains("散点图")||
				   driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
					
					logger.info("************使用highOptionThree method**********");
					count4 = ap.highOptionThree();
				}else{
					
					logger.info("************使用highOption method**********");
					count4 = ap.highOption();
				}
		}
		logger.info("高级选项循环的错误次数为：count========================================{}",count4);
		if(count4!=0){
			count = count4+count;
		}
		//相对时间
		for (int i = 0; i < 2; i++) {
			//性能图展现的时候不需要循环性能指标
			if(driverBrowser.getPageText(booleanTitle).contains("中国地图")||
			   driverBrowser.getPageText(booleanTitle).contains("世界地图")||
			   	driverBrowser.getPageText(booleanTitle).contains("散点图")||
			   	driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
				//选择相对时间为1天开始循环性能指标
				if(i==0){
					int count3 = PerIndexCycle();
					if(count3!=0){
						count = count3+count;
					}
					sb.delete(0, sb.length());//清楚stringbuffer内容
				}
				}
			
			
			//选择相对时间为2周开始循环性能指标
			if(i==1){
				
				driverBrowser.pause(1000);
				if(driverBrowser.getPageText(booleanTitle).contains("散点图")||
				   driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
					logger.info("快捷选项===》select相对时间为：1周");
					sb.append("快捷选项===》select相对时间为：1周");
					driverBrowser.select(nameSelRelativeTime, "10080", "value");//选择相对时间1周
				}else{
					logger.info("快捷选项===》select相对时间为：2周");
					sb.append("快捷选项===》select相对时间为：2周");
					driverBrowser.select(nameSelRelativeTime, "20160", "value");//选择相对时间两周
				}
				try{
					//判断地图是否存在 表格
					this.tryCatch();
				}catch(Exception e){
					Reporter.log("/n"+sb.toString()+"失败");
					sb.delete(0, sb.length());
					count++;
				}
				//性能图展现的时候不需要循环性能指标
				if(driverBrowser.getPageText(booleanTitle).contains("中国地图")||
				   driverBrowser.getPageText(booleanTitle).contains("世界地图")||
				    driverBrowser.getPageText(booleanTitle).contains("散点图")||
				   driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
					//性能指标循环
					int count3 = PerIndexCycle();
					if(count3!=0){
						count = count3+count;
					}
					driverBrowser.pause(1000);
					sb.delete(0, sb.length());//清楚stringbuffer内容
				}
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
				//性能图展现的时候不需要循环性能指标
				if(driverBrowser.getPageText(booleanTitle).contains("中国地图")||
				   driverBrowser.getPageText(booleanTitle).contains("世界地图")||
					driverBrowser.getPageText(booleanTitle).contains("散点图")||
				driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){	  
					//性能指标循环
					int count3 = PerIndexCycle();
					if(count3!=0){
						count = count3+count;
					}
					sb.delete(0, sb.length());//清楚stringbuffer内容
				}
			}
			//选择绝对时间为2周
			if(i==1){
				driverBrowser.pause(1000);
				if(driverBrowser.getPageText(booleanTitle).contains("城市性能曲线图")){
					//消除alert
					try{
						//判断地图是否存在
						this.tryCatch();
					}catch(Exception e){
						Reporter.log("/n"+sb.toString()+"失败");
						sb.delete(0, sb.length());
						count++;
					}
				}else{
					if(driverBrowser.getPageText(booleanTitle).contains("散点图")||
					   driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
						logger.info("快捷选项===》send绝对时间为：1周");
						sb.append("快捷选项===》send绝对时间为：1周");
						driverBrowser.sendKeys(idTypeEndTime, StrAndDateUtil.randowStringTime("end", 7));//选择绝对时间两周
					}else{
						logger.info("快捷选项===》select绝对时间为：2周");
						sb.append("快捷选项===》select绝对时间为：2周");
						driverBrowser.sendKeys(idTypeEndTime, StrAndDateUtil.randowStringTime("end", 14));//选择绝对时间两周
					}
					try{
						//判断地图是否存在
						this.tryCatch();
					}catch(Exception e){
						Reporter.log("/n"+sb.toString()+"失败");
						sb.delete(0, sb.length());
						count++;
					}
				}
				//性能图展现的时候不需要循环性能指标
				if(		   driverBrowser.getPageText(booleanTitle).contains("中国地图")||
						   driverBrowser.getPageText(booleanTitle).contains("世界地图")||
							driverBrowser.getPageText(booleanTitle).contains("散点图")||
						   	driverBrowser.getPageText(booleanTitle).contains("性能分布直方图")){
					int count3 = PerIndexCycle();
					if(count3!=0){
						count = count3+count;
					}
					
					sb.delete(0, sb.length());//清楚stringbuffer内容
				}
				//性能指标循环选择
			}
			
		}
		logger.info("测试用例共未找到地图或表格的次数为：{}",count);
		if(count !=0){
			Reporter.log("测试用例共未找到地图或表格的次数为:"+count+"次");
			fail("中国地图用例测试失败");
		}
		
	}
	public int PerIndexCycle(){
		//判断有没有性能指标来使用
		String title =driverBrowser.getPageText(booleanTitle);
		if(title.equals("对比曲线图")){
			return 0;
		}
		logger.info("******************* 执行性能指标选项的循环测试 start******************循环的次数为：==={}",driverBrowser.getElementNums(xpathGetOptions));
		int count2 = 0;
		//循环select性能指标
		for (int i = 0; i < driverBrowser.getElementNums(xpathGetOptions); i++) {
			driverBrowser.pause(1000);
			logger.info("快捷选项===》select性能指标选项下的，通过下标:{}",String.valueOf(i));
			sb.append("快捷选项===》select性能指标选项下的，通过下标"+String.valueOf(i));
			driverBrowser.select(xpathSelOption
					,String.valueOf(i), "index");
			//消除alert
			driverBrowser.confirmAlert(true);
			
			//判断图标是否存在
			try{
				this.tryCatch();
			}catch(Exception e){
				Reporter.log("/n"+sb.toString()+"失败");
				sb.delete(0, sb.length());
				count2++;
			}
		}
		logger.info("*******************************  性能指标循环完成 end  ******************************");
		logger.info("性能指标循环的错误次数为count2================:{}",count2);
		return count2;
	}
	
}
