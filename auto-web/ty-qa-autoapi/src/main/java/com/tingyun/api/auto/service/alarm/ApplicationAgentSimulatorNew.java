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
import com.networkbench.newlens.datacollector.entity.avro.ActionTraceData;
import com.networkbench.newlens.datacollector.entity.avro.ActionTraceItem;
import com.networkbench.newlens.datacollector.entity.avro.ApdexItem;
import com.networkbench.newlens.datacollector.entity.avro.ErrorCounterItem;
import com.networkbench.newlens.datacollector.entity.avro.ErrorTraceData;
import com.networkbench.newlens.datacollector.entity.avro.ErrorTraceData.ErrorItem;
import com.networkbench.newlens.datacollector.entity.avro.GenericMetricItem;
import com.networkbench.newlens.datacollector.entity.avro.MetricKey;
import com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo;
import com.networkbench.newlens.datacollector.entity.avro.SqlTraceData;
import com.networkbench.newlens.datacollector.entity.avro.SqlTraceData.SqlTraceItem;
import com.networkbench.newlens.datacollector.entity.avro.legacy.PerfMetrics;
import com.networkbench.newlens.datacollector.mvc.command.InitAgentAppRequestCommand;
import com.networkbench.newlens.datacollector.mvc.command.UploadRequestCommand;
import com.networkbench.newlens.datacollector.serialize.JacksonSerializableObjectMapper;
import com.tingyun.api.auto.entity.alarm.ServerParmaters;

/**
 * @author BurningIce
 *
 */
public class ApplicationAgentSimulatorNew {
	private final static JacksonSerializableObjectMapper JSON_OBJECT_MAPPER = new JacksonSerializableObjectMapper();
	private final static Pattern PATTERN_INIT_MOBILE_APP_RESPONSE = Pattern.compile("\\{\"status\"\\:\"success\",\"result\"\\:\\{.*\"appSessionKey\"\\s*\\:\\s*\"([a-zA-Z0-9_]+)\".*}");
	private final static String USER_AGENT_HEADER_VALUE = "NBS Newlens Agent/1.0.0 (iOS 6.1.2)";
	private final static String DEFAULT_MOBILE_APP_TOKEN = "888-888-888";
	private final static int REQUEST_TIME_OUT = 60000;
	private final static String DATA_VERSION = "2.0";
	private final static MobileDeviceInfo[] MOBILE_DEVICES = new MobileDeviceInfo[4];
	private final static com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[] MOBILE_DEVS = new com.networkbench.newlens.datacollector.entity.avro.MobileDeviceInfo[6];
	//应用名称
	private final static String[] applicationNames = new String[]{"sever 警报测试"};
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
    
