package com.tingyun.api.auto.service;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppHttpClient {

	private static String DEFAULT_ENCODE = "UTF-8";
	private static Integer CONNECTION_TIMEOUT = 30000;
	private static Logger LOG = LoggerFactory.getLogger(AppHttpClient.class);
	/**
	 * @param url
	 *            目标url
	 * @param paramMap
	 *            请求参数
	 * @return 目标服务器返回的字符串
	 * @throws Exception 
	 */
	public static String post(String url, Map<String, String> headerMap, Map<String, String> paramMap) throws Exception {
		LOG.info("**********************start post 提交数据请求！***************");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(CONNECTION_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	                return true;
	            }
	        }).build();
	        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
			httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).setDefaultRequestConfig(requestConfig).build();

			HttpPost httpPost = new HttpPost(url);
			
			if (headerMap != null && headerMap.size() > 0) {
				Header[] headers = new BasicHeader[headerMap.size()];
				int index = 0;
				for (String name : headerMap.keySet()) {
					String value = headerMap.get(name);
					Header header = new BasicHeader(name, value);
					headers[index++] = header;
				}
				httpPost.setHeaders(headers);
			}
			
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			if(paramMap != null && paramMap.size() > 0) {
				for (String key : paramMap.keySet()) {
				    parameters.add(new BasicNameValuePair(key, paramMap.get(key)));
				}
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, DEFAULT_ENCODE);
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				String entityContent  = EntityUtils.toString(response.getEntity(), DEFAULT_ENCODE);
				JSONObject jsonObject =  new JSONObject(entityContent);
				JSONObject  chart = jsonObject.getJSONObject("chart");
				String beginTime=chart.getString("beginTime");
				String endTime=chart.getString("endTime");
				String timeGranularity=chart.getString("timeGranularity");
				JSONArray dataset=chart.getJSONArray("dataset");
				List<String> listJsons = new ArrayList<String>();
				listJsons.add(beginTime);
				listJsons.add(endTime);
				listJsons.add(timeGranularity);
				listJsons.add(dataset.toString());
//				jsonObject.get
//				response.close();
				return listJsons.toString();
			} else {
				throw new Exception("HTTP response ERROR and statusCode=" + statusCode);
			}
		} finally {
			try {
				if(httpClient != null){
					httpClient.close();
				}
			} catch (IOException e) {
				LOG.error("io关闭异常！{}",e);
			}
		}
	}
	
	/**
	* @author : chenjingli
	* @decription 组合参数
	* @return
	 */
	public static String doRequest(String authKey, String param, String url) {
		Map<String, String> headerMap = new HashMap<String, String>();
		Map<String, String> paramMap = new HashMap<String, String>();
		try {
			headerMap.put("X-Auth-Key", authKey.trim());
			String[] paramArray1 = param.split("&");
			for(String paramStr : paramArray1){
				String[] arr = paramStr.trim().split("=");
				paramMap.put(arr[0].trim(), arr[1].trim());
			}
			String returnData = post(url, headerMap, paramMap);
			return returnData;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	* @author : chenjingli
	* @decription  请求方式
	* @return
	 */
	public static String convertCurlCMD(String authKey, String param, String url) {
		String base = "curl -k -H 'X-Auth-Key: " + authKey.trim() + "' -d ";
		return base + " '" + param.trim() + "' " + url.trim();
	}
}
