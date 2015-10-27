package com.tingyun.api.auto.dao;

import java.util.List;

import com.tingyun.api.auto.entity.ServerReportApiBean;


/**
* @author :chenjingli 
* @version ：2015-7-29 上午11:28:30 
* @decription:
 */
public interface ServerReportApiDao {
	
	List<ServerReportApiBean> findAll()throws Exception;
	void del(int id)throws Exception;
	void save(Object params[])throws Exception;
	ServerReportApiBean findById(int id) throws Exception;
	void update(ServerReportApiBean r)throws Exception;
	List<ServerReportApiBean> findAllByPaging(int pages,int rowsPerPage)throws Exception;
	int totalPages(int rowsPerPages) throws Exception;
	List<String> findXmlAndJsonBuId(int id)throws Exception;
	List<ServerReportApiBean> findAllByPagingAndName(String caseName)throws Exception;
}
