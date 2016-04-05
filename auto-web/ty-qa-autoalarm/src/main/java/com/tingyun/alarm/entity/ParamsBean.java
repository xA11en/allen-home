package com.tingyun.alarm.entity;

public class ParamsBean {
	public ParamsBean() {
		// TODO Auto-generated constructor stub
	}
	public ParamsBean(String k,String v) {
		// TODO Auto-generated constructor stub
		this.key = k;
		this.value = v;
	}
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
