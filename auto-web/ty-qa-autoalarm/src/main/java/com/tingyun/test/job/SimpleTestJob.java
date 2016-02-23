package com.tingyun.test.job;

import java.util.ArrayList;
import java.util.List;

import com.tingyun.test.TestJob;
import com.tingyun.test.TestStep;

public class SimpleTestJob implements TestJob {
	
	private String name;
	private List<TestStep> steps = new ArrayList<TestStep>();
	private String description;
	private List results;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<TestStep> getSteps() {
		return steps;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public List<String> getResults() {
		return results;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSteps(List<TestStep> steps) {
		this.steps = steps;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setResults(List results) {
		this.results = results;
	}
	
	

}
