package com.tingyun.api.auto.controller;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
				Runtime process = Runtime.getRuntime();
				SVNUtils.checkCodeFromSvn();
				try {
				process.exec("cmd /c start");
				Robot r;
				Thread.sleep(2000);
					r = new Robot();
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
					Thread.sleep(5000);
					for(;;){
						if(MarkingImpl.selStatus()!=null){
							modelMap.addAttribute("msg", "批量接口测试执行完毕....");
							break;
						}else{
							continue;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOG.info("执行测试异常",e);
					e.printStackTrace();
				}
				return "test";
			
}
	
}
