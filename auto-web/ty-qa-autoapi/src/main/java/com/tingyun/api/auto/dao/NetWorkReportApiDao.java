package com.tingyun.api.auto.dao;

import java.util.List;

import com.tingyun.api.auto.entity.NetworkReportApiBean;


/**
* @author :chenjingli 
* @version ：2015-7-29 上午11:28:30 
* @decription:
 */
public interface NetWorkReportApiDao {
	
	List<NetworkReportApiBean> findAll()throws Exception;
	void del(int id)throws Exception;
	void save(Object params[])throws Exception;
	NetworkReportApiBean findById(int id) throws Exception;
	void update(NetworkReportApiBean r)throws Exception;
	List<NetworkReportApiBean> findAllByPaging(int pages,int rowsPerPage)throws Exception;
	int totalPages(int rowsPerPages) throws Exception;
	List<String> findXmlAndJsonBuId(int id)throws Exception;
	List<NetworkReportApiBean> findAllByPagingAndName(String caseName)throws Exception;
}
