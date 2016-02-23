package com.tingyun.test;

import java.util.List;

public interface TestStep {

	public int getSequence();
	public String getName();
	public List<TestTask> getTasks();
	public String getStartTimeExp();
	public String getEndTimeExp();
	public int getSleepTime();
}
