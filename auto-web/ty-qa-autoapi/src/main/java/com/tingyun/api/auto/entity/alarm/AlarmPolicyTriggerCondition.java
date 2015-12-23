package com.tingyun.api.auto.entity.alarm;

import java.io.Serializable;

/**
* @author :chenjingli 
* @version ：2015-11-23 下午3:44:09 
* @decription: 警报策略-触发条件关联
 */
public class AlarmPolicyTriggerCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7815961424951571291L;
	
	private Integer policy_id;//  报警策略ID   FK(NL_U_ALARM_POLICY.id)
	
	private Integer  trigger_condition_id;//FK(NL_U_ALARM_TRIGGER_CONDITION.id)

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getTrigger_condition_id() {
		return trigger_condition_id;
	}

	public void setTrigger_condition_id(Integer trigger_condition_id) {
		this.trigger_condition_id = trigger_condition_id;
	}

	@Override
	public String toString() {
		return "AlarmPolicyTriggerCondition [policy_id=" + policy_id
				+ ", trigger_condition_id=" + trigger_condition_id + "]";
	}
	
	
	
}
