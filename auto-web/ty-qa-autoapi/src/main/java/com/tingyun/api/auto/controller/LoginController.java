package com.tingyun.api.auto.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingyun.api.auto.common.Constant;
import com.tingyun.api.auto.entity.LoginBean;

/**
* @author :chenjingli 
* @version ：2015-10-30 上午11:02:57 
* @decription:
 */
@Controller
@RequestMapping("/reportLogin")
public class LoginController {
	
	private static Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public String login(HttpSession httpSessio,HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LOG.info("**********start 用户 ：【{}】登陆了接口平台！",username);
		try {
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password) )
		{
			LOG.info("用户名和密码为null");
			return "login";
		}
		LoginBean bean = new LoginBean();
		if(username.equals("test") && password.equals("1")){
			bean.setUsername(username);bean.setPassword(password);
			LOG.info("把登陆信息存入seesion!{}",bean.toString());
			httpSessio.setAttribute(Constant.LOGIN_SUCCESS_STATUS,bean);
			response.getWriter().write("success");
		}else{
			response.getWriter().write("fail");;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
