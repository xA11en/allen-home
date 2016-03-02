package com.tingyun.alarm.task;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.time.DateUtils;

import com.networkbench.newlens.datacollector.util.NumberUtils;
import com.tingyun.alarm.Main;
import com.tingyun.alarm.entity.AlarmEvents;
import com.tingyun.alarm.entity.AlarmSQL;
import com.tingyun.alarm.utils.DBUtils;
import com.tingyun.test.TestJobContext;
import com.tingyun.test.task.AbstractTestTask;

public class DBSelectAlarmEventTask extends AbstractTestTask {
	
	private final static int TIME_OFFSET = 130;

	public DBSelectAlarmEventTask() {
	}

	@Override
	public List doTask() {
		Map<String,Object> params = this.params;
		String startTimeExp = (String)params.get("startTimeExp");
		String endTimeExp = (String)params.get("endTimeExp");
//		int targetType = NumberUtils.intValue(params.get("targetType"));
//		int targetId = NumberUtils.intValue(params.get("targetId"));
		int targetParentId = NumberUtils.intValue(params.get("targetParentId"));
		
		TestJobContext context = TestJobContext.getContext();
		Date startTime = (Date)context.get(startTimeExp);
		startTime = DateUtils.addSeconds(startTime, -TIME_OFFSET);
		String sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
		Date endTime = (Date)context.get(endTimeExp);
		endTime = DateUtils.addSeconds(endTime, TIME_OFFSET);
		String eTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
		List<AlarmEvents> alarmEvents = null;
		if(Main.SWITCH == false){
			alarmEvents =this.excuteSql(AlarmSQL.SELECT_EVENTS_SQL_JIECHU, targetParentId, sTime, eTime);
		}else{
			alarmEvents =this.excuteSql(AlarmSQL.SELECT_EVENTS_SQL, targetParentId, sTime, eTime);
		}
		
		return alarmEvents;
	}
	
	private List<AlarmEvents> excuteSql(String sql,int targetParentId ,String sTime,String eTime){
		List<AlarmEvents> alarmEvents = null;
		try {
			QueryRunner runner = new QueryRunner();
			Thread.sleep(3000);
			 alarmEvents = 	runner.query
					(DBUtils.getConnection(), sql,
							new BeanListHandler<AlarmEvents>(AlarmEvents.class),targetParentId,1,sTime,eTime);
			System.out.println("查询结果显示："+alarmEvents.size()+"   targetId!=0 的情况下的参数"+
					"targetParentId = "+targetParentId+" eventType="+1+" startTime="+sTime +" endTime="+eTime);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return alarmEvents;
	}


}
