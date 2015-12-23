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
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.networkbench.base.util.UncString;
import com.networkbench.newlens.datacollector.entity.MobileAppInfo;
import com.networkbench.newlens.datacollector.entity.MobileDeviceInfo;
import com.networkbench.newlens.datacollector.entity.avro.GenericMetricItem;
import com.networkbench.newlens.datacollector.entity.avro.MetricKey;
import com.networkbench.newlens.datacollector.entity.avro.MobileActionItem;
import com.networkbench.newlens.datacollector.entity.avro.MobileActions;
import com.networkbench.newlens.datacollector.entity.avro.MobileErrorItem;
import com.networkbench.newlens.datacollector.entity.avro.MobileErrors;
import com.networkbench.newlens.datacollector.entity.avro.MobileInteractionTraceData;
import com.networkbench.newlens.datacollector.entity.avro.MobileInteractionTraceItem;
import com.networkbench.newlens.datacollector.entity.avro.MobileMetricItem;
import com.networkbench.newlens.datacollector.mvc.command.InitMobileAppRequestCommand;
import com.networkbench.newlens.datacollector.mvc.command.MobileUploadRequestCommand;
import com.networkbench.newlens.datacollector.serialize.JacksonSerializableObjectMapper;
import com.tingyun.api.auto.entity.alarm.AppParmaters;

/**
 * @author BurningIce
 *
 */

//sihaiyue?
public class MobileAgentSimulatorNew {
	
	
	

	private final static JacksonSerializableObjectMapper JSON_OBJECT_MAPPER = new JacksonSerializableObjectMapper();
	private final static Pattern PATTERN_INIT_MOBILE_APP_RESPONSE = Pattern.compile("\\{\"status\"\\:\"success\",\"result\"\\:\\{\"token\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\"enabled\"\\s*\\:\\s*([0-1]).*\"did\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\\}");
	private final static String USER_AGENT_HEADER_VALUE = "NBS Newlens Agent/1.0.0 (iOS 6.1.2)";
	private final static String DEFAULT_MOBILE_APP_TOKEN = "888-888-888";
	private final static int REQUEST_TIME_OUT = 60000;
	private final static String DATA_VERSION = "1.0";
	private  MobileDeviceInfo[] MOBILE_DEVICES = new MobileDeviceInfo[25];
	private final static com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[] MOBILE_DEVS = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[5];
	

	private	final static MetricKey[] METRIC_VIEW_KEY = new MetricKey[] {
		new MetricKey(0, 0, "MobileView/Controller/MyViewController", null)
		//new MetricKey(0, 0, "MobileView/Activity/MyController", null)
	};
		
	private final static MetricKey[] METRIC_COMPONENT_KEY = new MetricKey[] {
		new MetricKey(0, 0, "Method/ProfileBase/viewDidLoad", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "Method Storage/MyViewController/executeSqlLite", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "Method ViewLoading/MyViewController/loadingAll", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "Method ViewLayout/MyViewController/layoutAll", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "Method Image/MyViewController/imageWithData", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "Method Json/MyViewController/JSONObjectWithStream", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "Method Network/MyViewController/networkData", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "BgMethod/ProfileBase/dispatch_async", "MobileView/Activity/MyController"),
		new MetricKey(0, 0, "BgMethod ViewLoading/ProfileBase/viewloadAll", "MobileView/Activity/MyController"),
		new MetricKey(0, 0, "BgMethod ViewLayout/ProfileBase/viewDidAppear", "MobileView/Activity/MyController"),
		new MetricKey(0, 0, "Method/MyViewController/viewWillAppear", "MobileView/Activity/MyController"),
		new MetricKey(0, 0, "BgMethod Image/MyViewController/imageWithContentsOfFile", "MobileView/Activity/MyController"),
		new MetricKey(0, 0, "BgMethod Json/MyViewController/JSONObjectWithStream", "MobileView/Activity/MyController"),
		new MetricKey(0, 0, "BgMethod Network/MyViewController/networkAdd", "MobileView/Activity/MyController")
		};
		
