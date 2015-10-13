package com.tingyun.api.test;


import java.util.List;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import com.tingyun.api.auto.dao.ApiRuturnResultDao;
import com.tingyun.api.auto.dao.impl.ApiRuturnResultDaoImpl;
import com.tingyun.api.auto.entity.ApiRuturnResultBean;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-servlet.xml") 
public class test {
	
	@Test
    public void testSearchApiResult(){
		try {
			ApiRuturnResultDao resultDao = new ApiRuturnResultDaoImpl();
			List<ApiRuturnResultBean> resultBeans = resultDao.selectAll();
			for (ApiRuturnResultBean apiRuturnResultBean : resultBeans) {
				System.out.println(apiRuturnResultBean.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
		
}
