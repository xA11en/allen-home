package com.tingyun.auto.page.rpc.report;
import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.page.GlobalPage;
/**
* @author :chenjingli 
* @version ：2015-5-19 上午10:41:21 
* @decription: rpc 系统报表page
 */
public class ReportPage extends GlobalPage {
	protected StringBuffer sb = new StringBuffer();
	public ReportPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	@FindBy(xpath="//*[@id='tasksContainer']/div/div[1]/button")
	public WebElement xpathCliSelectTask;//点击任务列表下拉框选择单任务
	
	@FindBy(xpath="//*[@id='perfAvailChart']/li")
	public List<WebElement> xpathGenePerfoNum;//性能概括下拉数量 
	
	@FindBy(xpath="//*[@id='perfAvailChart']/li[1]/a")
	public WebElement xpathCliChinaMap;//点击第一个中国地图
	
	@FindBy(xpath="//*[@id='chinamap']/embed")
	public WebElement booleanMapIsExist;//用来判断地图是否出现
	
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
	
	//取1天和两周的时间
	private  String randowStringTime(String flag,int day){//开始和结束时间
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		if(flag.equals("start")){
			date.setDate(date.getDate()-day);
		return sd.format(date);
		}else if(flag.equals("end")){
		date.setDate(date.getDate());
		return sd.format(date);
		}
		return null;
	}
	//公共方法块 end***************************************************************
	/**
	 * 性能概括下图表显示
	 */
	public void ChinaMap(){
		AdvancedOptionsPage ap = new AdvancedOptionsPage(driverBrowser);
		driverBrowser.click(xpathCliChinaMap);
		assertEquals(true, driverBrowser.isElementPresent(booleanMapIsExist));
		driverBrowser.select(nameSelRelativeTime, "2880", "value");//1 day
		System.out.println( driverBrowser.getElementNums(xpathGetOptions));
		assertEquals(true, driverBrowser.isElementPresent(booleanMapIsExist));
		for (int i = 0; i < driverBrowser.getElementNums(xpathGetOptions); i++) {
			driverBrowser.select(xpathSelOption
					,String.valueOf(i), "index");
			driverBrowser.click(xpathCliAdvancedOptions);
			System.out
			.println("开始调用高级选项的方法 start==========================================");
			ap.highOption();
			assertEquals(true, driverBrowser.isElementPresent(booleanMapIsExist));
		}
		
	}
	
}
