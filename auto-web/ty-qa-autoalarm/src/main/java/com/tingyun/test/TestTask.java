package com.tingyun.test;

import java.util.List;
import java.util.Map;

public interface TestTask {

	public void setParams(String key,Object value);
	public TestStep getStep();
	public List doTask();
	public String getName();
	public String getStartTimeExp();
	public String getEndTimeExp();
	public int getSleepTime();
}
