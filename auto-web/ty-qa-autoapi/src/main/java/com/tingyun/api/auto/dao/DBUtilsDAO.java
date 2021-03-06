package com.tingyun.api.auto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.tingyun.api.auto.utils.DBUtils;

/**
* @author :chenjingli 
* @version ：2015-9-21 下午3:34:54 
* @decription:
 */
public class DBUtilsDAO  extends AbstractRoutingDataSource {
	
	/**
	 * 数据源切换
	 */
	
	public static final String DATA_SOURCE_A = "dataSourceA";
    public static final String DATA_SOURCE_B = "dataSourceB";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    
    public static void setCustomerType(String customerType) {
            contextHolder.set(customerType);
    }
    public static String getCustomerType() {
            return contextHolder.get();
    }
    public static void clearCustomerType() {
            contextHolder.remove();
    }
	
	
	@Resource
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	private QueryRunner queryRunner ;   
	
	private static final Logger LOG = LoggerFactory.getLogger(DBUtilsDAO.class);
	



    /** 
     * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 查询结果 
     * @throws SQLException 
     */ 
    @SuppressWarnings("unchecked") 
    public <T> List<T> query(Class<T> entityClass, String sql, Object... params) throws SQLException {
    	LOG.info("打印sql信息：====》{}",sql);
        queryRunner = new QueryRunner(dataSource); 
        List<T> list = new ArrayList<T>(); 
        	list = (params == null) ?
        			(List<T>) queryRunner.query(sql, new BeanListHandler(entityClass)) :
        			(List<T>) queryRunner.query(sql, new BeanListHandler(entityClass), params);	
        return list; 
    }
    
    /**
     * 返回多条记录
     */
    public List<Map<String, Object>> queryMore(String sql, Object... params)throws SQLException{
    	LOG.info("打印sql信息：====》{}",sql);
    	 queryRunner = new QueryRunner(dataSource); 
    	 List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
    	 maps = (params == null) ?
     			 queryRunner.query(sql, new MapListHandler()) :
     				queryRunner.query(sql, new MapListHandler(), params);	
     	 return maps;
    }
    
    
    /** 
     * 执行sql语句 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 受影响的行数 
     * @throws SQLException 
     */ 
    public int update(String sql, Object[] params) throws SQLException {
    	LOG.info("打印sql信息：====》{}",sql);
        queryRunner = new QueryRunner(dataSource); 
        int affectedRows = 0; 
            if (params == null) { 
                affectedRows = queryRunner.update(sql); 
            } else { 
                affectedRows = queryRunner.update(DBUtils.getConnection(),sql, params); 
            } 
        return affectedRows; 
    } 
    /**
     * 批处理多条数据
     */
    public void insertMore(String sql, Object[][] params) throws SQLException {
    	LOG.info("打印sql信息：====》{}",sql);
        queryRunner = new QueryRunner(dataSource);
        queryRunner.batch(sql, params);
    } 
    
    /** 
     * 执行sql语句 
     * @param sql sql语句 
     * @param params 多个参数
     * @return 受影响的行数 
     * @throws SQLException 
     */ 
    public int updateByParams(String sql, Object... params) throws SQLException { 
    	LOG.info("打印sql信息：====》{}",sql);
        queryRunner = new QueryRunner(dataSource); 
        int affectedRows = 0; 
            if (params == null) { 
                affectedRows = queryRunner.update(sql); 
            } else { 
                affectedRows = queryRunner.update(DBUtils.getConnection(),sql, params); 
            } 
        return affectedRows; 
    } 
    /** 
     * 查询出结果集中的第一条记录，并封装成对象 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 对象 
     * @throws SQLException 
     */ 
    @SuppressWarnings("unchecked") 
    public <T> T findFirst(Class<T> entityClass, String sql, Object[] params) throws SQLException { 
    	LOG.info("打印sql信息：====》{}",sql);
        queryRunner = new QueryRunner(dataSource); 
        Object object = null; 
            if (params == null) { 
                object = queryRunner.query(sql, new BeanHandler<T>(entityClass)); 
            } else { 
                object = queryRunner.query(sql, new BeanHandler<T>(entityClass), params); 
            } 
        return (T) object; 
    }

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return getCustomerType();
	} 
    
}
