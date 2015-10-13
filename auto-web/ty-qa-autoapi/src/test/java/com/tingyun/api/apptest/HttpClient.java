package com.tingyun.api.apptest;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.net.ssl.SSLContext;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang.StringUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.api.auto.common.ApiException;
import com.tingyun.api.auto.entity.ReportApiBean;
import com.tingyun.api.auto.utils.ConnectionContext;
import com.tingyun.api.auto.utils.DBUtils;


/**
 * HttpClientUtils
 * 
 * @author chenjingli
 *
 */
public class HttpClient {
	private static final String SAVE_SQL = "update TEST_APP_API set xml=?,json=? where id = ?";
	private static final String SELECT_PAR = "select id,c6nnnfcg,parameter,url from TEST_APP_API limit ?,1";
	private String DEFAULT_ENCODE = "UTF-8";
	private Integer CONNECTION_TIMEOUT = 30000;
	private Logger LOG = LoggerFactory.getLogger(HttpClient.class);
	/**
	 * @param url
	 *            目标url
	 * @param paramMap
	 *            请求参数
	 * @return 目标服务器返回的字符串
	 * @throws Exception 
	 */
	public String post(String url, Map<String, String> headerMap, Map<String, String> paramMap) throws Exception {
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
				response.close();
				return entityContent;
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
	private String doRequest(String authKey, String param, String url) {
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
	 * 把基准数据插入数据库
	 */
	private void insertDataToDB(){
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		int number = Integer.parseInt(bundle.getString("app.count.number"));
		LOG.info("获得循环的number熟练是=======[ {} ]",number);
		for (int i = 0; i < number; i++) {
			try{
				ReportApiBean app =  new QueryRunner().query(DBUtils.getConnection(),
						SELECT_PAR,  new BeanHandler<ReportApiBean>(ReportApiBean.class),i);
				int id = app.getId();
				String authKey = app.getC6nnnfcg();
				String par = app.getParameter();
				String url = app.getUrl();
				if(
				    StringUtils.isNotBlank(authKey) &&
				    StringUtils.isNotBlank(par)     &&
				    StringUtils.isNotBlank(url)
				    )
				{
					new QueryRunner().update(DBUtils.getConnection(),SAVE_SQL, new String[]{
						doRequest(authKey, par, url),doRequest(authKey, par, url.replace(".json", ".xml")),String.valueOf(id)
					}); 
				}
				else
				{
					throw new ApiException("参数为Null");
				}
			}catch(Exception e1){
				LOG.error("插入json或xml到数据库异常：{}",e1);
			}finally{
				try {
					DBUtils.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		HttpClient client= new HttpClient();
		client.insertDataToDB();
	}
}
