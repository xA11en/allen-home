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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.networkbench.base.util.UncString;
import com.networkbench.newlens.datacollector.entity.ClientConfiguration;
import com.networkbench.newlens.datacollector.entity.avro.ApdexItem;
import com.networkbench.newlens.datacollector.entity.avro.ErrorCounterItem;
import com.networkbench.newlens.datacollector.entity.avro.GenericMetricItem;
import com.networkbench.newlens.datacollector.entity.avro.MetricKey;
import com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo;
import com.networkbench.newlens.datacollector.entity.avro.PerfMetrics;
import com.networkbench.newlens.datacollector.mvc.command.InitAgentAppRequestCommand;
import com.networkbench.newlens.datacollector.mvc.command.UploadRequestCommand;
import com.networkbench.newlens.datacollector.serialize.JacksonSerializableObjectMapper;
import com.tingyun.api.auto.entity.alarm.ServerParmaters;

public class ApplicationSimulatorWithFixValuesAAA {
	
	private static final JacksonSerializableObjectMapper JSON_OBJECT_MAPPER = new JacksonSerializableObjectMapper();
	private static final Pattern PATTERN_INIT_MOBILE_APP_RESPONSE = Pattern.compile("\\{\"status\"\\:\"success\",\"result\"\\:\\{.*\"appSessionKey\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*}");
	private static final String USER_AGENT_HEADER_VALUE = "NBS Newlens Agent/1.0.0 (iOS 6.1.2)";
	private static final String DEFAULT_MOBILE_APP_TOKEN = "888-888-888";
	private static final int REQUEST_TIME_OUT = 60000;
	private static final String DATA_VERSION = "1.0";
	private static final MobileDeviceInfo[] MOBILE_DEVICES = new MobileDeviceInfo[4];
	private static final MobileDeviceInfo[] MOBILE_DEVS = new MobileDeviceInfo[6];
	private String[] applicationNames = null;
	private static final ClientConfiguration[] CLIENT_CON = new ClientConfiguration[3];
	private static int ActionCount;

