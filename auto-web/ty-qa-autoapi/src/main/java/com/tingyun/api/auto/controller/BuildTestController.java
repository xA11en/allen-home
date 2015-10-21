package com.tingyun.api.auto.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ntp.TimeStamp;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingyun.api.auto.common.ApiException;
import com.tingyun.api.auto.common.Constant;
import com.tingyun.api.auto.dao.ApiRuturnResultDao;
import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.entity.ReportApiBean;
import com.tingyun.api.auto.entity.TestReportBeanVO;
import com.tingyun.api.auto.service.AppHttpClient;


/**
* @author :chenjingli 
* @version ：2015-8-7 下午3:34:23 
* @decription:
*/
@Controller
public class BuildTestController {
	private static Logger LOG = LoggerFactory.getLogger(BuildTestController.class);
	
	private static final int ERROR_COUNT=1;
	
	private static final String SUCCESS_INFO = "没有失败接口";
	@Autowired
	public ReportApiDao reportApiDao;
	
	@Autowired
	public ApiRuturnResultDao resultDao;
	

	@RequestMapping("/go")
	public String startTest(ModelMap modelMap,HttpServletRequest request) {
		LOG.info("********************开始执行后台测试【method: {} 】***********************","startTest");
		List<ReportApiBean> list = null;
	    try {
	    	 list = reportApiDao.findAll();
	    	 if(list.size()==0)
		    		throw new ApiException("查询全部的app api测试用例结果集为0！");
	    	
	    	 List<TestReportBeanVO> testReportBeanVOs = new ArrayList<TestReportBeanVO>();
	    	 for (int i = 0; i < list.size(); i++) 
	    	 {
	    		 //数据库存储的请求参数
	    		 String authKey = list.get(i).getC6nnnfcg();
				 String par = list.get(i).getParameter();
				 String url = list.get(i).getUrl();
				 //数据存储的结果
				 String json = list.get(i).getJson();
				 //处理timestamp取当前事前问题 只对比其余项目
				 JSONObject jsonObject =  new JSONObject(json);
				 JSONObject  chart = jsonObject.getJSONObject("chart");
				 String beginTime=chart.getString("beginTime");
				 String endTime=chart.getString("endTime");
				 String timeGranularity=chart.getString("timeGranularity");
				 JSONArray dataset=chart.getJSONArray("dataset");
				 List<String> listJsons = new ArrayList<String>();
				 listJsons.add(beginTime);
				 listJsons.add(endTime);
				 listJsons.add(timeGranularity);
				 listJsons.add(dataset.toString());	
				 int id = list.get(i).getId();
				 String apiReturnRulst = AppHttpClient.doRequest(authKey, par, url);
	    		if(!apiReturnRulst.trim().equals(listJsons.toString().trim()))
	    		{	
	    			//查询结果集判断这个表中数据是否为null 如果是null说明第一次插入接口的错误数据
	    				String timeStamp = String.valueOf((new Date().getTime())/1000);
	    				//标识错误次数
	    				String[] params = {String.valueOf(id),apiReturnRulst,timeStamp,String.valueOf(ERROR_COUNT)};
	    				resultDao.saveAppApiResult(params);
	    				TestReportBeanVO vo = new TestReportBeanVO();
	    				vo.setCaseName(list.get(i).getCaseName());
	    				vo.setRequestTou(AppHttpClient.convertCurlCMD(authKey, par, url));
	    				vo.setStatus(Constant.REPORT_FAIL_STATUS);
	    				vo.setAppId(id);
	    				testReportBeanVOs.add(vo);
	    				if(testReportBeanVOs.size()==0){
	    					modelMap.addAttribute(Constant.SUCCESS_STATUS,SUCCESS_INFO);
	    				}
	    				modelMap.addAttribute("listVo", testReportBeanVOs);
	    		}	
	    		LOG.info("接口测试完毕！");
			 }
		} catch (Exception e) {
			LOG.error("执行测试接口测试过程出现异常{}",e);
			throw new ApiException(e);
		}
	    return "testReport";
	}			
}
