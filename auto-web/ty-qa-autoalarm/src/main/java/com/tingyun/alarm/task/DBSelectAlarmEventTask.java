package com.tingyun.alarm.task;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.time.DateUtils;

import com.networkbench.newlens.datacollector.util.NumberUtils;
import com.tingyun.alarm.entity.AlarmEvents;
import com.tingyun.alarm.entity.AlarmSQL;
import com.tingyun.alarm.utils.DBUtils;
import com.tingyun.test.TestJobContext;
import com.tingyun.test.task.AbstractTestTask;

public class DBSelectAlarmEventTask extends AbstractTestTask {
	
	private final static int TIME_OFFSET = 60;

	public DBSelectAlarmEventTask() {
	}

	@Override
	public List doTask() {
		Map<String,Object> params = this.params;
		String startTimeExp = (String)params.get("startTimeExp");
		String endTimeExp = (String)params.get("endTimeExp");
		
		
		int targetType = NumberUtils.intValue(params.get("targetType"));
		int targetId = NumberUtils.intValue(params.get("targetId"));
		int targetParentId = NumberUtils.intValue(params.get("targetParentId"));
		
		TestJobContext context = TestJobContext.getContext();
		Date startTime = (Date)context.get(startTimeExp);
		startTime = DateUtils.addSeconds(startTime, -TIME_OFFSET);
		Date endTime = (Date)context.get(endTimeExp);
		endTime = DateUtils.addSeconds(endTime, TIME_OFFSET);
		System.out.println(startTime);
		System.out.println(endTime);
		
		List<AlarmEvents> alarmEvents = null;
		try {
			
					QueryRunner runner = new QueryRunner();
					alarmEvents = 	runner.query
					(DBUtils.getConnection(), AlarmSQL.SELECT_EVENTS_SQL,
							new BeanListHandler<AlarmEvents>(AlarmEvents.class),targetParentId,targetId,targetType,1,startTime,endTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		return alarmEvents;
	}


}
