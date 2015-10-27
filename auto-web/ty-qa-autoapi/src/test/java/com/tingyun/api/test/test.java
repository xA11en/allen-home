package com.tingyun.api.test;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import com.tingyun.api.auto.entity.ApiRuturnResultSql;
import com.tingyun.api.auto.utils.DBUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-servlet.xml") 
public class test {
	
	@Test
    public void testSearchApiResult(){
		List<Map<String, Object>> map;
		try {
			map = new QueryRunner(
				).query(DBUtils.getConnection(),ApiRuturnResultSql.SELECT_APP_API_ERROR_SQL, new MapListHandler ());
			for (Map<String, Object> map2 : map) {
				int id = (Integer) map2.get("app_id");
				System.out.println(id);
//				System.out.println(map2.get("app_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
		
}
