package com.tingyun.api.test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.dao.impl.ReportApiDaoImpl;
import com.tingyun.api.auto.entity.ReportApi;

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
	@Test(description="查询分页测试")
	public void testFindAll(){// pages, int rowsPerPage
		ReportApiDao ra = new ReportApiDaoImpl();
		List<ReportApi> list;
		try {
			list = ra.findAllByPaging(1, 5);
//			for (int i = 0; i < list.size(); i++) {
//				ReportApi reportApi = list.get(i);
//			}
			System.out.println(list.size());
			Assert.assertEquals(list.size(), 6);
//			for (ReportApi reportApi : list) {
//				System.out.println(reportApi.getCaseName());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterSuite
	public void after(){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/ty-qa-autoapi/start.do?status=1");
//		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		try {
//		parameters.add(new BasicNameValuePair("status","1"));
//		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
//		httppost.setEntity(entity);
		httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
