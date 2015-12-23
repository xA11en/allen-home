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


/**
 * @author BurningIce
 *
 */
public class QyMobileAgentSimulator {
	private final static JacksonSerializableObjectMapper JSON_OBJECT_MAPPER = new JacksonSerializableObjectMapper();
	private final static Pattern PATTERN_INIT_MOBILE_APP_RESPONSE = Pattern.compile("\\{\"status\"\\:\"success\",\"result\"\\:\\{\"token\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\"enabled\"\\s*\\:\\s*([0-1]).*\"did\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*\\}");
	private final static String USER_AGENT_HEADER_VALUE = "NBS Newlens Agent/1.0.0 (iOS 6.1.2)";
	//private final static String DEFAULT_MOBILE_APP_TOKEN = "123456789";
	private final static String DEFAULT_MOBILE_APP_TOKEN = "70664f8b3c484fb9a814463691f9565b";
	
	private final static int REQUEST_TIME_OUT = 60000;
	private final static String DATA_VERSION = "1.0";
	private final static MobileDeviceInfo[] MOBILE_DEVICES = new MobileDeviceInfo[25];
	private final static com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[] MOBILE_DEVS = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[6];
	
	
	
	private final static String[] CHINA_CITY_LAT_LNG = new String[] {
			"481101,北京市,39.917,116.4166667",
			"481201,天津市,39.133,117.2",
			"481301,石家庄市,38.033,114.4833333",
			"481302,唐山市,39.633,118.0166667",
			"481303,秦皇岛市,39.95,119.5666667",
			"481304,邯郸市,36.6,114.4666667",
			"481305,邢台市,37.05,114.4833333",
			"481306,保定市,38.85,115.4833333",
			"481307,张家口市,40.817,114.8666667",
			"481308,承德市,40.967,117.9333333",
			"481309,沧州市,38.333,116.8333333",
			"481310,廊坊市,39.533,116.7",
			"481311,衡水市,37.717,115.7166667",
			"481401,太原市,37.867,112.5333333",
			"481402,大同市,40.117,113.3",
			"481403,阳泉市,37.85,113.5666667",
			"481404,长治市,36.183,113.0833333",
			"481405,晋城市,35.517,112.8333333",
			"481406,朔州市,39.3,112.4",
			"481407,晋中市,37.7,112.7",
			"481408,运城市,35,111",
			"481409,忻州市,38.4,112.7",
			"481410,临汾市,36.1,111.5",
			"481411,吕梁市,37.5,111.2",
			"481501,呼和浩特市,40.817,111.65",
			"481502,包头市,40.583,110",
			"481503,乌海市,39.667,106.8166667",
			"481504,赤峰市,42.283,118.8666667",
			"481505,通辽市,43.633,122.2833333",
			"482101,沈阳市,41.8,123.3833333",
			"482102,大连市,38.917,121.6166667",
			"482103,鞍山市,41.2,123",
			"482104,抚顺市,41.967,123.9666667",
			"482105,本溪市,41.3,123.7333333",
			"482106,丹东市,40.133,124.3666667",
			"482107,锦州市,41.133,121.15",
			"482108,营口市,40.65,122.1833333",
			"482109,阜新市,42.2,121.8",
			"482110,辽阳市,41.283,123.1666667",
			"482111,盘锦市,41.2,122.1",
			"482112,铁岭市,42.317,123.85",
			"482113,朝阳市,41.583,120.4166667",
			"482114,葫芦岛市,40.8,120.8",
			"482201,长春市,43.883,125.35",
			"482202,吉林市,43.867,126.5666667",
			"482203,四平市,43.167,124.3666667",
			"482204,辽源市,42.967,125.15",
			"482205,通化市,41.5,125.9166667",
			"482206,白山市,41.9,126.4",
			"482207,松原市,45.3,124.8",
			"482208,白城市,45.633,122.8166667",
			"482301,哈尔滨市,45.75,126.6333333",
			"482302,齐齐哈尔市,47.333,123.9666667",
			"482303,鸡西市,45.3,130.9",
			"482304,鹤岗市,47.333,130.3",
			"482305,双鸭山市,46.65,131.1666667",
			"482306,大庆市,46.583,125.0333333",
			"482307,伊春市,47.733,128.9166667",
			"482308,佳木斯市,46.833,130.35",
			"482309,七台河市,45.817,130.8333333",
			"482310,牡丹江市,44.6,129.5833333",
			"482311,黑河市,50.217,127.5333333",
			"482312,绥化市,46.633,127",
			"483101,上海市,31.2,121.4",
			"483201,南京市,32.05,118.7833333",
			"483202,无锡市,31.6,120.3",
			"483203,徐州市,34.267,117.2",
			"483204,常州市,31.8,119.95",
			"483205,苏州市,31.317,120.6166667",
			"483206,南通市,32.017,120.8666667",
			"483207,连云港市,34.6,119.1666667",
			"483208,淮安市,33.5,119.15",
			"483209,盐城市,33.383,120.1333333",
			"483210,扬州市,32.4,119.4166667",
			"483211,镇江市,32.2,119.45",
			"483212,泰州市,32.5,119.9",
			"483213,宿迁市,33.967,118.3",
			"483301,杭州市,30.267,120.2",
			"483302,宁波市,29.867,121.5666667",
			"483303,温州市,28.017,120.65",
			"483304,嘉兴市,30.767,120.7666667",
			"483305,湖州市,30.867,120.1",
			"483306,绍兴市,30.017,120.5833333",
			"483307,金华市,29.117,119.65",
			"483308,衢州市,28.967,118.8833333",
			"483309,舟山市,30,122.1",
			"483310,台州市,28.65,121.2666667",
			"483311,丽水市,28.45,119.9166667",
			"483401,合肥市,31.867,117.2666667",
			"483402,芜湖市,31.333,118.3833333",
			"483403,蚌埠市,32.933,117.35",
			"483404,淮南市,32.617,116.9833333",
			"483405,马鞍山市,31.567,118.4833333",
			"483406,淮北市,33.967,116.7666667",
			"483407,铜陵市,30.933,117.8166667",
			"483408,安庆市,30.517,117.0333333",
			"483410,黄山市,30.2,118.1",
			"483411,滁州市,32.333,118.3166667",
			"483412,阜阳市,32.9,115.8166667",
			"483413,宿州市,33.633,116.9666667",
			"483414,巢湖市,31.617,117.8666667",
			"483415,六安市,31.733,116.5",
			"483416,亳州市,33.8,115.7",
			"483417,池州市,30.6,117.4",
			"483418,宣城市,30.9,118.7",
			"483501,福州市,26.083,119.3",
			"483502,厦门市,24.467,118.1",
			"483503,莆田市,25.45,119",
			"483504,三明市,26.233,117.6166667",
			"483505,泉州市,24.933,118.5833333",
			"483506,漳州市,24.517,117.35",
			"483507,南平市,26.65,118.1666667",
			"483508,龙岩市,25.117,117.0166667",
			"483509,宁德市,26.65,119.5166667",
			"483601,南昌市,28.683,115.9",
			"483602,景德镇市,29.3,117.2166667",
			"483603,萍乡市,27.6,113.85",
			"483604,九江市,29.717,115.9666667",
			"483605,新余市,27.817,114.9166667",
			"483606,鹰潭市,28.233,117.0166667",
			"483607,赣州市,25.85,114.9166667",
			"483608,吉安市,27.1,114.9",
			"483609,宜春市,27.7,114.3",
			"483610,抚州市,27.9,116.2",
			"483611,上饶市,28.4,117.9",
			"483701,济南市,36.65,117",
			"483702,青岛市,36.067,120.3333333",
			"483703,淄博市,36.783,118.05",
			"483704,枣庄市,34.867,117.5666667",
			"483705,东营市,37.467,118.5",
			"483706,烟台市,37.5,121.4",
			"483707,潍坊市,36.617,119.1",
			"483708,济宁市,35.383,116.6",
			"483709,泰安市,36.183,117.1333333",
			"483710,威海市,37.5,122.1",
			"483711,日照市,35.417,119.4666667",
			"483712,莱芜市,36.2,117.6666667",
			"483713,临沂市,35.05,118.35",
			"483714,德州市,37.45,116.3",
			"483715,聊城市,36.45,115.9666667",
			"483716,滨州市,37.3,118",
			"483717,菏泽市,35,115.7",
			"484101,郑州市,34.767,113.65",
			"484102,开封市,34.8,114.35",
			"484103,洛阳市,34.7,112.45",
			"484104,平顶山市,33.75,113.3",
			"484105,安阳市,36.1,114.35",
			"484106,鹤壁市,35.9,114.1666667",
			"484107,新乡市,35.317,113.85",
			"484108,焦作市,35.25,113.2166667",
			"484109,濮阳市,35.717,114.9833333",
			"484110,许昌市,34.017,113.8166667",
			"484111,漯河市,33.567,114.0166667",
			"484112,三门峡市,34.767,111.2",
			"484113,南阳市,33,112.5",
			"484114,商丘市,34.45,115.65",
			"484115,信阳市,32.133,114.0833333",
			"484116,周口市,33.6,114.6",
			"484117,驻马店市,32.9,114",
			"484118,济源市,35.083,112.5666667",
			"484201,武汉市,30.517,114.3166667",
			"484202,黄石市,30.2,115.1",
			"484203,十堰市,32.65,110.8",
			"484205,宜昌市,30.7,111.3",
			"484206,襄樊市,30.017,112.15",
			"484207,鄂州市,30.3,114.8",
			"484208,荆门市,31.017,112.2",
			"484209,孝感市,30.92342,113.91777",
			"484210,荆州市,30.3,112.1",
			"484211,黄冈市,30.45,114.8666667",
			"484212,咸宁市,29.867,114.2833333",
			"484213,随州市,31.6,113.3",
			"484228,恩施土家族苗族州,30.2,109.4",
			"484301,长沙市,28.217,113",
			"484302,株洲市,27.833,113.1666667",
			"484303,湘潭市,27.867,112.9166667",
			"484304,衡阳市,26.9,112.6166667",
			"484305,邵阳市,27.217,111.5",
			"484306,岳阳市,29.367,113.1",
			"484307,常德市,29.05,111.7",
			"484308,张家界市,29.1,110.4",
			"484309,益阳市,28.6,112.3333333",
			"484310,郴州市,25.8,113",
			"484311,永州市,26.217,111.6333333",
			"484312,怀化市,27.517,109.95",
			"484313,娄底市,27.717,111.9666667",
			"484401,广州市,23.167,113.2333333",
			"484402,韶关市,24.85,113.6166667",
			"484403,深圳市,22.617,114.0666667",
			"484404,珠海市,22.3,113.5166667",
			"484405,汕头市,23.4,116.7",
			"484406,佛山市,23.05,113.1166667",
			"484407,江门市,22.617,113.0666667",
			"484408,湛江市,21.2,110.3",
			"484409,茂名市,21.683,110.8833333",
			"484412,肇庆市,23.05,112.45",
			"484413,惠州市,23.1,114.4",
			"484414,梅州市,24.55,116.1",
			"484415,汕尾市,22.9,115.3",
			"484416,河源市,23.733,114.6833333",
			"484417,阳江市,21.85,111.95",
			"484418,清远市,23.7,113.0166667",
			"484419,东莞市,23.05,113.75",
			"484420,中山市,22.517,113.3833333",
			"484451,潮州市,23.683,116.6333333",
			"484452,揭阳市,23.55,116.35",
			"484453,云浮市,22.933,112.0166667",
			"484501,南宁市,22.8,108.3",
			"484502,柳州市,23.7,108.8",
			"484503,桂林市,25.3,110.2833333",
			"484504,梧州市,23.517,111.35",
			"484505,北海市,21.5,109.1166667",
			"484506,防城港市,21.7,108.3",
			"484507,钦州市,21.967,108.6166667",
			"484508,贵港市,21.7,109.6",
			"484509,玉林市,22.65,110.15",
			"484510,百色市,23.9,106.6",
			"484511,贺州市,22.9,110.9",
			"484512,河池市,24.7,108",
			"484513,来宾市,23.767,109.25",
			"484514,崇左市,22.417,107.3666667",
			"484601,海口市,20.017,110.35",
			"484602,三亚市,18.2,109.5",
			"485001,重庆市,29.567,106.45",
			"485101,成都市,30.667,104.0666667",
			"485103,自贡市,29.3,104.7",
			"485104,攀枝花市,26.5,101.7",
			"485105,泸州市,28.917,105.4",
			"485106,德阳市,31.133,104.3666667",
			"485107,绵阳市,31.483,104.7333333",
			"485108,广元市,32.4,105.8",
			"485109,遂宁市,30.517,105.5833333",
			"485110,内江市,29.6,105.05",
			"485111,乐山市,29.3,103.7",
			"485113,南充市,30.8,106.5",
			"485114,眉山市,30,103.8",
			"485115,宜宾市,29.767,104.5666667",
			"485116,广安市,30.483,106.6166667",
			"485117,达州市,31.2,107.4",
			"485118,雅安市,29.9,102.9",
			"485119,巴中市,31.8,106.7",
			"485120,资阳市,30.1,104.6",
			"485132,阿坝藏族羌族州,31.933,101.7166667",
			"485133,甘孜藏族自治州,31.65,99.96666667",
			"485201,贵阳市,26.567,106.7166667",
			"485202,六盘水市,26.583,104.8166667",
			"485203,遵义市,27.7,106.9",
			"485204,安顺市,26.2,105.9",
			"485222,铜仁地区,27.7,109.2",
			"485223,黔西南布依族苗族州,27,106",
			"485224,毕节地区,27.3,105.3",
			"485226,黔东南苗族侗族州,26.5,107",
			"485227,黔南布依族苗族州,26.2,107.5",
			"485301,昆明市,25.05,102.7333333",
			"485303,曲靖市,25.517,103.8",
			"485304,玉溪市,24.35,102.5166667",
			"485305,保山市,25.1,99.1",
			"485306,昭通市,27.338257,103.71746",
			"485308,思茅市,22.78691,100.97716",
			"485309,临沧市,23.877573,100.07958",
			"485323,楚雄彝族自治州,25.017,101.55",
			"485325,红河哈尼族彝族州,23.35,102.4166667",
			"485326,文山壮族苗族州,23.367,104.25",
			"485329,大理白族自治州,25.7,100.2",
			"485401,拉萨市,29.6,91",
			"485421,昌都地区,31.1,97.1",
			"485423,日喀则地区,29.2,88.8",
			"485424,那曲地区,31.4,92",
			"485426,林芝地区,29.5,94.3",
			"486101,西安市,34.267,108.95",
			"486102,铜川市,35.1,109.1166667",
			"486103,宝鸡市,34.3,107.1",
			"486104,咸阳市,34.367,108.7166667",
			"486105,渭南市,34.517,109.5",
			"486106,延安市,36.6,109.4666667",
			"486107,汉中市,33.067,108.05",
			"486108,榆林市,38.3,109.7666667",
			"486109,安康市,32.6,109",
			"486110,商洛市,33.8,109.9",
			"486201,兰州市,36.033,103.7333333",
			"486202,嘉峪关市,39.8,98.2",
			"486203,金昌市,38.4,102.1",
			"486204,白银市,36.5,104.2",
			"486205,天水市,34.6,105.7",
			"486206,武威市,37.9,102.6",
			"486207,张掖市,38.9,100.4",
			"486208,平凉市,35.5,106.6",
			"486209,酒泉市,39.8,97.5",
			"486210,庆阳市,35.7,107.6",
			"486211,定西市,35.5,104.5",
			"486212,陇南市,33.7,105.7",
			"486229,临夏回族自治州,35.617,103.2166667",
			"486230,甘南藏族自治州,34.9,102.9",
			"486301,西宁市,36.567,101.75",
			"486327,玉树藏族自治州,33.033,96.96666667",
			"486401,银川市,38.467,106.2666667",
			"486402,石嘴山市,39.05,106.4",
			"486403,吴忠市,38,106.2166667",
			"486404,固原市,36,106.2",
			"486405,中卫市,37.517,105.1833333",
			"486501,乌鲁木齐市,43.767,87.68333333",
			"486502,克拉玛依市,45.6,84.76666667",
			"486521,吐鲁番地区,42.9,89.2",
			"486522,哈密地区,42.8,93.4",
			"486523,昌吉回族自治州,44.05,87.31666667",
			"486527,博尔塔拉蒙古州,44.9,82",
			"486529,阿克苏地区,41.1,80.2",
			"486531,喀什地区,39.4,75.9",
			"486532,和田地区,37.1,79.9",
			"486540,伊犁哈萨克州,44.4,84.8",
			"486542,塔城地区,46.75,82.96666667",
			"486543,阿勒泰地区,47.867,88.15",
			
			"487101,台北市,25.0465658,121.548867"
			/*
			"488101,香港,22.396428,114.10949",
			"488201,澳门,22.198745,113.54387"
			*/
		};
	
	

