package com.tingyun.api.auto.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingyun.api.auto.common.ApiException;
import com.tingyun.api.auto.dao.ApiRuturnResultDao;
import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.entity.ApiRuturnResultBean;
import com.tingyun.api.auto.entity.ReportApiBean;

/**
* @author :chenjingli 
* @version ：2015-9-24 上午10:28:08 
* @decription:
 */
@Controller
public class ApiResultDetailController {
	
	private static Logger LOG = LoggerFactory.getLogger(ApiResultDetailController.class);
	
	@Autowired
	public ApiRuturnResultDao resultDao;
	@Autowired
	public ReportApiDao reportApiDao;
	
	@RequestMapping("/apiResult")
	public String  apiResultDetail(ModelMap modelMap,HttpServletRequest request){
		LOG.info("********************开始进入测试结果详情页【method: {} 】***********************","apiResultDetail");
		try {
			List<ApiRuturnResultBean> resultBeans = resultDao.selectAll();
			if(resultBeans.size()==0)
				throw new ApiException("api 测试结果为0");
			
				//返回界面原来的接口json 和 这个用例的错误接口返回结果
				modelMap.addAttribute("errorJson", resultBeans.get(0).getApp_api_result());
				ReportApiBean reportApiBean =  reportApiDao.findById(resultBeans.get(0).getApp_id());
				modelMap.addAttribute("appJson", reportApiBean.getJson());
				
		} catch (Exception e) {
			LOG.error("测试结果详情页出现异常{}",e);
			// TODO Auto-generated catch block
			throw new ApiException(e);
		}
		return "detail";
	}
}