	private	final static MetricKey[] METRIC_CLASSIFIEDCOMPONENT_KEY = new MetricKey[] {
		new MetricKey(0, 0, "MobileViewSummary/NULL/ViewLoading", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "MobileViewSummary/NULL/ViewLayout", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "MobileViewSummary/NULL/Storage", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "MobileViewSummary/NULL/Image", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "MobileViewSummary/NULL/Json", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "MobileViewSummary/NULL/Network", "MobileView/Controller/MyViewController"),
		new MetricKey(0, 0, "MobileViewSummary/NULL/Background", "MobileView/Activity/MyController")
	};

	private	final static MetricKey[] METRIC_GENERAL_KEY = new MetricKey[] {
		new MetricKey(0, 0, "CPU/NULL/Utilization", null),
		new MetricKey(0, 0, "Memory/NULL/PhysicalUsed", null)
	};
	
	private final static MobileInteractionTraceItem[] traceItem = new MobileInteractionTraceItem[]{
		new MobileInteractionTraceItem((int) (System.currentTimeMillis()/1000),"tracedate",UncString.randomInt(1000),"org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:337)\",\"org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489)\",\"java.lang.Thread.run(Thread.java:744"),
		new MobileInteractionTraceItem((int) (System.currentTimeMillis()/1000),"tracedate",UncString.randomInt(1000),"org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:337)\",\"org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489"),
	};
     
	
	
	
	  /**
                         主机地址*/
	
	
	
	
	private final static String[] MOBILE_REQUEST_URLS = new String[] {
		    /** 将主机地址改为测试的主机的url即可*/
		 
		"http://www.cesuba.com/cesuba/config.do",
		"http://www.taobao.com/shangcheng",   
		  "http://www.sohu.com",
		  "http://www.jd.com",
		  "http://www.tmall.com",
		  "http://www.suning.com"
		 
	
	};
	
	
	
	private final static String[] REQUEST_URL_PARAMS = new String[] {
/*		"a=1&b=1&c=1",
		"a=2&b=2&c=2",
		"a=3&b=3&c=3",*/
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null
	};
	
