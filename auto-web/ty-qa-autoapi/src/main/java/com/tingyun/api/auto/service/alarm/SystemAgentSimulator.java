/**
 * Copyright 2007, NetworkBench Systems Corp.
 */
package com.tingyun.api.auto.service.alarm;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.networkbench.base.util.UncString;
import com.networkbench.newlens.datacollector.entity.ClientConfiguration;
import com.networkbench.newlens.datacollector.entity.MetricName;
import com.networkbench.newlens.datacollector.entity.avro.GenericMetricItem;
import com.networkbench.newlens.datacollector.entity.avro.MetricKey;
import com.networkbench.newlens.datacollector.entity.avro.PerfMetrics;
import com.networkbench.newlens.datacollector.mvc.command.InitAgentAppRequestCommand;
import com.networkbench.newlens.datacollector.mvc.command.UploadRequestCommand;
import com.networkbench.newlens.datacollector.serialize.JacksonSerializableObjectMapper;

/**
 * @author BurningIce
 * modified by baiyl
 */
public class SystemAgentSimulator {
	private final static JacksonSerializableObjectMapper JSON_OBJECT_MAPPER = new JacksonSerializableObjectMapper();
	private final static Pattern PATTERN_INIT_MOBILE_APP_RESPONSE = Pattern.compile("\\{\"status\"\\:\"success\",\"result\"\\:\\{.*\"appSessionKey\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*}");
	private final static String USER_AGENT_HEADER_VALUE = "NBS Newlens Agent/1.0.0 (iOS 6.1.2)";
	private final static String DEFAULT_MOBILE_APP_TOKEN = "888-888-888";
	private final static int REQUEST_TIME_OUT = 60000;
	private final static String DATA_VERSION = "2.0";
	private final static String[] applicationNames = new String[]{"ceshi"};   //应用名称
    private final static ClientConfiguration[] CLIENT_CON = new ClientConfiguration[3];
	
    static {
    	
    	ClientConfiguration clientCon = new ClientConfiguration();
    	clientCon.setAgentEnabled(true);
    	clientCon.setAgentLogFileCount(1);
    	clientCon.setAgentLogFileName("newlens.log");
//    	clientCon.setAgentLogFileSize("100");
    	clientCon.setAgentLogLevel("debug");
    	clientCon.setAppName("testing_app_name");
    	clientCon.setAuditMode(false);
    	clientCon.setAutoAppNaming(false);
    	clientCon.setLogSql(true);
    	clientCon.setProxyHost("");
    	clientCon.setProxyPassword("");
    	clientCon.setProxyPort(88);
    	clientCon.setProxySsl(true);
    	clientCon.setProxyUser("");
		CLIENT_CON[0] = clientCon;
		
		clientCon = new ClientConfiguration();
    	clientCon.setAgentEnabled(false);
    	clientCon.setAgentLogFileCount(2);
    	clientCon.setAgentLogFileName("javaagent.log");
//    	clientCon.setAgentLogFileSize("200");
    	clientCon.setAgentLogLevel("info");
    	clientCon.setAppName("PHP Application");
    	clientCon.setAuditMode(false);
    	clientCon.setAutoAppNaming(true);
    	clientCon.setLogSql(false);
    	clientCon.setProxyHost("");
    	clientCon.setProxyPassword("");
    	clientCon.setProxyPort(99);
    	clientCon.setProxySsl(false);
    	clientCon.setProxyUser("");
		CLIENT_CON[1] = clientCon;
		
		clientCon = new ClientConfiguration();
    	clientCon.setAgentEnabled(true);
    	clientCon.setAgentLogFileCount(3);
    	clientCon.setAgentLogFileName("phpagent.log");
//    	clientCon.setAgentLogFileSize("100");
    	clientCon.setAgentLogLevel("info");
    	clientCon.setAppName("networkbench-newlens-javaagent-test");
    	clientCon.setAuditMode(false);
    	clientCon.setAutoAppNaming(true);
    	clientCon.setLogSql(false);
    	clientCon.setProxyHost("");
    	clientCon.setProxyPassword("");
    	clientCon.setProxyPort(89);
    	clientCon.setProxySsl(false);
    	clientCon.setProxyUser("");
		CLIENT_CON[2] = clientCon;
		
    }
    
  
    private final static  Map<String, String> env =new HashMap<String,String>();
    
