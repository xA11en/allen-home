package com.tingyun.alarm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
* @author :chenjingli 
* @version ：2015-11-26 下午2:40:08 
* @decription:  警报事件表
 */
public class AlarmEvents implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3956990653811205181L;
	
	private Integer target_parent_id;//监控目标父ID 如对于某监控目标App或关键元素，其父对象为相应的AppId；若监控目标为Server，则其父对象为相应的ServerId
	
	private Integer target_id; //监控目标ID  如某特定的App或关键元素
	
	private Integer target_type; //监控类型：1 - App 2 - App主机 3 - App页面 4 - App关键元素 11 - （听云Network）任务 12 - （听云Network）元素
								 // 21 - （听云Server）应用   22 - （听云Server）关键应用过程   31 - （听云Sys）服务器     32 - （听云Sys）磁盘 
	
	private String target_name;//目标名称
	
	private Integer alarm_settings_id;//警报设置ID
	
	private Integer agreement_id;//合同id
	
	private Integer event_trace_id;//事件跟踪ID（同一组事件使用同一个跟踪ID）
	
	private Integer related_event_id;//关联事件的ID。 仅对报警通知和解除报警类型有效
	
	private Integer event_type;//事件类型：0 - 无 1 - 指标超过阈值 9 - 触发报警 10 - 解除报警通知
	
	private Integer event_level;//事件级别：0 - 无 1 - 警告 2 - 严重
	
	private Integer related_event_type;//关联事件的事件类型    仅对报警通知和解除报警类型有效
	
	private Integer sample_group;//取样分组方式: 1 - 全部 2 - 按区域 4 - 按区域运营商
	
	private Integer metric_id; /*指标ID：1 - 响应时间 2 - 交互时间 3 - 视图加载时间 4 - 图像处理时间 5 - 数据存储时间 6 - 网络访问时间 7 - JSON处理时间
								8 - 网络错误率  9 - HTTP错误率  10 - 传输数据量  11 - 崩溃率  12 - 新增崩溃  21 - Apdex  22 - 应用响应时间   23 - 错误率
								24 - CPU使用率  25 - 磁盘IO利用率  26 - 内存使用率  27 - 磁盘空间使用率  41 - 总下载时间  42 - 首屏时间  43 - DNS时间
								44 - 建连时间  45 - 首包时间  46 - 页面对象数  47 - 总下载字节数  48 - 基础页面下载字节数  49 - Ping延时   50 - 丢包率 */
	private BigDecimal threshold;//当前触发警告的阈值
	
	private Integer city_id;//触发警报城市ID
	
	private Integer carrier_id;//触发警报运营商ID
	
	private BigDecimal value;//警报触发时间段内的平均值或错误率  对响应时间警报，是指该警报触发时间段内的平均值；对错误警报，则为警报触发时间段内错误率。若警报处于开放状态，则为从警报超过阈值到被触发（即警报前3
	
	private Integer count;//警报触发时间段内的请求数    时间段的定义同value字段
	
	private String message;//备注
	
	private Timestamp begin_time;//事件开始时间
	
	private Timestamp end_time;//事件结束时间   备忘，触发报警或开放中警报无截止时间
	
	private Timestamp ctime;//事件触发或创建时间
	
	private Integer status;//状态：0 - 关闭   1 - 开放
	
	private Integer read_status;//阅读状态：0 - 未读 1 - 已读

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

	public String getTarget_name() {
		return target_name;
	}

	public void setTarget_name(String target_name) {
		this.target_name = target_name;
	}

	public Integer getAlarm_settings_id() {
		return alarm_settings_id;
	}

	public void setAlarm_settings_id(Integer alarm_settings_id) {
		this.alarm_settings_id = alarm_settings_id;
	}

	public Integer getAgreement_id() {
		return agreement_id;
	}

	public void setAgreement_id(Integer agreement_id) {
		this.agreement_id = agreement_id;
	}

	public Integer getEvent_trace_id() {
		return event_trace_id;
	}

	public void setEvent_trace_id(Integer event_trace_id) {
		this.event_trace_id = event_trace_id;
	}

	public Integer getRelated_event_id() {
		return related_event_id;
	}

	public void setRelated_event_id(Integer related_event_id) {
		this.related_event_id = related_event_id;
	}

	public Integer getEvent_type() {
		return event_type;
	}

	public void setEvent_type(Integer event_type) {
		this.event_type = event_type;
	}

	public Integer getEvent_level() {
		return event_level;
	}

	public void setEvent_level(Integer event_level) {
		this.event_level = event_level;
	}

	public Integer getRelated_event_type() {
		return related_event_type;
	}

	public void setRelated_event_type(Integer related_event_type) {
		this.related_event_type = related_event_type;
	}

	public Integer getSample_group() {
		return sample_group;
	}

	public void setSample_group(Integer sample_group) {
		this.sample_group = sample_group;
	}

	public Integer getMetric_id() {
		return metric_id;
	}

	public void setMetric_id(Integer metric_id) {
		this.metric_id = metric_id;
	}

	public BigDecimal getThreshold() {
		return threshold;
	}

	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public Integer getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(Integer carrier_id) {
		this.carrier_id = carrier_id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Timestamp begin_time) {
		this.begin_time = begin_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRead_status() {
		return read_status;
	}

	public void setRead_status(Integer read_status) {
		this.read_status = read_status;
	}

	@Override
	public String toString() {
		return "AlarmEvents [target_parent_id=" + target_parent_id
				+ ", target_id=" + target_id + ", target_type=" + target_type
				+ ", target_name=" + target_name + ", alarm_settings_id="
				+ alarm_settings_id + ", agreement_id=" + agreement_id
				+ ", event_trace_id=" + event_trace_id + ", related_event_id="
				+ related_event_id + ", event_type=" + event_type
				+ ", event_level=" + event_level + ", related_event_type="
				+ related_event_type + ", sample_group=" + sample_group
				+ ", metric_id=" + metric_id + ", threshold=" + threshold
				+ ", city_id=" + city_id + ", carrier_id=" + carrier_id
				+ ", value=" + value + ", count=" + count + ", message="
				+ message + ", begin_time=" + begin_time + ", end_time="
				+ end_time + ", ctime=" + ctime + ", status=" + status
				+ ", read_status=" + read_status + "]";
	}
	
	
	
}
