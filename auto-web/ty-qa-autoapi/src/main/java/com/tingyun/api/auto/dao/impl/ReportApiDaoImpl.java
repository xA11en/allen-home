package com.tingyun.api.auto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.entity.ReportApi;
import com.tingyun.api.auto.utils.ConnectionContext;
import com.tingyun.api.auto.utils.DBUtils;



public class ReportApiDaoImpl  implements ReportApiDao  {
	
	private static final String FIND_ALL_SQL = "select * from REPORT_API order by id";
	
	private static final String DELETE_SQL = "delete from REPORT_API where id=?";
	
	private static final String SAVE_SQL = "insert into REPORT_API(caseName,c6nnnfcg,parameter,url) values(?,?,?,?)";
	
	private static final String UPDATE_SQL = "update REPORT_API set caseName=?,c6nnnfcg=?,parameter=?,url=? where id=?";
	
	private static final String COUNT_SQL = "select count(*) from REPORT_API";
	
	private static final String FIND_XML_JSON_SQL = "select xml,json from REPORT_API where id=?";
	private static Logger logger = LoggerFactory.getLogger(ReportApiDaoImpl.class);
	@Override
	public List<ReportApi> findAll()throws Exception {
		logger.info("开始查询所有的api接口数据..........");
		List<ReportApi> list = null;
		try {
			QueryRunner runner = new QueryRunner();  
			list =  runner.query(ConnectionContext.getInstance().getConnection(),FIND_ALL_SQL, new BeanListHandler<ReportApi>(ReportApi.class));  
		} catch (SQLException e) {
			logger.error("查询全部异常：{}",e);
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void del(int id) throws Exception {
		// TODO Auto-generated method stub
		try{
			PreparedStatement prep = 
					ConnectionContext.getInstance().getConnection().prepareStatement(DELETE_SQL);
			prep.setInt(1, id);
			prep.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("删除数据异常：{}",e);
			throw e;
		}
	}

	@Override
	public void save(Object params[]) throws Exception {
		// TODO Auto-generated method stub
		try{
			new QueryRunner().update(ConnectionContext.getInstance().getConnection(),SAVE_SQL, params); 
		}catch(Exception e1){
			logger.error("编辑api接口数据异常：{}",e1);
			e1.printStackTrace();
			throw e1;
		}
	}

	@Override
	public void update(ReportApi r) throws Exception {
		// TODO Auto-generated method stub
		try{
			PreparedStatement prep = 
					ConnectionContext.getInstance().getConnection().prepareStatement(UPDATE_SQL);
			prep.setString(1, r.getCaseName());
			prep.setString(2, r.getC6nnnfcg());
			prep.setString(3, r.getParameter());
			prep.setString(4, r.getUrl());
			prep.setInt(5, r.getId());
			prep.executeUpdate();
		}catch(Exception e1){
			logger.error("修改api接口数据异常：{}",e1);
			e1.printStackTrace();
			throw e1;
		}
	}

	@Override
	public ReportApi findById(int id) throws Exception {
		ReportApi api = null;
		try{
			Statement stat = 
					ConnectionContext.getInstance().getConnection().createStatement();
			ResultSet rst =
				stat.executeQuery(
						"select * from REPORT_API where id=" + id);
			if(rst.next()){
				 api = new ReportApi();
				 api.setId(rst.getInt("id"));
				 api.setCaseName(rst.getString("caseName"));
				 api.setC6nnnfcg(rst.getString("c6nnnfcg"));
				 api.setParameter(rst.getString("parameter"));
				 api.setUrl(rst.getString("url"));
				 api.setXml(rst.getString("xml"));
				 api.setJson(rst.getString("json"));
			}
		}catch(Exception e1){
			logger.error("查询reportApi单条记录数据异常：{}",e1);
			e1.printStackTrace();
			throw e1;
		}
	  return api;
	}

	@Override
	public List<ReportApi> findAllByPaging(int pages, int rowsPerPage)
			throws Exception {
		logger.info("开始查询所有的api接口数据..........");
		List<ReportApi> list = null;
		try {
			QueryRunner runner = new QueryRunner();  
			int start = rowsPerPage * (pages - 1) + 1;
		    int end = start + rowsPerPage; 
			list =  runner.query(ConnectionContext.getInstance().getConnection(),FIND_ALL_SQL+" limit ?,?", 
					new BeanListHandler<ReportApi>(ReportApi.class),end,start);  
		} catch (SQLException e) {
			logger.error("查询全部异常：{}",e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int totalPages(int rowsPerPages) throws Exception {
		int totalPages = 0;
		try{
			ResultSet rst = DBUtils.getConnection().createStatement()
					.executeQuery(COUNT_SQL);
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
			ReportApi r =  new QueryRunner().query(DBUtils.getConnection(),
					FIND_XML_JSON_SQL,  new BeanHandler<ReportApi>(ReportApi.class),id );
			System.out.println(r.getXml());
			listString.add(r.getXml());
			listString.add(r.getJson());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询单条数据异常：{}",e);
			throw e;
		}
		return listString;
	}


}
