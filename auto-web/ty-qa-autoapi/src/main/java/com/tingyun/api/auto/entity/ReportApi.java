package com.tingyun.api.auto.entity;

import java.io.Serializable;

/**
* @author :chenjingli 
* @version ：2015-7-27 下午3:59:00 
* @decription:  report api entity
 */
public class ReportApi  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String caseName;	
	private String c6nnnfcg;
	private String  parameter;
	private String url;
	private String xml;
	private String json;
	private Integer status;
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
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getC6nnnfcg() {
		return c6nnnfcg;
	}
	public void setC6nnnfcg(String c6nnnfcg) {
		this.c6nnnfcg = c6nnnfcg;
	}
	@Override
	public String toString() {
		return "ReportApi [id=" + id + ", caseName=" + caseName + ", c6nnfig="
				+ c6nnnfcg + ", parameter=" + parameter + ", url=" + url
				+ ", xml=" + xml + ", json=" + json + ", status=" + status
				+ "]";
	}
	
	
}
