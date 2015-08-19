package com.tingyun.api.test;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.dao.impl.MarkingImpl;
import com.tingyun.api.auto.dao.impl.ReportApiDaoImpl;
import com.tingyun.api.auto.entity.ReportApi;
import com.tingyun.api.auto.utils.SVNUtils;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:applicationContext.xml") 
public class test {
	
//	@Resource(name="reportService")
//	public  ReportApiService apiService;
//	@Test
//    public void testLoginCheck(){
//			System.out.println("test start");
//			List<ReportApi> list =  apiService.findAllReportApi();
//			System.out.println(list.size());
//			for (ReportApi reportApi : list) {
//				System.out.println(reportApi.getId()+"----------"+reportApi.getCaseName());
//			}
//		}
	@Test
	public void testFindAll(){// pages, int rowsPerPage
		ReportApiDao ra = new ReportApiDaoImpl();
		List<ReportApi> list;
		try {
			list = ra.findAllByPaging(1, 5);
//			for (int i = 0; i < list.size(); i++) {
//				ReportApi reportApi = list.get(i);
//			}
			MarkingImpl.insertStatus();
			System.out.println(list.size());
			Assert.assertEquals(list.size(), 6);
//			for (ReportApi reportApi : list) {
//				System.out.println(reportApi.getCaseName());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Test
	public void save() throws Exception{
		ReportApiDao ra = new ReportApiDaoImpl();
		int num = ra.totalPages(3);
		System.out.println(num);
		
	}
	
	
	//@Test
	public void detail() throws Exception{
		ReportApiDao ra = new ReportApiDaoImpl();
		List<String> list = ra.findXmlAndJsonBuId(85);
		
		System.out.println(list.get(0));
	}
	
		
}
