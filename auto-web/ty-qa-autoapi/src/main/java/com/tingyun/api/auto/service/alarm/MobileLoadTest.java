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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.networkbench.base.util.UncString;
import com.networkbench.newlens.datacollector.entity.MobileAppInfo;
import com.networkbench.newlens.datacollector.entity.MobileDeviceInfo;
import com.networkbench.newlens.datacollector.entity.avro.MobileActionItem;
import com.networkbench.newlens.datacollector.entity.avro.MobileActions;
import com.networkbench.newlens.datacollector.entity.avro.MobileErrorItem;
import com.networkbench.newlens.datacollector.entity.avro.MobileErrors;
import com.networkbench.newlens.datacollector.entity.avro.MobileMetricItem;
import com.networkbench.newlens.datacollector.mvc.command.InitMobileAppRequestCommand;
import com.networkbench.newlens.datacollector.mvc.command.MobileUploadRequestCommand;
import com.networkbench.newlens.datacollector.serialize.JacksonSerializableObjectMapper;

/**
 * @author BurningIce
 *
 */
public class MobileLoadTest {
	private final static JacksonSerializableObjectMapper JSON_OBJECT_MAPPER = new JacksonSerializableObjectMapper();
	private final static Pattern PATTERN_INIT_MOBILE_APP_RESPONSE = Pattern.compile("\\{\"status\"\\:\"success\",\"result\"\\:\\{\"token\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\"did\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\\}");
	private final static String USER_AGENT_HEADER_VALUE = "NBS Newlens Agent/1.0.0 (iOS 6.1.2)";
	private final static String DEFAULT_MOBILE_APP_TOKEN = "888-888-888";
	private final static int REQUEST_TIME_OUT = 60000;
	private final static String DATA_VERSION = "1.0";
	private final static MobileDeviceInfo[] MOBILE_DEVICES = new MobileDeviceInfo[3];
	private final static com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[] MOBILE_DEVS = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[6];
	
	private final static String[] MOBILE_REQUEST_URLS = new String[] {
		"http://192.168.1.5/rpc/index.jsp",
		"http://img.adbox.sina.com.cn/pic/20217.png",
		"http://www.networkbench.com/index.html",
		"http://rpcbeta.networkbench.com/rpc/home.do",
		"http://rpc.networkbench.com:8080/rpc/measure.do?formAction=graph&pw=yes",
		"http://video.sina.com.cn/z/sports/k/nba/131008atlmia/",
		"http://i0.sinaimg.cn/home/2013/1008/U8059P30DT20131008102816.jpg",
		"http://img.adbox.sina.com.cn/pic/22650.jpg",
		"http://cdn.tanx.com/t/acookie/acbeacon2.html"
	};
	
	private final static int[] ERROR_CODES = new int[] {
		900, 901, 902, 903, 904
	};
	
