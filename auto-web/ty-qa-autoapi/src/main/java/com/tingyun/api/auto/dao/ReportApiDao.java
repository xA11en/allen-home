package com.tingyun.api.auto.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tingyun.api.auto.entity.ReportApiBean;


/**
* @author :chenjingli 
* @version ：2015-7-29 上午11:28:30 
* @decription:
 */
public interface ReportApiDao {
	
	List<ReportApiBean> findAll()throws Exception;
	void del(int id)throws Exception;
	void save(Object params[])throws Exception;
	ReportApiBean findById(int id) throws Exception;
	void update(ReportApiBean r)throws Exception;
	List<ReportApiBean> findAllByPaging(int pages,int rowsPerPage)throws Exception;
	int totalPages(int rowsPerPages) throws Exception;
	List<String> findXmlAndJsonBuId(int id)throws Exception;
	List<ReportApiBean> findAllByPagingAndName(String caseName)throws Exception;
}
