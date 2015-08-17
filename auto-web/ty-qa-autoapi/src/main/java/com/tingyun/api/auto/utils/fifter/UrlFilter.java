package com.tingyun.api.auto.utils.fifter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author :chenjingli 
* @version ：2015-8-3 上午11:47:42 
* @decription: url拦截
 */
public class UrlFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse res = (HttpServletResponse) response;
		try{
			String requestUrl = req.getServletPath();
			if(requestUrl.contains("/list.do")){
				chain.doFilter(request, response);  
			}else{
				res.sendRedirect("list.do");
				chain.doFilter(request, response);  
			}
		}catch(Exception e){
			 //req.setAttribute("errMsg", e.getMessage());
			 //req.getRequestDispatcher("/error.jsp").forward(req, res);
			 //出现异常之后跳转到错误页面
			 res.sendRedirect("error.jsp");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
