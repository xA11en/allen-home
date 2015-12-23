package com.tingyun.api.auto.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingyun.api.auto.dao.AlarmDao;

@Controller
@RequestMapping
public class AlarmController {
	
	private static Logger LOG = LoggerFactory.getLogger(AlarmController.class);
	
	@Resource
	AlarmDao alarmDao;
	
	@RequestMapping
	public  void  getAlarmTestData(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		
	}
}