    //Web action或后台应用相关的性能数据。actions中仅包含正常访问的数据，错误数据（HTTP或服务器端异常的Action）不包含在actions中。
	private	final static MetricKey[] METRIC_KEY = new MetricKey[] {
/*		new MetricKey(0, 0, "WebAction/Servlet/MongodbFindS", null),
		new MetricKey(0, 0, "WebAction/Servlet/SaveForC3p0OracleS", null),
		new MetricKey(0, 0, "WebAction/Servlet/JspServlet", null),
		new MetricKey(0, 0, "WebAction/Servlet/RedisGetS", null),
		new MetricKey(0, 0, "WebAction/Servlet/JavaMemcachedAddS", null),
		new MetricKey(0, 0, "WebAction/JSP/fail.jsp", null),
		new MetricKey(0, 0, "WebAction/Servlet/QueryForC3p0OracleS", null),
		new MetricKey(0, 0, "WebAction/Servlet/RedisSetS", null),
		new MetricKey(0, 0, "WebAction/Filter/hell.java", null),
		new MetricKey(0, 0, "WebAction/Filter/hellJob.java", null),
		new MetricKey(0, 0, "WebAction/Filter/hellMBean.java", null),
		new MetricKey(0, 0, "WebAction/PHP/index.php", null),
		new MetricKey(0, 0, "WebAction/PHP/index2.php", null),
		new MetricKey(0, 0, "WebAction/YII/User.php", null),
		new MetricKey(0, 0, "WebAction/CI/Person.php", null),
		new MetricKey(0, 0, "BackgroundAction/Script/houtai.jsp", null),*/
		new MetricKey(0, 0, "WebAction/Servlet/SpyMemcacheAddS", null),
		new MetricKey(0, 0, "WebAction/Servlet/MongodbFindS", null),
		new MetricKey(0, 0, "WebAction/Servlet/SaveForC3p0PostgresS", null),
		new MetricKey(0, 0, "WebAction/Servlet/SaveForC3p0OracleS", null),
		new MetricKey(0, 0, "WebAction/Servlet/SaveForC3p0MysqlS", null),
		new MetricKey(0, 0, "WebAction/Servlet/SaveForDbcpMysqlS", null),
		new MetricKey(0, 0, "WebAction/Servlet/HttpClient3S", null),
		new MetricKey(0, 0, "WebAction/JSP/index.jsp", null),
		new MetricKey(0, 0, "WebAction/Servlet/RedisGetS", null),
		new MetricKey(0, 0, "WebAction/Servlet/JavaMemcachedAddS", null),
/*		new MetricKey(0, 0, "BackgroundAction/NULL/All", null),*/
		//new MetricKey(0, 0, "BackgroundAction/Job/java.util.concurrent.Executors$RunnableAdapter%2Fcall", null),
		
		new MetricKey(0, 0, "WebAction/Servlet/JavaMemcachedAllS", null),
		new MetricKey(0, 0, "WebAction/Servlet/JavaMongoDBAllS", null),
		new MetricKey(0, 0, "WebAction/Servlet/JavaDatabaseAllS", null),
		new MetricKey(0, 0, "WebAction/Servlet/JavaRedisAllS", null),
	};
	//Web action Apdex数据
	private	final static MetricKey[] APDEX_KEY = new MetricKey[] {
		new MetricKey(0, 0, "Apdex/Servlet/SpyMemcacheAddS", null),
		new MetricKey(0, 0, "Apdex/Servlet/MongodbFindS", null),
		new MetricKey(0, 0, "Apdex/Servlet/SaveForC3p0PostgresS", null),
		new MetricKey(0, 0, "Apdex/Servlet/SaveForC3p0OracleS", null),
		new MetricKey(0, 0, "Apdex/Servlet/SaveForC3p0MysqlS", null),
		new MetricKey(0, 0, "Apdex/Servlet/SaveForDbcpMysqlS", null),
		new MetricKey(0, 0, "Apdex/Servlet/HttpClient3S", null),
		new MetricKey(0, 0, "Apdex/JSP/index.jsp", null),
		new MetricKey(0, 0, "Apdex/Servlet/RedisGetS", null),
		new MetricKey(0, 0, "Apdex/Servlet/JavaMemcachedAddS", null),
		
		new MetricKey(0, 0, "Apdex/Servlet/JavaMemcachedAllS", null),
		new MetricKey(0, 0, "Apdex/Servlet/JavaMongoDBAllS", null),
		new MetricKey(0, 0, "Apdex/Servlet/JavaDatabaseAllS", null),
		new MetricKey(0, 0, "Apdex/Servlet/JavaRedisAllS", null)
		
	};
	//Web应用过程或后台应用break down性能数据
	private	final static MetricKey[] METRIC_COMPONENTS_KEY = new MetricKey[] {
/*		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.mongodb.MongodbFind/service", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0,  "Java/com.network.newlens.test.nosql.mongodb.MongodbFind/init", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0, "MongoDB/users/FIND", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle/service", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle/init", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Database/test_user/INSERT", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.C3p0DataSource/getConnection", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.mysql.SaveForC3p0Mysql/service", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.mysql.SaveForC3p0Mysql/init", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.C3p0DataSource/getConnection", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.web.JspServlet/init", "WebAction/Servlet/JspServlet"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/JspServlet"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.web.JspServlet/service", "WebAction/Servlet/JspServlet"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.other.HttpClient3/service", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "External/www.apache.org/CommonsHttp", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.other.HttpClient3/init", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "External/www.networkbench.com/CommonsHttp", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "Jsp/service/index.jsp", "WebAction/JSP/index.jsp"),
		new MetricKey(0, 0, "Filter/com.network.newlens.test.web.JspServlet/set.java", "WebAction/Filter/hell.java"),*/
	

		new MetricKey(0, 0, "Database MySQL/NULL/All", "WebAction/Servlet/JavaDatabaseAllS"),
		new MetricKey(0, 0, "Database Oracle/person/INSERT", "WebAction/Servlet/SaveForC3p0PostgresS"),
		new MetricKey(0, 0, "Database SQLServer/test_user/INSERT", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Database DB2/person/INSERT", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Database PostgreSQL/variables/SELECT", "WebAction/Servlet/SaveForDbcpMysqlS"),
		
		new MetricKey(0, 0, "Memcached/NULL/All", "WebAction/Servlet/JavaMemcachedAllS"),
		new MetricKey(0, 0, "MongoDB/NULL/All", "WebAction/Servlet/JavaMongoDBAllS"),
		new MetricKey(0, 0, "Database/NULL/All", "WebAction/Servlet/JavaDatabaseAllS"),
		new MetricKey(0, 0, "Redis/NULL/All", "WebAction/Servlet/JavaRedisAllS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.spymemcached.SpyMemcacheAdd/service", "WebAction/Servlet/SpyMemcacheAddS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.spymemcached.SpyMemcacheAdd/init", "WebAction/Servlet/SpyMemcacheAddS"),
		new MetricKey(0, 0, "Memcached/NULL/SET", "WebAction/Servlet/SpyMemcacheAddS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SpyMemcacheAddS"),
		new MetricKey(0, 0, "MongoDB/persion/FIND", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.mongodb.MongodbFind/init", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.mongodb.MongodbFind/service", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/MongodbFindS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.postgres.SaveForC3p0Postgres/service", "WebAction/Servlet/SaveForC3p0PostgresS"),
		new MetricKey(0, 0, "Database/person/INSERT", "WebAction/Servlet/SaveForC3p0PostgresS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.postgres.SaveForC3p0Postgres/init", "WebAction/Servlet/SaveForC3p0PostgresS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SaveForC3p0PostgresS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.C3p0DataSource/getConnection", "WebAction/Servlet/SaveForC3p0PostgresS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle/service", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle/init", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Database/test_user/INSERT", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.C3p0DataSource/getConnection", "WebAction/Servlet/SaveForC3p0OracleS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.sqlserver.SaveForC3p0Sql/service", "WebAction/Servlet/SaveForC3p0SqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.sqlserver.SaveForC3p0Sql/init", "WebAction/Servlet/SaveForC3p0SqlS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SaveForC3p0SqlS"),
		new MetricKey(0, 0, "Database/test_user/INSERT", "WebAction/Servlet/SaveForC3p0SqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.C3p0DataSource/getConnection", "WebAction/Servlet/SaveForC3p0SqlS"),
		new MetricKey(0, 0, "Database/person/INSERT", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.mysql.SaveForC3p0Mysql/service", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.mysql.SaveForC3p0Mysql/init", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.C3p0DataSource/getConnection", "WebAction/Servlet/SaveForC3p0MysqlS"),
		new MetricKey(0, 0, "Database/variables/SELECT", "WebAction/Servlet/SaveForDbcpMysqlS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/SaveForDbcpMysqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.DbcpDataSource/getConnection", "WebAction/Servlet/SaveForDbcpMysqlS"),
		new MetricKey(0, 0, "Database/collations/SELECT", "WebAction/Servlet/SaveForDbcpMysqlS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.mysql.SaveForDbcpMysql/service", "WebAction/Servlet/SaveForDbcpMysqlS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.mysql.SaveForDbcpMysql/init", "WebAction/Servlet/SaveForDbcpMysqlS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.other.HttpClient3/service", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "External/www.apache.org/CommonsHttp", "WebAction/Servlet/HttpClient3S"),
		
		new MetricKey(0, 0, "External/http:%2F%2Fwww.apche.org%2FUserService.getUserNameHttp/CommonsHttp", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "External/https:%2F%2Fwww.apche.org%2FUserService.getUserNameHttps/CommonsHttp", "WebAction/Servlet/HttpClient3S"),
	    new MetricKey(0, 0, "External/dubbo:%2F%2Fwww.apche.org%2FUserService.getUserNameDubbo/CommonsHttp", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "External/thrift:%2F%2F192.168.1.8:21110%2FUserService.getUserNameThrift/ThriftClient", "WebAction/Servlet/HttpClient3S"),
		
		new MetricKey(0, 0, "Java/com.network.newlens.test.other.HttpClient3/init", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "External/www.networkbench.com/CommonsHttp", "WebAction/Servlet/HttpClient3S"),
		new MetricKey(0, 0, "Jsp/service/index.jsp", "WebAction/JSP/index.jsp"),
		new MetricKey(0, 0, "Servlet/org.apache.jsp.index_jsp/service", "WebAction/JSP/index.jsp"),
		new MetricKey(0, 0, "Java/org.apache.jsp.index_jsp/init", "WebAction/JSP/index.jsp"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/JSP/index.jsp"),
		new MetricKey(0, 0, "Jsp/_jspService/index.jsp", "WebAction/JSP/index.jsp"),
		new MetricKey(0, 0, "Servlet/org.apache.jasper.servlet.JspServlet/service", "WebAction/JSP/index.jsp"),
		new MetricKey(0, 0, "Redis/NULL/GET", "WebAction/Servlet/RedisGetS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.redis.RedisGet/service", "WebAction/Servlet/RedisGetS"),
		new MetricKey(0, 0, "Redis/NULL/SET", "WebAction/Servlet/RedisGetS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/RedisGetS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.redis.RedisGet/init", "WebAction/Servlet/RedisGetS"),
		new MetricKey(0, 0, "Memcached/NULL/SET", "WebAction/Servlet/JavaMemcachedAddS"),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.javamemcached.JavaMemcachedAdd/service", "WebAction/Servlet/JavaMemcachedAddS"),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", "WebAction/Servlet/JavaMemcachedAddS"),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.javamemcached.JavaMemcachedAdd/init", "WebAction/Servlet/JavaMemcachedAddS"),
		//new MetricKey(0, 0, "Java/java.util.concurrent.Executors$RunnableAdapter/call", "BackgroundAction/Job/java.util.concurrent.Executors$RunnableAdapter%2Fcall"),
		
	};
	//其它性能数据，包括数据库、NoSQL、当前应用的CPU、内存、Deadlock线程、系统探针数据等。
	private	final static MetricKey[] METRIC_GENERAL_KEY = new MetricKey[] {
		//跨应用拓扑
		new MetricKey(0, 0, "ExternalTransaction/NULL/ufgRAnI%2Fugjscajvwmoiyt55ywdhNuR5|f3Akf44qv00=", null),
		new MetricKey(0, 0, "ExternalTransaction/https/ufgRAnI%2Fugjscajvwmoiyt55ywdhNuR5|f3Akf44qv00=", null),
		new MetricKey(0, 0, "ExternalTransaction/dubbo/ufgRAnI%2Fugjscajvwmoiyt55ywdhNuR5|f3Akf44qv00=", null),
		new MetricKey(0, 0, "ExternalTransaction/dubbo:sync/ufgRAnI%2Fugjscajvwmoiyt55ywdhNuR5|f3Akf44qv00=", null),
		new MetricKey(0, 0, "ExternalTransaction/https/wTlEGbqgL7s=|i6d0EL98w2A=", null),
		new MetricKey(0, 0, "ExternalTransaction/http/wTlEGbqgL7s=|\\/saXLqM3qGI=", null),
		
		/**
		new MetricKey(0, 0, "Database MySQL/NULL/All", null),
		new MetricKey(0, 0, "Database Oracle/person/INSERT", null),
		new MetricKey(0, 0, "Database SQLServer/test_user/INSERT", null),
		new MetricKey(0, 0, "Database DB2/person/INSERT", null),
		new MetricKey(0, 0, "Database PostgreSQL/variables/SELECT", null),
		*/
		
		new MetricKey(0, 0, "Memcached/NULL/All", null),
		new MetricKey(0, 0, "Database/NULL/All", null),
		new MetricKey(0, 0, "Database/NULL/AllWeb", null),
		new MetricKey(0, 0, "Database/NULL/AllBackground", null),
		new MetricKey(0, 0, "Database/NULL/SELECT", null),
		new MetricKey(0, 0, "Database/NULL/INSERT", null),
		new MetricKey(0, 0, "Database/NULL/UPDATE", null),
		new MetricKey(0, 0, "Database/NULL/DELETE", null),
		new MetricKey(0, 0, "Database/NULL/getConnection", null),
		new MetricKey(0, 0, "Database/collations/SELECT", null),
		new MetricKey(0, 0, "Database/variables/SELECT", null),
		new MetricKey(0, 0, "Database/test_user/INSERT", null),
		new MetricKey(0, 0, "Database/person/INSERT", null),
		new MetricKey(0, 0, "MongoDB/persion/FIND", null),
		new MetricKey(0, 0, "Memcached/NULL/SET", null),
		new MetricKey(0, 0, "WebFrontend/NULL/QueueTime", null),
		new MetricKey(0, 0, "Java/NULL/Other", null),
		new MetricKey(0, 0, "External/NULL/All", null),
		new MetricKey(0, 0, "External/NULL/AllWeb", null),
		new MetricKey(0, 0, "External/NULL/AllBackground", null),
		new MetricKey(0, 0, "Memcached/NULL/AllWeb", null),
		new MetricKey(0, 0, "Memcached/NULL/AllBackground", null),
		new MetricKey(0, 0, "MongoDB/NULL/All", null),
		new MetricKey(0, 0, "MongoDB/NULL/AllWeb", null),
		new MetricKey(0, 0, "ongoDB/NULL/AllBackground", null),
		new MetricKey(0, 0, "Redis/NULL/All", null),
		new MetricKey(0, 0, "Redis/NULL/AllWeb", null),
		new MetricKey(0, 0, "Redis/NULL/AllBackground", null),
		new MetricKey(0, 0, "Redis/NULL/SET", null),
		new MetricKey(0, 0, "Redis/NULL/GET", null),
		new MetricKey(0, 0, "Errors/Count/All", null),
		new MetricKey(0, 0, "rrors/Count/AllWeb", null),
		new MetricKey(0, 0, "Errors/Count/AllBackground", null), 
		
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.spymemcached.SpyMemcacheAdd/service", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.spymemcached.SpyMemcacheAdd/init", null),
		new MetricKey(0, 0, "RequestDispatcher/NULL/RequestDispatcher", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.mongodb.MongodbFind/init", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.mongodb.MongodbFind/service", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.mongodb.MongodbFind/service", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.postgres.SaveForC3p0Postgres/init", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.C3p0DataSource/getConnection", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle/service", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle/init", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.sqlserver.SaveForC3p0Sql/service", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.sqlserver.SaveForC3p0Sql/init", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.mysql.SaveForC3p0Mysql/service", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.mysql.SaveForC3p0Mysql/init", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.database.DbcpDataSource/getConnection", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.javamemcached.JavaMemcachedAdd/init", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.javamemcached.JavaMemcachedAdd/service", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.nosql.redis.RedisGet/init", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.nosql.redis.RedisGet/service", null),
		new MetricKey(0, 0, "Servlet/org.apache.jasper.servlet.JspServlet/service", null),
		new MetricKey(0, 0, "Jsp/_jspService/index.jsp", null),
		new MetricKey(0, 0, "Java/org.apache.jsp.index_jsp/init", null),
		new MetricKey(0, 0, "Servlet/org.apache.jsp.index_jsp/service", null),
		new MetricKey(0, 0, "Jsp/service/index.jsp", null),
		new MetricKey(0, 0, "External/www.networkbench.com/CommonsHttp", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.other.HttpClient3/init", null),
		new MetricKey(0, 0, "External/www.apache.org/CommonsHttp", null),
		
		new MetricKey(0, 0, "External/http:%2F%2Fwww.apche.org%2FUserService.getUserNameHttp/CommonsHttp", null),
		new MetricKey(0, 0, "External/https:%2F%2Fwww.apche.org%2FUserService.getUserNameHttps/CommonsHttp", null),
		new MetricKey(0, 0, "External/dubbo:%2F%2Fwww.apche.org%2FUserService.getUserNameDubbo/CommonsHttp", null),
		new MetricKey(0, 0, "External/thrift:%2F%2F192.168.1.8:21110%2FUserService.getUserNameThrift/ThriftClient", null),
		
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.other.HttpClient3/service", null),
		new MetricKey(0, 0, "Java/com.network.newlens.test.dbservice.mysql.SaveForDbcpMysql/init", null),
		new MetricKey(0, 0, "Servlet/com.network.newlens.test.dbservice.mysql.SaveForDbcpMysql/service", null),
		
		//cpu
		new MetricKey(0, 0, "CPU/NULL/UserTime", null),
		new MetricKey(0, 0, "CPU/NULL/UserUtilization", null),
		//Memory
		new MetricKey(0, 0, "Memory/NULL/PhysicalUsed", null),
		new MetricKey(0, 0, "Memory/JVM/Used", null),
		new MetricKey(0, 0, "Memory/Heap/Used", null),
		new MetricKey(0, 0, "Memory/Heap/Committed", null),
		new MetricKey(0, 0, "Memory/Heap/Max", null),
		new MetricKey(0, 0, "Memory/NonHeap/Used", null),
		new MetricKey(0, 0, "Memory/NonHeap/Committed", null),
		new MetricKey(0, 0, "Memory/NonHeap/Max", null),
		//MemoryPool
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Eden Space/Max", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Eden Space/Used", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Eden Space/Committed", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Survivor Space/Committed", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Survivor Space/Used", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Survivor Space/Max", null),
		new MetricKey(0, 0, "MemoryPool/NonHeap%2FCode Cache/Max", null),
		new MetricKey(0, 0, "MemoryPool/NonHeap%2FCode Cache/Used", null),
		new MetricKey(0, 0, "MemoryPool/NonHeap%2FCode Cache/Committed", null),
		new MetricKey(0, 0, "MemoryPool/NonHeap%2FPS Perm Gen/Max", null),
		new MetricKey(0, 0, "MemoryPool/NonHeap%2FPS Perm Gen/Committed", null),
		new MetricKey(0, 0, "MemoryPool/NonHeap%2FPS Perm Gen/Used", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Old Gen/Max", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Old Gen/Committed", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Old Gen/Used", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Tenured Gen/Max", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Tenured Gen/Committed", null),
		new MetricKey(0, 0, "MemoryPool/Heap%2FPS Tenured Gen/Used", null),
		//gc
		new MetricKey(0, 0, "GC/Copy", null),
		new MetricKey(0, 0, "GC/Copy/Count", null),
		new MetricKey(0, 0, "GC/Copy/Time", null),
		//classLoading
		new MetricKey(0, 0, "ClassLoading/NULL/LoadedClassCount", null),
		new MetricKey(0, 0, "ClassLoading/NULL/UnloadedClassCount", null),
		
		//ThreadPool
		new MetricKey(0, 0, "ThreadPool/http-8080/CurrentThreadsBusy", null),
		new MetricKey(0, 0, "ThreadPool/http-8080/MaxThreads", null),
		new MetricKey(0, 0, "ThreadPool/http-8080/CurrentThreadCount", null),
		new MetricKey(0, 0, "ThreadPool/http-8080/KeepAliveCount", null),
		new MetricKey(0, 0, "ThreadPool/jk-8009/CurrentThreadsBusy", null),
		new MetricKey(0, 0, "ThreadPool/jk-8009/MaxThreads", null),
		new MetricKey(0, 0, "ThreadPool/jk-8009/CurentThreadsCount", null),
		new MetricKey(0, 0, "ThreadPool/jk-8009/KeepAliveCount", null),

		//Thread
		new MetricKey(0, 0, "Thread/NULL/TotalStartedCount", null),
		new MetricKey(0, 0, "Thread/NULL/Count", null),
		new MetricKey(0, 0, "Thread/NULL/All", null),
		
		//Session
		new MetricKey(0, 0, "Session/docs/AverageAliveTime", null),
		new MetricKey(0, 0, "Session/docs/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/docs/RejectedSessions", null),
		new MetricKey(0, 0, "Session/docs/ActiveSessions", null),
		new MetricKey(0, 0, "Session/examples/RejectedSessions", null),
		new MetricKey(0, 0, "Session/examples/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/examples/ActiveSessions", null),
		new MetricKey(0, 0, "Session/examples/AverageAliveTime", null),
		new MetricKey(0, 0, "Session/http_error_code_test/ActiveSessions", null),
		new MetricKey(0, 0, "Session/http_error_code_test/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/http_error_code_test/RejectedSessions", null),
		new MetricKey(0, 0, "Session/http_error_code_test/AverageAliveTime", null),
		new MetricKey(0, 0, "Session/manager/ActiveSessions", null),
		new MetricKey(0, 0, "Session/manager/RejectedSessions", null),
		new MetricKey(0, 0, "Session/manager/AverageAliveTime", null),
		new MetricKey(0, 0, "Session/manager/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/ioexception_test/AverageAliveTime", null),
		new MetricKey(0, 0, "Session/ioexception_test/ActiveSessions", null),
		new MetricKey(0, 0, "Session/ioexception_test/RejectedSessions", null),
		new MetricKey(0, 0, "Session/ioexception_test/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/RejectedSessions", null),
		new MetricKey(0, 0, "Session/AverageAliveTime", null),
		new MetricKey(0, 0, "Session/ActiveSessions", null),
		new MetricKey(0, 0, "Session/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/host-manager/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/host-manager/RejectedSessions", null),
		new MetricKey(0, 0, "Session/host-manager/AverageAliveTime", null),
		new MetricKey(0, 0, "Session/host-manager/ActiveSessions", null),
		new MetricKey(0, 0, "Session/mysql_select/ExpiredSessions", null),
		new MetricKey(0, 0, "Session/mysql_select/RejectedSessions", null),
		new MetricKey(0, 0, "Session/mysql_select/ActiveSessions", null),
		new MetricKey(0, 0, "Session/mysql_select/AverageAliveTime", null),
/*		new MetricKey(0, 0, "System/Memory/Memory used", null),
		new MetricKey(0, 0, "System/Memory/Swap used", null),
		new MetricKey(0, 0, "System/NULL/Load", null),
		new MetricKey(0, 0, "System/CPU/SystemUtilization", null),
		new MetricKey(0, 0, "System/CPU/UserUtilization", null),
		new MetricKey(0, 0, "System/CPU/iowait", null),
		new MetricKey(0, 0, "System/CPU/Stolen", null),
		new MetricKey(0, 0, "System Network/All/SentPacketsPerSec", null),
		new MetricKey(0, 0, "System Network/All/SentErrorsPerSec", null),
		new MetricKey(0, 0, "System Network/All/SentBytesPerSec", null),
		new MetricKey(0, 0, "System Network/All/ReceivedPacketsPerSec", null),
		new MetricKey(0, 0, "System Network/All/ReceivedErrorsPerSec", null),
		new MetricKey(0, 0, "System Network/All/ReceivedBytesPerSec", null),
		new MetricKey(0, 0, "System Network/All/AllBytesPerSec", null),
		new MetricKey(0, 0, "System Network/All/AllErrorsPerSec", null),
		new MetricKey(0, 0, "System Disk/All/WriteBytesPerSec", null),
		new MetricKey(0, 0, "System Disk/All/WriteUtilization", null),
		new MetricKey(0, 0, "System Disk/All/WriteTime", null),
		new MetricKey(0, 0, "System Disk/All/Utilization", null),
		new MetricKey(0, 0, "System Disk/All/ReadsPerSec", null),
		new MetricKey(0, 0, "System Disk/All/ReadBytesPerSec", null),
		new MetricKey(0, 0, "System Disk/All/ReadUtilization", null),
		new MetricKey(0, 0, "System Disk/All/ReadTime", null),
		new MetricKey(0, 0, "System Disk/All/ReadWriteBytesPerSec", null)*/
	};
	
	private	final static MetricKey[] METRIC_ERROR_KEY = new MetricKey[] {
		new MetricKey(0, 0, "Errors/Count/All", null),
		new MetricKey(0, 0, "Errors/Count/WebAction%2FServlet%2FSaveForC3p0MysqlS", null),
		new MetricKey(0, 0, "Errors/Count/AllWeb", null),
	};
	
	private final static CharSequence[] ErrorMetricName = new CharSequence[]{
		"errorTraceData"	
	};
	
	private final static CharSequence[] ErrorMessage = new CharSequence[]{
		"java.lang.NullPointerException",
		"database error",
		"can not connect db"
	};
	
	private final static CharSequence[] ExceptionClassName = new CharSequence[]{
		"SaveForC3p0MysqlS",
		"index2.php",
		"index4.php"
	};
	
	private final static CharSequence[] Params = new CharSequence[]{
		"{\"params\":{\"threadName\":\"nbs.48\",\"httpStatus\":500},\"requestParams\":{},\"stacktrace\":[\"\\tcom.network.newlens.test.dbservice.mysql.SaveForC3p0Mysql._service(SaveForC3p0Mysql.java:40)\",\"\\tcom.network.newlens.test.web.AbstractTestServlet.service(AbstractTestServlet.java:29)\",\"\\tjavax.servlet.http.HttpServlet.service(HttpServlet.java:723)\",\"\\torg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\",\"\\torg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\",\"\\torg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\",\"\\torg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\",\"\\torg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\",\"\\torg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\",\"\\torg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\",\"\\torg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\",\"\\torg.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"\\torg.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"\\torg.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489)\",\"\\tjava.lang.Thread.run(Thread.java:744)\"]}",
		"{\"params\":{\"Status\":\"905\",\"referer\":\"http://www.networkbench.com/\",\"thread-id\":\"tid-5\"},\"requestParams\":{\"referer\":\"http://www.sina.com/\",\"user-agent\":\"ie9.0\"},\"stacktrace\":[\"WebAction/PHP/info4.php\",\"\"]}"
	};
	
	private final static CharSequence[] RequestUrl = new CharSequence[]{
		"WebAction/Servlet/SaveForC3p0MysqlS",
		"WebAction/php/index2.php",
		"WebAction/php/index4.php"
	};
	
	private final static CharSequence[] traceMetircName = new CharSequence[]{
		"actionTraceData"
	};
	
	private final static CharSequence[] TRACE = new CharSequence[]{
		//"[1394609506183,{},{\"httpStatus\":200,\"threadName\":\"nbs.48\"},[0,831,\"ROOT\",null,1,\"org.apache.catalina.connector.CoyoteAdapter\",\"service\",{\"stacktrace\":[\"org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:337)\",\"org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489)\",\"java.lang.Thread.run(Thread.java:744)\"]},[[0,831,\"RequestDispatcher\\/NULL\\/RequestDispatcher\\/org.apache.catalina.connector.CoyoteAdapter\\/service\",null,1,\"org.apache.catalina.connector.CoyoteAdapter\",\"service\",{\"stacktrace\":[\"org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:337)\",\"org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489)\",\"java.lang.Thread.run(Thread.java:744)\"]},[[3,738,\"Java\\/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle\\/init\",\"\",1,\"javax.servlet.GenericServlet\",\"init\",{\"partialtrace\":[\"javax.servlet.GenericServlet.init(GenericServlet.java:213)\",\"org.apache.catalina.core.StandardWrapper.loadServlet(StandardWrapper.java:1213)\",\"org.apache.catalina.core.StandardWrapper.allocate(StandardWrapper.java:827)\",\"org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:129)\",\"org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\",\"org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\",\"org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\",\"org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\"]},[[3,645,\"Java\\/com.network.newlens.test.database.C3p0DataSource\\/getConnection\",\"\",1,\"com.network.newlens.test.database.C3p0DataSource\",\"getConnection\",{\"partialtrace\":[\"com.network.newlens.test.database.C3p0DataSource.getConnection(C3p0DataSource.java:97)\",\"com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle.init(SaveForC3p0Oracle.java:22)\"]},[]]]],[738,830,\"Servlet\\/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle\\/service\",\"\",1,\"javax.servlet.http.HttpServlet\",\"service\",{\"partialtrace\":[\"javax.servlet.http.HttpServlet.service(HttpServlet.java:724)\",\"org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\",\"org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\",\"org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\",\"org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\",\"org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\",\"org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\",\"org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\"]},[[740,828,\"Database\\/test_user\\/INSERT\",\"Database\\/NULL\\/INSERT\",1,\"com.mchange.v2.c3p0.impl.NewProxyStatement\",\"execute\",{\"sql\":\"insert into test_user values (51, '500', 'haha')\",\"partialtrace\":[\"com.mchange.v2.c3p0.impl.NewProxyStatement.execute(NewProxyStatement.java:1006)\",\"com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle._service(SaveForC3p0Oracle.java:34)\",\"com.network.newlens.test.web.AbstractTestServlet.service(AbstractTestServlet.java:29)\"]},[]]]]]]]]]"
		"[1394609506183,{},{\"httpStatus\":200,\"threadName\":\"nbs.48\"},[0,831,\"ROOT\",null,1,\"org.apache.catalina.connector.CoyoteAdapter\",\"service\",{\"stacktrace\":[\"org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:337)\",\"org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489)\",\"java.lang.Thread.run(Thread.java:744)\"],\"txId\":\"2015070211180005\",\"txData\":{\"id\":\"ufgRAnI/ugjscajvwmoiyt55ywdhNuR5|f3Akf44qv00=\",\"action\":\"WebAction/Servlet/SaveForC3p0MysqlS\",\"trId\":\"201507061445000000090001\",\"time\": {\"duration\": 1,\"qu\": 2,\"db\": 3,\"ex\": 4,\"rds\": 5,\"mc\": 6,\"mon\": 7,\"code\": 8}}},[[0,831,\"RequestDispatcher\\/NULL\\/RequestDispatcher\\/org.apache.catalina.connector.CoyoteAdapter\\/service\",null,1,\"org.apache.catalina.connector.CoyoteAdapter\",\"service\",{\"stacktrace\":[\"org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:337)\",\"org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489)\",\"java.lang.Thread.run(Thread.java:744)\"]},[[3,738,\"Java\\/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle\\/init\",\"\",1,\"javax.servlet.GenericServlet\",\"init\",{\"partialtrace\":[\"javax.servlet.GenericServlet.init(GenericServlet.java:213)\",\"org.apache.catalina.core.StandardWrapper.loadServlet(StandardWrapper.java:1213)\",\"org.apache.catalina.core.StandardWrapper.allocate(StandardWrapper.java:827)\",\"org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:129)\",\"org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\",\"org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\",\"org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\",\"org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\"]},[[3,645,\"Java\\/com.network.newlens.test.database.C3p0DataSource\\/getConnection\",\"\",1,\"com.network.newlens.test.database.C3p0DataSource\",\"getConnection\",{\"partialtrace\":[\"com.network.newlens.test.database.C3p0DataSource.getConnection(C3p0DataSource.java:97)\",\"com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle.init(SaveForC3p0Oracle.java:22)\"]},[]]]],[738,830,\"Servlet\\/com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle\\/service\",\"\",1,\"javax.servlet.http.HttpServlet\",\"service\",{\"partialtrace\":[\"javax.servlet.http.HttpServlet.service(HttpServlet.java:724)\",\"org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\",\"org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\",\"org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\",\"org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\",\"org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\",\"org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\",\"org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\"]},[[740,828,\"Database\\/test_user\\/INSERT\",\"Database\\/NULL\\/INSERT\",1,\"com.mchange.v2.c3p0.impl.NewProxyStatement\",\"execute\",{\"sql\":\"insert into test_user values (51, '500', 'haha')\",\"partialtrace\":[\"com.mchange.v2.c3p0.impl.NewProxyStatement.execute(NewProxyStatement.java:1006)\",\"com.network.newlens.test.dbservice.oracle.SaveForC3p0Oracle._service(SaveForC3p0Oracle.java:34)\",\"com.network.newlens.test.web.AbstractTestServlet.service(AbstractTestServlet.java:29)\"]},[]]]]]]]]]"
	};
	
	private final static CharSequence[] sqlMetircName = new CharSequence[]{
		"Database/test_user/SELECT"
	};
	
	private final static CharSequence[] sqlsql = new CharSequence[]{
		"select * from test_user where id=21"
	};
	
	private final static CharSequence[] sqlparams = new CharSequence[]{
		"{\"stacktrace\":[\"com.mchange.v2.c3p0.impl.NewProxyStatement.execute(NewProxyStatement.java:1006)\",\"com.network.newlens.test.dbservice.oracle.QueryForC3p0Oracle._service(QueryForC3p0Oracle.java:34)\",\"com.network.newlens.test.web.AbstractTestServlet.service(AbstractTestServlet.java:29)\",\"javax.servlet.http.HttpServlet.service(HttpServlet.java:723)\",\"org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\",\"org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\",\"org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\",\"org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\",\"org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\",\"org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\",\"org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\",\"org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\",\"org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861)\",\"org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606)\",\"org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489)\",\"java.lang.Thread.run(Thread.java:744)\"],\"explain_plan\":[\"Unable to run explain plans for Oracle databases\"]}"
	};
	
	
	private final static int[] ERROR_CODES = new int[] {
		900, 901, 902, 903, 904
	};

	
	/**
	 * @param args
	 */
	public static void main(String[] args,ServerParmaters parmaters) {
		String serverUrl = (args.length == 0 ? "http://localhost" : args[0]);
		if(serverUrl.indexOf("://") == -1) {
			serverUrl = "http://" + serverUrl;
		}
		
		String appToken = (args.length > 1 ? args[1] : DEFAULT_MOBILE_APP_TOKEN);
		int thinkTime = (UncString.toInt(System.getProperty("thinkTime"), 0));
		int count = (UncString.toInt(System.getProperty("count"), Integer.MAX_VALUE));
		int period = (UncString.toInt(parmaters.getPeriod(), 0));
		String mode = parmaters.getMode();
		int responseTime = UncString.toInt(parmaters.getResponseTime(), 0);
		double httpErrorPercentage = (UncString.toDouble(System.getProperty("httpErrorPercentage"), 0.0));
		//double networkErrorPercentage = (UncString.toDouble(parmaters.getNetworkErrorPercentage(), 0.0));
		
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
				System.out.println("running in " + (withIdle ? "idle" : "normal") + " mode with server: " + serverUrl + ", appToken: " + appToken + ", count: " + count);
				simulate(protocol, host, port, count, thinkTime, appToken, withIdle);
				System.out.println("finished, exit!");
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static Map<String, Integer> DEVICE_LAST_LAUNCH_TIME = new HashMap<String, Integer>();
	
	private static void simulate(String protocol, String host, int port, int count, int thinkTime, String appToken, boolean withIdle) throws Exception {
		String response = simulateInitAgentApp(protocol, host, port, appToken);
		if(response != null) {
			Matcher matcher = PATTERN_INIT_MOBILE_APP_RESPONSE.matcher(response);
			//System.out.print(response);
			if(matcher.matches()) {
				String appsessionkey = matcher.group(1);
				String deviceId = String.valueOf((int)(System.currentTimeMillis()));
				System.out.println("upload data using appsessionkey=" + appsessionkey +  "...");
				DEVICE_LAST_LAUNCH_TIME.put(deviceId, Integer.valueOf((int)(System.currentTimeMillis()/ 1000L)));
				
				for(int i = 0; i < count; ++i) {
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
						
						String resp = simulateUploadAgentData(protocol, host, port, appToken, appsessionkey, isLastRequest);
						System.out.println("uploaded " + (i + 1) + ", response: " + resp);
						
						if(isLastRequest) {
							boolean isCrash = (UncString.randomInt(3) == 1);
							int sleepTime = (withIdle ? UncString.randomInt(5000, 120000) : 1000);
							System.out.println("disconnect device: " + deviceId + ", active time: " + activeTime);
							System.out.println("sleep " + sleepTime + "ms after disconnect ...");
							Thread.sleep(sleepTime);
							System.out.println("reconnect ...");
							// reconnect
							response = simulateInitAgentApp(protocol, host, port, appToken);
							
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
					
					Thread.sleep(thinkTime);
				}
			} else {
				System.out.println("token not found in response: " + response);
			}
		}
	}
	
	
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

	private static String simulateInitAgentApp(String protocol, String host, int port, String appToken) throws Exception {
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
		command.setHost("xucong");
		command.setLanguage("java");
		command.setPid(4790);
		command.setPort(8080);
		
		String text = jsonize(command);
		String response = post(protocol, host, port, "/initAgentApp?licenseKey="+appToken+"&version=" + DATA_VERSION, text, appToken);
		System.out.println("initMobileAgentApp finished: " + response);
		return response;
	}
	
	
	
	private static String simulateUploadAgentData(String protocol, String host, int port, String appToken, String token, boolean isLastRequest) throws Exception {
		UploadRequestCommand command = createUploadMessage(isLastRequest, 0, false, false);
		String text = jsonize(command);
		System.out.println(text);
		return post(protocol, host, port, "/upload?licenseKey="+appToken+"&version=" + DATA_VERSION + "&appSessionKey=" + token, text, appToken);
	}

	private static UploadRequestCommand createUploadMessage(boolean isLastRequest, int fixedResponseTimeValue, boolean httpError, boolean networkError) throws Exception {
		
		UploadRequestCommand command = new UploadRequestCommand();
		
		PerfMetrics perfMetrics = new PerfMetrics();
		ErrorTraceData errorTraceData = new ErrorTraceData();
		//跨应用追踪
		ActionTraceData actionTraceData = new ActionTraceData();
		SqlTraceData sqlTraceData = new SqlTraceData();
		List<GenericMetricItem> actions = new ArrayList<GenericMetricItem>();
		List<ApdexItem> apdex = new ArrayList<ApdexItem>();
		//跨应用追踪
		List<GenericMetricItem> components = new ArrayList<GenericMetricItem>();
		//跨应用追踪
		List<GenericMetricItem> general = new ArrayList<GenericMetricItem>();
		List<ErrorCounterItem> errors = new ArrayList<ErrorCounterItem>();
		
		
		//actions 数据模拟
		for(int i = METRIC_KEY.length - 1; i >= 0; i--) {
			GenericMetricItem genericmetricitem = new GenericMetricItem();
			//com.networkbench.newlens.datacollector.entity.avro.GenericMetricItem.MetricKey metrickey = METRIC_KEY[UncString.randomInt(METRIC_KEY.length)];
			com.networkbench.newlens.datacollector.entity.avro.MetricKey metrickey = METRIC_KEY[i];
			genericmetricitem.setMetricKey(metrickey);
			int count=UncString.randomInt(50,500);
			genericmetricitem.setCount(count);
			genericmetricitem.setSum(UncString.randomInt(10000,1000000));
			genericmetricitem.setSumEx(UncString.randomInt(10000,1000000));
			genericmetricitem.setSumSquare(UncString.randomInt(10000,1000000));
			genericmetricitem.setMax(UncString.randomInt(20,20000));
			genericmetricitem.setMin(UncString.randomInt(10,10000));
			actions.add(genericmetricitem);
			
			GenericMetricItem genericmetricitem1 = new GenericMetricItem();
			com.networkbench.newlens.datacollector.entity.avro.MetricKey apdexkey = APDEX_KEY[i];
			genericmetricitem1.setMetricKey(apdexkey);
			ApdexItem apdexitem = new ApdexItem();
			apdexitem.setMetricKey(apdexkey);
			apdexitem.setFrustratedCount((int) Math.round(count*0.2));
			apdexitem.setApdexT((int) Math.round(count*0.1));
			apdexitem.setSatisfiedCount((int) Math.round(count*0.4));
			apdexitem.setToleratingCount((int) Math.round(count*0.3));
			apdex.add(apdexitem);
		}
			
		/*for(int i = APDEX_KEY.length - 1; i >= 0; i--) {
			GenericMetricItem genericmetricitem = new GenericMetricItem();
			//com.networkbench.newlens.datacollector.entity.avro.GenericMetricItem.MetricKey metrickey = METRIC_KEY[UncString.randomInt(METRIC_KEY.length)];
			com.networkbench.newlens.datacollector.entity.avro.GenericMetricItem.MetricKey apdexkey = APDEX_KEY[i];
			genericmetricitem.setMetricKey(apdexkey);
			ApdexItem apdexitem = new ApdexItem();
			apdexitem.setMetricKey(apdexkey);
			apdexitem.setFrustratedCount((int) Math.round(count*0.2));
			apdexitem.setApdexT((int) Math.round(count*0.1));
			apdexitem.setSatisfiedCount((int) Math.round(count*0.4));
			apdexitem.setToleratingCount((int) Math.round(count*0.3));
			apdex.add(apdexitem);
			}*/
		
		
		for(int i = METRIC_COMPONENTS_KEY.length - 1; i >= 0; i--) {
			GenericMetricItem comperfmetricitem = new GenericMetricItem();
			//MetricKey metrickey =METRIC_COMPONENTS_KEY[UncString.randomInt(METRIC_COMPONENTS_KEY.length)];
			MetricKey metrickey =METRIC_COMPONENTS_KEY[i];
			comperfmetricitem.setMetricKey(metrickey);
			comperfmetricitem.setCount(UncString.randomInt(1,500));
			comperfmetricitem.setSum(UncString.randomInt(200,5000));
			comperfmetricitem.setSumEx(UncString.randomInt(500,50000));
			comperfmetricitem.setSumSquare(UncString.randomInt(500,500000));
			comperfmetricitem.setMax(UncString.randomInt(1,500));
			comperfmetricitem.setMin(UncString.randomInt(1,500));
			components.add(comperfmetricitem);
		}
		
		int general_count=UncString.randomInt(1,500);
		for(int i = METRIC_GENERAL_KEY.length - 1; i >= 0; i--) {
			GenericMetricItem comperfmetricitem = new GenericMetricItem();
			//MetricKey metrickey =METRIC_GENERAL_KEY[UncString.randomInt(METRIC_GENERAL_KEY.length)];
			MetricKey metrickey =METRIC_GENERAL_KEY[i];
			comperfmetricitem.setMetricKey(metrickey);
			comperfmetricitem.setCount(general_count);
			comperfmetricitem.setSum(UncString.randomInt(200,5000));
			comperfmetricitem.setSumEx(UncString.randomInt(500,50000));
			comperfmetricitem.setSumSquare(UncString.randomInt(500,500000));
			comperfmetricitem.setMax(UncString.randomInt(1,500));
			comperfmetricitem.setMin(UncString.randomInt(1,500));
			general.add(comperfmetricitem);
		}
		
		for(int i = METRIC_ERROR_KEY.length - 1; i >= 0; i--) {
			ErrorCounterItem errorCount = new ErrorCounterItem();
			//MetricKey metrickey =METRIC_ERROR_KEY[UncString.randomInt(METRIC_ERROR_KEY.length)];
			MetricKey metrickey =METRIC_ERROR_KEY[i];
			errorCount.setMetricKey(metrickey);
			errorCount.setErrorCount(UncString.randomInt(1,50));
			errors.add(errorCount);
		}
		
		perfMetrics.setActions(actions);
		perfMetrics.setApdex(apdex);
		perfMetrics.setComponents(components);
		perfMetrics.setErrors(errors);
		perfMetrics.setGeneral(general);
		perfMetrics.setInterval(60);
		perfMetrics.setTimeTo((int) (System.currentTimeMillis() / 1000L));
		perfMetrics.setTimeFrom((int) ((System.currentTimeMillis() / 1000L)-60));
		perfMetrics.setOwnerId(1);
		perfMetrics.setServerId(2);
		perfMetrics.setApplicationId(new int[]{1,2,3});
		perfMetrics.setApplicationInstanceId(new int[]{1,2,3});
		
		
		
		// errorTraceData 数据上传
		List<ErrorItem> errorlist = new ArrayList<ErrorItem>();
		ErrorItem erroritem = new ErrorItem();
		erroritem.setActionMetricName((String) ErrorMetricName[UncString.randomInt(ErrorMetricName.length)]);
		erroritem.setErrorMessage((String) ErrorMessage[UncString.randomInt(ErrorMessage.length)]);
		erroritem.setExceptionClassName((String) ExceptionClassName[UncString.randomInt(ExceptionClassName.length)]);
		erroritem.setParams((String) Params[UncString.randomInt(Params.length)]);
		erroritem.setRequestUrl((String) RequestUrl[UncString.randomInt(RequestUrl.length)]);
		erroritem.setErrorCount(UncString.randomInt(1,10));
		erroritem.setTimestamp((int) (System.currentTimeMillis()/1000));
		erroritem.setHttpStatus(0);
		errorlist.add(erroritem);
		errorTraceData.setErrors(errorlist);
		
		// actionTraceData 数据上传
		List<ActionTraceItem> actiontracelist = new ArrayList<ActionTraceItem>();
		ActionTraceItem actiontrace = new ActionTraceItem();
		actiontrace.setActionMetricName((String) traceMetircName[UncString.randomInt(traceMetircName.length)]);
		actiontrace.setRequestUri((String) RequestUrl[UncString.randomInt(RequestUrl.length)]);
		actiontrace.setTrace((String) TRACE[UncString.randomInt(TRACE.length)]);
		actiontrace.setTimestamp((int) (System.currentTimeMillis()/1000));
		actiontrace.setDuration(UncString.randomInt(10000));
		actiontrace.setTransactionId("b92896d31a4ed076");//Action trace的transactionId，参见4.6 跨应用追踪之X-Tingyun-Tx-Id。当发起跨应用追踪时，由跨应用追踪的发起方生成txId，并传输至后端，后端的应用若产生Action trace，则直接复用前端传过来的txId。若发起方未启用跨应用追踪，则该字段置空。
		actiontrace.setTraceGuid("2015070211560000008");//Action trace的GUID，由探针生成，若被调用方未触发Action trace，则不返回该字段，或置空。
		actiontracelist.add(actiontrace);
		actionTraceData.setActionTraces(actiontracelist);
		
		// sqlTraceData 数据上传
		List<SqlTraceItem> sqltracelist = new ArrayList<SqlTraceItem>();
		SqlTraceItem sqltraceitem = new SqlTraceItem();
		sqltraceitem.setActionMetricName("sqlTraceData");
		sqltraceitem.setMetricName((String) sqlMetircName[UncString.randomInt(sqlMetircName.length)]);
		sqltraceitem.setParams((String) sqlparams[UncString.randomInt(sqlparams.length)]);
		sqltraceitem.setRequestUri((String) RequestUrl[UncString.randomInt(RequestUrl.length)]);
		sqltraceitem.setSql((String) sqlsql[UncString.randomInt(sqlsql.length)]);
		sqltraceitem.setCallCount(UncString.randomInt(1,10));
		sqltraceitem.setSum(UncString.randomInt(1,100));
		sqltraceitem.setTimestamp((int) (System.currentTimeMillis()/1000));
		sqltraceitem.setMax(UncString.randomInt(1,10));
		sqltraceitem.setMin(UncString.randomInt(1,10));
		sqltracelist.add(sqltraceitem);
		sqlTraceData.setSqlTraces(sqltracelist);
		
		command.setPerfMetrics((com.networkbench.newlens.datacollector.entity.avro.PerfMetrics) perfMetrics);
		command.setActionTraceData(actionTraceData);
		command.setErrorTraceData(errorTraceData);
		command.setSqlTraceData(sqlTraceData);
		
		return command;
	}
	
	private static String jsonize(Object obj) throws Exception {
		return JSON_OBJECT_MAPPER.writeValueAsString(obj);
	}
	
	private static String post(String protocol, String host, int port, String uri, String content, String appToken) throws Exception {
		HttpURLConnection conn = createConnection(protocol, host, port, uri, REQUEST_TIME_OUT, appToken);
		OutputStream os = conn.getOutputStream();
		os.write(gzip(content, "UTF-8"));
		System.out.println("content:"+content);
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