	private final static int[] ERROR_CODES = new int[] {
		900
	};
	
/*	private final static int[] ERROR_CODES = new int[] {
		900, 901, 902, 903, 904
	};*/
	
//	static {
//		
//	
//	}
	public MobileAgentSimulatorNew(){
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
		dev.setCarrier("46003");
		//dev.setConnectType(2);
		dev.setConnectType(4);       //接入方式：wifi、2G、3G、4G
		dev.setNetworkType("TD-LET");   //运营商：联通、电信、移动
		dev.setLatitude(39.917f);       //经度
		dev.setLongitude(116.4166667f);  // 纬度
		MOBILE_DEVS[0] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46000");
		dev.setConnectType(2);
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
		dev.setConnectType(4);
		dev.setNetworkType("EVDO_0");
		dev.setLatitude(35.42f);
		dev.setLongitude(139.46f);
		MOBILE_DEVS[3] = dev;
		
		dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		dev.setCarrier("46005");
		dev.setConnectType(4);
		dev.setNetworkType("CDMA");
		dev.setLatitude(51.3f);
		dev.setLongitude(0.1f);
		MOBILE_DEVS[4] = dev;
	}
	
//	private static Set<String> set= new HashSet<String>();
	/**
	 * @param args
	 */
	public  void main(AppParmaters appParmaters) {

		//String serverUrl = (args.length == 0 ? "http://192.168.2.83:8080" : args[0]);
		String serverUrl = "https://dc1.networkbench.com";
//		if(serverUrl.indexOf("://") == -1) {
//			serverUrl = "http://" + serverUrl;
//		}
		
		//String appToken = (args.length > 1 ? args[1] : DEFAULT_MOBILE_APP_TOKEN);
		String appToken = appParmaters.getKey();
		System.out.println(appParmaters.getThinkTime()+"================");
		/**
		 * 睡眠时间
		 */
		int thinkTime = (UncString.toInt(appParmaters.getThinkTime(), 100));
		/**
		 * 总次数
		 */
		int count = (UncString.toInt(appParmaters.getCount(), Integer.MAX_VALUE));
		//int count = 190;
		int period = (UncString.toInt(appParmaters.getPeriod(), 5));
		String mode = appParmaters.getMode();
		/**
		 * 响应时间
		 */ 
		int responseTime = UncString.toInt(appParmaters.getResponseTime(), 0);
		/**
		 * 崩溃次率
		 */
		double crashPercentage = (UncString.toDouble(System.getProperty("crashPercentage"),0.0));
		/**
		 * Http错误率
		 */
		double httpErrorPercentage = (UncString.toDouble(appParmaters.getHttpErrorPercentage(), 0.0));
		/**
		 * 网络错误率
		 */
		double networkErrorPercentage = (UncString.toDouble(appParmaters.getNetworkErrorPercentage(), 0.9));
		
		int interactionTime = UncString.toInt(appParmaters.getInteractionTime(),0);
		int viewLoadTime = UncString.toInt(appParmaters.getViewLoadTime(),0);
		int imageProcessTime = UncString.toInt(appParmaters.getImageProcessTime(),0);
		int dataStorageTime = UncString.toInt(appParmaters.getDataStorageTime(),0);
		int networkVisitTime = UncString.toInt(appParmaters.getNetworkVisitTime(),0);
		int jsonProcessTime = UncString.toInt(appParmaters.getJsonProcessTime(),0);
		/**
		 * App应用Id
		 */
		int mobileAppId = UncString.toInt(System.getProperty("mobileAppId"),1);
		int deviceIndex = UncString.toInt(appParmaters.getDeviceIndex(),1);
		int byteReceived = UncString.toInt(System.getProperty("byteReceived"),1024);
		String ipSet = (String)System.getProperty("ip");
		String readIp = "192.168.2.118";
		
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
				System.out.println("running in fixed mode with server: " + serverUrl + ", appToken: " + appToken + ", count: " + count + 
										", period: " + period + ", responseTime: " + responseTime + 
										", httpErrorPercentage: " + httpErrorPercentage + ", networkErrorPercentage: " + networkErrorPercentage
										 + ", crashPercentage: " + crashPercentage + ", interactionTime: " + interactionTime
										 + ", viewLoadTime: " + viewLoadTime + ", imageProcessTime: " + imageProcessTime
										 + ", dataStorageTime: " + dataStorageTime + ", networkVisitTime: " + networkVisitTime
										 + ", jsonProcessTime: " + jsonProcessTime);
				simulateInFixedValues(deviceIndex,thinkTime,readIp,mobileAppId,protocol, host, port, appToken, count, period, responseTime, httpErrorPercentage, networkErrorPercentage,crashPercentage,interactionTime,viewLoadTime,imageProcessTime,dataStorageTime,networkVisitTime,jsonProcessTime,byteReceived);
			} else {
				boolean withIdle = "idle".equals(mode);
				System.out.println("running in " + (withIdle ? "idle" : "normal") + " mode with server: " + serverUrl + ", appToken: " + appToken + ", count: " + count);
				simulate(ipSet,mobileAppId,protocol, host, port, count, thinkTime, appToken, withIdle);
			}
			
			System.out.println("finished, exit!");
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static Map<String, Integer> DEVICE_LAST_LAUNCH_TIME = new HashMap<String, Integer>();
	
