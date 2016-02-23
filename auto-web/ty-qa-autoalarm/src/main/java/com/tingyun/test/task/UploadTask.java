package com.tingyun.test.task;

import java.util.List;
import java.util.Map;

import com.tingyun.test.TestStep;

public class UploadTask extends AbstractTestTask {

	public UploadTask() {
	}
	
	public UploadTask(TestStep step) {
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
		System.out.println(this.params);
		return null;
	}

}
