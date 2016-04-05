package com.tingyun.alarm.entity;

import java.io.Serializable;

/**
 * @author allen
 *
 */
public class BrowserParmaters implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 2917330795314430831L;
	
	protected String domTreatment;//Dom处理
	
	protected String apdex;//Apdex
	
	protected String jsErrorRate;//js错误率
	
	protected String serviceResponseTime;//服务器响应时间
	
	protected String networkDelay;//网络延时
	
	protected String pagePrint;//页面渲染
	
	protected String firstPrintTime;//首次渲染时间
	
	protected String firstInteractionTime;//首次交互时间
	
	protected String customLoad;//自定义加载
	
	protected String aplicationLongRunning;//应用耗时
	
	protected String requestQueue;//请求排队
	
	protected String dnsTime;//dns时间
	
	protected String tcpConnectTime;//tcp建联时间
	
	protected String backEndLongRunning;//后端耗时
	
	protected String frontEndLongRunning;//前端耗时
	
	protected String pageLoad;//页面加载
	
	protected String callBackTime;//回调时间
	
	protected String backEndResponseLongRunning;//后端响应耗时
	
	protected String frontEndLoadLongRunning;//前端加载耗时
	
	protected String applicationLongRunning;//应用耗时
	
	protected String ajaxRequestTime;//ajax请求时间
	
	protected String pvid;
	
	protected String waitSecond;//上传等待时间 
	
	protected String count;//循环次数
	
	protected String pfUrl;//测试地址
	
	protected String key;//测试地址
	
	private String ajaxUrl;//ajax  url
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}




	public String getPfUrl() {
		return pfUrl;
	}

	public void setPfUrl(String pfUrl) {
		this.pfUrl = pfUrl;
	}

	public String getAjaxUrl() {
		return ajaxUrl;
	}

	public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}

	public BrowserParmaters(String domTreatment, String apdex,
			String jsErrorRate, String serviceResponseTime,
			String networkDelay, String pagePrint, String firstPrintTime,
			String firstInteractionTime, String customLoad,
			String aplicationLongRunning, String requestQueue, String dnsTime,
			String tcpConnectTime, String backEndLongRunning,
			String frontEndLongRunning, String pageLoad, String callBackTime,
			String backEndResponseLongRunning, String frontEndLoadLongRunning,
			String applicationLongRunning, String ajaxRequestTime, String pvid,
			String waitSecond, String count, String pfUrl, String key,
			String ajaxUrl) {
		super();
		this.domTreatment = domTreatment;
		this.apdex = apdex;
		this.jsErrorRate = jsErrorRate;
		this.serviceResponseTime = serviceResponseTime;
		this.networkDelay = networkDelay;
		this.pagePrint = pagePrint;
		this.firstPrintTime = firstPrintTime;
		this.firstInteractionTime = firstInteractionTime;
		this.customLoad = customLoad;
		this.aplicationLongRunning = aplicationLongRunning;
		this.requestQueue = requestQueue;
		this.dnsTime = dnsTime;
		this.tcpConnectTime = tcpConnectTime;
		this.backEndLongRunning = backEndLongRunning;
		this.frontEndLongRunning = frontEndLongRunning;
		this.pageLoad = pageLoad;
		this.callBackTime = callBackTime;
		this.backEndResponseLongRunning = backEndResponseLongRunning;
		this.frontEndLoadLongRunning = frontEndLoadLongRunning;
		this.applicationLongRunning = applicationLongRunning;
		this.ajaxRequestTime = ajaxRequestTime;
		this.pvid = pvid;
		this.waitSecond = waitSecond;
		this.count = count;
		this.pfUrl = pfUrl;
		this.key = key;
		this.ajaxUrl = ajaxUrl;
	}

	public String getDomTreatment() {
		return domTreatment;
	}


	public void setDomTreatment(String domTreatment) {
		this.domTreatment = domTreatment;
	}


	public String getApdex() {
		return apdex;
	}


	public void setApdex(String apdex) {
		this.apdex = apdex;
	}


	public String getJsErrorRate() {
		return jsErrorRate;
	}


	public void setJsErrorRate(String jsErrorRate) {
		this.jsErrorRate = jsErrorRate;
	}


	public String getServiceResponseTime() {
		return serviceResponseTime;
	}


	public void setServiceResponseTime(String serviceResponseTime) {
		this.serviceResponseTime = serviceResponseTime;
	}


	public String getNetworkDelay() {
		return networkDelay;
	}


	public void setNetworkDelay(String networkDelay) {
		this.networkDelay = networkDelay;
	}


	public String getPagePrint() {
		return pagePrint;
	}


	public void setPagePrint(String pagePrint) {
		this.pagePrint = pagePrint;
	}


	public String getFirstPrintTime() {
		return firstPrintTime;
	}


	public void setFirstPrintTime(String firstPrintTime) {
		this.firstPrintTime = firstPrintTime;
	}


	public String getFirstInteractionTime() {
		return firstInteractionTime;
	}


	public void setFirstInteractionTime(String firstInteractionTime) {
		this.firstInteractionTime = firstInteractionTime;
	}


	public String getCustomLoad() {
		return customLoad;
	}


	public void setCustomLoad(String customLoad) {
		this.customLoad = customLoad;
	}


	public String getAplicationLongRunning() {
		return aplicationLongRunning;
	}


	public void setAplicationLongRunning(String aplicationLongRunning) {
		this.aplicationLongRunning = aplicationLongRunning;
	}


	public String getRequestQueue() {
		return requestQueue;
	}


	public void setRequestQueue(String requestQueue) {
		this.requestQueue = requestQueue;
	}


	public String getDnsTime() {
		return dnsTime;
	}


	public void setDnsTime(String dnsTime) {
		this.dnsTime = dnsTime;
	}


	public String getTcpConnectTime() {
		return tcpConnectTime;
	}


	public void setTcpConnectTime(String tcpConnectTime) {
		this.tcpConnectTime = tcpConnectTime;
	}


	public String getBackEndLongRunning() {
		return backEndLongRunning;
	}


	public void setBackEndLongRunning(String backEndLongRunning) {
		this.backEndLongRunning = backEndLongRunning;
	}


	public String getFrontEndLongRunning() {
		return frontEndLongRunning;
	}


	public void setFrontEndLongRunning(String frontEndLongRunning) {
		this.frontEndLongRunning = frontEndLongRunning;
	}


	public String getPageLoad() {
		return pageLoad;
	}


	public void setPageLoad(String pageLoad) {
		this.pageLoad = pageLoad;
	}


	public String getCallBackTime() {
		return callBackTime;
	}


	public void setCallBackTime(String callBackTime) {
		this.callBackTime = callBackTime;
	}


	public String getBackEndResponseLongRunning() {
		return backEndResponseLongRunning;
	}


	public void setBackEndResponseLongRunning(String backEndResponseLongRunning) {
		this.backEndResponseLongRunning = backEndResponseLongRunning;
	}


	public String getFrontEndLoadLongRunning() {
		return frontEndLoadLongRunning;
	}


	public void setFrontEndLoadLongRunning(String frontEndLoadLongRunning) {
		this.frontEndLoadLongRunning = frontEndLoadLongRunning;
	}


	public String getApplicationLongRunning() {
		return applicationLongRunning;
	}


	public void setApplicationLongRunning(String applicationLongRunning) {
		this.applicationLongRunning = applicationLongRunning;
	}


	public String getAjaxRequestTime() {
		return ajaxRequestTime;
	}


	public void setAjaxRequestTime(String ajaxRequestTime) {
		this.ajaxRequestTime = ajaxRequestTime;
	}


	public String getPvid() {
		return pvid;
	}


	public void setPvid(String pvid) {
		this.pvid = pvid;
	}


	public String getWaitSecond() {
		return waitSecond;
	}


	public void setWaitSecond(String waitSecond) {
		this.waitSecond = waitSecond;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "BrowserParmaters [domTreatment=" + domTreatment + ", apdex="
				+ apdex + ", jsErrorRate=" + jsErrorRate
				+ ", serviceResponseTime=" + serviceResponseTime
				+ ", networkDelay=" + networkDelay + ", pagePrint=" + pagePrint
				+ ", firstPrintTime=" + firstPrintTime
				+ ", firstInteractionTime=" + firstInteractionTime
				+ ", customLoad=" + customLoad + ", aplicationLongRunning="
				+ aplicationLongRunning + ", requestQueue=" + requestQueue
				+ ", dnsTime=" + dnsTime + ", tcpConnectTime=" + tcpConnectTime
				+ ", backEndLongRunning=" + backEndLongRunning
				+ ", frontEndLongRunning=" + frontEndLongRunning
				+ ", pageLoad=" + pageLoad + ", callBackTime=" + callBackTime
				+ ", backEndResponseLongRunning=" + backEndResponseLongRunning
				+ ", frontEndLoadLongRunning=" + frontEndLoadLongRunning
				+ ", applicationLongRunning=" + applicationLongRunning
				+ ", ajaxRequestTime=" + ajaxRequestTime + ", pvid=" + pvid
				+ ", waitSecond=" + waitSecond + ", count=" + count + "]";
	}
	
	
	
	
	
	
	
}
