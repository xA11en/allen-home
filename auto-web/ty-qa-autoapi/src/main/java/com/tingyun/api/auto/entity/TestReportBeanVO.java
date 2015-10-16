package com.tingyun.api.auto.entity;

import java.io.Serializable;

public class TestReportBeanVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String caseName;
	
	private String requestTou;
	
	private String status;
	
	private Integer appId;
	
	

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getRequestTou() {
		return requestTou;
	}

	public void setRequestTou(String requestTou) {
		this.requestTou = requestTou;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TestReportBeanVO [caseName=" + caseName + ", requestTou="
				+ requestTou + ", status=" + status + ", appId=" + appId + "]";
	}

	
	
}
