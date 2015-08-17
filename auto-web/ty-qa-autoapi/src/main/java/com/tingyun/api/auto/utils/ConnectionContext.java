package com.tingyun.api.auto.utils;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionContext {
	
	private ConnectionContext(){
	}
	/**
	 * 单例ConnectionContext
	 */
	private  static ConnectionContext connectionContext = new ConnectionContext();
	
	public static ConnectionContext getInstance(){
		return connectionContext;
	}
	
	private static ThreadLocal<Connection> threadLocal =
			new ThreadLocal<Connection>();
	/**
	* @author : chenjingli
	* @decription 把数据库连接对象绑定到当前线程
	* @return
	 * @throws SQLException 
	 */
	public void bind(Connection connection) throws SQLException{
		if(connection !=null){
			threadLocal.set(connection);
		}else{
			return;
		}
	}
	/**
	 * 从ThreadLocal  获得数据库连接
	 */
	public synchronized Connection getConnection(){
		return threadLocal.get();
	}
	
	/**
	 * 接触当前线程绑定的 Connection
	 */
	public void remove(){
		threadLocal.remove();
	}
//	public static void main(String[] args) throws SQLException {
//		ConnectionContext.getInstance().bind(getConnection1());
//	}
}