	static {
		ClientConfiguration clientCon = new ClientConfiguration();
		clientCon.setAgentEnabled(true);
		clientCon.setAgentLogFileCount(1);
		clientCon.setAgentLogFileName("newlens.log");
		clientCon.setAgentLogLevel("debug");
		clientCon.setAppName("applicationName");
		clientCon.setAuditMode(true);
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

	private static final Map<String, String> env = new HashMap();

	private final static MetricKey[] METRIC_ERROR_KEY = new MetricKey[] {
		new MetricKey(0, 0, "Errors/Count/All", null),
		new MetricKey(0, 0,"Errors/Count/WebAction/Servlet/SaveForC3p0MysqlS", null),
		new MetricKey(0, 0, "Errors/Count/AllWeb", null) 
	};

	/**
	 * @param args
	 */
	public void main(String[] args,ServerParmaters serverParmaters) {
		String serverUrl = (args.length == 0 ? "http://192.168.2.219:8080": args[0]);
		if (serverUrl.indexOf("://") == -1) {
			serverUrl = "http://" + serverUrl;
		}

		String appToken = (args.length > 1 ? args[1] : DEFAULT_MOBILE_APP_TOKEN);
		int thinkTime = (UncString.toInt(System.getProperty("thinkTime"), 60000));
		int period = (UncString.toInt(serverParmaters.getPeriod(), 10));

		//String mode = System.getProperty("mode");
		String mode = serverParmaters.getMode();
		applicationNames= serverParmaters.getApplicationName();//
//		System.out.println(serverParmaters.getCount()+"----------------------------");
			System.out.println(applicationNames[0]+"-------------------");
		double apdex = (UncString.toDouble(serverParmaters.getApdex(), 0.9));//apdex默认0.9
		int Actioncount = UncString.toInt(serverParmaters.getCount(),10);//吞吐率默认10
		int responseTime = UncString.toInt(serverParmaters.getResponseTime(),50);//响应时间默认50
		double errRate = (UncString.toDouble(serverParmaters.getErrRate(), 0.0));//错误率默认0


		ActionCount=Actioncount;

		int protocolIndex = serverUrl.indexOf("://");
		String protocol = serverUrl.substring(0, protocolIndex);
		String hostAndPort = serverUrl.substring(protocolIndex + 3);
		int portIndex = hostAndPort.indexOf(':');
		String host;
		int port;
		if (portIndex == -1) {
			host = hostAndPort;
			port = ("https".equals(protocol) ? 443 : 80);
		} else {
			host = hostAndPort.substring(0, portIndex);
			port = UncString.toInt(hostAndPort.substring(portIndex + 1));
		}

		try {
			if ("fixed".equals(mode)) {
				System.out.println("running in fixed mode with server: " + serverUrl + ", appToken: " + appToken + ", period: " + period);
				simulateInFixedValues(protocol, host, port, period, thinkTime,appToken, apdex, errRate, responseTime);
			} else {
				System.out.println("running in normal mode with server: " + serverUrl + ", appToken: " + appToken + ", period: " + period);
				simulate(protocol, host, port, period, thinkTime, appToken);
			}

			System.out.println("finished, exit!");
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static Map<String, Integer> DEVICE_LAST_LAUNCH_TIME = new HashMap<String, Integer>();

	private void simulateInFixedValues(String protocol, String host,int port, int period, int thinkTime, String appToken, double apdex,double errRate, int responseTime) throws Exception {
		String response = simulateInitAgentApp(protocol, host, port, appToken);
		//long startTime = System.currentTimeMillis() / 1000;
		
		if (response != null) {
			
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			
			if (matcher.matches()) {
				
				String appsessionkey = matcher.group(1);
				String deviceId = String.valueOf((int) (System.currentTimeMillis()));
				
				//System.out.println("upload data using appsessionkey=" + appsessionkey + "...");
				DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int) (System.currentTimeMillis() / 1000L)));

				//while (System.currentTimeMillis() / 1000 - startTime <= time) {
					
//				for(int i=1;i<=period;i++){
//					//System.out.println("upload "+i);
//
//					/*Integer launchTime = DEVICE_LAST_LAUNCH_TIME.get(deviceId);
//					int activeTime = (int) (System.currentTimeMillis() / 1000L) - launchTime.intValue();
//					int expectedActiveTime = UncString.randomInt(90, 600);
//					boolean isLastRequest = (activeTime > expectedActiveTime);
//*/
//					String resp = simulateUploaFixValuedAgentData(protocol,host, port, appToken, appsessionkey, apdex, errRate, responseTime);
//					//System.out.println("uploaded " + ", response: " + resp);
//				/*	if (isLastRequest) {
//						
//						System.out.println("disconnect device: " + deviceId + ", active time: " + activeTime);
//						System.out.println("reconnect ...");
//						
//						// reconnect
//						response = simulateInitAgentApp(protocol, host, port,appToken);
//
//						if (response != null) {
//							matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
//							if (matcher.matches()) {
//								appsessionkey = matcher.group(1);
//								deviceId = String.valueOf((int) (System.currentTimeMillis()));
//								DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int) (System.currentTimeMillis() / 1000L)));
//							}
//						}
//					}*/
//					//System.out.println("sleep---"+thinkTime+"ms");
//					//Thread.sleep(thinkTime);
//					Thread.sleep(60000);
//				}
			} else {
				System.out.println("token not found in response: " + response);
			}
		}
	}

	private void simulate(String protocol, String host, int port,int period, int thinkTime, String appToken) throws Exception {
		
		//long startTime = System.currentTimeMillis() / 1000;
		String response = simulateInitAgentApp(protocol, host, port, appToken);
		
		if (response != null) {
			
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			System.out.print(response);
			
			if (matcher.matches()) {
				
				String appsessionkey = matcher.group(1);
				String deviceId = String.valueOf((int) (System.currentTimeMillis()));
				System.out.println("upload data using appsessionkey=" + appsessionkey + "...");
				DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int) (System.currentTimeMillis() / 1000L)));

				//while (System.currentTimeMillis() / 1000 - startTime <= period * 60) {
				
