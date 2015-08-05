package com.tingyun.api.auto.utils.fifter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
 /**
 * @author :chenjingli 
 * @version ：2015-8-4 上午10:00:32 
 * @decription: 
  */
public class EncodingFilter implements Filter {
     
    private static String encoding;
    private static final String DEFAULT_CHARSET="UTF-8";
    @Override
    public void destroy() {
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        if("GET".equals(httpRequest.getMethod())){
            EncodingHttpServletRequest wrapper = new EncodingHttpServletRequest(httpRequest, encoding);   
            chain.doFilter(wrapper, response);  
        }else{
            httpRequest.setCharacterEncoding(encoding);
            httpResponse.setContentType("text/html;charset="+encoding);
            chain.doFilter(request, response);  
        }
    }
    private static class  EncodingHttpServletRequest extends HttpServletRequestWrapper{
         private HttpServletRequest request;
         public EncodingHttpServletRequest(HttpServletRequest request,String encoding) {
           super(request);
           this.request = request;
         }
         @Override
        public String getParameter(String name) {
               String value = request.getParameter(name);
               try {
            	if(null == value){
            		value = new String(encoding); 
            	}else{
            		value = new String(value.getBytes("iso8859-1"), encoding);
            	}
               } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
               }
               return super.getParameter(name);
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
         encoding = config.getInitParameter("encoding");
         if(encoding==null||"".equals(encoding))
             encoding=DEFAULT_CHARSET;
    }
 
}