package com.tingyun.api.auto.controller;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.tingyun.api.auto.common.ApiException;
import com.tingyun.api.auto.dao.DBUtilsDAO;
import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.entity.ReportApiBean;



/**
* @author :chenjingli 
* @version ：2015-7-27 下午4:47:05 
* @decription:
 */
@Controller
public class ReportApiController{
	public Logger logger = LoggerFactory.getLogger(ReportApiController.class);
	
	
	@Resource ReportApiDao reportApiDao;
	
	private static final int pageNumber =15	;
	@RequestMapping("/list")
	public String reportList(ModelMap modelMap,HttpServletRequest request){
			String firstPage = request.getParameter("pages");
			if(firstPage == null){
				firstPage="1";
			}
		try {
			int pages = Integer.parseInt(firstPage);
			int totalPages = reportApiDao.totalPages(pageNumber);
			List<ReportApiBean> listApis = reportApiDao.findAllByPaging(pages, pageNumber);
			logger.info("查询出记录数为：{}",listApis.size());
			modelMap.put("listApis", listApis);
			request.setAttribute("pages", pages);
		    request.setAttribute("totalPages", totalPages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询全部api数据异常：{}",e);
		    throw new ApiException("分页查询数据异常",e);
		}
		
		
		return "reportApiList";
		
	}
	
	@RequestMapping("/add")
	public void reportAdd(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String parameter = request.getParameter("canshu");
		String url = request.getParameter("url");
		String c6nnnfcg = request.getParameter("c6nnnfcg");
		try {
			reportApiDao.save(new String[]{name,c6nnnfcg,parameter,url});
	    response.getWriter().write("success");
	    } catch (Exception e) {
	    	logger.error("删除全部api数据异常：{}",e);
	    	 throw new ApiException("删除全部api数据异常",e);
	    }
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public void reportDel(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			reportApiDao.del(id);
			response.sendRedirect("list.do");
      } catch (Exception e) {
    	  logger.error("删除数据异常：{}",e);
	      throw new ApiException("删除数据异常",e);
      }
		
	}
	
	@RequestMapping("/load")
	public String reportLoad(ModelMap modelMap,HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ReportApiBean e = reportApiDao.findById(id);
			request.setAttribute("e", e);
			return "updateReportApi";
			}catch (Exception e) {
			      e.printStackTrace();
			      throw new ApiException(e);
		    }
		
	}
	
	@RequestMapping("/update")
	public void reportUpdate(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String parameter = request.getParameter("canshu");
		String url = request.getParameter("url");
		String c6nnnfcg = request.getParameter("c6nnnfcg");
		if(StringUtils.isBlank(name) || 
		   StringUtils.isBlank(parameter) ||
		   StringUtils.isBlank(url) ||
		   StringUtils.isBlank(c6nnnfcg) )
		return;
		ReportApiBean r = new ReportApiBean();
		r.setCaseName(name);
		r.setC6nnnfcg(c6nnnfcg);
		r.setParameter(parameter);
		r.setUrl(url);
		r.setId(id);
		try {
			reportApiDao.update(r);
			response.sendRedirect("list.do");
		   } catch (Exception e1) {
			      logger.error("修改数据异常：{}",e1);
			      e1.printStackTrace();
			      throw new ApiException(e1);
		   }
		
	}
	
	
	@RequestMapping("/detailJson")
	public String reportDetailXml(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			List<String> listString = reportApiDao.findXmlAndJsonBuId(id);
			modelMap.put("listString", listString.get(1));
			return "detail";
      } catch (Exception e) {
    	  logger.error("查询xml异常：{}",e);
	      e.printStackTrace();
	      throw new ApiException(e);
      }
		
	}
	
	
	@RequestMapping("/detailXml")
	public String reportDetailJson(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			List<String> listString = reportApiDao.findXmlAndJsonBuId(id);
			modelMap.addAttribute("listString", listString.get(0));
			return "detail";
      } catch (Exception e) {
    	  logger.error("查询json异常：{}",e);
	      e.printStackTrace();
	      throw new ApiException(e);
      }
		
	}
	@RequestMapping("/addReport")
	public String addReport(){
			return "addReportApi";
		
	}
}