	private  void simulate(String ipSet,int mobileAppId,String protocol, String host, int port, int count, int thinkTime, String appToken, boolean withIdle) throws Exception {
	   
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
						
						String resp = simulateUploadMobileData(ipSet,mobileAppId,protocol, host, port, appToken, token, isLastRequest);
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
					
					Thread.sleep(2000);
					
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
	private  void simulateInFixedValues(int deviceIndex,int thinkTime,String ipSet,int mobileAppId,String protocol, String host, int port, String appToken,
													int count, int period,
													int responseTime, double httpErrorPercentage, double networkErrorPercentage
												,double crashPercentage,int interactionTime,int viewLoadTime,int imageProcessTime
												,int dataStorageTime,int networkVisitTime,int jsonProcessTime,int byteReceived) throws Exception {
		
		String response = simulateInitMobileAgentApp(protocol, host, port, appToken);
		
		if(response != null) {
			
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			
			if(matcher.matches()) {
				
				String token = matcher.group(1);
				long beginTime = System.currentTimeMillis();
				int countPerMin = count / period;
				
				int countLeft = count;
				for(int i = 0; i < period; ++i) {
					
					simulateUploadInFixedValues(deviceIndex,ipSet,mobileAppId,protocol, host, port, appToken, token, countPerMin, responseTime, httpErrorPercentage, networkErrorPercentage,crashPercentage,interactionTime,viewLoadTime,imageProcessTime,dataStorageTime,networkVisitTime,jsonProcessTime,byteReceived);
					countLeft = countLeft - countPerMin;
					
					if(countLeft > 0) {
						Thread.sleep(thinkTime);
					}
					
				}
				
				System.out.println("finished uploaded " + count + " items in " + (System.currentTimeMillis() - beginTime) + " ms.");
				
			} else {
				
				System.out.println("token not found in response: " + response);
				
			}
			
		}
		
	}
	
	private  void simulateUploadInFixedValues(int deviceIndex,String ipSet,int mobileAppId,String protocol, String host, int port, String appToken, String token,
														int count,int responseTime, double httpErrorPercentage, double networkErrorPercentage,
													double crashPercentage,int interactionTime,int viewLoadTime,int imageProcessTime,
													int dataStorageTime,int networkVisitTime,int jsonProcessTime,int bytesReceived) throws Exception {
		
		System.out.println("upload data using token " + token + "..." +"deviceIndex : "+deviceIndex);
		int uploadedCount = 0;
		
		// 测试百分比数据时，保证每分钟count为100个，这样计算数据准确
		int httpErrorCount = (int)(count * httpErrorPercentage);
		int networkErrorCount = (int)(count * networkErrorPercentage);
		int crashErrorCount = (int)(count * crashPercentage);
		
		int actualHttpErrorCount = 0;
		int actualNetworkErrorCount = 0;
		int actualCrashErrorCount = 0;
		
		List<MobileUploadRequestCommand> messages = new ArrayList<MobileUploadRequestCommand>();
		
		if (httpErrorCount > 0) {
			
			for(int i = 0; i < count; ++i) {
				
				boolean httpError = false;

				if (httpErrorCount > 0) {
					httpError = true;
					--httpErrorCount;
				}

				
				messages.add(createMobileUploadMessage(deviceIndex,ipSet,mobileAppId,false, responseTime, httpError, false,false,interactionTime,viewLoadTime,imageProcessTime,dataStorageTime,networkVisitTime,jsonProcessTime,bytesReceived));
			}
			
		}
		
		if (networkErrorCount > 0) {
			
			for(int i = 0; i < count; ++i) {
				
				boolean networkError = false;
				
				if (networkErrorCount > 0) {
					networkError = true;
					--networkErrorCount;
				}
				
				messages.add(createMobileUploadMessage(deviceIndex,ipSet,mobileAppId,false, responseTime, false, networkError,false,interactionTime,viewLoadTime,imageProcessTime,dataStorageTime,networkVisitTime,jsonProcessTime,bytesReceived));
			}
			
		}
		
		if (httpErrorCount == 0 && networkErrorCount == 0) {
			
			for(int i = 0; i < count; ++i) {
				messages.add(createMobileUploadMessage(deviceIndex,ipSet,mobileAppId,false, responseTime, false, false,false,interactionTime,viewLoadTime,imageProcessTime,dataStorageTime,networkVisitTime,jsonProcessTime,bytesReceived));
			}
			
		}
		
		for(int i = 0; i < count; ++i) {

			boolean crashError = false;

			if (crashErrorCount > 0) {
				crashError = true;
				--crashErrorCount;
			}

			messages.add(createMobileUploadMessage(deviceIndex,ipSet,mobileAppId,false, responseTime, false, false,crashError,interactionTime,viewLoadTime,imageProcessTime,dataStorageTime,networkVisitTime,jsonProcessTime,bytesReceived));
		}
		
		if (null != messages && messages.size() > 0) {
			
			for(int i = 0; i < count; ++i) {
				
				MobileUploadRequestCommand message = messages.get(i);
				String text = jsonize(message);
				
				post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, text, appToken);
				++uploadedCount;
				
				Integer errorCode = message.getActions().getActions().get(0).getErrorCode();
				if(errorCode != null && errorCode.intValue() > 0) {
					if(errorCode.intValue() >= 900) {
						++actualNetworkErrorCount;
					} else {
						++actualHttpErrorCount;
						++actualCrashErrorCount;
						++actualNetworkErrorCount;
					}
				}
				
			}
			
			System.out.println("uploaded " + uploadedCount + ", http errors: " + actualHttpErrorCount + "(" + (actualHttpErrorCount/(double)uploadedCount) + 
					"), " + ", network errors: " + actualNetworkErrorCount + "(" + (actualNetworkErrorCount/(double)uploadedCount) + "), "
					+ ", crash errors: " + actualCrashErrorCount + "(" + (actualCrashErrorCount/(double)uploadedCount) + ")");	
			
		}
	}
	
