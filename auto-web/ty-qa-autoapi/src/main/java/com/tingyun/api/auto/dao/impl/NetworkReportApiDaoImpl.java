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
import com.tingyun.api.auto.dao.NetWorkReportApiDao;
import com.tingyun.api.auto.entity.NetworkReportApiBean;
import com.tingyun.api.auto.entity.NetworkReportApiSQL;
import com.tingyun.api.auto.utils.DBUtils;


@Repository
@Transactional
public class NetworkReportApiDaoImpl  implements NetWorkReportApiDao  {
	
	private static Logger logger = LoggerFactory.getLogger(NetworkReportApiDaoImpl.class);
	
	@Autowired DBUtilsDAO dbUtilsDAO;
	
	@Override
	public List<NetworkReportApiBean> findAll()throws Exception {
		logger.info("开始查询所有的api接口数据..........");
		List<NetworkReportApiBean> list =  dbUtilsDAO.query(NetworkReportApiBean.class,NetworkReportApiSQL.FIND_ALL_SQL,null);  
		return list;
	}
	
	@Override
	public void del(int id) throws Exception {
		// TODO Auto-generated method stub
		dbUtilsDAO.update(NetworkReportApiSQL.DELETE_SQL, new Integer[]{id});
	}

	@Override
	public void save(Object params[]) throws Exception {
		// TODO Auto-generated method stub
		dbUtilsDAO.update(NetworkReportApiSQL.SAVE_SQL, params); 
	}

	@Override
	public void update(NetworkReportApiBean r) throws SQLException  {
		// TODO Auto-generated method stub
			dbUtilsDAO.update(NetworkReportApiSQL.UPDATE_SQL, new String[]
					{r.getCaseName(),r.getAuthKey(),r.getParameter(),r.getUrl(),String.valueOf(r.getId())});
	}

	@Override
	public NetworkReportApiBean findById(int id) throws Exception {
		  NetworkReportApiBean api =	dbUtilsDAO.findFirst(NetworkReportApiBean.class,"select * from TEST_NETWORK_API where id=" + id,null);
		  return api;
	}

	@Override
	public List<NetworkReportApiBean> findAllByPaging(int pages, int rowsPerPage) throws SQLException
			 {
		logger.info("开始查询所有的api接口数据..........");
			int start = rowsPerPage * (pages - 1);
		    int end = rowsPerPage; 
		    List<NetworkReportApiBean> list =  dbUtilsDAO.query(NetworkReportApiBean.class, NetworkReportApiSQL.PAGE_SQL, start,end);
		    return list;
	}

	@Override
	public int totalPages(int rowsPerPages) throws Exception {
		int totalPages = 0;
		try{
			ResultSet rst = DBUtils.getConnection().createStatement()
					.executeQuery(NetworkReportApiSQL.COUNT_SQL);
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
			NetworkReportApiBean r =  new QueryRunner().query(DBUtils.getConnection(),
					NetworkReportApiSQL.FIND_XML_JSON_SQL,  new BeanHandler<NetworkReportApiBean>(NetworkReportApiBean.class),id );
			listString.add(r.getJson());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询单条数据异常：{}",e);
			throw e;
		}
		return listString;
	}

	@Override
	public List<NetworkReportApiBean> findAllByPagingAndName(
			String caseName) throws Exception {
		logger.info("开始查询所有的api接口数据..........");
	    String sql ="select * from TEST_NETWORK_API where caseName like '%"+caseName+"%' order by id desc";
	    List<NetworkReportApiBean> list =  dbUtilsDAO.query
	    		(
	    		NetworkReportApiBean.class, sql
	    		);
	    return list;
	}
	


}
