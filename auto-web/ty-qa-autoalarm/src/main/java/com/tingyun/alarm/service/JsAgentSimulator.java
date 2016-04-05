package com.tingyun.alarm.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.alarm.entity.BrowserPF;
import com.tingyun.alarm.entity.BrowserParmaters;
import com.tingyun.alarm.entity.ParamsBean;
import com.tingyun.alarm.utils.HttpParamUtil;

public class JsAgentSimulator {
	
	private  final Logger logger = LoggerFactory.getLogger(getClass());
	private final static String DEFAULT_DC_SERVER_URL = "http://beacondev.tingyun.com/";
	
	private static List<String> ips = new ArrayList<String>();
	static{
		ips.add("124.202.217.214");//北京
		ips.add("121.155.124.35");
		ips.add("60.29.241.102");//天健
		ips.add("124.236.223.17");//河北
		ips.add("219.132.30.151");//广东
		ips.add("61.132.88.36");//江苏
		
	}
	
	
	 public void simulatorBrowserData(BrowserParmaters bp)  {
		int loadTime = 0 ;
		int errorTime = 0;
		int count = Integer.parseInt(bp.getCount());
		for (int i = 0; i < count; i++) {
			try {
				postPf(putParmtersToPf(bp));
				System.out.println("上传了一次数据："+bp.toString());
				//get 当前时间
				loadTime = (int)System.currentTimeMillis()/1000;
				postXhr(putParmtersToPf(bp), bp.getCallBackTime());
				TimeUnit.SECONDS.sleep(Long.parseLong(bp.getWaitSecond()));
			} catch (NumberFormatException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(ClientProtocolException | UnsupportedEncodingException e){
				e.printStackTrace();
			}
			int jsCount = Integer.parseInt(bp.getJsErrorRate())  * count;
			for (int j = 0; j < jsCount; j++) {
				errorTime = (int)System.currentTimeMillis()/1000;
				postJsError(putParmtersToPf(bp),errorTime-loadTime);
				logger.info("上传了一次数据：{}",bp.toString());
			}
			
		}
	}
	
	
	//send  jserror data
	private void postJsError(BrowserPF bp,int os){
		List<ParamsBean> list = add(bp);
		list.add(new ParamsBean("fu", String.valueOf(0)));
		list.add(new ParamsBean("os", String.valueOf(os)));
		String paramstr = HttpParamUtil.getParams("get", list);
	    String urlString =DEFAULT_DC_SERVER_URL+"err"+paramstr;
		CloseableHttpClient httpclient = getHttpClient();
		HttpPost post = new HttpPost(urlString);
		post.setHeader("User-Agent", 
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
		//创建参数列表
        List<NameValuePair> listPairs = new ArrayList<NameValuePair>();
        listPairs.add(new BasicNameValuePair("datas",
        		"[[\"Uncaught ReferenceError: t is not defined\",46, 2,"
        				+ "\"http://192.168.5.50:8081/testSDTY/test2.jsp\", "
        				+ "1, \"ReferenceError: t is not defined at window.onload (http://192.168.5.50:8081/testSDTY/test2.jsp:46:2)\","
        				+ "\"undefined\" "
        				+ ","+1+"]]"));
        listPairs.add(new BasicNameValuePair("datas",
        		"[[\"Uncaught ReferenceError: t is not defined\",46, 2,"
        				+ "\"http://192.168.5.50:8081/testSDTY/test2.jsp\", "
        				+ "1, \"ReferenceError: t is not defined at window.onload (http://192.168.5.50:8081/testSDTY/test2.jsp:46:2)\","
        				+ "\"undefined\" "
        				+ ","+1+"]]"));
        listPairs.add(new BasicNameValuePair("datas",
        		"[[\"Uncaught ReferenceError: t is not defined\",46, 2,"
        				+ "\"http://192.168.5.50:8081/testSDTY/test2.jsp\", "
        				+ "1, \"ReferenceError: t is not defined at window.onload (http://192.168.5.50:8081/testSDTY/test2.jsp:46:2)\","
        				+ "\"undefined\" "
        				+ ","+1+"]]"));
        try {
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(listPairs, "utf-8");
        post.setEntity(entity);
        httpclient.execute(post);
        }catch(IOException e){
        	e.printStackTrace();
        }finally{
			try {
				closeHttpClient(httpclient);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	private List<ParamsBean> add(BrowserPF bp){
		List<ParamsBean> list = new LinkedList<ParamsBean>();                    
	    list.add(new ParamsBean("av",bp.getAv()));
	    list.add(new ParamsBean("v", bp.getV()));
	    list.add(new ParamsBean("key", bp.getKey()));
	    list.add(new ParamsBean("ref", bp.getRef()));
	    list.add(new ParamsBean("rand", bp.getRand()));
	    list.add(new ParamsBean("pvid", bp.getPvid()));
	    return list;
	}
	
	//send xhr  data ajax about
	private void postXhr(BrowserPF bp,String callBackTime) throws ClientProtocolException, UnsupportedEncodingException{
		List<ParamsBean> list = add(bp);	
	    String paramstr = HttpParamUtil.getParams("get", list);
	    String urlString =DEFAULT_DC_SERVER_URL+"xhr"+paramstr;
		CloseableHttpClient httpclient = getHttpClient();
		HttpPost post = new HttpPost(urlString);
		post.setHeader("User-Agent", 
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
		//创建参数列表
        List<NameValuePair> listPairs = new ArrayList<NameValuePair>();
        listPairs.add(new BasicNameValuePair("xhr", "[[\"POST http://192.168.5.50:8081/testSDTY/servlet/testServlet\",20, '"+callBackTime+"', 200, 0, 15, 10]]"));
        listPairs.add(new BasicNameValuePair("xhr", "[[\"POST http://192.168.5.50:8081/testSDTY/servlet/testServlet\",20, '"+callBackTime+"', 200, 0, 15, 10]]"));
        listPairs.add(new BasicNameValuePair("xhr", "[[\"POST http://192.168.5.50:8081/testSDTY/servlet/testServlet\",20, '"+callBackTime+"', 200, 0, 15, 10]]"));
        try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(listPairs, "utf-8");
			post.setEntity(entity);
			Random random = new Random();
			String ip = ips.get(random.nextInt(ips.size()));
			post.setHeader("x-forwarded-for",ip);
			httpclient.execute(post);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				closeHttpClient(httpclient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}  
	
	
	
	
	// send pf 
	private void postPf( BrowserPF bp)  
	{
		//放参数
		List<ParamsBean> list = add(bp);	
	    list.add(new ParamsBean("os", String.valueOf(bp.getOs())));	
	    list.add(new ParamsBean("oe", String.valueOf(bp.getOe())));	
	    list.add(new ParamsBean("rs", String.valueOf(bp.getRs())));	
	    list.add(new ParamsBean("re", String.valueOf(bp.getRe())));	
	    list.add(new ParamsBean("f", String.valueOf(bp.getF())));
	    list.add(new ParamsBean("qs", String.valueOf(bp.getQs())));	
	    list.add(new ParamsBean("oi", String.valueOf(bp.getOi())));	
	    list.add(new ParamsBean("oc", String.valueOf(bp.getOc())));	
	    list.add(new ParamsBean("ls", String.valueOf(bp.getLs())));	
	    list.add(new ParamsBean("le", String.valueOf(bp.getLe())));	
	    list.add(new ParamsBean("ue", String.valueOf(bp.getUe())));	
	    list.add(new ParamsBean("cs", String.valueOf(bp.getCs())));	
	    list.add(new ParamsBean("ce", String.valueOf(bp.getCe())));	
	    list.add(new ParamsBean("je",String.valueOf(bp.getJe())));	
	    list.add(new ParamsBean("a", String.valueOf(bp.getA())));	
	    list.add(new ParamsBean("q", String.valueOf(bp.getQ())));	
	    list.add(new ParamsBean("fi", String.valueOf(bp.getFi())));	
	    list.add(new ParamsBean("fp", String.valueOf(bp.getFp())));	
	    list.add(new ParamsBean("ct", String.valueOf(bp.getCt())));	
	    list.add(new ParamsBean("de", String.valueOf(bp.getDe())));	
	    list.add(new ParamsBean("ds", String.valueOf(bp.getDs())));	
	    list.add(new ParamsBean("sh", String.valueOf(bp.getSh())));	
	    list.add(new ParamsBean("sw", String.valueOf(bp.getSw())));	
	    //把map拼接到url里
	    String paramstr = HttpParamUtil.getParams("get", list);
	    String urlString =DEFAULT_DC_SERVER_URL+"pf"+paramstr;
		 CloseableHttpClient httpclient = getHttpClient();
		 try {
			HttpPost post = new HttpPost(urlString);
			post.setHeader("User-Agent", 
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
			Random random = new Random();
			String ip = ips.get(random.nextInt(ips.size()));
			post.setHeader("x-forwarded-for",ip);
			httpclient.execute(post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("发送pf数据错误", e.getMessage());
		}finally{
			try {
				closeHttpClient(httpclient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
     private CloseableHttpClient getHttpClient(){
	        return HttpClients.createDefault();
	    }
	     
	  private void closeHttpClient(CloseableHttpClient client) throws IOException{
	        if (client != null){
	            client.close();
	        }
	    }    
	
	private BrowserPF putParmtersToPf(BrowserParmaters bp){
		BrowserPF parameter = new BrowserPF();
		Long time = System.currentTimeMillis();
		String stringtime = time.toString();
		// 生成随机数，使指标值每次都不一样
		parameter.setAv("1");
		parameter.setV("1");
		parameter.setKey(bp.getKey());// sJHYO6_g_NI , H3Kseu_O-U0 516
		parameter.setRef(bp.getPfUrl());// http://192.168.5.50:8081/testSDTY/test/hi.jsp
		parameter.setRand(stringtime);
		parameter.setPvid(bp.getPvid());
		//dom = oe-rs 让rs 随机生成   oe = dom+rs
		int q =  randomNum();
		int a = Integer.parseInt(bp.getServiceResponseTime());
		int rs = Integer.parseInt(bp.getRequestQueue())+a+Integer.parseInt(bp.getNetworkDelay());
		parameter.setOs(randomNum());
		parameter.setOe(Integer.parseInt(bp.getDomTreatment())+rs);//dom处理
		parameter.setRs(rs);//后端耗时
		parameter.setRe(randomNum());
		parameter.setF(randomNum());
		parameter.setQs(randomNum());
		parameter.setOi(randomNum());
		parameter.setOc(randomNum());
		parameter.setLs(randomNum());
		int le = rs + Integer.parseInt(bp.getFrontEndLongRunning());
		parameter.setLe(le);// 当le为0，则取ue//页面渲染  || 页面加载
		parameter.setUe(0);
		//ce-cs = tcp  ce = tcp+cs
		int cs = randomNum();
		int ce = Integer.parseInt(bp.getTcpConnectTime())+cs;
		parameter.setCe(ce);
		parameter.setCs(cs);
		parameter.setJe(randomNum());
		parameter.setA(a);//服务器响应时间  ||  应用耗时
		parameter.setQ(Integer.parseInt(bp.getRequestQueue()));//请求排队
		parameter.setFi(Integer.parseInt(bp.getFirstInteractionTime()));//首次交互时间
		parameter.setFp(Integer.parseInt(bp.getFirstPrintTime()));//首次渲染时间
		parameter.setCt(Integer.parseInt(bp.getCustomLoad()));//自定义加载
		//de -ds = dns de = dns+ds
		int ds = randomNum();
		int de = Integer.parseInt(bp.getDnsTime())+ds;
		parameter.setDe(de);
		parameter.setDs(ds);
		parameter.setSh(1080);
		parameter.setSw(1920);
		return parameter;
	}
	
	private int randomNum(){
		return (int) (20.0 * Math.random());
	}
	


}
