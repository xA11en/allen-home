package com.tingyun.api.auto.utils.fifter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tingyun.api.auto.common.Constant;
import com.tingyun.api.auto.controller.LoginController;

public class SecurityInterceptor implements HandlerInterceptor{
	private static Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse res,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		//LOG.info("调用了登陆拦截器！");
		// TODO Auto-generated method stub
		 	HttpSession session = req.getSession(true);  
	        // 从session 里面获取用户名的信息  
	        Object obj = session.getAttribute(Constant.LOGIN_SUCCESS_STATUS);  
	       // LOG.info("session中取出的用户信息为：{}",obj.toString());
	        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
	        if (obj == null || "".equals(obj.toString())) {  
	            res.sendRedirect("reportLogin/login");  
	        }  
	        return true;  
	}

}
