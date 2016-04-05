package com.tingyun.alarm.task;

import java.util.List;
import java.util.Map;

import com.tingyun.alarm.entity.AppParmaters;
import com.tingyun.alarm.entity.BrowserParmaters;
import com.tingyun.alarm.entity.ServerParmaters;
import com.tingyun.alarm.service.ApplicationSimulatorWithFixValuesAAA;
import com.tingyun.alarm.service.JsAgentSimulator;
import com.tingyun.alarm.service.MobileAgentSimulatorNew;
import com.tingyun.test.task.AbstractTestTask;

public class uploadBrowserTask extends AbstractTestTask {

	public uploadBrowserTask() {
	}

	@Override
	public List doTask() {
//		protected String domTreatment;//Dom处理
//		protected String apdex;//Apdex
//		protected String jsErrorRate;//js错误率
//		protected String serviceResponseTime;//服务器响应时间
//		protected String networkDelay;//网络延时
//		protected String pagePrint;//页面渲染
//		protected String firstPrintTime;//首次渲染时间
//		protected String firstInteractionTime;//首次交互时间
//		protected String customLoad;//自定义加载
//		protected String aplicationLongRunning;//应用耗时
//		protected String requestQueue;//请求排队
//		protected String dnsTime;//dns时间
//		protected String tcpConnectTime;//tcp建联时间
//		protected String backEndLongRunning;//后端耗时
//		protected String frontEndLongRunning;//前端耗时
//		protected String pageLoad;//页面加载
//		protected String callBackTime;//回调时间
//		protected String backEndResponseLongRunning;//后端响应耗时
//		protected String frontEndLoadLongRunning;//前端加载耗时
//		protected String applicationLongRunning;//应用耗时
//		protected String ajaxRequestTime;//ajax请求时间
//		protected String pvid;
//		protected String waitSecond;//上传等待时间 
//		protected String count;//循环次数
//		protected String pfUrl;//测试地址
//		protected String key;//测试地址
		
		Map<String,Object> params = this.params;
		String domTreatment = (String)params.get("domTreatment");
		String apdex = (String)params.get("apdex");
		String jsErrorRate = (String)params.get("jsErrorRate");
		String serviceResponseTime = (String)params.get("serviceResponseTime");
		String networkDelay = (String)params.get("networkDelay");
		String pagePrint = (String)params.get("pagePrint");
		String firstPrintTime = (String)params.get("firstPrintTime");
		String firstInteractionTime = (String)params.get("firstInteractionTime");
		String customLoad = (String)params.get("customLoad");
		String aplicationLongRunning = (String)params.get("aplicationLongRunning");
		String requestQueue = (String)params.get("requestQueue");
		String dnsTime = (String)params.get("dnsTime");
		String tcpConnectTime = (String)params.get("tcpConnectTime");
		String backEndLongRunning = (String)params.get("backEndLongRunning");
		String frontEndLongRunning = (String)params.get("frontEndLongRunning");
		String pageLoad = (String)params.get("pageLoad");
		String callBackTime = (String)params.get("callBackTime");
		String backEndResponseLongRunning = (String)params.get("backEndResponseLongRunning");
		String frontEndLoadLongRunning = (String)params.get("frontEndLoadLongRunning");
		String applicationLongRunning = (String)params.get("applicationLongRunning");
		String ajaxRequestTime = (String)params.get("ajaxRequestTime");
		String pvid = (String)params.get("pvid");
		String waitSecond = (String)params.get("waitSecond");
		String count = (String)params.get("count");
		String pfUrl = (String)params.get("pfUrl");
		String key = (String)params.get("key");
		String ajaxUrl = (String)params.get("ajaxUrl");
		
		
		new JsAgentSimulator().simulatorBrowserData(new BrowserParmaters
				(domTreatment, apdex, jsErrorRate, serviceResponseTime, 
						networkDelay, pagePrint, firstPrintTime,
						firstInteractionTime, customLoad, aplicationLongRunning,
						requestQueue, dnsTime, tcpConnectTime, backEndLongRunning, 
						frontEndLongRunning, pageLoad, callBackTime, backEndResponseLongRunning, 
						frontEndLoadLongRunning, applicationLongRunning, ajaxRequestTime,
						pvid, waitSecond, count, pfUrl, key, ajaxUrl));
		return null;
	}


}
