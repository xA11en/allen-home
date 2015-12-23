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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.networkbench.base.util.UncString;
import com.networkbench.newlens.datacollector.entity.MobileAppInfo;
import com.networkbench.newlens.datacollector.entity.MobileDeviceInfo;
import com.networkbench.newlens.datacollector.entity.avro.MobileCrashReportItem;
import com.networkbench.newlens.datacollector.mvc.command.InitMobileAppRequestCommand;
import com.networkbench.newlens.datacollector.mvc.command.MobileCrashReportRequestCommand2;
import com.networkbench.newlens.datacollector.mvc.command.MobileUploadRequestCommand;
import com.networkbench.newlens.datacollector.serialize.JacksonSerializableObjectMapper;

/**
 * @author baiyl
 *
 */

//sihaiyue?
public class MobileAgentCrashSimulator {
	private final static JacksonSerializableObjectMapper JSON_OBJECT_MAPPER = new JacksonSerializableObjectMapper();
	private final static Pattern PATTERN_INIT_MOBILE_APP_RESPONSE = Pattern.compile("\\{\"status\"\\:\"success\",\"result\"\\:\\{\"token\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\"enabled\"\\s*\\:\\s*([0-1]).*\"did\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\\}");
	private final static String USER_AGENT_HEADER_VALUE = "NBS Newlens Agent/1.0.0 (iOS 6.1.2)";
	private final static String DEFAULT_MOBILE_APP_TOKEN = "888-888-888";
	private final static int REQUEST_TIME_OUT = 60000;
	private final static String DATA_VERSION = "1.0";
	private final static MobileDeviceInfo[] MOBILE_DEVICES = new MobileDeviceInfo[25];
	private final static com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[] MOBILE_DEVS = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[5];
	
	static {
		MobileDeviceInfo deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("Apple");
		deviceInfo.setManufacturerModel("iPhone6,2");
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
		deviceInfo.setManufacturer("Xiaomi");
		deviceInfo.setManufacturerModel("MI 1S");
		deviceInfo.setDeviceType(2);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("7.0.3");
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[1] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("samsung");
		deviceInfo.setManufacturerModel("GT-I9082I");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.2");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.2.3");
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[2] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("SAMSUNG");
		deviceInfo.setManufacturerModel("GT-I9082");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.3");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.5.6");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[3] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("HUAWEI");
		deviceInfo.setManufacturerModel(" HUAWEI G750-T00");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.3");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.5.6");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[4] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("Huawei");
		deviceInfo.setManufacturerModel(" Huawei H30-U10");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.3");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.5.6");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[5] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.4");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.5.6");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[6] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.6.6");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[7] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.6.7");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[8] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.6.8");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[9] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.6.9");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[10] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.1");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[11] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.2");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[12] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.3");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[13] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.4");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[14] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.5");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[15] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.6");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[16] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.7");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[17] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.8");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[18] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.7.9");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[19] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.8.1");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[20] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.8.2");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[21] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.8.3");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[22] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.8.4");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[23] = deviceInfo;
		
		deviceInfo = new MobileDeviceInfo();
		deviceInfo.setManufacturer("BBK");
		deviceInfo.setManufacturerModel("vivo S7i(t)");
		deviceInfo.setDeviceType(1);
		deviceInfo.setAgentName("Android Agent");
		deviceInfo.setAgentVersion("0.0.1");
		deviceInfo.setOsName("Android");
		deviceInfo.setOsVersion("4.8.5");
		misc = new HashMap<String, Object>();
		misc.put("size", 4);
		deviceInfo.setMisc(misc);
		MOBILE_DEVICES[24] = deviceInfo;
		
		com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46000");
		//dev.setConnectType(2);
		dev.setConnectType(0);
		dev.setNetworkType("GPRS");
		dev.setLatitude(39.917f);
		dev.setLongitude(116.4166667f);
		MOBILE_DEVS[0] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46000");
		dev.setConnectType(3);
		dev.setNetworkType("GPRS");
		dev.setLatitude(13.723f);
		dev.setLongitude(100.476f);
		MOBILE_DEVS[1] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46001");
		dev.setConnectType(3);
		dev.setNetworkType("EDGE");
		dev.setLatitude(37.33f);
		dev.setLongitude(126.58f);
		MOBILE_DEVS[2] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46003");
		dev.setConnectType(3);
		dev.setNetworkType("EVDO_0");
		dev.setLatitude(35.42f);
		dev.setLongitude(139.46f);
		MOBILE_DEVS[3] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46005");
		dev.setConnectType(3);
		dev.setNetworkType("CDMA");
		dev.setLatitude(51.3f);
		dev.setLongitude(0.1f);
		MOBILE_DEVS[4] = dev;
	
	}
	
