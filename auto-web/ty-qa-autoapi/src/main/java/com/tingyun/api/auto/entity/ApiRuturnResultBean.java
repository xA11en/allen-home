package com.tingyun.api.auto.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @author :chenjingli 
* @version ：2015-9-22 下午4:29:47 
* @decription:
 */
public class ApiRuturnResultBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer app_id;
	
	private String app_api_result;
	
	private String timestamp;
	
	private Integer errorCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApp_id() {
		return app_id;
	}

	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}

	public String getApp_api_result() {
		return app_api_result;
	}

	public void setApp_api_result(String app_api_result) {
		this.app_api_result = app_api_result;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	@Override
	public String toString() {
		return "ApiRuturnResultBean [id=" + id + ", app_id=" + app_id
				+ ", app_api_result=" + app_api_result + ", timestamp="
				+ timestamp + ", errorCount=" + errorCount + "]";
	}






	
	
}
