package com.tingyun.api.auto.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tingyun.api.auto.common.ApiException;
import com.tingyun.api.auto.dao.NetWorkReportApiDao;
import com.tingyun.api.auto.entity.NetworkReportApiBean;



/**
* @author :chenjingli 
* @version ：2015-7-27 下午4:47:05 
* @decription:
 */
@Controller
public class NetworkApiController{
	public Logger logger = LoggerFactory.getLogger(NetworkApiController.class);
	
	
	@Resource NetWorkReportApiDao reportApiDao;
	
	private static final int pageNumber =15	;
	@RequestMapping("/listNetwork")
	public String reportList(ModelMap modelMap,HttpServletRequest request){
			String firstPage = request.getParameter("pages");
			if(firstPage == null){
				firstPage="1";
			}
		try {
			int pages = Integer.parseInt(firstPage);
			int totalPages = reportApiDao.totalPages(pageNumber);
			List<NetworkReportApiBean> listApis = reportApiDao.findAllByPaging(pages, pageNumber);
			logger.info("查询出记录数为：{}",listApis.size());
			modelMap.put("listApis", listApis);
			request.setAttribute("pages", pages);
		    request.setAttribute("totalPages", totalPages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询全部api数据异常：{}",e);
		    throw new ApiException("分页查询数据异常",e);
		}
		
		
		return "Network/reportApiList";
		
	}
	
	@RequestMapping("/addNetwork")
	public void reportAdd(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String parameter = request.getParameter("canshu");
		String url = request.getParameter("url");
		String c6nnnfcg = request.getParameter("authKey");
		String json = request.getParameter("json");
		try {
			reportApiDao.save(new String[]{name,c6nnnfcg,parameter,url,json});
			response.getWriter().write("success");
	    } catch (Exception e) {
	    	logger.error("删除全部api数据异常：{}",e);
	    	 throw new ApiException("删除全部api数据异常",e);
	    }
	}
	
	@RequestMapping("/delNetwork")
	@ResponseBody
	public void reportDel(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			reportApiDao.del(id);
			response.sendRedirect("listNetwork.do");
      } catch (Exception e) {
    	  logger.error("删除数据异常：{}",e);
	      throw new ApiException("删除数据异常",e);
      }
		
	}
	
	@RequestMapping("/loadNetwork")
	public String reportLoad(ModelMap modelMap,HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			NetworkReportApiBean e = reportApiDao.findById(id);
			request.setAttribute("e", e);
			return "Network/updateReportApi";
			}catch (Exception e) {
			      e.printStackTrace();
			      throw new ApiException(e);
		    }
		
	}
	
	@RequestMapping("/updateNetWork")
	public void reportUpdate(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String parameter = request.getParameter("canshu");
		String url = request.getParameter("url");
		String authKey = request.getParameter("authKey");
		if(StringUtils.isBlank(name) || 
		   StringUtils.isBlank(parameter) ||
		   StringUtils.isBlank(url) ||
		   StringUtils.isBlank(authKey) )
		return;
		NetworkReportApiBean r = new NetworkReportApiBean();
		r.setCaseName(name);
		r.setAuthKey(authKey);
		r.setParameter(parameter);
		r.setUrl(url);
		r.setId(id);
		try {
			reportApiDao.update(r);
			response.sendRedirect("listNetwork.do");
		   } catch (Exception e1) {
			      logger.error("修改数据异常：{}",e1);
			      e1.printStackTrace();
			      throw new ApiException(e1);
		   }
		
	}
	
	
//	@RequestMapping("/detailJson")
//	public String reportDetailXml(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
//		int id = Integer.parseInt(request.getParameter("id"));
//		try {
//			List<String> listString = reportApiDao.findXmlAndJsonBuId(id);
//			modelMap.put("listString", listString.get(1));
//			return "detail";
//      } catch (Exception e) {
//    	  logger.error("查询xml异常：{}",e);
//	      e.printStackTrace();
//	      throw new ApiException(e);
//      }
//		
//	}
	
	
//	@RequestMapping("/detailXml")
//	public String reportDetailJson(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
//		int id = Integer.parseInt(request.getParameter("id"));
//		try {
//			List<String> listString = reportApiDao.findXmlAndJsonBuId(id);
//			modelMap.addAttribute("listString", listString.get(0));
//			return "Network/detail";
//      } catch (Exception e) {
//    	  logger.error("查询json异常：{}",e);
//	      e.printStackTrace();
//	      throw new ApiException(e);
//      }
		
//	}
	@RequestMapping("/addNetworkReport")
	public String addReport(){
			return "Network/addReportApi";
		
	}
	
	@RequestMapping("/searchNetwork")
	public String searchApiList(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		try {
			String name = URLDecoder.decode(request.getParameter("caseName"), "utf-8");
			List<NetworkReportApiBean> listApis = reportApiDao.findAllByPagingAndName(name);
			if(listApis.size()==0){
				modelMap.put("info","没查到任何关于【'"+name+"'】的记录！");
			}
			modelMap.put("listApis", listApis);
			logger.info("查询出记录数为：{}",listApis.size());
			return "Network/result";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("搜索api异常：{}",e);
		    throw new ApiException("搜索api异常",e);
		}
	}
}