	private	final static MetricKey[] METRIC_VIEW_KEY = new MetricKey[] {
		new MetricKey(0, 0, "MobileView/Controller/MyViewController", null),
		new MetricKey(0, 0, "MobileView/Activity/MyController", null)
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

	private final static String[] MOBILE_REQUEST_URLS = new String[] {
		"http://passport.networkbench.com/passport/login/1111/2222/ffff/3333/54665/111111/6666/1111/hhhhh/gggg/gggg/hhhh/hhhhhhhh/kkkkkkk/kkkkk/lllll/llllll/llllll/lllll/wwwww/wwwww/wwwww/rrrrrr/rrrrggag/g/aegaegae/gaegaeg/gagarghhsh/hyjyjyuj/kyukyagaerg/gaegqegtr/gfhshty/hytjhtjy/hargtwgrg/gregrshth/hytjyuj/jytjhwset/grgrgw/gtsghsg/gthhj/jyjytegtw/grtwgrfw/htyhyhwe/gthtrghrth/hthrwfr/grgrhgth/hthtgtw/gghthth/grwgrerg/hthtyhyte/hthtwfr/gtrgrth/hythtrg/gregferfref/grwgth/hythyhwe/grfrgf/gtgthth/hthte/hthth/hththt/hthht/hthth/htht/wrerewt/hythyhwftr/grwgwg/gtwhwtew/tretrwt/hthjtjwt/htywhwt/ggtrsht/hytjhytjt/jryjyr/hetyhe/htyje/jetje/hnethyetg/grhetht/hetyheg/grtwgertheth/hetyjheytjt/jytjeghte/hthetyhjej/jyreeherth/jnytjyteh/jrytjejhey/etyjehbtyhj/njrtyjrjrj/htrhtjej/hgrhrth/hythyjte/hrtgrgr/grsgrth/hntjtej/jyukjyk/hthwrhr/nhytjytej/htyjetyj/tuyetuty/fhshsgthbs/hsfghsfhfsgh/hsghsfgj/fshsfgjsfj//6666/1111/hhhhh/gggg/gggg/hhhh/hhhhhhhh/kkkkkkk/kkkkk/lllll/llllll/llllll/lllll/wwwww/wwwww/wwwww/rrrrrr/rrrrggag/g/aegaegae/gaegaeg/gagarghhsh/hyjyjyuj/kyukyagaerg/gaegqegtr/gfhshty/hytjhtjy/hargtwgrg/gregrshth/hytjyuj/jytjhwset/grgrgw/gtsghsg/gthhj/jyjytegtw/grtwgrfw/htyhyhwe/gthtrghrth/hthrwfr/grgrhgth/hthtgtw/gghthth/grwgrerg/hthtyhyte/hthtwfr/gtrgrth/hythtrg/gregferfref/grwgth/hythyhwe/grfrgf/gtgthth/hthte/hthth/hththt/hthht/hthth/htht/wrerewt/hythyhwftr/grwgwg/gtwhwtew/tretrwt/hthjtjwt/htywhwt/ggtrsht/hytjhytjt/jryjyr/hetyhe/htyje/jetje/hnethyetg/grhetht/hetyheg/grtwgertheth/hetyjheytjt/jytjeghte/hthetyhjej/jyreeherth/jnytjyteh/jrytjejhey/etyjehbtyhj/njrtyjrjrj/htrhtjej/hgrhrth/hythyjte/hrtgrgr/grsgrth/hntjtej/jyukjyk/hthwrhr/nhytjytej/htyjetyj/tuyetuty/fhshsgthbs/hsfghsfhfsgh/hsghsfgj/fshsfgjsfj/?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www/passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www",
		"http://passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www/passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www&passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www",
		"http://wlan.ct10000.com?wlanuserip=115.193.58.88&wlanacname=&mscgip=61.164.1.0&userlocation=ethtrunk",
		"http://www.baidu.com",
		"http://211.138.195.31?wlanacname=0010/0513/250/00&wlanuserip=10.71.87.162&ssid=cmcc-edu&vlan=363",
		"http://192.168.1.99:9000",
		"http://192.168.1.5",
		"http://s16.mogucdn.comhttp%3a%2f%2fs6.mogujie.cn%2fpic%2f131018%2fs7ulr_kqyvg5kwkfbfqwtwgfjeg5sckzsew_270x270.jpg",
		"http://s16.mogucdn.comhttp%3a%2f%2fs6.mogujie.cn%2fpic%2f131018%ghhshthteyjhetj.html",
		"http://s16.mogucdn.comhttp%3a%2f%2fs6.mogujie.cn%2fpic%2f131018%hetjetyjeje6.php",
		"http://s16.mogucdn.comhttp%3a%2f%2fs6.mogujie.cn%2fpic%2f131018%2fs7ulr_kqyvg5kwkfbfqwtwgfjeg5sckzsew_270x270.jpg?products/webview.html",
		"http://passport.networkbench.com/passport/login?service=http%3A%2F%2Frpc.networkbench.com%2Frpc%2Fj_acegi_cas_security_check.do%3Bjsessionid%3DA00A2DE1CD52CA09180D9079ED084BFF.jvm07%3Flang%3D%23%7Blang%7D%26tz%3D%23%7Btz%7D&loginView=www",
		"http://ops.networkbench.com/ops/apply.po",
		"http://www.networkbench.com/products/webview.html",
		"http://www.networkbench.com/solutions/integrated.html",
		"http://www.networkbench.com/case/ecommerce.html",
		"http://www.networkbench.com/case/travel.html",
		"http://www.networkbench.com/services/reports.html",
		"http://www.networkbench.com/alliance/download.html",
		"http://www.networkbench.com/alliance/index.html",
		"http://www.networkbench.com/download/NetworkBenchClientInstallation.pdf",
		"http://www.networkbench.com/alliance/jcmember.html",
		"http://www.networkbench.com/alliance/zfmember.html",
		"http://www.networkbench.com/alliance/area.html",
		"http://member.networkbench.com/member/memberAwards.po",
		"http://www.networkbench.com/products/streamingview.html",
		"http://www.networkbench.com/products/cdnmeter.html",
		"http://www.networkbench.com/products/mobileview.html",
		"http://www.networkbench.com/products/intraview.html",
		"http://www.networkbench.com/products/private.html",
		"http://www.networkbench.com/products/erroralarm.html",
		"http://www.networkbench.com/products/web_app.html",
		"http://www.networkbench.com/products/flowview.html",
		"http://192.168.1.21/rpc17/index17.jsp",
		"http://img.adbox.sina.com.cn/pic/20217.png",
		"http://www.networkbench.com/index.html",
		"http://rpcbeta.networkbench.com/rpc/home.do",
		"http://rpc.networkbench.com:8080/rpc/measure.do?formAction=graph&pw=yes",
		"http://video.sina.com.cn/z/sports/k/nba/131008atlmia/",	
		"https://lens.networkbench.com/analysis/mobileApp/34/crash",
		"https://lens.networkbench.com/analysis/mobileApp/33/crash",
		"https://lens.networkbench.com/analysis/mobileApp/32/crash",
		"https://lens.networkbench.com/analysis/mobileApp/31/crash",
		"http://i1.sinaimg.cn/home/2014/1008/U8059P30DT20131008102816.jpg",
		"http://i2.sinaimg.cn/home/2012/1008/U8059P30DT20131008102816.jpg",
		"http://i3.sinaimg.cn/home/2011/1008/U8059P30DT20131008102816.jpg",
		"http://i4.sinaimg.cn/home/2010/1008/U8059P30DT20131008102816.jpg",
		"http://i5.sinaimg.cn/home/2015/1008/U8059P30DT20131008102816.jpg",
		"http://img.adbox.sina.com.cn/pic/22650.jpg",
		"http://cdn.tanx.com/t/acookie/acbeacon2.html",
		"http://www.networkbench.com.cn/index.html",
		"http://www.networkbench.com/rpc/home.do",
		"http://newlens.networkbench.com/rpc/home.do",
		"http://networkbench.com/aaa.do",
		"http://www.nbs.com/pic/22650.jpg",
		"http://nbs.com/acookie/acbeacon2.html",
		"http://cesuba.abc2fdsafda-xxxx_33.nbs.la/mobileApp/31/crash",
		"http://cesuba.abc2fdsafda-xxxx_33.nbs.la/mobileApp/32/crash",
		"http://cesuba.abc2fdsafda-xxxx_33.nbsla.com/mobileApp/32/crash",
		"http://cesuba.abc2fdsafda-xxxx_33.nbsla.com/mobileApp/33/crash",
		"http://i0.sinaimg.cn/home/2013/1008/U8059P30DT20131008102816.jpg",
		"http://i0.sinaimg.cn/home/2013/1008/U8059P30DT20131008102817.jpg",
		"http://i0.sinaimg.cn/home/2013/1008/U8059P30DT20131008102818.jpg",
		"http://i0.sinaimg.cn/home/2013/1008/U8059P30DT20131008102819.jpg",
		"http://www.baidu.com/user/1234/apps/5678",
		"http://www.networkbench.com/user/user/1234",
		"http://www.networkbench.com/user/1234/apps/5678",
		"http://www.networkbench.com/user/666/apps/888",
		"http://networkbench.com/user/1234/apps/5678",
		"http://www.nbs.com/abcdefg0123456789-_./index.html",
		"http://nbs.com/abcdefg0123456789-_./index.html",
		"http://www.nbsla.com/topic.do?a=1234&b=5678",
		"http://www.nbsla.com/topic.do?a=666&b=888&c=999",
		"http://www.newlens.com/home/U8059P30DT20131008102816.jpg",
		"http://www.newlens.com/home/U8059P30DT20131008102817.jpg",
		"http://www.newlens.com/home/U8059P30DT20131008102818.jpg",
		"http://www.newlens.com/home/U8059P30DT20131008102819.jpg",
		"httpgaeg://sdfasgfsgadg",
		"http://y.networkbench.com",
		"http://192.168.1.99:9000",
		"http://www.networkbench.com/sdk-test/x_newlens_application_id.php",
		"https://jira.networkbench.com/",
		"https://192.168.1.2/"
	};
	
	private final static int[] ERROR_CODES = new int[] {
		900, 901, 902, 903, 904
	};
	
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
		String serverUrl = (args.length == 0 ? "http://192.168.2.188:8081" : args[0]);
		if(serverUrl.indexOf("://") == -1) {
			serverUrl = "http://" + serverUrl;
		}
		
		String appToken = (args.length > 1 ? args[1] : DEFAULT_MOBILE_APP_TOKEN);
		int thinkTime = (UncString.toInt(System.getProperty("thinkTime"), 600));
		int count = (UncString.toInt(System.getProperty("count"), Integer.MAX_VALUE));
		//int count = 200;
		int period = (UncString.toInt(System.getProperty("period"), 60));
		//int period = 300;
		String mode = System.getProperty("mode");
		//String mode = "fixed";
		int responseTime = UncString.toInt(System.getProperty("responseTime"), 5000);
		//int responseTime = 4;
		double httpErrorPercentage = (UncString.toDouble(System.getProperty("httpErrorPercentage"), 0.03));
		//double httpErrorPercentage = 5;
		double networkErrorPercentage = (UncString.toDouble(System.getProperty("networkErrorPercentage"), 0.03));
		//double networkErrorPercentage = 6;
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
		
		boolean fixLocation = true;
		if("false".equals((System.getProperty("fixLocation")))){
			fixLocation =false;
		}
		
		try {
			if("fixed".equals(mode)) {
				System.out.println("running in fixed mode with server: " + serverUrl + ", appToken: " + appToken + ", count: " + count + 
", period: " + period + ", responseTime: " + responseTime + 
										", httpErrorPercentage: " + httpErrorPercentage + ", networkErrorPercentage: " + networkErrorPercentage);
				simulateInFixedValues(protocol, host, port, appToken, count, period, responseTime, httpErrorPercentage, networkErrorPercentage);
			} else {
				boolean withIdle = "idle".equals(mode);
				System.out.println("running in " + (withIdle ? "idle" : "normal") + " mode with server: " + serverUrl + ", appToken: " + appToken + ", count: " + count);
				simulate(protocol, host, port, count, thinkTime, appToken, withIdle,fixLocation);
			}
			
			System.out.println("finished, exit!");
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static Map<String, Integer> DEVICE_LAST_LAUNCH_TIME = new HashMap<String, Integer>();
	
	private static void simulate(String protocol, String host, int port, int count, int thinkTime, String appToken, boolean withIdle,boolean fixLocation) throws Exception {
		//测试max_active_devices_ratio 方法：循环1000次初始化操作，统计enabled为1和0分个数，1为初始化成功的设备，0为初始化失败的设备
	/*	int active_1=0;
		int active_0=0;
	   for(int i=0;i<2000;i++){
		   String response = simulateInitMobileAgentApp(protocol, host, port, appToken);
		   if(response != null) {
			   Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
				if(matcher.matches()) {
					String enabled = matcher.group(2);
					if(enabled.equals("1")){
						active_1=active_1+1;
					}
				}else{
					   active_0=active_0+1; 
				     }
		   } 
	   }
	   
	   System.out.println("active_1="+active_1);
	   System.out.println("active_0="+active_0);*/
	   
	   
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
				DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int)(System.currentTimeMillis()/ 1000L)));
				
