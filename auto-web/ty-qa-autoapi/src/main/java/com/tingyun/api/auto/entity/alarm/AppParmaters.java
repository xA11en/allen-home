package com.tingyun.api.auto.entity.alarm;

import java.io.Serializable;

public class AppParmaters implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -2180263222481608129L;
	public String getDeviceIndex() {
		return deviceIndex;
	}
	public void setDeviceIndex(String deviceIndex) {
		this.deviceIndex = deviceIndex;
	}
	private String key;
	private String thinkTime;
	private String mode;
	private String count;
	private String period;
	private String responseTime;
	private String interactionTime;
	private String viewLoadTime;
	private String imageProcessTime;
	private String dataStorageTime;
	private String networkVisitTime;
	private String jsonProcessTime;
	private String httpErrorPercentage;
	private String networkErrorPercentage;
	private String deviceIndex;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public AppParmaters(String key, String thinkTime, String mode,
			String count, String period, String responseTime,
			String interactionTime, String viewLoadTime,
			String imageProcessTime, String dataStorageTime,
			String networkVisitTime, String jsonProcessTime,
			String httpErrorPercentage, String networkErrorPercentage,
			String deviceIndex) {
		super();
		this.key = key;
		this.thinkTime = thinkTime;
		this.mode = mode;
		this.count = count;
		this.period = period;
		this.responseTime = responseTime;
		this.interactionTime = interactionTime;
		this.viewLoadTime = viewLoadTime;
		this.imageProcessTime = imageProcessTime;
		this.dataStorageTime = dataStorageTime;
		this.networkVisitTime = networkVisitTime;
		this.jsonProcessTime = jsonProcessTime;
		this.httpErrorPercentage = httpErrorPercentage;
		this.networkErrorPercentage = networkErrorPercentage;
		this.deviceIndex = deviceIndex;
	}
	public String getThinkTime() {
		return thinkTime;
	}
	public void setThinkTime(String thinkTime) {
		this.thinkTime = thinkTime;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getInteractionTime() {
		return interactionTime;
	}
	public void setInteractionTime(String interactionTime) {
		this.interactionTime = interactionTime;
	}
	public String getViewLoadTime() {
		return viewLoadTime;
	}
	public void setViewLoadTime(String viewLoadTime) {
		this.viewLoadTime = viewLoadTime;
	}
	public String getImageProcessTime() {
		return imageProcessTime;
	}
	public void setImageProcessTime(String imageProcessTime) {
		this.imageProcessTime = imageProcessTime;
	}
	public String getDataStorageTime() {
		return dataStorageTime;
	}
	public void setDataStorageTime(String dataStorageTime) {
		this.dataStorageTime = dataStorageTime;
	}
	public String getNetworkVisitTime() {
		return networkVisitTime;
	}
	public void setNetworkVisitTime(String networkVisitTime) {
		this.networkVisitTime = networkVisitTime;
	}
	public String getJsonProcessTime() {
		return jsonProcessTime;
	}
	public void setJsonProcessTime(String jsonProcessTime) {
		this.jsonProcessTime = jsonProcessTime;
	}
	public String getHttpErrorPercentage() {
		return httpErrorPercentage;
	}
	public void setHttpErrorPercentage(String httpErrorPercentage) {
		this.httpErrorPercentage = httpErrorPercentage;
	}
	public String getNetworkErrorPercentage() {
		return networkErrorPercentage;
	}
	public void setNetworkErrorPercentage(String networkErrorPercentage) {
		this.networkErrorPercentage = networkErrorPercentage;
	}
	@Override
	public String toString() {
		return "AppParmaters [key=" + key + ", thinkTime=" + thinkTime
				+ ", mode=" + mode + ", count=" + count + ", period=" + period
				+ ", responseTime=" + responseTime + ", interactionTime="
				+ interactionTime + ", viewLoadTime=" + viewLoadTime
				+ ", imageProcessTime=" + imageProcessTime
				+ ", dataStorageTime=" + dataStorageTime
				+ ", networkVisitTime=" + networkVisitTime
				+ ", jsonProcessTime=" + jsonProcessTime
				+ ", httpErrorPercentage=" + httpErrorPercentage
				+ ", networkErrorPercentage=" + networkErrorPercentage
				+ ", deviceIndex=" + deviceIndex + "]";
	}
	
}
