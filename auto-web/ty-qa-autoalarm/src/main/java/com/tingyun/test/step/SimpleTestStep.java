package com.tingyun.test.step;

import java.util.ArrayList;
import java.util.List;

import com.tingyun.test.TestStep;
import com.tingyun.test.TestTask;

public class SimpleTestStep implements TestStep {
	private String name;
	private int Sequence;
	private List<TestTask> tasks = new ArrayList<TestTask>();
	
	private String startTimeExp;
	private String endTimeExp;
	private int sleepTime;
	
	@Override
	public int getSequence() {
		// TODO Auto-generated method stub
		return Sequence;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public List<TestTask> getTasks() {
		// TODO Auto-generated method stub
		return tasks;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSequence(int sequence) {
		Sequence = sequence;
	}

	public void setTasks(List<TestTask> tasks) {
		this.tasks = tasks;
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
