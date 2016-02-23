package com.tingyun.test.builder.xml;

import java.util.ArrayList;
import java.util.List;

public class Task {

	private String name;
	private String type;
	private String startTimeExp;
	private String endTimeExp;
	private int sleepTime;
	
	private List<Param> params = new ArrayList<Param>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}
	
	public void addParam(Param param){
		this.params.add(param);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartTimeExp() {
		return startTimeExp;
	}

	public void setStartTimeExp(String startTimeExp) {
		this.startTimeExp = startTimeExp;
	}

	public String getEndTimeExp() {
		return endTimeExp;
	}

	public void setEndTimeExp(String endTimeExp) {
		this.endTimeExp = endTimeExp;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
}
