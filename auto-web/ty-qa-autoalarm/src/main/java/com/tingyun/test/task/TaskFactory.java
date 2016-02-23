package com.tingyun.test.task;

import com.tingyun.test.TestTask;

public class TaskFactory {

	public final static String TYPE_UPLOAD = "upload";
	
	public static TestTask get(String type){
		TestTask task = null;
		if(TYPE_UPLOAD.equals(type)){
			task = new UploadTask();
		}
		
		return task;
	}
}
