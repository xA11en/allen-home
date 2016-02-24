package com.tingyun.test.builder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.digester3.Digester;
import org.codehaus.plexus.util.StringUtils;
import org.xml.sax.SAXException;

import com.tingyun.test.TestJob;
import com.tingyun.test.TestStep;
import com.tingyun.test.TestTask;
import com.tingyun.test.builder.xml.Job;
import com.tingyun.test.builder.xml.Param;
import com.tingyun.test.builder.xml.Step;
import com.tingyun.test.builder.xml.Task;
import com.tingyun.test.job.SimpleTestJob;
import com.tingyun.test.step.SimpleTestStep;
import com.tingyun.test.task.TaskFactory;

public class XmlConfigBuilder {

	protected Digester digester = new Digester();  
	
	public TestJob buildTestJob(String path) throws IllegalAccessException, InvocationTargetException{
		Job job = buildJob(path);
		List<Step> steps = job.getSteps();
		
		TestJob testJob = new SimpleTestJob();
		BeanUtils.copyProperty(testJob, "name", job.getName());
		
		//steps
		for(int i=0;i<steps.size();i++){
			Step step = steps.get(i);
			TestStep testStep = new SimpleTestStep();
			
			if(StringUtils.isEmpty(step.getName())){
				BeanUtils.copyProperty(testStep, "name", "step_"+(i+1));
			}else{
				BeanUtils.copyProperty(testStep, "name", step.getName());
			}
			BeanUtils.copyProperty(testStep, "startTimeExp", step.getStartTimeExp());
			BeanUtils.copyProperty(testStep, "endTimeExp", step.getEndTimeExp());
			BeanUtils.copyProperty(testStep, "sleepTime", step.getSleepTime());
			
			testJob.getSteps().add(testStep);
			//tasks
			List<Task> tasks = step.getTasks();
			for(int j=0;j<tasks.size();j++){
				Task task = tasks.get(j);
				
				TestTask testTask = null;
				if(StringUtils.isNotEmpty(task.getClazz())){
					try {
						testTask = (TestTask) Class.forName(task.getClazz()).newInstance();
					} catch (InstantiationException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}else{
					String type = StringUtils.isNotEmpty(task.getType())?task.getType():"upload";
					testTask = TaskFactory.get(type);
				}
				
				if(StringUtils.isEmpty(task.getName())){
					BeanUtils.copyProperty(testTask, "name", "task_"+(i+1));
				}else{
					BeanUtils.copyProperty(testTask, "name", task.getName());
				}
				BeanUtils.copyProperty(testTask, "startTimeExp", task.getStartTimeExp());
				BeanUtils.copyProperty(testTask, "endTimeExp", task.getEndTimeExp());
				BeanUtils.copyProperty(testTask, "sleepTime", task.getSleepTime());
				
				testStep.getTasks().add(testTask);
				
				//params
				for(int k=0;k<task.getParams().size();k++){
					Param param = task.getParams().get(k);
					testTask.setParams(param.getKey(), param.getValue());
				}
			}
		}
		return testJob;
	}
	
	public Job buildJob(String path){
		
		final String jobXpath = "Job";
		final String stepXpath = "Job/Step";
		final String taskXpath = "Job/Step/Task";
		final String paramXpath = "Job/Step/Task/params/param";
		
		digester.addObjectCreate(jobXpath, Job.class);  
		digester.addSetProperties(jobXpath);
		
		digester.addObjectCreate(stepXpath, Step.class); 
		digester.addSetProperties(stepXpath);
		digester.addSetNext(stepXpath,"addStep");
		
		digester.addObjectCreate(taskXpath, Task.class); 
		digester.addSetProperties(taskXpath);
//		digester.addSetProperty(taskXpath, "class", "clazz");
		digester.addCallMethod(taskXpath, "setClazz",1);
		digester.addCallParam(taskXpath, 0, "class");
		digester.addSetNext(taskXpath,"addTask");
		
		digester.addObjectCreate(paramXpath, Param.class); 
		digester.addSetProperties(paramXpath);
		digester.addSetNext(paramXpath,"addParam");
		
		
		
//		digester.addObjectCreate("Job/Step", targetClass);  
//		digester.addSetProperties(xpath);
//		digester.addSetNestedProperties(xpath);
//		digester.addSetNext(xpath,"add");
		
		Job job = null;
		
		try {
			job = (Job)digester.parse(getClassPathFileInputStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		System.out.println(job.getName());
		System.out.println(job.getSteps().get(0).getName());
		System.out.println(job.getSteps().get(0).getTasks().get(0).getName());
		System.out.println(job.getSteps().get(0).getTasks().get(0).getClazz());
		return job;
	}
	
	public static InputStream getClassPathFileInputStream(String path){
		return XmlConfigBuilder.class.getClassLoader().getResourceAsStream( path);
	}
	
	public static void main(String[] args) {
		try {
			TestJob job = new XmlConfigBuilder().buildTestJob("conf/Test-Case-1.xml");
			System.out.println(job.getSteps().get(0).getTasks().get(0));
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
