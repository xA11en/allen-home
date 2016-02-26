package com.tingyun.alarm.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tingyun.alarm.entity.AlarmEvents;
import com.tingyun.alarm.entity.AlarmTestResults;
import com.tingyun.test.TestJobContext;
import com.tingyun.test.task.AbstractTestTask;

public class CheckAlarmEventDataTask extends AbstractTestTask {
	
	private static final String DB_RESULT_FAIL = "TEST FAIL 无报警数据产生 !";
	
	private static final String DB_RESULT_WARNING = "触发警告通知 !";
	
	private static final String DB_RESULT_SERIOUS= "触发严重通知 !";

	public CheckAlarmEventDataTask() {
	}

	@Override
	public List doTask() {
		AlarmTestResults testResults = new AlarmTestResults();
		Map<String,Object> params = this.params;
		String dataName = (String)params.get("dataName");
		TestJobContext context = TestJobContext.getContext();
		String desc = (String) context.get("description");
		List<AlarmEvents> alarmEvents = (List<AlarmEvents>) context.get(dataName+"_data");
		
		if(alarmEvents.size() == 0){
			testResults.setDescription(desc);
			testResults.setResult(DB_RESULT_FAIL);
			System.out.println(DB_RESULT_FAIL);
		}
		for (AlarmEvents alarm : alarmEvents) {
			if(alarm.getEvent_level() ==1 && alarm.getStatus() ==1){
				testResults.setDescription(desc);
				testResults.setResult(DB_RESULT_WARNING);
			}else if(alarm.getEvent_level() ==2 && alarm.getStatus() ==1){
				testResults.setDescription(desc);
				testResults.setResult(DB_RESULT_SERIOUS);
			}else{
				testResults.setDescription(desc);
				testResults.setResult(DB_RESULT_WARNING);
			}
		}
		this.writeTestResultToText(testResults);
		return null;
	}
	
	
	
	private void writeTestResultToText(AlarmTestResults alarmTestResults){
		FileWriter fw = null; 
		try { 
			fw = new FileWriter( System.getProperty("user.dir")+"\\src\\main\\resources\\alarmResult.txt",true);// 第二个参数 true 表示写入方式是追加方式 
			fw.write(alarmTestResults.getDescription() + "   ----------------    " + alarmTestResults.getResult()+"\r\n"); 
			}
		catch (Exception e) 
		{ 
			System.out.println("书写日志发生错误：" + e.toString()); 
			}finally { 
			try{ 
				fw.flush();
				fw.close(); 
				}
			catch (IOException e){ // TODO 自动生成 catch 块 
			e.printStackTrace();
			}
			} 
		}
}
