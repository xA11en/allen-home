package com.tingyun.alarm.task;

import java.util.List;
import com.tingyun.alarm.entity.AlarmEvents;
import com.tingyun.test.TestJobContext;
import com.tingyun.test.task.AbstractTestTask;

public class CheckAlarmEventDataTask extends AbstractTestTask {

	public CheckAlarmEventDataTask() {
	}

	@Override
	public List doTask() {
		TestJobContext context = TestJobContext.getContext();
		List<AlarmEvents> alarmEvents = (List<AlarmEvents>) context.get("task_selectDB_alarm_event_data");
		
		if(alarmEvents.size() == 0){
			System.out.println("test fail ！ 没数据！！");
		}
		for (AlarmEvents alarm : alarmEvents) {
			if(alarm.getEvent_level() ==1 && alarm.getStatus() ==1){
				System.out.println("触发的是警告通知，已触发--------------------test pass !!"+"数据库条数为： "+alarmEvents.size());
			}else if(alarm.getEvent_level() ==2 && alarm.getStatus() ==1){
				System.out.println("触发的是严重通知，已触发--------------------test pass !!"+"数据库条数为： "+alarmEvents.size());
			}else{
				System.out.println("test fail !!"+" event_level = "+ alarm.getEvent_level()+"......."+" status = "+alarm.getStatus());
			}
		}
		return null;
	}

}
