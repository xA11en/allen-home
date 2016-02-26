package com.tingyun.alarm.task;

import java.util.List;
import java.util.Map;

import com.tingyun.test.TestStep;
import com.tingyun.test.task.AbstractTestTask;

public class SQL1Task extends AbstractTestTask {

	public SQL1Task() {
	}
	
	public SQL1Task(TestStep step) {
		super(step);
	}

	@Override
	public TestStep getStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List doTask() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName()+"===========");
		return null;
	}


}
