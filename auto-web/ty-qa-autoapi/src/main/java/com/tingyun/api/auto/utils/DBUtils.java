package com.tingyun.api.auto.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private static String driverClass;

	private static String url;

	private static String user;

	private static String password;

	static {
		try {
			Properties props = new Properties();
			InputStream is = DBUtils.class.getClassLoader().getResourceAsStream(
					"jdbc.properties");
			props.load(is);
			driverClass = props.getProperty("mysql.driverClassName");
			url = props.getProperty("mysql.url");
			user = props.getProperty("mysql.username");
			password = props.getProperty("mysql.password");
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获得Connection对象所消耗资源会占到整个jdbc操作的85%以上
	//批处理除外
	//尽量减少获得Connection对象	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/jdbc.properties");
		props.load(fis);
		System.out.println(props.getProperty("mysql.driverClassName"));
		System.out.println(props.getProperty("mysql.username"));
		System.out.println(getConnection()+"sd");
	}

}
