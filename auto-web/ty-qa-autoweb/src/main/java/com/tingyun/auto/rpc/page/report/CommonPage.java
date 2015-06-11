package com.tingyun.auto.rpc.page.report;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;

public class CommonPage extends GlobalPage {
	
	
	public CommonPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(xpath="//*[@id='treenav']/li[2]/div/a")
	public WebElement xpathCliGenPerfor;//点击性能概括
	
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
	
	
	
	
	
	//*********************性能概况map判断元素start**********************************
	
	/**
	 * 判断性能历史曲线 图和表格
	 */
	@FindBy(xpath="//*[@id='perfByTime']/img")
	public WebElement booleanMapXingNeng;//用来判断性能历史曲线性能
	
	@FindBy(xpath="//*[@id='availByTime']/img")
	public WebElement booleanMapKeYongXing;//用来判断性能历史曲线性能可用性
	
	@FindBy(xpath="//*[@id='dataList']/tbody")
	public WebElement booleanTableXingNeng;//用来判断性能历史曲线性能可用性\
	
	/**
	 * 判断运营商性能曲线图
	 */
	@FindBy(xpath="//*[@id='ispByTime']/img")
	public WebElement booleanMapYunYingShang;//运营商性能曲线图
	
	@FindBy(xpath="//*[@id='availIspByTime']/img")//运营商性能曲线图2
	public WebElement booleanMapYunYingShang2;
	
	@FindBy(xpath="//*[@id='nbs4']/div/table/tbody")//运营商性能表格
	public WebElement booleanTableYunYingShang;
	
	/**
	 * 城市性能曲线图
	 */
	@FindBy(xpath="//*[@id='locationByTime']/img")
	public WebElement booleanMapCity;//城市性能曲线图
	
	@FindBy(xpath="//*[@id='availLocationByTime']/img")//城市性能曲线图
	public WebElement booleanMapCity2;
	
	/**
	 * 城市运营商性能曲线图
	 */
	@FindBy(xpath="//*[@id='locationIspByTime']/img")
	public WebElement booleanMapCityXingN;//城市运营商性能曲线图
	
	@FindBy(xpath="//*[@id='nbs4']/div/table/tbody")//城市运营商性能table
	public WebElement booleanTableCityXingN;
	
	
	/**
	 * 运营商性能图
	 */
	@FindBy(xpath="//*[@id='perfByIsp']/img")
	public WebElement booleanMapComXing;//运营商性能图
	
	@FindBy(xpath="//*[@id='availByIsp']/img")//运营商性能图可用性
	public WebElement booleanMapAbale;
	
	/**
	 * 省份性能图
	 */
	@FindBy(xpath="//*[@id='perfByProv']/img")
	public WebElement booleanMapProvinceXing;//省份性能图
	
	@FindBy(xpath="//*[@id='availByLocation']/img")//省份性能图可用性
	public WebElement booleanMapProvinceAbale;
	
	/**
	 * 城市性能图
	 */
	@FindBy(xpath="//*[@id='perfByLocation']/img")
	public WebElement booleanMapCityXing;//省份性能图
//	
//	@FindBy(xpath="//*[@id='availByLocation']/img")//省份性能图可用性
//	public WebElement booleanMapProvinceAbale;
	
	/**
	 * 散点图
	 */
	@FindBy(id="scatterPlot")
	public WebElement booleanSanDianMap;//散点图
	
	@FindBy(id="scatterSmoke")//散点smoke图
	public WebElement booleanMapSmoke;
	
	@FindBy(xpath="//*[@class='nbcharttable']/descendant::img")//获得图片数量
	public List<WebElement> getMaps;
	
	/**
	 * 性能分布直方图
	 */
	@FindBy(id="scatterHistogram")
	public WebElement booDistributionHistogram;//性能分布直方图
	
	//********************性能概况map判断元素end**********************************

	
	
	//*********************性能指标map判断元素start**********************************
	
	/**
	 * 历史曲线图
	 */
	@FindBy(xpath="//*[@id='compPerfByTime']/img")
	public WebElement booleanHistoryShouPingMap;//首屏时间
	
	@FindBy(xpath="//*[@id='compAvailByTime']/img")
	public WebElement booleanHistoryAbleMap; //可用性图
	/**
	 * 运营商曲线图
	 */
	@FindBy(xpath="//*[@id='compPerfIspByTime']/img")
	public WebElement operatoShouPingrMap;//首屏时间
	
	@FindBy(xpath="//*[@id='availIspByTime']/img")//散点smoke图
	public WebElement operatoAblerMap; //可用性图
	
	/**
	 * 省份曲线图  城市曲线图
	 */
	@FindBy(xpath="//*[@id='compPerfLocationByTime']/img")
	public WebElement provincesAndCityShouPingrMap;//首屏时间
	
	@FindBy(xpath="//*[@id='availLocationByTime']/img")
	public WebElement provincesAndCityAblerMap; //可用性图
	
	/**
	 * 城市曲线图  城市运营商曲线图
	 */
	@FindBy(xpath="//*[@id='compPerfLocationIspByTime']/img")
	public WebElement provincesShouPingrMap;//首屏时间
	
	@FindBy(xpath="//*[@id='availLocationIspByTime']/img")
	public WebElement provincesAblerMap; //可用性图
	
	/**
	 * 环比曲线图
	 */
	@FindBy(xpath="//*[@id='compPerfCycleByTime']/img")
	public WebElement sequentialShouPingrMap;//首屏时间
	
	@FindBy(xpath="//*[@id='availCycleByTime']/img")
	public WebElement sequentialAblerMap; //可用性图
	
	/**
	 * 对比曲线图
	 */
	@FindBy(xpath="//*[@id='compPerfContrastByTime']/img")
	public WebElement contrastCurveMap;//性能指标
	
	//*********************性能指标map判断元素end**********************************
}