				for(int i = 0; i < count; ++i) {
					boolean isIdle = false;
					if(withIdle) {
						isIdle = (UncString.randomInt(8) == 1);
					}
					if(isIdle) {
						// idle upload
						System.out.println("idling for 60s...");
						//Thread.sleep(480000L);
						Thread.sleep(100);
						post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, "{}", appToken);
					} else {
						Integer launchTime = DEVICE_LAST_LAUNCH_TIME.get(deviceId);
						
						int activeTime = (int)(System.currentTimeMillis() / 1000L) - launchTime.intValue();
						int expectedActiveTime = UncString.randomInt(90, 600);
						boolean isLastRequest = (activeTime > expectedActiveTime);
						
						String resp = simulateUploadMobileData(protocol, host, port, appToken, token, isLastRequest,fixLocation);
						System.out.println("uploaded " + (i + 1) + ", response: " + resp);
						
						if(isLastRequest) {
							boolean isCrash = (UncString.randomInt(3) == 1);
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
	private static void simulateInFixedValues(String protocol, String host, int port, String appToken,
													int count, int period,
													int responseTime, double httpErrorPercentage, double networkErrorPercentage
												) throws Exception {
		String response = simulateInitMobileAgentApp(protocol, host, port, appToken);
		if(response != null) {
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			if(matcher.matches()) {
				String token = matcher.group(1);
				long beginTime = System.currentTimeMillis();
				long endTime = beginTime + period * 1000;
				int minutes = (int)Math.ceil((endTime - beginTime) / 60000.0);
				int countPerMin = count / minutes;
				int countFirstMin = (minutes == 1 ? count : (count - (minutes - 1) * countPerMin));
				int periodFirstMin = (minutes == 1 ? period : period - (minutes - 1) * 60);
				for(int i = 0; i < minutes; ++i) {
					simulateUploadInFixedValues(protocol, host, port, appToken, token, (i == 0 ? countFirstMin : countPerMin), (i == 0 ? periodFirstMin : 60), responseTime, httpErrorPercentage, networkErrorPercentage);
				}
				System.out.println("finished uploaded " + count + " items in " + (System.currentTimeMillis() - beginTime) + " ms.");
			} else {
				System.out.println("token not found in response: " + response);
			}
		}
	}
	
	private static void simulateUploadInFixedValues(String protocol, String host, int port, String appToken, String token,
														int count, int period,
														int responseTime, double httpErrorPercentage, double networkErrorPercentage
													) throws Exception {
		System.out.println("upload data using token " + token + "...");
		long beginTime = System.currentTimeMillis();
		long endTime = beginTime + period * 1000;
		int uploadedCount = 0;
		int httpErrorCount = (int)(count * httpErrorPercentage);
		int networkErrorCount = (int)(count * networkErrorPercentage);
		double httpCountPerError = (httpErrorPercentage == 0.0 ? 0D : 1 / httpErrorPercentage);
		double networkCountPerError = (networkErrorPercentage == 0.0 ? 0D : 1 / networkErrorPercentage);
		int[] httpErrorIndices = new int[httpErrorCount];
		int[] networkErrorIndices = new int[networkErrorCount];
		
		if(httpErrorCount > 0) {
			int index = 0;
			for(double httpErrorIndex = httpCountPerError - 1 ; httpErrorIndex < count ;httpErrorIndex += httpCountPerError, ++index) {
				if(index == httpErrorCount) {
					break;
				}
				httpErrorIndices[index] = (int)httpErrorIndex;
			}
		}
		
		if(networkErrorCount > 0) {
			int index = 0;
			for(double networkErrorIndex = networkCountPerError - 1 ; networkErrorIndex < count ;networkErrorIndex += networkCountPerError, ++index) {
				if(index == networkErrorCount) {
					break;
				}
				networkErrorIndices[index] = (int)networkErrorIndex;
			}
		}
		
		int actualHttpErrorCount = 0;
		int actualNetworkErrorCount = 0;
		
		List<MobileUploadRequestCommand> messages = new ArrayList<MobileUploadRequestCommand>();
		for(int i = 0; i < count; ++i) {
			boolean httpError = false;
			for(int j = 0; j < httpErrorIndices.length; ++j) {
				if(httpErrorIndices[j] == i) {
					httpError = true;
					break;
				}
			}
			
			boolean networkError = false;
			for(int j = 0; j < networkErrorIndices.length; ++j) {
				if(networkErrorIndices[j] == i) {
					networkError = true;
					break;
				}
			}
			messages.add(createMobileUploadMessage(false, responseTime, httpError, networkError,true));
		}
		
		int countLeft = count;
		for(int i = 0; i < count; ++i) {
			MobileUploadRequestCommand message = messages.get(i);
			String text = jsonize(message);
			String resp = post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, text, appToken);
			System.out.print('.');
			++uploadedCount;
			--countLeft;				
			
			Integer errorCode = message.getActions().getActions().get(0).getErrorCode();
			if(errorCode != null && errorCode.intValue() > 0) {
				if(errorCode.intValue() >= 900) {
					++actualNetworkErrorCount;
				} else {
					++actualHttpErrorCount;
				}
			}
			if(countLeft > 0) {
				long thinkTime = ((endTime - System.currentTimeMillis()) / countLeft) - 10L;
				if(thinkTime > 0)
					Thread.sleep(thinkTime);
			}
		}

		System.out.println();
		System.out.println("uploaded " + uploadedCount + ", http errors: " + actualHttpErrorCount + "(" + (actualHttpErrorCount/(double)uploadedCount) + 
								"), " + ", network errors: " + actualNetworkErrorCount + "(" + (actualNetworkErrorCount/(double)uploadedCount) + "), ");	
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

	private static String simulateInitMobileAgentApp(String protocol, String host, int port, String appToken) throws Exception {
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
		String response = post(protocol, host, port, "/initMobileApp?version=" + DATA_VERSION, text, appToken);
		System.out.println("initMobileAgentApp finished: " + response);
		return response;
	}
	
	private static String simulateUploadMobileData(String protocol, String host, int port, String appToken, String token, boolean isLastRequest,boolean fixLocation) throws Exception {
		MobileUploadRequestCommand command = createMobileUploadMessage(isLastRequest, 0, false, false,fixLocation);
		String text = jsonize(command);
		System.out.println(text);
		return post(protocol, host, port, "/uploadMobileData?version=" + DATA_VERSION + "&token=" + token, text, appToken);
	}

	private static int index = 0;
	
	private static MobileUploadRequestCommand createMobileUploadMessage(boolean isLastRequest, int fixedResponseTimeValue, boolean httpError, boolean networkError ,boolean fixLocation) throws Exception {
		MobileUploadRequestCommand command = new MobileUploadRequestCommand();
		MobileActions actionsData = new MobileActions();
		actionsData.setInterval(60);
		com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo dev = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo();
		
		com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo info = MOBILE_DEVS[UncString.randomInt(MOBILE_DEVS.length)];
		
		if(!fixLocation){
			if(index>=CHINA_CITY_LAT_LNG.length){
				index = 0;
			}
			String ps[] =	CHINA_CITY_LAT_LNG[index].split(",");
			System.out.println(ps[0]+":"+ps[1]);
			info.setConnectType(2);
			info.setLatitude(Float.parseFloat(ps[2]));
			info.setLongitude(Float.parseFloat(ps[3]));
			index +=1;
			if(index>=CHINA_CITY_LAT_LNG.length){
				index = 0;
			}
		}
		
		actionsData.setDeviceInfo(info);
		
		List<MobileActionItem> actions = new ArrayList<MobileActionItem>();
		List<Integer> errorCodes = new ArrayList<Integer>();
		List<String> errorRequestUris = new ArrayList<String>();
		
		// 固定响应时间用于模拟警报，一次1个action，方便准确估计警报触发的阈值
		boolean useFixedValues = (fixedResponseTimeValue > 0);
		//int actionsCount = (useFixedValues ? 1 : UncString.randomInt(1, MOBILE_REQUEST_URLS.length) + 1);
		int actionsCount = MOBILE_REQUEST_URLS.length;
		for(int i = actionsCount - 1; i >= 0; i--) {
			MobileActionItem action = new MobileActionItem();
			String url = MOBILE_REQUEST_URLS[(useFixedValues ? 0 : UncString.randomInt(MOBILE_REQUEST_URLS.length))];
			//action.setUrl(url); 此处做修改用于测试url及host匹配规则
			action.setUrl(MOBILE_REQUEST_URLS[i]);
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
		for(int i =0 ; i < 2 ; i++) {	
			int metrikeyIndex = UncString.randomInt(METRIC_VIEW_KEY.length);
			MetricKey metrickey = METRIC_VIEW_KEY[i];
			interactionViews.add(new GenericMetricItem(metrickey,
				    UncString.randomInt(1,5),
				    UncString.randomInt(50,300),
				    UncString.randomInt(10,100),
					UncString.randomInt(50,300),
					UncString.randomInt(50,300),
					UncString.randomInt(2500,90000)
				));
		}
		actionsData.setInteractionViews(interactionViews);

		//模拟上报interactionComponents中数据
		List<GenericMetricItem> interactionComponents = new ArrayList<GenericMetricItem>();
		for(int i =0 ; i < 6 ; i++) {	
			int metrikeyIndex = UncString.randomInt(METRIC_COMPONENT_KEY.length);
			MetricKey metrickey = METRIC_COMPONENT_KEY[i];
			interactionComponents.add(new GenericMetricItem(metrickey,
				    UncString.randomInt(1,5),
				    UncString.randomInt(50,300),
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
			int metrikeyIndex = UncString.randomInt(METRIC_CLASSIFIEDCOMPONENT_KEY.length);
			MetricKey metrickey = METRIC_CLASSIFIEDCOMPONENT_KEY[i];
			interactionClassifiedComponents.add(new GenericMetricItem(metrickey,
				    UncString.randomInt(1,5),
				    UncString.randomInt(50,300),
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
			int metrikeyIndex = UncString.randomInt(METRIC_GENERAL_KEY.length);
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
		int duration = UncString.randomInt(1,80000);
		traceItem.setDuration(duration);
		traceItem.setTimestamp((int) (System.currentTimeMillis()/1000));
		traceItem.setViewMetricName("MobileView/Controller/MyViewController");
		
		traceItem.setTrace("[1408931929867, [[0, 11], ["+duration/2+", 11]],[[0, 75], ["+duration/2+", 75]],[0, 1444, \"MobileView\\\\/Controller\\\\/NewsView#other\", [1, \"main\"], {}, [[0,"+duration/2+", \"UIImage#imageNamed:\", [1, \"Main thread\"], {}, []]]]]");
		mobileInterationItem.add(traceItem);
		mobileInterationtrace.setInteractionTraces(mobileInterationItem);
		mobileInterationtrace.setMobileAppId(UncString.randomInt(1,100));
		mobileInterationtrace.setMobileAppVersionId(UncString.randomInt(1,100));
		mobileInterationtrace.setOwnerId(UncString.randomInt(1,100));
		command.setInteractionTraceData(mobileInterationtrace);
		
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