    /**
     * sys触发警报需要的metric key
     */
	private	final static MetricKey[] METRIC_KEY = new MetricKey[] {

		new MetricKey(0, 0, "System/Memory/Memory used", null),
		new MetricKey(0, 0, "System/CPU/SystemUtilization", null),
		new MetricKey(0, 0, "System/CPU/UserUtilization", null),
		new MetricKey(0, 0, "System/CPU/iowait", null),
		new MetricKey(0, 0, "System Disk/All/Utilization", null),
		new MetricKey(0, 0, "System Filesystem/%2Fopt%2Fmnt/Used", "System Disk/NULL/%2Fdev%2Fsdb0"),
		
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		String serverUrl = (args.length == 0 ? "http://localhost" : args[0]);
		
		if(serverUrl.indexOf("://") == -1) {
			serverUrl = "http://" + serverUrl;
		}
		
		String appToken = (args.length > 1 ? args[1] : DEFAULT_MOBILE_APP_TOKEN);
		String hostName = System.getProperty("hostName");
		int period = (UncString.toInt(System.getProperty("period"), Integer.MAX_VALUE));
		String mode = System.getProperty("mode");
		
		// 指定触发警报的固定值
		double cpuUtilization = UncString.toDouble(System.getProperty("cpuUtilization"), 0.0d); // cpu警报阀值
		double memoryUtilization = UncString.toDouble(System.getProperty("memoryUtilization"), 0.0d); // 内存警报阀值
		double ioUtilization = UncString.toDouble(System.getProperty("ioUtilization"), 0.0d); // io警报阀值
		double diskUtilization = UncString.toDouble(System.getProperty("diskUtilization"), 0.0d); // 磁盘警报阀值
		
		double[] fixValueArray = null;
		if (cpuUtilization > 0.000000001d || ioUtilization > 0.000000001d || memoryUtilization > 0.000000001d || diskUtilization > 0.000000001d) {
			fixValueArray = new double[]{cpuUtilization,memoryUtilization,ioUtilization,diskUtilization};
		}

		int protocolIndex = serverUrl.indexOf("://");
		String protocol = serverUrl.substring(0, protocolIndex);
		String hostAndPort = serverUrl.substring(protocolIndex + 3);
		int portIndex = hostAndPort.indexOf(':');
		String host;
		int port;
		
		if(portIndex == -1) {
			host = hostAndPort;
			port = ("https".equals(protocol) ? 443 : 80);
		} else {
			host = hostAndPort.substring(0, portIndex);
			port = UncString.toInt(hostAndPort.substring(portIndex + 1));
		}
		
		try {
			
			boolean withIdle = "idle".equals(mode);
			
			System.out.println("running in " + (withIdle ? "idle" : "normal") + " mode with server: " + serverUrl + ", appToken: " + appToken);
			
			// 开始模拟
			simulate(protocol, host, port, hostName,period, appToken, withIdle,fixValueArray);
			
			System.out.println("finished, exit!");
				
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static Map<String, Integer> DEVICE_LAST_LAUNCH_TIME = new HashMap<String, Integer>();
	
	/**
	 * 模拟入口
	 * @param protocol
	 * @param host
	 * @param port
	 * @param count
	 * @param thinkTime
	 * @param appToken
	 * @param withIdle
	 * @param responseTime
	 * @param httpErrorPercentage
	 * @param networkErrorPercentage
	 * @throws Exception
	 */
	private static void simulate(String protocol, String host, int port,String hostName, int period, String appToken, boolean withIdle,double[] fixValues) throws Exception {
		
		// 模拟initAgentApp过程
		String response = simulateInitAgentApp(protocol, host, port, appToken,hostName);
		
		// init成功
		if(response != null) {
			
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			System.out.print(response);
			
			if(matcher.matches()) {
				
				String appsessionkey = matcher.group(1);
				String deviceId = String.valueOf((int)(System.currentTimeMillis()));
				System.out.println("upload data using appsessionkey=" + appsessionkey +  "...");
				
				double cpuUtilization = 0.0d;
				double ioUtilization = 0.0d;
				double memoryUtilization = 0.0d;
				double diskUtilization = 0.0d;
				
				if (null != fixValues && fixValues.length == 4) {
					cpuUtilization = fixValues[0];
					memoryUtilization = fixValues[1];
					ioUtilization = fixValues[2];
					diskUtilization = fixValues[3];
				}
				
				DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int)(System.currentTimeMillis()/ 1000L)));
				
				for(int i = 0; i < period; ++i) {
					
					boolean isIdle = false;
					
					if(withIdle) {
						isIdle = (UncString.randomInt(8) == 1);
					}
					
					if(isIdle) {
						
						// idle upload
						System.out.println("idling for 60s...");
						Thread.sleep(480000L);
						post(protocol, host, port, "/upload?licenseKey="+appToken+"&version=" + DATA_VERSION + "&appSessionKey=" + appsessionkey, "{}", appToken);
						
					} else {
						
						Integer launchTime = DEVICE_LAST_LAUNCH_TIME.get(deviceId);
						int activeTime = (int)(System.currentTimeMillis() / 1000L) - launchTime.intValue();
						int expectedActiveTime = UncString.randomInt(90, 600);
						boolean isLastRequest = (activeTime > expectedActiveTime);
						
						// 上传数据
						for (int j = 0; j < 200; j++) {
							
							String resp = simulateUploadAgentData(protocol, host, port,appToken, appsessionkey, isLastRequest,cpuUtilization,memoryUtilization,ioUtilization,diskUtilization);
							
							System.out.println("uploaded " + 1 + ", response: " + resp);
							
						}
						
						if(isLastRequest) {
							
							int sleepTime = (withIdle ? UncString.randomInt(5000, 120000) : 1000);
							System.out.println("disconnect device: " + deviceId + ", active time: " + activeTime);
							System.out.println("sleep " + sleepTime + "ms after disconnect ...");
							Thread.sleep(sleepTime);
							System.out.println("reconnect ...");
							
							// reconnect
							response = simulateInitAgentApp(protocol, host, port, appToken,hostName);
							
							if(response != null) {
								
								matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
								
								if(matcher.matches()) {
									appsessionkey = matcher.group(1);
									deviceId = String.valueOf((int)(System.currentTimeMillis()));
									DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int)(System.currentTimeMillis()/ 1000L)));
								}
								
							}
							
						}
							
					}
					
