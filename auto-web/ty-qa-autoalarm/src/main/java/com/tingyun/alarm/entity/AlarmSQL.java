package com.tingyun.alarm.entity;
/**
* @author :chenjingli 
* @version ：2015-11-24 上午10:51:38 
* @decription:
 */
public class AlarmSQL {
	/**
	
	//查询策略id
	public static final String SELECT_POLICY_ID_SQL = "select  id from NL_U_ALARM_POLICY order by  id desc LIMIT 0,1";
	
	//查询策略id
	public static final String SELECT_TRIGGER_CONDITION_ID_SQL = "select  * from NL_U_ALARM_TRIGGER_CONDITION order by  id desc LIMIT 0,?";
	//听云警报策略
	public static final String SAVE_POLICY_SQL = "insert into NL_U_ALARM_POLICY" +
												  "(" +
												  "agreement_id,name,product_type,target_type,min_throughput,creator_id,ctime" +
												  ")" +
												  "values" +
												  "(" +
												  "?,?,?,?,?,?,?" +
												  ")";
	
	//警报策略-触发条件关联
	public static final String SAVE_POLICY_TRIGGER_CON_SQL = "insert into NL_U_ALARM_POLICY_TRIGGER_CONDITIONS(policy_id,trigger_condition_id) " +
														 "values(?,?)";
	
	//听云警报触发条件
	public static final String SAVE_TRIGGER_CONDITION_SQL = "insert into NL_U_ALARM_TRIGGER_CONDITION" +
														"(" +
														"metric_id,threshold_type,dynamic_baseline_type,warn_threshold,warn_delay,critical_threshold,critical_delay,sample_group,min_throughput,compare_method" +
														") " +
														"values" +
														"(" +
														"?,?,?,?,?,?,?,?,?,?" +
														")";
	//听云警报设置
	public static final String SAVE_SETTINGS_SQL = "insert into NL_U_ALARM_SETTINGS" +
												   "(" +
												   "agreement_id,target_parent_id,target_id,target_type,trigger_policy_id,status,creator_id,ctime" +
												   ")" +
												   "values" +
												   "(" +
												   "?,?,?,?,?,?,?,?" +
						0						   ")";
	*/
	//events事件表查询sql
	
	public static final String SELECT_EVENTS_SQL = "select * from NL_U_ALARM_EVENTS where target_parent_id = ?  and event_type = ? and begin_time >= ? and begin_time <= ?";
	public static final String SELECT_EVENTS_SQL_JIECHU = "select * from NL_U_ALARM_EVENTS where target_parent_id = ?  and event_type = ? and end_time >= ? and end_time <= ?";
	//public static final String SELECT_EVENTS__NO_TAGGETID_SQL = "select * from NL_U_ALARM_EVENTS where target_parent_id = ? and target_type = ? and event_type = ? and begin_time >= ? and begin_time <= ?";
}
