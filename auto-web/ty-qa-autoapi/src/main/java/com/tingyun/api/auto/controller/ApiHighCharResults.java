package com.tingyun.api.auto.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tingyun.api.auto.common.ApiException;
import com.tingyun.api.auto.dao.ApiRuturnResultDao;
import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.entity.ReportApiBean;

/**
* @author :chenjingli 
* @version ：2015-10-23 下午3:18:42 
* @decription:
 */
@Controller
@RequestMapping("/ApihighChar")
public class ApiHighCharResults {
	
	private static Logger LOG = LoggerFactory.getLogger(ApiHighCharResults.class);
	
	@Autowired
	public ApiRuturnResultDao resultDao;
	
	@Autowired
	public ReportApiDao reportApiDao;
	
	@RequestMapping("/errorCount")
	public void  selectApiByErrorCount(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		LOG.info("**********start 查询app接口的按着错误次数，查询方法【{}】","selectApiByErrorCount");
		try {
			List<Map<String, Object>> maps = resultDao.selectByerrorCount();
			if(maps.size()==0){
				return ;
			}
			Map<String, Integer> appHighCharMap = new HashMap<String, Integer>();
			for (Map<String, Object> map : maps) {
				int appId = (Integer) map.get("app_id");
				BigDecimal errorCount =  (BigDecimal) map.get("errorcount");
				int errorC = errorCount.intValue();
				ReportApiBean reportApiBean = reportApiDao.findById(appId);
				appHighCharMap.put(reportApiBean.getCaseName(),errorC);
			}
			JSONArray json = JSONArray.fromObject(appHighCharMap);
			response.getWriter().write(json.toString());
			LOG.info("查询出的展示apphighchar接口的数据是{}",json.toString());
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("查询app api组装数据前台展示出错！",e);
			throw new ApiException("查询app api组装数据前台展示出错！",e);
		}
	}
	
	@RequestMapping("/getAppHighChar")
	public String  selectApi(){
		return "app/appHighChar";
}
}