package com.tingyun.alarm.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tingyun.alarm.entity.AlarmEvents;
import com.tingyun.alarm.entity.AlarmTestResults;
import com.tingyun.alarm.entity.ProductLineType;
import com.tingyun.test.TestJobContext;
import com.tingyun.test.task.AbstractTestTask;

public class CheckAlarmEventDataTask extends AbstractTestTask {
	
	

	public CheckAlarmEventDataTask() {
	}

	@Override
	public List doTask() {
		List<AlarmTestResults> listAlarmTestResults = new ArrayList<AlarmTestResults>();
		Map<String,Object> params = this.params;
		String dataName = (String)params.get("dataName");
		TestJobContext context = TestJobContext.getContext();
		String desc = (String) context.get("description");
		List<AlarmEvents> alarmEvents = (List<AlarmEvents>) context.get(dataName+"_data");
		if(alarmEvents.size() == 0){
			AlarmTestResults testResults = new AlarmTestResults();
			testResults.setDescription(desc);
			testResults.setResult(ProductLineType.DB_RESULT_FAIL);
			listAlarmTestResults.add(testResults);
			System.out.println(desc+"-------"+ProductLineType.DB_RESULT_FAIL);
		}
		for (AlarmEvents alarm : alarmEvents) {
			AlarmTestResults testResults = new AlarmTestResults();
			if(alarm.getEvent_level() ==1 && alarm.getStatus() ==1){
				testResults.setDescription(desc);
				testResults.setResult(ProductLineType.DB_RESULT_WARNING);
				listAlarmTestResults.add(testResults);
				System.out.println(desc+"--------"+ProductLineType.DB_RESULT_WARNING +" Event_level="+alarm.getEvent_level()+"status="+alarm.getStatus()
						+"targetName="+alarm.getTarget_name());
			}else if(alarm.getEvent_level() ==2 && alarm.getStatus() ==1){
				testResults.setDescription(desc);
				testResults.setResult(ProductLineType.DB_RESULT_SERIOUS);
				listAlarmTestResults.add(testResults);
				System.out.println(desc+"--------"+ProductLineType.DB_RESULT_SERIOUS+" Event_level="+alarm.getEvent_level()+"status="+alarm.getStatus()
						+"targetName="+alarm.getTarget_name());
			}else if(alarm.getEvent_level() ==1 && alarm.getStatus() ==0){
				testResults.setDescription(desc);
				testResults.setResult(ProductLineType.DB_RESULT_WARNING_JIECHU);
				listAlarmTestResults.add(testResults);
				System.out.println(desc+"--------"+ProductLineType.DB_RESULT_WARNING_JIECHU+" Event_level="+alarm.getEvent_level()+"status="+alarm.getStatus()
						+"targetName="+alarm.getTarget_name());
			}else if(alarm.getEvent_level() ==2 && alarm.getStatus() ==0){
				testResults.setDescription(desc);
				testResults.setResult(ProductLineType.DB_RESULT_SERIOUS_JIECHU);
				listAlarmTestResults.add(testResults);
				System.out.println(desc+"--------"+ProductLineType.DB_RESULT_SERIOUS_JIECHU+" Event_level="+alarm.getEvent_level()+"status="+alarm.getStatus()
						+"targetName="+alarm.getTarget_name());
			}else{
				testResults.setDescription(desc);
				testResults.setResult(ProductLineType.DB_RESULT_WARNING);
				listAlarmTestResults.add(testResults);
				System.out.println(ProductLineType.DB_RESULT_WARNING);
			}
		}
		this.writeTestResultToText(listAlarmTestResults);
		return null;
	}
	
	
	
	private void writeTestResultToText(List<AlarmTestResults> list){
		FileWriter fw = null; 
		try { 
			fw = new FileWriter(CheckAlarmEventDataTask.class.getResource("alarmResult.txt").getPath(),true);// 第二个参数 true 表示写入方式是追加方式
			for (AlarmTestResults results : list) {
				fw.write(results.getDescription() + "   ----------------    " + results.getResult()+"\r\n"); 
			}
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
