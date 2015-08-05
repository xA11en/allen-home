package com.tingyun.api.auto.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingyun.api.auto.dao.ReportApiDao;
import com.tingyun.api.auto.entity.ReportApi;
import com.tingyun.api.auto.utils.Factory;



/**
* @author :chenjingli 
* @version ：2015-7-27 下午4:47:05 
* @decription:
 */
@Controller("/reportList")
public class ReportApiController{
	private Logger logger = LoggerFactory.getLogger(ReportApiController.class);
	private ReportApiDao dao = (ReportApiDao) Factory
		    .getInstance("ReportApiDao");
	private static final int pageNumber = 5;
	@RequestMapping("/list")
	public String reportList(ModelMap modelMap,HttpServletRequest request){
		
			String firstPage = request.getParameter("pages");
			if(firstPage == null){
				firstPage="1";
			}
		try {
			int pages = Integer.parseInt(firstPage);
			int totalPages = dao.totalPages(pageNumber);
			List<ReportApi> listApis = dao.findAllByPaging(pages, pageNumber);
			logger.info("查询出记录数为：{}",listApis.size());
			//List<ReportApi> listApis = dao.findAll();
			modelMap.put("listApis", listApis);
			request.setAttribute("pages", pages);
		    request.setAttribute("totalPages", totalPages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询全部api数据异常：{}",e);
		    e.printStackTrace();
		    throw new RuntimeException(e);
		}
		return "reportApiList";
		
	}
	
	@RequestMapping("/add")
	public void reportAdd(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String parameter = request.getParameter("canshu");
		String url = request.getParameter("url");
		String c6nnnfcg = request.getParameter("c6nnnfcg");
		try {
	    dao.save(new String[]{name,c6nnnfcg,parameter,url});
	    response.sendRedirect("list.do");
	    } catch (Exception e) {
	    	logger.error("删除全部api数据异常：{}",e);
		    e.printStackTrace();
		    throw new RuntimeException(e);
	    }
		
	}
	
	@RequestMapping("/del")
	public void reportDel(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			dao.del(id);
			response.sendRedirect("list.do");
      } catch (Exception e) {
    	  logger.error("删除数据异常：{}",e);
	      e.printStackTrace();
	      throw new RuntimeException(e);
      }
		
	}
	
	@RequestMapping("/load")
	public String reportLoad(ModelMap modelMap,HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ReportApi e = dao.findById(id);
			request.setAttribute("e", e);
			return "updateReportApi";
			}catch (Exception e) {
			      e.printStackTrace();
			      throw new RuntimeException(e);
		    }
		
	}
	
	@RequestMapping("/update")
	public void reportUpdate(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String parameter = request.getParameter("canshu");
		String url = request.getParameter("url");
		String c6nnnfcg = request.getParameter("c6nnnfcg");
		ReportApi r = new ReportApi();
		r.setCaseName(name);
		r.setC6nnnfcg(c6nnnfcg);
		r.setParameter(parameter);
		r.setUrl(url);
		r.setId(id);
		try {
			dao.update(r);
			response.sendRedirect("list.do");
		   } catch (Exception e1) {
			      logger.error("修改数据异常：{}",e1);
			      e1.printStackTrace();
			      throw new RuntimeException(e1);
		   }
		
	}
	
	
	@RequestMapping("/detailXml")
	public String reportDetailXml(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			List<String> listString = dao.findXmlAndJsonBuId(id);
			modelMap.put("listString", listString.get(0));
			return "detail";
      } catch (Exception e) {
    	  logger.error("查询xml异常：{}",e);
	      e.printStackTrace();
	      throw new RuntimeException(e);
      }
		
	}
	
	
	@RequestMapping("/detailJson")
	public String reportDetailJson(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			List<String> listString = dao.findXmlAndJsonBuId(id);
			modelMap.addAttribute("listString", listString.get(1));
			return "detail";
      } catch (Exception e) {
    	  logger.error("查询json异常：{}",e);
	      e.printStackTrace();
	      throw new RuntimeException(e);
      }
		
	}
}
