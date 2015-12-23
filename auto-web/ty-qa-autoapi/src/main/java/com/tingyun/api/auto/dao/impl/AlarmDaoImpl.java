package com.tingyun.api.auto.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tingyun.api.auto.dao.AlarmDao;
import com.tingyun.api.auto.dao.DBUtilsDAO;
import com.tingyun.api.auto.entity.ReportApiBean;
import com.tingyun.api.auto.entity.ReportApiSQL;
import com.tingyun.api.auto.entity.alarm.AlarmEvents;
import com.tingyun.api.auto.entity.alarm.AlarmPolicy;
import com.tingyun.api.auto.entity.alarm.AlarmSQL;
import com.tingyun.api.auto.entity.alarm.AlarmTriggerCondition;

@Repository
@Transactional
public class AlarmDaoImpl implements AlarmDao{
	
	@Autowired DBUtilsDAO dbUtilsDAO;
	
	@Override
	public void saveToPolicy(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		returnDS();
		dbUtilsDAO.update(AlarmSQL.SAVE_POLICY_SQL, params);
	}

	@Override
	public void saveToPolicyTriggerCon(Object[][] params) throws Exception {
		// TODO Auto-generated method stub
		returnDS();
		dbUtilsDAO.insertMore(AlarmSQL.SAVE_POLICY_TRIGGER_CON_SQL, params);
	}

	@Override
	public void saveToTriggerCondition(Object[][] params) throws Exception {
		// TODO Auto-generated method stub
		returnDS();
		dbUtilsDAO.insertMore(AlarmSQL.SAVE_TRIGGER_CONDITION_SQL, params);
	}

	@Override
	public void saveToSettings(Object[][] params) throws Exception {
		// TODO Auto-generated method stub
		returnDS();
		dbUtilsDAO.insertMore(AlarmSQL.SAVE_SETTINGS_SQL, params);
	}
	

	@Override
	public List<AlarmTriggerCondition> selectToTriggerConditionId(int number)
			throws Exception {
		// TODO Auto-generated method stub
		returnDS();
		List<AlarmTriggerCondition> list =  dbUtilsDAO.query(AlarmTriggerCondition.class, AlarmSQL.SELECT_TRIGGER_CONDITION_ID_SQL,number );
		return list;
	}

	@Override
	public AlarmPolicy selectToPolicyId() throws Exception {
		// TODO Auto-generated method stub
		returnDS();
		AlarmPolicy ap =	dbUtilsDAO.findFirst(AlarmPolicy.class,AlarmSQL.SELECT_POLICY_ID_SQL,null);
		return ap;
	}

	@Override
	public List<AlarmEvents> selectToEvents(int target_parent_id,
			int target_id, int target_type, int alarm_settings_id)
			throws Exception {
		// TODO Auto-generated method stub
		returnDS();
		List<AlarmEvents> alarmEvents = dbUtilsDAO.query(AlarmEvents.class,AlarmSQL.SELECT_EVENTS_SQL,
				target_parent_id,target_id,target_type,alarm_settings_id);
		
		return alarmEvents;
	}

	private void returnDS(){
		DBUtilsDAO.setCustomerType(DBUtilsDAO.DATA_SOURCE_B);
	}
}