	static {
		MobileDeviceInfo deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("Apple");
		deviceInfo.setManufacturerModel("iPhone4,1");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("iOS Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("iOS");
		deviceInfo.setOsVersion("6.2.3");
		Map<String, Object> misc = new HashMap<String, Object>();
		misc.put("size", 1);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[0] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("Apple");
		deviceInfo.setManufacturerModel("iPhone6,2");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("iOS Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("iOS");
		deviceInfo.setOsVersion("7.0.1");
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[1] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("Samsung");
		deviceInfo.setManufacturerModel("I9200");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.2.3");
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[2] = deviceInfo;
		
		com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46000");
		dev.setConnectType(2);
		dev.setNetworkType("GPRS");
		dev.setLatitude(39.917f);
		dev.setLongitude(116.4166667f);
		// dev.setLatitude(0f);
		// dev.setLongitude(0f);
		MOBILE_DEVS[0] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46000");
		dev.setConnectType(3);
		dev.setNetworkType("GPRS");
		dev.setLatitude(39.133f);
		dev.setLongitude(117.2f);
		MOBILE_DEVS[1] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46001");
		dev.setConnectType(4);
		dev.setNetworkType("EDGE");
		dev.setLatitude(29.6f);
		dev.setLongitude(91f);
		MOBILE_DEVS[2] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46003");
		dev.setConnectType(3);
		dev.setNetworkType("EVDO_0");
		dev.setLatitude(43.883f);
		dev.setLongitude(125.35f);
		MOBILE_DEVS[3] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46005");
		dev.setConnectType(4);
		dev.setNetworkType("CDMA");
		dev.setLatitude(20.017f);
		dev.setLongitude(110.35f);
		MOBILE_DEVS[4] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("");
		dev.setConnectType(1);
		dev.setNetworkType("");
		MOBILE_DEVS[5] = dev;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String serverUrl = (args.length == 0 ? "http://localhost" : args[0]);
		if(serverUrl.indexOf("://") == -1) {
			serverUrl = "http://" + serverUrl;
		}
		
		String appToken = (args.length > 1 ? args[1] : DEFAULT_MOBILE_APP_TOKEN);
		int thinkTime = (UncString.toInt(System.getProperty("thinkTime"), 60000));
		int period = (UncString.toInt(System.getProperty("period"), Integer.MAX_VALUE));
		int threads = UncString.toInt(System.getProperty("threads"), 5);
		boolean gzip = ("true".equals(System.getProperty("gzip")));
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
			System.out.println("running in with server: " + serverUrl + ", appToken: " + appToken + ", period: " + period);
			simulate(protocol, host, port, threads, period, thinkTime, appToken, gzip);
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static Map<String, Integer> DEVICE_LAST_LAUNCH_TIME = new HashMap<String, Integer>();
	
	private static void simulate(final String protocol, final String host, final int port, final int threads, final int period, final int thinkTime, final String appToken, final boolean gzip) throws Exception {
		String response = simulateInitMobileAgentApp(protocol, host, port, appToken, gzip);
		if(response != null) {
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			if(matcher.matches()) {
				final String token = matcher.group(1);
				final String deviceId = matcher.group(2);
				System.out.println("upload data using token=" + token + ", did=" + deviceId + "...");
				DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int)(System.currentTimeMillis()/ 1000L)));
				final AtomicInteger uploadCount = new AtomicInteger();
				final long startTime = System.currentTimeMillis();
				for(int i = 0; i < threads; ++i) {
					new Thread() {
						public void run() {
							for( ; ; ) {
								try {
									simulateUploadMobileData(protocol, host, port, appToken, token, false, gzip);
									long now = System.currentTimeMillis();
									int timeElapsed = (int)((now - startTime) / 1000L);
									if(timeElapsed >= period) {
										System.out.println("finished " + uploadCount.get() + " at " + (uploadCount.get() / timeElapsed) + " qps.");
										return;
									}
									
									if((uploadCount.incrementAndGet() % 1000) == 0) {
										System.out.println("uploaded " + uploadCount.get() + " at " + (uploadCount.get() / timeElapsed) + " qps.");
									}
									Thread.sleep(thinkTime);
								} catch(Throwable t) {
									System.out.println("failed to upload: " + t.getMessage());
								}
							}
						}
					}.start();
				}
			} else {
				System.out.println("token not found in response: " + response);
			}
		}
	}

	
	private static List<MobileUploadRequestCommand> shuffle(List<MobileUploadRequestCommand> list) {
		if(list == null)
			return null;
		
		MobileUploadRequestCommand[] arr = list.toArray(new MobileUploadRequestCommand[list.size()]);
		for(int i = 0; i < arr.length; ++i) {
			int swapIndex1 = UncString.randomInt(arr.length);
			int swapIndex2 = UncString.randomInt(arr.length);
			MobileUploadRequestCommand temp = arr[swapIndex1];
			arr[swapIndex1] = arr[swapIndex2];
			arr[swapIndex2] = temp;
		}
		
		return Arrays.asList(arr);
	}

