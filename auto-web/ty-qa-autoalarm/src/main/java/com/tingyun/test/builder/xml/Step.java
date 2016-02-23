package com.tingyun.test.builder.xml;

import java.util.ArrayList;
import java.util.List;

public class Step {

	private String name;
	private String startTimeExp;
	private String endTimeExp;
	private int sleepTime;
	
	private List<Task> tasks = new ArrayList<Task>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTask(Task task){
		this.tasks.add(task);
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