//	private static Set<String> set= new HashSet<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String serverUrl = (args.length == 0 ? "http://192.168.2.83:8080" : args[0]);

		if(serverUrl.indexOf("://") == -1) {
			serverUrl = "http://" + serverUrl;
		}
		
		String appToken = (args.length > 1 ? args[1] : DEFAULT_MOBILE_APP_TOKEN);
		int thinkTime = (UncString.toInt(System.getProperty("thinkTime"), 0));
		int count = (UncString.toInt(System.getProperty("count"), Integer.MAX_VALUE));
		int period = (UncString.toInt(System.getProperty("period"), 0));
		String mode = System.getProperty("mode");
		String crashUUID = (String)System.getProperty("crashUUID");
		String crashMessage = (String)System.getProperty("crashMessage");
		int mobileAppId = UncString.toInt(System.getProperty("mobileAppId"));
		
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
			if("fixed".equals(mode)) {
				System.out.println("running in fixed mode with server: " + serverUrl + ", appToken: " + appToken + ", count: " + count + ", period: " + period + ", crashUUID: " + crashUUID + ", crashMessage: " + crashMessage);
				simulateInFixedValues(mobileAppId,protocol, host, port, appToken, count, period,crashUUID,crashMessage);
			} else {
				boolean withIdle = "idle".equals(mode);
				System.out.println("running in " + (withIdle ? "idle" : "normal") + " mode with server: " + serverUrl + ", appToken: " + appToken + ", count: " + count);
				simulate(mobileAppId,protocol, host, port, count, thinkTime, appToken, withIdle);
			}
			
			System.out.println("finished, exit!");
			
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static Map<String, Integer> DEVICE_LAST_LAUNCH_TIME = new HashMap<String, Integer>();
	
	private static void simulate(int mobileAppId,String protocol, String host, int port, int count, int thinkTime, String appToken, boolean withIdle) throws Exception {
	   
		String response = simulateInitMobileAgentApp(protocol, host, port, appToken);
		if(response != null) {
			
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			if(matcher.matches()) {
				
				String token = matcher.group(1);
				String deviceId = matcher.group(3);
				boolean enabled = "1".equals(matcher.group(2));
				
				if(!enabled){
					System.out.println("enabled is false,enalsed="+enabled);
					return;
				}
				
				System.out.println("upload data using token=" + token + ", did=" + deviceId + "...");
				System.out.println("========="+deviceId);
				DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int)(System.currentTimeMillis()/ 1000L)));
				
				for(int i = 0; i < count; ++i) {
					
					boolean isIdle = false;
					if(withIdle) {
						isIdle = (UncString.randomInt(8) == 1);
					}
					
					if(isIdle) {
						
						// idle upload
						System.out.println("idling for 60s...");
						
						Thread.sleep(100);
						post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, "{}", appToken);
						
					} else {
						
						Integer launchTime = DEVICE_LAST_LAUNCH_TIME.get(deviceId);
						
						int activeTime = (int)(System.currentTimeMillis() / 1000L) - launchTime.intValue();
						int expectedActiveTime = UncString.randomInt(90, 600);
						boolean isLastRequest = (activeTime > expectedActiveTime);
						
						String resp = simulateUploadMobileData(mobileAppId,protocol, host, port, appToken, token, isLastRequest);
						System.out.println("uploaded " + (i + 1) + ", response: " + resp);
						
						if(isLastRequest) {
							
							int sleepTime = (withIdle ? UncString.randomInt(5000, 120000) : 1000);
							System.out.println("disconnect device: " + deviceId + ", active time: " + activeTime);
							System.out.println("sleep " + sleepTime + "ms after disconnect ...");
							Thread.sleep(sleepTime);
							System.out.println("reconnect ...");
							
							// reconnect
							response = simulateInitMobileAgentApp(protocol, host, port, appToken);
							
							if(response != null) {
								
								matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
								
								if(matcher.matches()) {
									
									token = matcher.group(1);
									deviceId = matcher.group(2);
									System.out.println("========"+deviceId);
									DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int)(System.currentTimeMillis()/ 1000L)));
									
								}
								
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
	
	/**
	 * 模拟固定值上传，包括时间间隔，响应时间，吞吐量，错误率等均使用固定值
	 * 可用于模拟触发警报
	 * @throws Exception
	 */
	private static void simulateInFixedValues(int mobileAppId,String protocol, String host, int port, String appToken,
													int count, int period,String crashUUID,String crashMessage) throws Exception {
		
		String response = simulateInitMobileAgentApp(protocol, host, port, appToken);
		
		if(response != null) {
			
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			
			if(matcher.matches()) {
				
				String token = matcher.group(1);
				long beginTime = System.currentTimeMillis();
				int countPerMin = count / period;
				
				int countLeft = count;
				for(int i = 0; i < period; ++i) {
					
					simulateUploadInFixedValues(mobileAppId,protocol, host, port, appToken, token, countPerMin,crashUUID,crashMessage);
					countLeft = countLeft - countPerMin;
					
					if(countLeft > 0) {
						Thread.sleep(60000);
					}
					
				}
				
				System.out.println("finished uploaded " + count + " items in " + (System.currentTimeMillis() - beginTime) + " ms.");
				
			} else {
				
				System.out.println("token not found in response: " + response);
				
			}
			
		}
		
	}
	
	private static void simulateUploadInFixedValues(int mobileAppId,String protocol, String host, int port, String appToken, String token,
														int count,String crashUUID,String crashMessage) throws Exception {
		
		System.out.println("upload data using token " + token + "...");
		
		MobileCrashReportRequestCommand2 message = createMobileUploadMessage(mobileAppId, true, crashUUID,crashMessage);
		
		if (null != message) {
			
			String text = jsonize(message);
			
			post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, text, appToken);
			
		}
		
	}
	
	private static String simulateInitMobileAgentApp(String protocol, String host, int port, String appToken) throws Exception {
		
		System.out.println("initMobileAgentApp with token: " + appToken);
		MobileAppInfo appInfo = new MobileAppInfo();		
		appInfo.setAppName("Cesuba");
		String version[]={"1.0.1"};
		int versionID = UncString.randomInt(version.length);
		appInfo.setAppVersion(version[versionID]);
		appInfo.setBundleId("abcd-efgh-hijk");
		
		int deviceIndex = UncString.randomInt(MOBILE_DEVICES.length);
		MobileDeviceInfo deviceInfo = MOBILE_DEVICES[deviceIndex];
		
		InitMobileAppRequestCommand command = new InitMobileAppRequestCommand();
		command.setDeviceId("");
		command.setAppInfo(appInfo);
		command.setDeviceInfo(deviceInfo);
		
		String text = jsonize(command);
		String response = post(protocol, host, port, "/initMobileApp?version=" + DATA_VERSION, text, appToken);
		
		System.out.println("initMobileAgentApp finished: " + response);
		return response;
		
	}
	
	private static String simulateUploadMobileData(int mobileAppId,String protocol, String host, int port, String appToken, String token, boolean isLastRequest) throws Exception {
		
		MobileCrashReportRequestCommand2 command = createMobileUploadMessage(mobileAppId,isLastRequest,"","");
		String text = jsonize(command);
		System.out.println(text);
		return post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, text, appToken);
		
	}

	private static MobileCrashReportRequestCommand2 createMobileUploadMessage(int mobileAppId, boolean isLastRequest,String crashUUID,String crashMessage) throws Exception {
		
		MobileCrashReportRequestCommand2 command = new MobileCrashReportRequestCommand2();
		
		MobileCrashReportItem item = new MobileCrashReportItem();
		item.setCrashTime((int)new Date().getTime());
		item.setAppStartTime((int)new Date().getTime());
		item.setCrashMessage(crashMessage);
		item.setCrashUUID(crashUUID);
		item.setDeviceInfo(MOBILE_DEVS[1]);
		
		return command;
		
	}
	
	private static String jsonize(Object obj) throws Exception {
		return JSON_OBJECT_MAPPER.writeValueAsString(obj);
	}
	
	private static String post(String protocol, String host, int port, String uri, String content, String appToken) throws Exception {
		
		HttpURLConnection conn = createConnection(protocol, host, port, uri, 600000, appToken);
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
	
}
