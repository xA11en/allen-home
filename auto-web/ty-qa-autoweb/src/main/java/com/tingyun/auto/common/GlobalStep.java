package com.tingyun.auto.common;



import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.page.report.singletask.GeneralizPerforPage;

/**
* @author :chenjingli 
* @version ：2015-5-12 下午2:09:07 
* @decription: 初始化 before 或者 after 的一些信息
 */
public class GlobalStep {
	
	public static String classpath=ClassLoader.getSystemResource("").getPath();
	
	@BeforeSuite
	public void beforeSuite(){
		String seleniumServerPath = classpath+SeleniumSettings.SELENIUM_SERVER;
		String hubJson = classpath+SeleniumSettings.HUB_JSON;
		String starHub = classpath+SeleniumSettings.START_HUB;
		try {
			Runtime.getRuntime().exec("cmd /c start "+starHub+" "+hubJson);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("执行批出理文件异常{}",e);
		}
	}
	
	
	protected Logger logger;
	protected static final String caseStart ="执行开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	protected static final String caseEnd ="执行结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	protected static final String FAIL ="失败 >> >>";
	
	/**
	 * 性能指标
	 */
	public static final String DESHISTORYMAP = "性能指标-历史曲线图测试用例";
	public static final String DESOPERATORMAP = "性能指标-运营商曲线图测试用例";
	public static final String DESPROVINCESMAP = "性能指标-省份曲线图测试用例";
	public static final String DESCCITYMAP = "性能指标-城市曲线图测试用例";
	public static final String DESPROVINCESOPERATORMAP = "性能指标-省份运营商曲线图测试用例";
	public static final String DESCITYOPERATORMAP = "性能指标-城市运营商曲线图测试用例";
	public static final String DESSEQUENTIALCURVEMAP = "性能指标-环比曲线图测试用例";
	public static final String DESCONTRASTCURVEMAP = "性能指标-对比曲线图测试用例";
	//性能图
	public static final String operatorPerformanceMap = "性能指标-运营商性能图测试用例";
	public static final String provincesPerformanceMap = "性能指标-省份性能图测试用例";
	public static final String cityPerformanceMap = "性能指标-城市性能图测试用例";
	public static final String provinceOperatorsMap = "性能指标-省份运营商性能图测试用例";
	public static final String cityOperatorsMap = "性能指标-城市运营商性能图测试用例";
	
	//汇总图
	public static final String sumUpMap = "性能指标-汇总概况图测试用例";
	
	public static final String rpcDesLogin = "rpc系统登录测试用例";
	
	
	public void pinfo(Class<?> clazz,String info){
		logger = LoggerFactory.getLogger(clazz);
		logger.info(info);
	}
	public void perror(Class<?> clazz,String error,Throwable e){
		logger = LoggerFactory.getLogger(clazz);
		logger.error(error);
	}
	public void pdebug(Class<?> clazz,String debug){
		logger = LoggerFactory.getLogger(clazz);
		logger.debug(debug);
	}
}
