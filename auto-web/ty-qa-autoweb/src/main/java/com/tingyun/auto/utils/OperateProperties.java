package com.tingyun.auto.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* @author :chenjingli 
* @version ：2015-5-12 上午11:05:16 
* @decription:  per文件操作读写
 */
public class OperateProperties {
	
	private final static Logger logger = LoggerFactory
			.getLogger(OperateProperties.class);
	private static Properties props = new Properties(); 
	 //属性文件的路径   
    private static String PROFILEPATH="src/main/resources/testdate.properties";   
	  /**  
	    * 根据主键key读取主键的值value  
	    * @param filePath 属性文件路径  
	    * @param key 键名  
	    */   
	    public static String readValue(String key) {   
	         try {   
	             InputStream in = new BufferedInputStream(new FileInputStream(   
	            		 PROFILEPATH));   
	             props.load(in);   
	             String value = props.getProperty(key);   
	             logger.info(key +"键的值是："+ value);   
	             return value;   
	         } catch (Exception e) {   
	             e.printStackTrace();   
	             return null;   
	         }   
	    }   
	      
	    /**  
	    * 更新（或插入）一对properties信息(主键及其键值)  
	    * 如果该主键已经存在，更新该主键的值；  
	    * 如果该主键不存在，则插件一对键值。  
	    * @param keyname 键名  
	    * @param keyvalue 键值  
	    */   
	    public static void writeProperties(String keyname,String keyvalue) {          
	        try {   
	        	 OutputStream fos = new FileOutputStream(PROFILEPATH);   
	             props.setProperty(keyname, keyvalue);   
	            // 以适合使用 load 方法加载到 Properties 表中的格式，   
	            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
	            props.store(fos, "write '" + keyname + "' value");   
	        } catch (IOException e) {   
	        	logger.info("属性文件更新错误");   
	        }   
	    }   
	  
	    /**  
	    * 更新properties文件的键值对  
	    * 如果该主键已经存在，更新该主键的值；  
	    * 如果该主键不存在，则插件一对键值。  
	    * @param keyname 键名  
	    * @param keyvalue 键值  
	    */   
	    public static void updateProperties(String keyname,String keyvalue) {   
	        try {   
	        	 props.load(new FileInputStream(PROFILEPATH));   
	             // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。   
	             // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。   
	             OutputStream fos = new FileOutputStream(PROFILEPATH);              
	             props.setProperty(keyname, keyvalue);   
	            // 以适合使用 load 方法加载到 Properties 表中的格式，   
	            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
	            props.store(fos, "Update '" + keyname + "' value");   
	        } catch (IOException e) {   
	            logger.info("属性文件更新错误");   
	        }   
	    }   
}