				for(int i=1;i<=period;i++){
					double apdex = Math.random();
					double errRate = Math.random();
					Integer launchTime = DEVICE_LAST_LAUNCH_TIME.get(deviceId);
					int activeTime = (int) (System.currentTimeMillis() / 1000L) - launchTime.intValue();
					int expectedActiveTime = UncString.randomInt(90, 600);
					boolean isLastRequest = (activeTime > expectedActiveTime);

					String resp = simulateUploaFixValuedAgentData(protocol,host, port, appToken, appsessionkey, apdex, errRate,0);
					//System.out.println("uploaded" + ", response: " + resp);
					if (isLastRequest) {
						
						System.out.println("disconnect device: " + deviceId+ ", active time: " + activeTime);
						System.out.println("reconnect ...");
						
						// reconnect
						response = simulateInitAgentApp(protocol, host, port,appToken);
						if (response != null) {
							matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
							if (matcher.matches()) {
								appsessionkey = matcher.group(1);
								deviceId = String.valueOf((int) (System.currentTimeMillis()));
								DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int) (System.currentTimeMillis() / 1000L)));
							}
						}
						
					}
					
					Thread.sleep(thinkTime);
					
				}
				
			} else {
				System.out.println("token not found in response: " + response);
			}
			
		}
		
	}

	private String simulateInitAgentApp(String protocol, String host,
			int port, String appToken) throws Exception {
		System.out.println(appToken+"======================");
		//System.out.println("initAgentApp with token: " + appToken);

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

		InitAgentAppRequestCommand command = new InitAgentAppRequestCommand();
		command.setAgentVersion(DATA_VERSION);
		command.setApplicationNames(applicationNames);
		command.setConfig(clientconfig);
		command.setEnv(env);
		command.setHost("test44");
		command.setLanguage("java");
		command.setPid(4790);
		command.setPort(8080);

		String text = jsonize(command);
		//System.out.println(" Send init:   "+text);
		String response = post(protocol, host, port,
				"/initAgentApp?licenseKey=" + appToken + "&version="
						+ DATA_VERSION, text, appToken);
System.out.println(applicationNames[0]+"======================");
		System.out.println(applicationNames[0]+" Received init:   " + response);
		return response;
	}

	private String simulateUploaFixValuedAgentData(String protocol,String host, int port, String appToken, String token, double fixedApdex, double fixedErrRate,int responseTime) throws Exception {
		UploadRequestCommand command = createFixValueUploadMessage(fixedApdex, fixedErrRate, responseTime);
		String text = jsonize(command);
		//System.out.println(applicationNames[0]+"---"+text);
		return post(protocol, host, port, "/upload?licenseKey=" + appToken + "&version=" + DATA_VERSION + "&appSessionKey=" + token, text,appToken);
		
	}

	public UploadRequestCommand createFixValueUploadMessage( double fixedApdex, double fixedErrRate,int responseTime) {
		
		UploadRequestCommand command = new UploadRequestCommand();

		PerfMetrics perfMetrics = new PerfMetrics();

		perfMetrics.setApplicationId(new int[48]);

		perfMetrics.setTimeFrom((int) (System.currentTimeMillis() / 1000));
		perfMetrics.setTimeTo((int) (System.currentTimeMillis() / 1000 + 60));

		command.setPerfMetrics(perfMetrics);

		List<GenericMetricItem> actions = new ArrayList<GenericMetricItem>();
		List<ErrorCounterItem> errors = new ArrayList<ErrorCounterItem>();
		List<ApdexItem> apdexs = new ArrayList<ApdexItem>();
		List<GenericMetricItem> generals = new ArrayList<GenericMetricItem>();

		perfMetrics.setActions(actions);
		perfMetrics.setGeneral(generals);

		perfMetrics.setErrors(errors);
		perfMetrics.setApdex(apdexs);

		//int count = 100;
		int count=ActionCount;
		int ecount = (int) (fixedErrRate * count);
		int scount = count - ecount;
		int sacount = (int) (scount * fixedApdex / 2);
		int tcount = (int) ((scount * fixedApdex - sacount) * 2);
		System.out.println("---");
		System.out.println(applicationNames[0]+"---"+"errRate:" + fixedErrRate + " apdex:" + fixedApdex + "  count:" + count + "	ecount:" + ecount + " sount:" + scount + " sacount:" + sacount + " tcount" + tcount);
		GenericMetricItem webactionMetricItem = new GenericMetricItem();
		webactionMetricItem.setMetricKey(new MetricKey(0, 0,"WebAction/Servlet/SaveForC3p0MysqlS", null));
		webactionMetricItem.setSum(responseTime * count);
		webactionMetricItem.setSumEx(UncString.randomInt(500,50000));
		webactionMetricItem.setSumSquare(UncString.randomInt(500,500000));
		webactionMetricItem.setMax(UncString.randomInt(200,200000));
		webactionMetricItem.setMin(UncString.randomInt(100,100000));
		generals.add(webactionMetricItem);

		for (GenericMetricItem item : generals) {
			item.setCount((int) (Math.random() * 5000));
		}

		GenericMetricItem genericMetricItem = new GenericMetricItem();
		genericMetricItem.setMetricKey(new MetricKey(0, 0,"WebAction/Servlet/SaveForC3p0MysqlS", null));
		genericMetricItem.setCount(scount);
		genericMetricItem.setMax(UncString.randomInt(200,200000));
		genericMetricItem.setMin(UncString.randomInt(100,100000));
		genericMetricItem.setSum(responseTime * scount);
		genericMetricItem.setSumEx(UncString.randomInt(500,50000));
		genericMetricItem.setSumSquare(UncString.randomInt(500,500000));

		actions.add(genericMetricItem);

		for (int i = 0; i < METRIC_ERROR_KEY.length; i++) {
			ErrorCounterItem errorCounterItem = new ErrorCounterItem();
			errorCounterItem.setMetricKey(METRIC_ERROR_KEY[i]);
			errorCounterItem.setErrorCount(ecount);
			errors.add(errorCounterItem);
		}

		ApdexItem apdexItem = new ApdexItem();
		apdexItem.setMetricKey(new MetricKey(0, 0,"Apdex/Servlet/SaveForC3p0MysqlS", null));
		apdexItem.setSatisfiedCount(sacount);
		apdexItem.setToleratingCount(tcount);
		apdexItem.setFrustratedCount(ecount);
		apdexItem.setApdexT(500);

		apdexs.add(apdexItem);

		return command;

	}

	private static String jsonize(Object obj) throws Exception {
		return JSON_OBJECT_MAPPER.writeValueAsString(obj);
	}

	private static String post(String protocol, String host, int port,
			String uri, String content, String appToken) throws Exception {
		HttpURLConnection conn = createConnection(protocol, host, port, uri,
				REQUEST_TIME_OUT, appToken);
		OutputStream os = conn.getOutputStream();
		os.write(gzip(content, "UTF-8"));
		int statusCode = conn.getResponseCode();
		String responseBody = (statusCode == 200 ? readResponseBody(conn)
				: null);
		conn.disconnect();
		return responseBody;
	}

	private static HttpURLConnection createConnection(String protocol,
			String host, int port, String uri, int requestTimeoutInMillis,
			String appToken) throws Exception {
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
		conn.setRequestProperty("X-License-Key",
				appToken == null ? DEFAULT_MOBILE_APP_TOKEN : appToken);
		return conn;
	}

	private static String readResponseBody(HttpURLConnection conn)
			throws Exception {
		InputStream is = null;
		BufferedReader in = null;
		try {
			is = conn.getInputStream();
			in = getBufferedReader(conn, is);
			char[] cbuf = new char[4096];
			StringBuilder sb = new StringBuilder();
			int k;
			for (; (k = in.read(cbuf)) != -1;) {
				sb.append(cbuf, 0, k);
			}
			String s = sb.toString();
			return s;
		} finally {
			if (in != null)
				in.close();

			if (is != null)
				is.close();
		}
	}

	private static BufferedReader getBufferedReader(HttpURLConnection conn,
			InputStream is) throws IOException {
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

}
