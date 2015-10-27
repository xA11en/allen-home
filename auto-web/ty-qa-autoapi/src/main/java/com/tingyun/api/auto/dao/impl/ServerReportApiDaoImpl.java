package com.tingyun.api.auto.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tingyun.api.auto.dao.DBUtilsDAO;
import com.tingyun.api.auto.dao.ServerReportApiDao;
import com.tingyun.api.auto.entity.ServerReportApiBean;
import com.tingyun.api.auto.entity.ServerReportApiSQL;
import com.tingyun.api.auto.utils.DBUtils;


@Repository
@Transactional
public class ServerReportApiDaoImpl  implements ServerReportApiDao  {
	
	private static Logger logger = LoggerFactory.getLogger(ServerReportApiDaoImpl.class);
	
	@Autowired DBUtilsDAO dbUtilsDAO;
	
	@Override
	public List<ServerReportApiBean> findAll()throws Exception {
		logger.info("开始查询所有的api接口数据..........");
		List<ServerReportApiBean> list =  dbUtilsDAO.query(ServerReportApiBean.class,ServerReportApiSQL.FIND_ALL_SQL,null);  
		return list;
	}
	
	@Override
	public void del(int id) throws Exception {
		// TODO Auto-generated method stub
		dbUtilsDAO.update(ServerReportApiSQL.DELETE_SQL, new Integer[]{id});
	}

	@Override
	public void save(Object params[]) throws Exception {
		// TODO Auto-generated method stub
		dbUtilsDAO.update(ServerReportApiSQL.SAVE_SQL, params); 
	}

	@Override
	public void update(ServerReportApiBean r) throws SQLException  {
		// TODO Auto-generated method stub
			dbUtilsDAO.update(ServerReportApiSQL.UPDATE_SQL, new String[]
					{r.getCaseName(),r.getAuthKey(),r.getParameter(),r.getUrl(),String.valueOf(r.getId())});
	}

	@Override
	public ServerReportApiBean findById(int id) throws Exception {
		  ServerReportApiBean api =	dbUtilsDAO.findFirst(ServerReportApiBean.class,"select * from TEST_SERVER_API where id=" + id,null);
		  return api;
	}

	@Override
	public List<ServerReportApiBean> findAllByPaging(int pages, int rowsPerPage) throws SQLException
			 {
		logger.info("开始查询所有的api接口数据..........");
			int start = rowsPerPage * (pages - 1);
		    int end = rowsPerPage; 
		    List<ServerReportApiBean> list =  dbUtilsDAO.query(ServerReportApiBean.class, ServerReportApiSQL.PAGE_SQL, start,end);
		    return list;
	}

	@Override
	public int totalPages(int rowsPerPages) throws Exception {
		int totalPages = 0;
		try{
			ResultSet rst = DBUtils.getConnection().createStatement()
					.executeQuery(ServerReportApiSQL.COUNT_SQL);
			int totalRows = 0;
			if(rst.next()){
				totalRows = rst.getInt(1);
			}
			totalPages = (totalRows % rowsPerPages == 0) ? 
					totalRows / rowsPerPages:totalRows / rowsPerPages + 1;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return totalPages;
	}
	
	@Override
	public List<String> findXmlAndJsonBuId(int id) throws Exception {
		List<String> listString = null;
		try{
			listString = new ArrayList<String>();
			ServerReportApiBean r =  new QueryRunner().query(DBUtils.getConnection(),
					ServerReportApiSQL.FIND_XML_JSON_SQL,  new BeanHandler<ServerReportApiBean>(ServerReportApiBean.class),id );
			listString.add(r.getJson());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询单条数据异常：{}",e);
			throw e;
		}
		return listString;
	}

	@Override
	public List<ServerReportApiBean> findAllByPagingAndName(
			String caseName) throws Exception {
		logger.info("开始查询所有的api接口数据..........");
	    String sql ="select * from TEST_SERVER_API where caseName like '%"+caseName+"%' order by id desc";
	    List<ServerReportApiBean> list =  dbUtilsDAO.query
	    		(
	    		ServerReportApiBean.class, sql
	    		);
	    return list;
	}
	


}
