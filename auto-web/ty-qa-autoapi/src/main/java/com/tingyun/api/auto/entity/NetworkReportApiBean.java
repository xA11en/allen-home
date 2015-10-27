package com.tingyun.api.auto.entity;

import java.io.Serializable;

/**
* @author :chenjingli 
* @version ：2015-7-27 下午3:59:00 
* @decription:  report api entity
 */
public class NetworkReportApiBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String caseName;	
	private String authKey;
	private String  parameter;
	private String url;
	private String json;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	@Override
	public String toString() {
		return "ServerReportApiBean [id=" + id + ", caseName=" + caseName
				+ ", authKey=" + authKey + ", parameter=" + parameter
				+ ", url=" + url + ", json=" + json + "]";
	}
	
	
	
}
