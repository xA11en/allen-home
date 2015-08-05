package com.tingyun.api.auto.dao;

import java.util.List;

import com.tingyun.api.auto.entity.ReportApi;


/**
* @author :chenjingli 
* @version ：2015-7-29 上午11:28:30 
* @decription:
 */
public interface ReportApiDao {
	
	List<ReportApi> findAll()throws Exception;
	void del(int id)throws Exception;
	void save(Object params[])throws Exception;
	ReportApi findById(int id) throws Exception;
	void update(ReportApi r)throws Exception;
	List<ReportApi> findAllByPaging(int pages,int rowsPerPage)throws Exception;
	int totalPages(int rowsPerPages) throws Exception;
	List<String> findXmlAndJsonBuId(int id)throws Exception;
}
