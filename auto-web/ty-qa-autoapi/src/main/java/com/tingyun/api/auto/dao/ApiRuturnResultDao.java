package com.tingyun.api.auto.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tingyun.api.auto.entity.ApiRuturnResultBean;


public interface ApiRuturnResultDao {
	
	void saveAppApiResult(Object params[])throws Exception;
	List<ApiRuturnResultBean>  selectAll()throws Exception;
	List<ApiRuturnResultBean>  selectByAppId(int id)throws Exception;
	
	
}
