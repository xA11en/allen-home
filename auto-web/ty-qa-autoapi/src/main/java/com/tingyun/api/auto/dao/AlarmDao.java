package com.tingyun.api.auto.dao;

import java.util.List;

import com.tingyun.api.auto.entity.alarm.AlarmEvents;
import com.tingyun.api.auto.entity.alarm.AlarmPolicy;
import com.tingyun.api.auto.entity.alarm.AlarmTriggerCondition;



public interface AlarmDao {
	
	void saveToPolicy(Object params[]) throws Exception; //插入听云警报策略表数据
	
	void saveToPolicyTriggerCon(Object params[][]) throws Exception;//警报策略-触发条件关联表
	
	void saveToTriggerCondition(Object params[][]) throws Exception;//听云警报触发条件
	
	void saveToSettings(Object params[][]) throws Exception;//听云警报设置表
	
	AlarmPolicy selectToPolicyId() throws Exception; //插入听云警报策略表数据
	
	List<AlarmTriggerCondition> selectToTriggerConditionId(int number) throws Exception;//听云警报触发条件
	
	List<AlarmEvents> selectToEvents(int target_parent_id,int target_id,int target_type,int alarm_settings_id)throws Exception;//查询警报事件
}