	private  String simulateInitMobileAgentApp(String protocol, String host, int port, String appToken) throws Exception {
		
		System.out.println("initMobileAgentApp with token: " + appToken);
		MobileAppInfo appInfo = new MobileAppInfo();
		
		//应用名称AppName
		appInfo.setAppName("警报测试");
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
	
	private  String simulateUploadMobileData(String ipSet,int mobileAppId,String protocol, String host, int port, String appToken, String token, boolean isLastRequest) throws Exception {
		MobileUploadRequestCommand command = createMobileUploadMessage(UncString.randomInt(MOBILE_DEVS.length),ipSet,mobileAppId,isLastRequest,6000,true,true,true,0,0,0,0,0,0,0);
		String text = jsonize(command);
		System.out.println(text);
		return post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, text, appToken);
		
	}

	private static MobileUploadRequestCommand createMobileUploadMessage(int deviceIndex,String ipSet,int mobileAppId, boolean isLastRequest, int fixedResponseTimeValue, boolean httpError, boolean networkError,
			boolean crashError,int interactionTime,int viewLoadTime,int imageProcessTime,int dataStorageTime,int networkVisitTime,int jsonProcessTime,int bytesReceived) throws Exception {
		
		MobileUploadRequestCommand command = new MobileUploadRequestCommand();
		MobileActions actionsData = new MobileActions();
		actionsData.setInterval(60);
		
		actionsData.setDeviceInfo(MOBILE_DEVS[deviceIndex >= 1 ? deviceIndex - 1 : 0]);
		
		List<MobileActionItem> actions = new ArrayList<MobileActionItem>();
		List<Integer> errorCodes = new ArrayList<Integer>();
		List<String> errorRequestUris = new ArrayList<String>();
		
		// 固定响应时间用于模拟警报，一次1个action，方便准确估计警报触发的阈值
		boolean useFixedValues = (fixedResponseTimeValue > 0);
		int actionsCount = (useFixedValues ?  MOBILE_REQUEST_URLS.length : 1);
		
		for(int i = actionsCount - 1; i >= 0; i--) {
			
			MobileActionItem action = new MobileActionItem();
			
			action.setUrl(MOBILE_REQUEST_URLS[i]);
			action.setRequestParams(REQUEST_URL_PARAMS[i]);
			action.setIp(ipSet + "");
			int duration = fixedResponseTimeValue;
			action.setDuration(duration);
			action.setDnsTime(-1);
			action.setConnectTime(-1);
			action.setFirstPacketTime(duration == 0 ? 0 : UncString.randomInt(duration));
			action.setBytesSent(UncString.randomInt(1000000));
			action.setBytesReceived(bytesReceived > 0 ? bytesReceived : UncString.randomInt(1000000));
			
			if(useFixedValues) {
				
				boolean isError = (httpError || networkError);
				
				if(isError) {
					
					if(networkError) {
						
						action.setErrorCode(ERROR_CODES[UncString.randomInt(ERROR_CODES.length)]);
						action.setHttpStatusCode(0);
						errorCodes.add(action.getErrorCode());
						errorRequestUris.add(action.getUrl().toString());
						
					}
					
					
					if(httpError) {
						
						action.setErrorCode(Integer.valueOf(401));
						action.setHttpStatusCode(401);
						errorCodes.add(Integer.valueOf(401));
						errorRequestUris.add(action.getUrl().toString());
						
					}
					
					
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
						
						action.setErrorCode(401);
						action.setHttpStatusCode(401);
						errorCodes.add(Integer.valueOf(401));
						
					}
					
					errorRequestUris.add(action.getUrl().toString());
					
					if(httpError) {
						
						action.setErrorCode(ERROR_CODES[UncString.randomInt(ERROR_CODES.length)]);
						action.setHttpStatusCode(401);
						errorCodes.add(Integer.valueOf(401));
						
					} else {
						
						action.setErrorCode(ERROR_CODES[UncString.randomInt(ERROR_CODES.length)]);
						action.setHttpStatusCode(0);
						errorCodes.add(action.getErrorCode());
						
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
		metrics.addAll(Arrays.asList(
								new MobileMetricItem("mem", UncString.randomInt(1, 1024)),
								new MobileMetricItem("cpu_sys", UncString.randomInt(10, 100)),
								new MobileMetricItem("cpu_user", UncString.randomInt(10, 100)),
								new MobileMetricItem("cpu", UncString.randomInt(10, 100))
						));
		
		if(isLastRequest) {
			
			metrics.add(new MobileMetricItem("sd", UncString.randomInt(1000, 50000)));
			metrics.add(new MobileMetricItem("req", actions.size()));
			
		}
		
		actionsData.setMetrics(metrics);
		
		//模拟上报interationView中数据
		List<GenericMetricItem> interactionViews = new ArrayList<GenericMetricItem>();
		for(int i =0 ; i < 1 ; i++) {	
			
			MetricKey metrickey = METRIC_VIEW_KEY[i];
			
			interactionViews.add(new GenericMetricItem(metrickey,
				    1,
				    interactionTime,
				    UncString.randomInt(10,100),
					UncString.randomInt(50,300),
					UncString.randomInt(50,300),
					UncString.randomInt(2500,90000)
				));
			
		}
		
		actionsData.setInteractionViews(interactionViews);

		//模拟上报interactionComponents中数据
		List<GenericMetricItem> interactionComponents = new ArrayList<GenericMetricItem>();
		
		for(int i =0 ; i < 7 ; i++) {	
			
			MetricKey metrickey = METRIC_COMPONENT_KEY[i];
			
			int componentTime = 0;
			
			// 视图加载
			if (metrickey.getName().equals("Method ViewLoading/MyViewController/loadingAll")) {
				componentTime = viewLoadTime;
			}else if (metrickey.getName().equals("Method Image/MyViewController/imageWithData")) {
				componentTime = imageProcessTime;
			}else if (metrickey.getName().equals("Method Storage/MyViewController/executeSqlLite")) {
				componentTime = dataStorageTime;
			}else if (metrickey.getName().equals("Method Network/MyViewController/networkData")) {
				componentTime = networkVisitTime;
			}else if (metrickey.getName().equals("Method Json/MyViewController/JSONObjectWithStream")) {
				componentTime = jsonProcessTime;
			}
			
			interactionComponents.add(new GenericMetricItem(metrickey,
				    1,
				    componentTime,
				    UncString.randomInt(10,100),
					UncString.randomInt(50,300),
					UncString.randomInt(50,300),
					UncString.randomInt(2500,90000)
				));
			
		}
		
		actionsData.setInteractionComponents(interactionComponents);
		
		//模拟上报interactionClassifiedComponents中数据
		List<GenericMetricItem> interactionClassifiedComponents = new ArrayList<GenericMetricItem>();
		for(int i =0 ; i < 6 ; i++) {	
			
			MetricKey metrickey = METRIC_CLASSIFIEDCOMPONENT_KEY[i];
			
			int classifiedTime = 0;
			
			if (metrickey.getName().equals("MobileViewSummary/NULL/ViewLoading")) {
				classifiedTime = viewLoadTime;
			}else if (metrickey.getName().equals("MobileViewSummary/NULL/Image")) {
				classifiedTime = imageProcessTime;
			}else if (metrickey.getName().equals("MobileViewSummary/NULL/Storage")) {
				classifiedTime = dataStorageTime;
			}else if (metrickey.getName().equals("MobileViewSummary/NULL/Network")) {
				classifiedTime = networkVisitTime;
			}else if (metrickey.getName().equals("MobileViewSummary/NULL/Json")) {
				classifiedTime = jsonProcessTime;
			}
			
			interactionClassifiedComponents.add(new GenericMetricItem(metrickey,
				    1,
				    classifiedTime,
				    UncString.randomInt(10,100),
					UncString.randomInt(50,300),
					UncString.randomInt(50,300),
					UncString.randomInt(2500,90000)
				));
			
		}
		
		actionsData.setInteractionClassifiedComponents(interactionClassifiedComponents);
		
		//模拟上报interactionGeneral中数据
		List<GenericMetricItem> interactionGeneral = new ArrayList<GenericMetricItem>();
		for(int i =0 ; i < 2 ; i++) {	
			
			MetricKey metrickey = METRIC_GENERAL_KEY[i];
			interactionGeneral.add(new GenericMetricItem(metrickey,
				    UncString.randomInt(1,5),
				    UncString.randomInt(50,300),
				    UncString.randomInt(10,100),
					UncString.randomInt(50,300),
					UncString.randomInt(50,300),
					UncString.randomInt(2500,90000)
				));
			
		}
		
		actionsData.setInteractionGeneral(interactionGeneral);
		
		command.setActions(actionsData);
		
		if(errorCodes.size() > 0) {
			
			MobileErrors errorsData = new MobileErrors();
			errorsData.setInterval(60);
			List<MobileErrorItem> errors = new ArrayList<MobileErrorItem>();
			
			for(int j = 0; j < errorCodes.size(); ++j) {
				
				MobileErrorItem error = new MobileErrorItem();
				error.setCount(1);
				error.setHttpStatusCode(errorCodes.get(j));
				String url = errorRequestUris.get(j);
				if(url != null && url.indexOf('?') != -1) {
					url = url.substring(0, url.indexOf('?'));
				}
				
				error.setRequestUrl(url);
				
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
		
 	    MobileInteractionTraceData mobileInterationtrace = new MobileInteractionTraceData();
		mobileInterationtrace.setDeviceInfo(MOBILE_DEVS[UncString.randomInt(MOBILE_DEVS.length)]);
		List<MobileInteractionTraceItem> mobileInterationItem = new ArrayList<MobileInteractionTraceItem>();
		MobileInteractionTraceItem traceItem = new MobileInteractionTraceItem();
		traceItem.setDuration(fixedResponseTimeValue);
		traceItem.setTimestamp((int) (System.currentTimeMillis()/1000));
		traceItem.setViewMetricName("MobileView/Controller/MyViewController");
		traceItem.setTrace("[0,[["+UncString.randomInt(0,100)+","+UncString.randomInt(0,50)+"]]," +
				           "[["+UncString.randomInt(100,500)+","+UncString.randomInt(0,50)+"]]," +
				           	"[0,"+UncString.randomInt(1000,5000)+",\"MyViewController\",{},[[0,"+UncString.randomInt(1000,5000)+",\"Main Thread\",{\"threadId\":1,\"threadName\":\"Main Thread\"},[["+UncString.randomInt(1000,2000)+","+UncString.randomInt(2000,4000)+",\"MyViewController#viewWillLoad\",{},[]],["+UncString.randomInt(1000,2000)+","+UncString.randomInt(2000,3000)+",\"MyViewController#viewDidLoad\",{},[["+UncString.randomInt(0,1000)+","+UncString.randomInt(1000,2000)+",\"JsonParser#parseJson\",{},[]],["+UncString.randomInt(0,500)+","+UncString.randomInt(1000,1500)+",\"Network/192.168.1.5\",{\"url\":\"http://192.168.1.5/api/v2.0\",\"httpStatus\":200,\"errorCode\":0,\"bytesSent\":1024,\"bytesReceived\":2048},[]]]]]],["+UncString.randomInt(500,1000)+","+UncString.randomInt(1000,2000)+",\"2\",{\"threadId\":2,\"threadName\":\"1\"},[]]]]]");
		mobileInterationItem.add(traceItem);
		mobileInterationtrace.setInteractionTraces(mobileInterationItem);
		mobileInterationtrace.setMobileAppId(mobileAppId > 0 ? mobileAppId : UncString.randomInt(1,100));
		mobileInterationtrace.setMobileAppVersionId(UncString.randomInt(1,100));
		mobileInterationtrace.setOwnerId(UncString.randomInt(1,100));
		command.setInteractionTraceData(mobileInterationtrace);
		
		return command;
		
	}
	
	private static String jsonize(Object obj) throws Exception {
		return JSON_OBJECT_MAPPER.writeValueAsString(obj);
	}
	
	private  String post(String protocol, String host, int port, String uri, String content, String appToken) throws Exception {
		
		HttpURLConnection conn = createConnection(protocol, host, port, uri, 600000, appToken);
		OutputStream os = conn.getOutputStream();
		os.write(gzip(content, "UTF-8"));
		int statusCode = conn.getResponseCode();
		String responseBody = (statusCode == 200 ? readResponseBody(conn) : null);
		conn.disconnect();
		return responseBody;
		
	}
	
	private  HttpURLConnection createConnection(String protocol, String host, int port, String uri, int requestTimeoutInMillis, String appToken) throws Exception {
		
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
