package com.tingyun.auto.rpc.page.report;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.tingyun.auto.framework.browser.DriverBrowser;
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
	@FindBy(xpath="//*[@id='tasksContainer']/div/div[1]/button")
	public WebElement xpathCliSelectTask;//点击任务列表下拉框选择单任务
	
	@FindBy(xpath="//*[@id='perfAvailChart']/li")
	public List<WebElement> xpathGenePerfoNum;//性能概括下拉数量 
	
	@FindBy(xpath="//*[@id='perfAvailChart']/li[1]/a")
	public WebElement xpathCliChinaMap;//点击第一个中国地图
	

	
	@FindBy(xpath="//*[@name='basedOn0']")
	public WebElement xpathSelOption;//选择性能指标选项
	
	@FindBy(xpath="//*[@id='st_box']/ul[2]/li/select/descendant::option")
	public List<WebElement> xpathGetOptions;//选择性能指标选项
	
	@FindBy(xpath="//*[@id='btnOverlayAdvance']/a")
	public WebElement xpathCliAdvancedOptions ;//点击高级选项
	
	@FindBy(name="relativeTimeRange0")
	public WebElement nameSelRelativeTime ;//选择相对时间
	
	@FindBy(xpath="//*[@id='st_box']/ul[5]/li[1]/input")
	public WebElement xpathSelAbsoluteTime ;//点击高级选项
	
	@FindBy(id="absoluteTimeFrom")
	public WebElement idTypeStartTime ;//输入绝对时间开始
	
	@FindBy(id="absoluteTimeTo")
	public WebElement idTypeEndTime ;//输入绝对时间结束
	
	@FindBy(xpath="//*[@id='s_t_b_break']/a")
	public WebElement xpathCliCreatReport ;//点击高级选
	
	@FindBy(css="#taskMap44335 > div.jvectormap-container > svg")
	public WebElement booleanWordMapIsExist;//用来判断世界地图是否出现
	
	@FindBy(id="chartTitle")
	public WebElement booleanTitle;//用来地图标题
	
	/**
	 * 判断地图显示路径中国地图 和表格
	 */
	@FindBy(xpath="//*[@id='chinamap']/embed")
	public WebElement booleanMapIsExist;//用来判断地图是否出现
	
	@FindBy(xpath="//*[@id='nbs2']/div/table/tbody")
	public WebElement booleanTableIsExist;//用来判断表格是否出现
	

	
	public void tryCatch()throws Exception{
		driverBrowser.pause(2000);
		//引用父类的方法
		tryCatch2();
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
	public void cityCommXing(){
		driverBrowser.getWebDriver().findElement(By.xpath("//*[@id='perfAvailChart']/li[7]/a")).click();
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
		count4 = ap.highOption();
		logger.info("高级选项循环的错误次数为：count========================================{}",count4);
		if(count4!=0){
			count = count4+count;
		}
		//相对时间
		for (int i = 0; i < 2; i++) {
			
			//性能图展现的时候不需要循环性能指标
			if(driverBrowser.getPageText(booleanTitle).contains("中国地图")||
			   driverBrowser.getPageText(booleanTitle).contains("世界地图")){
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
				logger.info("快捷选项===》select相对时间为：2周");
				sb.append("快捷选项===》select相对时间为：2周");
				driverBrowser.select(nameSelRelativeTime, "20160", "value");//选择相对时间两周
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
						   driverBrowser.getPageText(booleanTitle).contains("世界地图")){
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
						   driverBrowser.getPageText(booleanTitle).contains("世界地图")){
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
				logger.info("快捷选项===》select绝对时间为：2周");
				sb.append("快捷选项===》select绝对时间为：2周");
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
				//性能图展现的时候不需要循环性能指标
				if(driverBrowser.getPageText(booleanTitle).contains("中国地图")||
						   driverBrowser.getPageText(booleanTitle).contains("世界地图")){
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
			try{
				//判断图标是否存在
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
