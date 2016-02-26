package com.tingyun.test.builder.xml;

import java.util.ArrayList;
import java.util.List;

public class Job {

	private String name;
	private String description;
	private List<Step> steps = new ArrayList<Step>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	public void addStep(Step step){
		this.steps.add(step);
	}
	
	
}
