package com.tingyun.api.auto.entity.alarm;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author :chenjingli 
* @version ：2015-11-23 下午2:01:49 
* @decription: 听云警报触发条件
 */
public class AlarmTriggerCondition implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer metric_id; /*指标ID：1 - 响应时间 2 - 交互时间 3 - 视图加载时间 4 - 图像处理时间 5 - 数据存储时间 6 - 网络访问时间 7 - JSON处理时间
								8 - 网络错误率  9 - HTTP错误率  10 - 传输数据量  11 - 崩溃率  12 - 新增崩溃  21 - Apdex  22 - 应用响应时间   23 - 错误率
								24 - CPU使用率  25 - 磁盘IO利用率  26 - 内存使用率  27 - 磁盘空间使用率  41 - 总下载时间  42 - 首屏时间  43 - DNS时间
								44 - 建连时间  45 - 首包时间  46 - 页面对象数  47 - 总下载字节数  48 - 基础页面下载字节数  49 - Ping延时   50 - 丢包率 */
	private Integer threshold_type; //阈值类型：0 - NA 1 - 静态阈值 2 - 动态阈值
	
	private Integer dynamic_baseline_type;//动态基线类型：0 - 无 1 - 前一小时平均值 2 - 前一天平均值 3 - 前一天同时段平均值 4 - 上周平均值 
										//005 - 上周同一天平均值 6 - 上周同一天同一时段平均值
	private BigDecimal warn_threshold;//警报策略
	
	private Integer warn_delay;//警告事件持续时间（分钟）
	
	private BigDecimal critical_threshold;//严重阈值
	
	private Integer critical_delay;//严重事件持续时间（分钟）
	
	private Integer sample_group;//分组类型：1 - 总体 2 - 区域 4 - 区域+运营商
	
	private Integer min_throughput;//最小吞吐率(rpm)，0表示不限制或者使用策略缺省值
	
	private Integer compare_method;//0 - 未定义 1 - 大于阈值触发 2 - 小于阈值时触发 3 - 等于阈值时触发    缺省为未定义，对于大部分指标，均为大于阈值触发；Apdex为小于阈值时触发

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMetric_id() {
		return metric_id;
	}

	public void setMetric_id(Integer metric_id) {
		this.metric_id = metric_id;
	}

	public Integer getThreshold_type() {
		return threshold_type;
	}

	public void setThreshold_type(Integer threshold_type) {
		this.threshold_type = threshold_type;
	}

	public Integer getDynamic_baseline_type() {
		return dynamic_baseline_type;
	}

	public void setDynamic_baseline_type(Integer dynamic_baseline_type) {
		this.dynamic_baseline_type = dynamic_baseline_type;
	}

	public BigDecimal getWarn_threshold() {
		return warn_threshold;
	}

	public void setWarn_threshold(BigDecimal warn_threshold) {
		this.warn_threshold = warn_threshold;
	}

	public Integer getWarn_delay() {
		return warn_delay;
	}

	public void setWarn_delay(Integer warn_delay) {
		this.warn_delay = warn_delay;
	}

	public BigDecimal getCritical_threshold() {
		return critical_threshold;
	}

	public void setCritical_threshold(BigDecimal critical_threshold) {
		this.critical_threshold = critical_threshold;
	}

	public Integer getCritical_delay() {
		return critical_delay;
	}

	public void setCritical_delay(Integer critical_delay) {
		this.critical_delay = critical_delay;
	}

	public Integer getSample_group() {
		return sample_group;
	}

	public void setSample_group(Integer sample_group) {
		this.sample_group = sample_group;
	}

	public Integer getMin_throughput() {
		return min_throughput;
	}

	public void setMin_throughput(Integer min_throughput) {
		this.min_throughput = min_throughput;
	}

	public Integer getCompare_method() {
		return compare_method;
	}

	public void setCompare_method(Integer compare_method) {
		this.compare_method = compare_method;
	}

	@Override
	public String toString() {
		return "AlarmTriggerCondition [id=" + id + ", metric_id=" + metric_id
				+ ", threshold_type=" + threshold_type
				+ ", dynamic_baseline_type=" + dynamic_baseline_type
				+ ", warn_threshold=" + warn_threshold + ", warn_delay="
				+ warn_delay + ", critical_threshold=" + critical_threshold
				+ ", critical_delay=" + critical_delay + ", sample_group="
				+ sample_group + ", min_throughput=" + min_throughput
				+ ", compare_method=" + compare_method + "]";
	}
	
	
	
}
