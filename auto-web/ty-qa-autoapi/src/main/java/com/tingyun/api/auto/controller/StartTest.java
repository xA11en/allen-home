package com.tingyun.api.auto.controller;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmatesoft.sqljet.core.internal.lang.SqlParser.match_op_return;

import com.tingyun.api.auto.dao.impl.MarkingImpl;
import com.tingyun.api.auto.utils.HtmlMail;
import com.tingyun.api.auto.utils.SVNUtils;


/**
* @author :chenjingli 
* @version ：2015-8-7 下午3:34:23 
* @decription:
*/
@Controller
public class StartTest {
	private static boolean flag = false;
	//本地副本代码路径
	private static File filedir = null;
	private static long workVersion = -1;
	private static Logger LOG = LoggerFactory.getLogger(StartTest.class);
	@RequestMapping("/go")
	public String startTest(ModelMap modelMap,HttpServletRequest request) {
			try {
				//创建本地代码副本路径
				filedir = new File(HtmlMail.LOCAL_SVN_WORKSPACE);
				if (filedir.exists()) {
					if(workVersion == SVNUtils.getLastVersionNum()){
						goTest();
						modelMap.addAttribute("msg", "测试正在后台执行，点击查看报告后可能需要耐心等待一点时间！....");
					}else{
						LOG.warn("您本地已存在相同路径：{}即将做更新操作....",filedir.getAbsolutePath());
						SVNUtils.updateCodeFromSvn(filedir);
						goTest();
						modelMap.addAttribute("msg", "测试正在后台执行，点击查看报告后可能需要耐心等待一点时间！....");
					}
				}else{
					filedir.mkdir();
					workVersion =SVNUtils.checkCodeFromSvn(filedir);
					goTest();
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOG.info("执行测试异常",e);
					e.printStackTrace();
				}
				return "test";
			
}
	@RequestMapping("/start")
	@ResponseBody
	public ModelMap startTest(HttpServletRequest request) {
		int count=0;
		LOG.info("******************开始进入后台执行测试用例action:startTest(),等待所有测试执行完毕！************");
		try{
		ModelMap modelMap = new ModelMap();
		String par = request.getParameter("status");
		System.out.println(par+"============");
		if(StringUtils.isNotBlank(par) && par.equals("1")){
			flag = true;
			return null;
		} 
		if(flag){
			modelMap.addAttribute("msg", "success");
			flag=false;	
		}else{
			modelMap.addAttribute("msg", "flag");
		}
		return modelMap;
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	private void goTest()throws Exception{
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
	}
}
