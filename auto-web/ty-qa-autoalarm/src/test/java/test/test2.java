package test;

import com.tingyun.alarm.entity.BrowserAjaxRequest;
import com.tingyun.alarm.entity.BrowserPF;
import com.tingyun.alarm.entity.BrowserParmaters;
import com.tingyun.alarm.entity.BrowserWebapp;
import com.tingyun.alarm.service.JsAgentSimulator;

public class test2 {
public static void main(String[] args) {
//	protected String domTreatment;//Dom处理
//	protected String apdex;//Apdex
//	protected String jsErrorRate;//js错误率
//	protected String serviceResponseTime;//服务器响应时间
//	protected String networkDelay;//网络延时
//	protected String pagePrint;//页面渲染
//	protected String firstPrintTime;//首次渲染时间
//	protected String firstInteractionTime;//首次交互时间
//	protected String customLoad;//自定义加载
//	protected String aplicationLongRunning;//应用耗时
//	protected String requestQueue;//请求排队
//	protected String dnsTime;//dns时间
//	protected String tcpConnectTime;//tcp建联时间
//	protected String backEndLongRunning;//后端耗时
//	protected String frontEndLongRunning;//前端耗时
//	protected String pageLoad;//页面加载
//	protected String callBackTime;//回调时间
//	protected String backEndResponseLongRunning;//后端响应耗时
//	protected String frontEndLoadLongRunning;//前端加载耗时
//	protected String applicationLongRunning;//应用耗时
//	protected String ajaxRequestTime;//ajax请求时间
//	protected String pvid;
//	protected String waitSecond;//上传等待时间 
//	protected String count;//循环次数
//	protected String pfUrl;//测试地址
//	protected String key;//测试地址
	BrowserParmaters bp = new BrowserParmaters
			("5", "5", "5", "5",
					"5", "5", "5", 
					"5", "5", "5", 
					"5", "5", "5", "5", 
					"5", "5", "5", "5", 
					"5", "5", "5", "c36d-f9a03883",
					"3", "5", "http%3A%2F%2F192.168.5.50%3A8081%2FtestSDTY%2Ftest%2Fhi.jsp", "yQSNkTzc7Kg", "");
	new JsAgentSimulator().simulatorBrowserData(bp);
}
		
}