	private static String simulateInitMobileAgentApp(String protocol, String host, int port, String appToken, boolean gzip) throws Exception {
		System.out.println("initMobileAgentApp with token: " + appToken);
		MobileAppInfo appInfo = new MobileAppInfo();		
		appInfo.setAppName("Cesuba");
		appInfo.setAppVersion("1.0.0");
		appInfo.setBundleId("abcd-efgh-hijk");
		
		int deviceIndex = UncString.randomInt(MOBILE_DEVICES.length);
		MobileDeviceInfo deviceInfo = MOBILE_DEVICES[deviceIndex];
		deviceInfo.setAgentVersion("10.0.9");
		
		InitMobileAppRequestCommand command = new InitMobileAppRequestCommand();
		command.setDeviceId("");
		command.setAppInfo(appInfo);
		command.setDeviceInfo(deviceInfo);
		
		String text = jsonize(command);
		String response = post(protocol, host, port, "/initMobileApp?version=" + DATA_VERSION, text, appToken, gzip);
		System.out.println("initMobileAgentApp finished: " + response);
		return response;
	}
	
	private static String simulateUploadMobileData(String protocol, String host, int port, String appToken, String token, boolean isLastRequest, boolean gzip) throws Exception {
		MobileUploadRequestCommand command = createMobileUploadMessage(isLastRequest, 0, false, false);
		String text = jsonize(command);
		return post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, text, appToken, gzip);
	}

	private static MobileUploadRequestCommand createMobileUploadMessage(boolean isLastRequest, int fixedResponseTimeValue, boolean httpError, boolean networkError) throws Exception {
		MobileUploadRequestCommand command = new MobileUploadRequestCommand();
		MobileActions actionsData = new MobileActions();
		actionsData.setInterval(60);
		
		com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		
		actionsData.setDeviceInfo(MOBILE_DEVS[UncString.randomInt(MOBILE_DEVS.length)]);
		
		List<MobileActionItem> actions = new ArrayList<MobileActionItem>();
		List<Integer> errorCodes = new ArrayList<Integer>();
		List<String> errorRequestUris = new ArrayList<String>();
		
		// 固定响应时间用于模拟警报，一次1个action，方便准确估计警报触发的阈值
		boolean useFixedValues = (fixedResponseTimeValue > 0);
		int actionsCount = (useFixedValues ? 1 : UncString.randomInt(1, MOBILE_REQUEST_URLS.length) + 1);
		for(int i = actionsCount - 1; i >= 0; i--) {
			MobileActionItem action = new MobileActionItem();
			String url = MOBILE_REQUEST_URLS[(useFixedValues ? 0 : UncString.randomInt(MOBILE_REQUEST_URLS.length))];
			action.setUrl(url);
			action.setIp("192.168.1.31");
			int duration = (useFixedValues ? fixedResponseTimeValue : UncString.randomInt(10000));
			action.setDuration(duration);
			action.setDnsTime(-1);
			action.setConnectTime(-1);
			action.setFirstPacketTime(duration == 0 ? 0 : UncString.randomInt(duration));
			action.setBytesSent(UncString.randomInt(1000000));
			action.setBytesReceived(UncString.randomInt(1000000));
			
			if(useFixedValues) {
				boolean isError = (httpError || networkError);
				if(isError) {
					if(networkError) {
						action.setErrorCode(ERROR_CODES[UncString.randomInt(ERROR_CODES.length)]);
						action.setHttpStatusCode(0);
						errorCodes.add(action.getErrorCode());
					} else {
						int httpStatusCode = UncString.randomInt(401, 599);
						action.setErrorCode(httpStatusCode);
						action.setHttpStatusCode(httpStatusCode);
						errorCodes.add(Integer.valueOf(httpStatusCode));
					}
					
					errorRequestUris.add(action.getUrl().toString());
				} else {
					action.setHttpStatusCode(200);
					action.setErrorCode(0);
				}
			} else {
				boolean isError = (UncString.randomInt(3) == 1);
				if(isError) {
					boolean isNetworkError = (UncString.randomInt(3) == 1);
					if(isNetworkError) {
						action.setErrorCode(ERROR_CODES[UncString.randomInt(ERROR_CODES.length)]);
						action.setHttpStatusCode(0);
						errorCodes.add(action.getErrorCode());
					} else {
						int httpStatusCode = UncString.randomInt(401, 599);
						action.setErrorCode(httpStatusCode);
						action.setHttpStatusCode(httpStatusCode);
						errorCodes.add(Integer.valueOf(httpStatusCode));
					}
					
					errorRequestUris.add(action.getUrl().toString());
				} else {
					action.setHttpStatusCode(200);
					action.setErrorCode(0);
				}
			}
			
			actions.add(action);
		}
		
		actionsData.setActions(actions);
		
		List<MobileMetricItem> metrics = new ArrayList<MobileMetricItem>();
		metrics.addAll(
						Arrays.asList(
								new MobileMetricItem("mem", UncString.randomInt(1024, 1024 * 1024 * 1024)),
								new MobileMetricItem("cpu_sys", UncString.randomInt(10, 100)),
								new MobileMetricItem("cpu_user", UncString.randomInt(10, 100)),
								new MobileMetricItem("cpu", UncString.randomInt(10, 100))
						));
		
		if(isLastRequest) {
			metrics.add(new MobileMetricItem("sd", UncString.randomInt(1000, 50000)));
			metrics.add(new MobileMetricItem("req", actions.size()));
		}
		
		actionsData.setMetrics(metrics);		
		command.setActions(actionsData);
		/*
		if(errorCodes.size() > 0) {
			MobileErrors errorsData = new MobileErrors();
			errorsData.setInterval(60);
			List<MobileErrorItem> errors = new ArrayList<MobileErrorItem>();
			for(int j = 0; j < errorCodes.size(); ++j) {
				MobileErrorItem error = new MobileErrorItem();
				error.setCount(1);
				error.setHttpStatusCode(errorCodes.get(j));
				error.setRequestUrl(errorRequestUris.get(j));
				
				Map<String, Object> params = new HashMap<String, Object>();
				if(errorCodes.get(j).intValue() >= 900) {
					params.put("stacktrace", "[stacktrace start]" + UncString.randomString(UncString.randomInt(1024, 2048)) + "[stacktrace end]");
				} else {
					params.put("response", "<html><body><div style=\"color:#ff0000\">" + errorCodes.get(j) + "</div></body></html>");
				}
				error.setParams(jsonize(params));
				errors.add(error);
			}
			errorsData.setErrors(errors);
			command.setErrors(errorsData);
		}
		*/
		return command;
	}
	
	private static String jsonize(Object obj) throws Exception {
		return JSON_OBJECT_MAPPER.writeValueAsString(obj);
	}
	
	private static String post(String protocol, String host, int port, String uri, String content, String appToken, boolean gzip) throws Exception {
		HttpURLConnection conn = createConnection(protocol, host, port, uri, REQUEST_TIME_OUT, appToken, gzip);
		OutputStream os = conn.getOutputStream();
		os.write(gzip ? gzip(content, "UTF-8") : content.getBytes("UTF-8"));
		int statusCode = conn.getResponseCode();
		String responseBody = (statusCode == 200 ? readResponseBody(conn) : null);
		conn.disconnect();
		return responseBody;
	}
	
	private static HttpURLConnection createConnection(String protocol, String host, int port, String uri, int requestTimeoutInMillis, String appToken, boolean gzip) throws Exception {
		URL url = new URL(protocol, host, port, uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Connection", "Close");
		conn.setRequestProperty("User-Agent", USER_AGENT_HEADER_VALUE);
		conn.setConnectTimeout(requestTimeoutInMillis);
		conn.setReadTimeout(requestTimeoutInMillis);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("CONTENT-TYPE", "application/octet-stream");
		conn.setRequestProperty("ACCEPT-ENCODING", gzip ? "gzip, deflate, plain" : "plain");
		conn.setRequestProperty("CONTENT-ENCODING", gzip ? "gzip" : "plain");
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
