package com.tingyun.api.auto.entity.alarm;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author :chenjingli 
* @version ：2015-11-23 上午11:15:10 
* @decription:  听云警报策略
 */
public class AlarmPolicy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer agreement_id;//关联合同ID  FK(NL_U_AGREEMENT.id) 
	
	private Integer  product_type;// 产品类型：1 - 听云App 2 - 听云Server 3 - 听云Sys 4 - 听云Network
	
	private Integer target_type;  //监控类型1- App 2 - App主机 3 - App页面 4 - App关键元素 11 - （听云Network）任务 12 - （听云Network）元素
	 								// 21 - （听云Server）应用   22 - （听云Server）关键应用过程   31 - （听云Sys）服务器     32 - （听云Sys）磁盘 	
	private String name;//策略名称 
	
	private Integer min_throughput;//最小吞吐率（rpm），0表示不限制 
	
	private Integer creator_id;//创建者ID
	
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

	public Integer getProduct_type() {
		return product_type;
	}

	public void setProduct_type(Integer product_type) {
		this.product_type = product_type;
	}

	public Integer getTarget_type() {
		return target_type;
	}

	public void setTarget_type(Integer target_type) {
		this.target_type = target_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMin_throughput() {
		return min_throughput;
	}

	public void setMin_throughput(Integer min_throughput) {
		this.min_throughput = min_throughput;
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
		return "AlarmPolicy [id=" + id + ", agreement_id=" + agreement_id
				+ ", product_type=" + product_type + ", target_type="
				+ target_type + ", name=" + name + ", min_throughput="
				+ min_throughput + ", creator_id=" + creator_id + ", ctime="
				+ ctime + ", mtime=" + mtime + "]";
	}
	
}
