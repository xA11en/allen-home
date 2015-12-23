package com.tingyun.api.auto.entity.alarm;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author :chenjingli 
* @version ：2015-11-23 上午10:45:15 
* @decription: 听云警报设置表
 */
public class AlarmSettings  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//序列id
	
	private Integer agreement_id; //关联合同账号ID
	
	private Integer target_parent_id;//监控目标父ID 如对于某监控目标App或关键元素，其父对象为相应的AppId；若监控目标为Server，则其父对象为相应的ServerId
	
	private Integer target_id; //监控目标ID  如某特定的App或关键元素
	
	private Integer target_type; //监控类型：1 - App 2 - App主机 3 - App页面 4 - App关键元素 11 - （听云Network）任务 12 - （听云Network）元素
								 // 21 - （听云Server）应用   22 - （听云Server）关键应用过程   31 - （听云Sys）服务器     32 - （听云Sys）磁盘 
	private Integer trigger_policy_id; //警报策略ID
	
	private Integer status; //警报设置启用状态    0删除，1正常
	
	private Integer creator_id; //创建者ID   FK(NL_U_AGREEMENT.id)
	
	private Timestamp ctime;//创建时间
	
	private Timestamp mtime;//修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgreement_id() {
		return agreement_id;
	}

	public void setAgreement_id(Integer agreement_id) {
		this.agreement_id = agreement_id;
	}

	public Integer getTarget_parent_id() {
		return target_parent_id;
	}

	public void setTarget_parent_id(Integer target_parent_id) {
		this.target_parent_id = target_parent_id;
	}

	public Integer getTarget_id() {
		return target_id;
	}

	public void setTarget_id(Integer target_id) {
		this.target_id = target_id;
	}

	public Integer getTarget_type() {
		return target_type;
	}

	public void setTarget_type(Integer target_type) {
		this.target_type = target_type;
	}

	public Integer getTrigger_policy_id() {
		return trigger_policy_id;
	}

	public void setTrigger_policy_id(Integer trigger_policy_id) {
		this.trigger_policy_id = trigger_policy_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getMtime() {
		return mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	@Override
	public String toString() {
		return "AlarmSettings [id=" + id + ", agreement_id=" + agreement_id
				+ ", target_parent_id=" + target_parent_id + ", target_id="
				+ target_id + ", target_type=" + target_type
				+ ", trigger_policy_id=" + trigger_policy_id + ", status="
				+ status + ", creator_id=" + creator_id + ", ctime=" + ctime
				+ ", mtime=" + mtime + "]";
	}

	
	
	
	
}
