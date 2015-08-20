package com.tingyun.api.auto.controller;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmatesoft.sqljet.core.internal.lang.SqlParser.match_op_return;

import com.tingyun.api.auto.dao.impl.MarkingImpl;
import com.tingyun.api.auto.utils.SVNUtils;


/**
* @author :chenjingli 
* @version ：2015-8-7 下午3:34:23 
* @decription:
*/
@Controller
public class StartTest {
	private static Logger LOG = LoggerFactory.getLogger(StartTest.class);
	@RequestMapping("/go")
	public String startTest(ModelMap modelMap,HttpServletRequest request) {
				try {
					SVNUtils.checkCodeFromSvn();
				Thread.sleep(1000);
				Runtime process = Runtime.getRuntime();
				process.exec("cmd /c start");
				Thread.sleep(2000);
				Robot r = new Robot();
					r.keyPress(KeyEvent.VK_C);
					r.keyPress(KeyEvent.VK_D);
					r.keyPress(KeyEvent.VK_SPACE);
					r.keyPress(KeyEvent.VK_L);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyPress(KeyEvent.VK_M);
					r.keyPress(KeyEvent.VK_V);
					r.keyPress(KeyEvent.VK_N);
					r.keyPress(KeyEvent.VK_SPACE);
					r.keyPress(KeyEvent.VK_T);
					r.keyPress(KeyEvent.VK_E);
					r.keyPress(KeyEvent.VK_S);
					r.keyPress(KeyEvent.VK_T);
					r.keyPress(KeyEvent.VK_ENTER);
					//退出
					r.keyPress(KeyEvent.VK_E);
					r.keyPress(KeyEvent.VK_X);
					r.keyPress(KeyEvent.VK_I);
					r.keyPress(KeyEvent.VK_T);
					r.keyPress(KeyEvent.VK_ENTER);
					modelMap.addAttribute("msg", "测试正在后台执行，点击查看报告后可能需要耐心等待一点时间！....");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOG.info("执行测试异常",e);
					e.printStackTrace();
				}
				return "test";
			
}
	@RequestMapping("/start")
	@ResponseBody
	public ModelMap startTest() {
		int count=0;
		LOG.info("******************开始进入后台执行测试用例action:startTest(),等待所有测试执行完毕！************");
		try{
		Thread.sleep(3000);
		ModelMap modelMap = new ModelMap();
		if(MarkingImpl.selStatus()!=null){
			MarkingImpl.deleteStatus();
			modelMap.addAttribute("msg", "success");
		}else {
			int timeout = 20;
			count++;
			if(count==timeout){
				modelMap.addAttribute("msg", "error");
			}
		}
		return modelMap;
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
