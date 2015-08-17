package com.tingyun.api.auto.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.tingyun.api.auto.entity.Marking;
import com.tingyun.api.auto.utils.DBUtils;

public class MarkingImpl {
	
	static String STATUS="success";
	
	public static void insertStatus(){
		try {
		 QueryRunner queryRunner = new QueryRunner(true); 
		 queryRunner.update(DBUtils.getConnection(),"insert into MARKING_TABLE (status) values ('"+STATUS+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DBUtils.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static  void deleteStatus(){
		try {
		 QueryRunner queryRunner = new QueryRunner(true); 
		 queryRunner.update(DBUtils.getConnection(),"delete  from MARKING_TABLE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DBUtils.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String  selStatus(){
		try {
		 QueryRunner queryRunner = new QueryRunner(true); 
		 Marking mark = queryRunner.query(DBUtils.getConnection(),"select * from MARKING_TABLE", new BeanHandler<Marking>(Marking.class));
		 return mark.getStatus();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DBUtils.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String[] args) {
//		MarkingImpl.insertStatus();
//		System.out.println(selStatus());
		//MarkingImpl.deleteStatus();
	}
}
