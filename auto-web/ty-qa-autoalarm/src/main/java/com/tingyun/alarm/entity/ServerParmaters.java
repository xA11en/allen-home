package com.tingyun.alarm.entity;

import java.io.Serializable;
import java.util.Arrays;

public class ServerParmaters implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7225537728876866276L;

	private String[] ApplicationName;	
	private String mode;
	private String count;
	private String period;
	private String apdex;
	private String responseTime;
	private String errRate;
	public String[] getApplicationName() {
		return ApplicationName;
	}
	public void setApplicationName(String[] applicationName) {
		ApplicationName = applicationName;
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
	public String getApdex() {
		return apdex;
	}
	public void setApdex(String apdex) {
		this.apdex = apdex;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getErrRate() {
		return errRate;
	}
	public void setErrRate(String errRate) {
		this.errRate = errRate;
	}
	public ServerParmaters(String[] applicationName, String mode, String count,
			String period, String apdex, String responseTime, String errRate) {
		super();
		ApplicationName = applicationName;
		this.mode = mode;
		this.count = count;
		this.period = period;
		this.apdex = apdex;
		this.responseTime = responseTime;
		this.errRate = errRate;
	}
	@Override
	public String toString() {
		return "ServerParmaters [ApplicationName="
				+ Arrays.toString(ApplicationName) + ", mode=" + mode
				+ ", count=" + count + ", period=" + period + ", apdex="
				+ apdex + ", responseTime=" + responseTime + ", errRate="
				+ errRate + "]";
	}
	
	
}
