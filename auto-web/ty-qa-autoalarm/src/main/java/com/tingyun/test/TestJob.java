package com.tingyun.test;

import java.util.List;

public interface TestJob {

	public String getName();
	public List<TestStep> getSteps();
	public String getDescription();
	public List<String> getResults();
}
