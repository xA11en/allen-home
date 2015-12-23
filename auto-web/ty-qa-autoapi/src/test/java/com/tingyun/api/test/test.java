package com.tingyun.api.test;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tingyun.api.auto.dao.AlarmDao;
import com.tingyun.api.auto.entity.ApiRuturnResultSql;
import com.tingyun.api.auto.entity.alarm.AlarmEvents;
import com.tingyun.api.auto.entity.alarm.AlarmPolicy;
import com.tingyun.api.auto.entity.alarm.AlarmSettings;
import com.tingyun.api.auto.entity.alarm.AlarmTriggerCondition;
import com.tingyun.api.auto.utils.DBUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-servlet.xml"})
@TransactionConfiguration(defaultRollback = false)
public class test {
	@Autowired
	private AlarmDao alarmDao;
	
	
	
	//@Test
    public void testSearchApiResult(){
		List<Map<String, Object>> map;
		try {
			map = new QueryRunner(
				).query(DBUtils.getConnection(),ApiRuturnResultSql.SELECT_APP_API_ERROR_SQL, new MapListHandler ());
			for (Map<String, Object> map2 : map) {
				int id = (Integer) map2.get("app_id");
				System.out.println(id);
//				System.out.println(map2.get("app_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    
    @Test
    public void testAlarm(){
    	Integer[] i = {1,11};
    	try {
			getData(1, 1, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
	//Integer[] i  是*指标ID
    private void getData(int product_type,int target_type,Integer[] i) throws Exception{
    	int iNum = i.length;
    	//1 ----------app整体
    	if(1 ==product_type){
//    		if(target_type !=1 || target_type!=2 || target_type!=3 || target_type!=4 || target_type!=11){
//    			System.out.println("没有这几种目标类型，请填写正确监控类型");
//    		}else{
    			AlarmPolicy ap = setPolicy(target_type,product_type);
    			alarmDao.saveToPolicy(new Object[]{ap.getAgreement_id(),ap.getName(),ap.getProduct_type(),ap.getTarget_type(),ap.getMin_throughput()
    					,ap.getCreator_id(),ap.getCtime()});
    			
//    		}
    		if(target_type ==1){
    				Object params[][] = new Object[iNum][];
    				AlarmTriggerCondition ac = setTriggerCon(i[0],1, new BigDecimal(550), 3, new BigDecimal(2100), 4, 1, 200);
    				params[0] = new Object[]{ac.getMetric_id(),ac.getThreshold_type(),ac.getDynamic_baseline_type(),ac.getWarn_threshold(),ac.getWarn_delay()
    						,ac.getCritical_threshold(),ac.getCritical_delay(),ac.getSample_group(),ac.getMin_throughput(),ac.getCompare_method()};
    				
    				
    				AlarmTriggerCondition ac1 = setTriggerCon(i[1],1, new BigDecimal(0.002), 3, new BigDecimal(0.01), 6, 1, 1000);
    				params[1] = new Object[]{ac1.getMetric_id(),ac1.getThreshold_type(),ac1.getDynamic_baseline_type(),ac1.getWarn_threshold(),ac1.getWarn_delay()
    						,ac1.getCritical_threshold(),ac1.getCritical_delay(),ac1.getSample_group(),ac1.getMin_throughput(),ac1.getCompare_method()};
    				alarmDao.saveToTriggerCondition(params);
    		}
    	}
    	/*
    	 * set 关联表
    	 */
    	int policyId = alarmDao.selectToPolicyId().getId();
    	List<AlarmTriggerCondition> list = alarmDao.selectToTriggerConditionId(iNum);
    	Object[][] params = new Object[iNum][];;
    	for (int j = 0; j <  params.length ; j++) {
    		params[j] = new Object[]{policyId,list.get(j).getId()};
		}
    	alarmDao.saveToPolicyTriggerCon(params);
    	/**
    	 * set 警报表
    	 */
    	Object[][] paramsSettings = new Object[1][];
    	//修改这里
    	AlarmSettings as = setSettings(187, target_type, 187, policyId);
    	paramsSettings[0] = new Object[]{as.getAgreement_id(),as.getTarget_parent_id(),as.getTarget_id()
    			,as.getTarget_type(),as.getTrigger_policy_id(),as.getStatus(),as.getCreator_id(),as.getCtime()};
    	alarmDao.saveToSettings(paramsSettings);
    	
    	
    }
    
    /**
     * 
    * @author : chenjingli
    * @decription  组装策略数据
    * @return
    */
    private AlarmPolicy setPolicy(final int target_type,final int product_type){
    	AlarmPolicy ap = new AlarmPolicy();
		ap.setProduct_type(product_type);
		ap.setAgreement_id(1760);
		ap.setTarget_type(target_type);//监控目标
		String name = "qa_policy_name"+System.currentTimeMillis();
		System.out.println("=================================="+name);
		ap.setName(name);//策略名称
		ap.setMin_throughput(100);//最小吞吐率（rpm），0表示不限制 
		ap.setCreator_id(1759);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new java.util.Date());
		Timestamp ts = Timestamp.valueOf(time); 
		ap.setCtime(ts);//当前时间
		return ap;
    }
    /**
    * @author : chenjingli
    * @decription  组装监控点的数据
    * @return
     */
	private AlarmTriggerCondition  setTriggerCon
			(
			int metric_id,int threshold_type,BigDecimal warn_threshold,int warn_delay,BigDecimal critical_threshold, 
			int critical_delay,int sample_group,int min_throughput
			){
	 	AlarmTriggerCondition atc = new AlarmTriggerCondition();
	 	atc.setMetric_id(metric_id);//
	 	atc.setThreshold_type(threshold_type);//
	 	atc.setDynamic_baseline_type(0);
	 	atc.setWarn_delay(warn_delay);
	 	atc.setWarn_threshold(warn_threshold);
	 	atc.setCritical_delay(critical_delay);
	 	atc.setCritical_threshold(critical_threshold);
	 	atc.setSample_group(sample_group);
	 	atc.setMin_throughput(min_throughput);
	 	atc.setCompare_method(2);
	 	return atc;
	}
	/**
	 * 组装警报表的数据
	 */
	private AlarmSettings setSettings(int target_parent_id,int target_type,int target_id, int trigger_policy_id){
		AlarmSettings as = new AlarmSettings();
		as.setAgreement_id(1760);
		as.setTarget_parent_id(target_parent_id);
		as.setTarget_id(target_id);
		as.setTarget_type(target_type);
		as.setTrigger_policy_id(trigger_policy_id);
		as.setStatus(1);
		as.setCreator_id(1759);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new java.util.Date());
		Timestamp ts = Timestamp.valueOf(time); 
		as.setCtime(ts);
		return as	;
	}
	
	/**
	 * 开始对比
	 * @throws Exception 
	 */
	private void assertAlarmResults(int target_parent_id,
			int target_id, int target_type, int alarm_settings_id,BigDecimal warn_threshold,BigDecimal critical_threshold) throws Exception{
		List<AlarmEvents> alarmEvents = alarmDao.selectToEvents(target_parent_id, target_id, target_type, alarm_settings_id);
		if(alarmEvents.size() == 0){
			System.out.println("****************没有报警！！！！！！！！！！！！*********************");
		}else{
			for (AlarmEvents alarme : alarmEvents) {
				//事件级别：0 - 无 1 - 警告 2 - 严重
				if(alarme.getEvent_level()==1){
					if(alarme.getValue().compareTo(warn_threshold)==1){
						System.out.println("已发出警告");
					}else{
						System.out.println("未发出警告");
					}
				}
			}
		}
	}
}