					if (period > 1) {
						
						if (i < period - 1) {
							Thread.sleep(60000);
						}
					}
					
				}
				
			} else {
				System.out.println("token not found in response: " + response);
			}
		}
	}
	
	/**
	 * 未使用
	 * @param list
	 * @return
	 */
	private static List<UploadRequestCommand> shuffle(List<UploadRequestCommand> list) {
		
		if(list == null)
			return null;
		
		UploadRequestCommand[] arr = list.toArray(new UploadRequestCommand[list.size()]);
		
		for(int i = 0; i < arr.length; ++i) {
			
			int swapIndex1 = UncString.randomInt(arr.length);
			int swapIndex2 = UncString.randomInt(arr.length);
			UploadRequestCommand temp = arr[swapIndex1];
			arr[swapIndex1] = arr[swapIndex2];
			arr[swapIndex2] = temp;
			
		}
		
		return Arrays.asList(arr);
	}

	private static String simulateInitAgentApp(String protocol, String host, int port, String appToken,String hostName) throws Exception {
		
		System.out.println("initAgentApp with token: " + appToken);
	
		int configIndex = UncString.randomInt(CLIENT_CON.length);
		ClientConfiguration clientconfig = CLIENT_CON[configIndex];
		
		env.put("Dispatcher", "Apache Tomcat");
		env.put("Processors", "1");
		env.put("OS version", "2.6.18-308.el5PAE");
		env.put("Dispatcher Version", "6.0.37");
		env.put("Server port", "8080");
		env.put("OS", "Linux");
		env.put("Log path", "/opt/renyp/newlens/logs/newlens_agent.log");
		env.put("Heap initial (MB)", "512.0");
		env.put("Framework", "java");
		
		InitAgentAppRequestCommand command = new  InitAgentAppRequestCommand();
		command.setAgentVersion(DATA_VERSION);
		command.setApplicationNames(applicationNames);
		command.setConfig(clientconfig);
		command.setEnv(env);
		
		//设置host名称
		command.setHost("警报测试8");
		command.setLanguage("system");
		command.setPid(4790);
		command.setPort(8080);
		command.setHost("Kvmla-20141114782");
		
		String text = jsonize(command);
		String response = post(protocol, host, port, "/initAgentApp?licenseKey="+appToken+"&version=" + DATA_VERSION, text, appToken);
		System.out.println("initMobileAgentApp finished: " + response);
		return response;
		
	}
	

	/**
	 * 模拟上传数据
	 * @param protocol
	 * @param host
	 * @param port
	 * @param appToken
	 * @param token
	 * @param isLastRequest
	 * @return
	 * @throws Exception
	 */
	private static String simulateUploadAgentData(String protocol, String host, int port, String appToken, String token, boolean isLastRequest,double cpuFixValue,double memoryFixValue,double ioFixValue,double diskFixValue) throws Exception {
		
		UploadRequestCommand command = createUploadMessage(isLastRequest,cpuFixValue,memoryFixValue,ioFixValue,diskFixValue);
		String text = jsonize(command);
		
		System.out.println(text);
		
		return post(protocol, host, port, "/upload?licenseKey="+appToken+"&version=" + DATA_VERSION + "&appSessionKey=" + token, text, appToken);
		
	}

	/**
	 * 构建上传数据
	 * @param isLastRequest
	 * @param fixedResponseTimeValue 响应时间
	 * @param httpError http错误
	 * @param networkError 网络错误
	 * @return
	 * @throws Exception
	 */
	private static UploadRequestCommand createUploadMessage(boolean isLastRequest, double cpuFixValue,double memoryFixValue,double ioFixValue,double diskFixValue) throws Exception {
		
		// 上传数据结构
		UploadRequestCommand command = new UploadRequestCommand();
		
		PerfMetrics perfMetrics = new PerfMetrics();
		List<GenericMetricItem> general = new ArrayList<GenericMetricItem>();
		
		//actions 数据模拟
		for(int i = METRIC_KEY.length - 1; i >= 0; i--) {
			
			GenericMetricItem genericmetricitem = new GenericMetricItem();
			
			com.networkbench.newlens.datacollector.entity.avro.MetricKey metrickey = METRIC_KEY[i];
			genericmetricitem.setMetricKey(metrickey);
			genericmetricitem.setCount(1);
			
			// Memory
			if (metrickey.getName().equals(com.networkbench.newlens.datacollector.entity.MetricKey.METRIC_KEY_MEMORY_USED)) {
				
				if (memoryFixValue > 0.0000000001d) {
					genericmetricitem.setSum((long)(memoryFixValue * 100L));
					genericmetricitem.setSumEx(100L);
				}else {
					genericmetricitem.setSum(UncString.randomInt(10000,1000000));
					genericmetricitem.setSumEx(UncString.randomInt(10000,1000000));
				}
				
			}
			
			// CPU
			if (metrickey.getName().equals(com.networkbench.newlens.datacollector.entity.MetricKey.METRIC_KEY_SYSTEM_CPU_UTILIZATION)
					|| metrickey.getName().equals(com.networkbench.newlens.datacollector.entity.MetricKey.METRIC_KEY_USER_CPU_UTILIZATION)
					|| metrickey.getName().equals(com.networkbench.newlens.datacollector.entity.MetricKey.METRIC_KEY_IOWAIT)) {
				
				if (cpuFixValue > 0.0000000001d) {
					genericmetricitem.setSum((long)((((cpuFixValue * 100L) / 3) * genericmetricitem.getCount())));
				}else {
					genericmetricitem.setSum(UncString.randomInt(10000,1000000) * genericmetricitem.getCount());
				}
				
			}
			
            // disk
            if (metrickey.getName().equals(com.networkbench.newlens.datacollector.entity.MetricKey.METRIC_KEY_IO_UTILIZATION)) {
				
				if (diskFixValue > 0.0000000001d) {
					genericmetricitem.setSum((long)(diskFixValue * 100L) * genericmetricitem.getCount());
				}else {
					genericmetricitem.setSum(UncString.randomInt(10000,1000000) * genericmetricitem.getCount());
				}
				
			}
            
            // FileSystem
            if (metrickey.getName().contains(MetricName.METRIC_NAME_SPACE_USED)) {
				
				if (ioFixValue > 0.0000000001d) {
					genericmetricitem.setSum((long)(ioFixValue * 100L));
					genericmetricitem.setSumEx(100L);
				}else {
					genericmetricitem.setSum(UncString.randomInt(10000,1000000));
					genericmetricitem.setSumEx(UncString.randomInt(10000,1000000));
				}
				
			}
			
			genericmetricitem.setSumSquare(UncString.randomInt(10000,1000000));
			genericmetricitem.setMax(UncString.randomInt(20,20000));
			genericmetricitem.setMin(UncString.randomInt(10,10000));
			general.add(genericmetricitem);
			
		}
			
		perfMetrics.setGeneral(general);
		perfMetrics.setInterval(60);
		perfMetrics.setTimeTo((int) (System.currentTimeMillis() / 1000L));
		perfMetrics.setTimeFrom((int) ((System.currentTimeMillis() / 1000L)-60));
		perfMetrics.setOwnerId(1);
		command.setPerfMetrics(perfMetrics);
		
		return command;
		
	}
	
	private static String jsonize(Object obj) throws Exception {
		
		return JSON_OBJECT_MAPPER.writeValueAsString(obj);
		
	}
	
	private static String post(String protocol, String host, int port, String uri, String content, String appToken) throws Exception {
		
		HttpURLConnection conn = createConnection(protocol, host, port, uri, REQUEST_TIME_OUT, appToken);
		OutputStream os = conn.getOutputStream();
		os.write(gzip(content, "UTF-8"));
		int statusCode = conn.getResponseCode();
		String responseBody = (statusCode == 200 ? readResponseBody(conn) : null);
		conn.disconnect();
		return responseBody;
		
	}
	
	private static HttpURLConnection createConnection(String protocol, String host, int port, String uri, int requestTimeoutInMillis, String appToken) throws Exception {
		
		URL url = new URL(protocol, host, port, uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("User-Agent", USER_AGENT_HEADER_VALUE);
		conn.setConnectTimeout(requestTimeoutInMillis);
		conn.setReadTimeout(requestTimeoutInMillis);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("CONTENT-TYPE", "application/octet-stream");
		conn.setRequestProperty("ACCEPT-ENCODING", "gzip, deflate, plain");
		conn.setRequestProperty("CONTENT-ENCODING", "gzip");
		conn.setRequestProperty("X-License-Key", appToken == null ? DEFAULT_MOBILE_APP_TOKEN : appToken);
		return conn;
		
	}
	
	private static String readResponseBody(HttpURLConnection conn) throws Exception {
		
		InputStream is = null;
		BufferedReader in = null;
		
		try {
			
			is = conn.getInputStream();
			in = getBufferedReader(conn, is);
			char[] cbuf = new char[4096];
			StringBuilder sb = new StringBuilder();
			
			int k;
			for( ; (k = in.read(cbuf)) != -1 ; ) {
				sb.append(cbuf, 0, k);
			}
			
			String s = sb.toString();
			
			return s;
			
		} finally {
			
			if(in != null)
				in.close();
			
			if(is != null)
				is.close();
			
		}
		
	}

	private static BufferedReader getBufferedReader(HttpURLConnection conn, InputStream is) throws IOException {
		
		String encoding = conn.getHeaderField("content-encoding");
		
		if ("gzip".equals(encoding))
			is = new GZIPInputStream(is);
		
		return new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
	}

	private static byte[] gzip(String content, String charset) throws Exception {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		GZIPOutputStream gout = new GZIPOutputStream(baos);
		gout.write(content.getBytes(charset));
		gout.flush();
		gout.close();
		
		baos.close();
		
		return baos.toByteArray();
		
	}
	
	private static byte[] deflate(String content, String charset) throws Exception {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DeflaterOutputStream gout = new DeflaterOutputStream(baos);
		gout.write(content.getBytes(charset));
		gout.flush();
		gout.close();
		
		baos.close();
		
		return baos.toByteArray();
		
	}
}
