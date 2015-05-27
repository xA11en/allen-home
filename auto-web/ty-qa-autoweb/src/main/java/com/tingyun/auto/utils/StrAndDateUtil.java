package com.tingyun.auto.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author :chenjingli 
* @version ：2015-5-27 上午11:39:52 
* @decription:
 */
public class StrAndDateUtil {
	//取1天和两周的时间
	public static  String randowStringTime(String flag,int day){//开始和结束时间
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		if(flag.equals("start")){
			date.setDate(date.getDate()-day);
		return sd.format(date);
		}else if(flag.equals("end")){
		date.setDate(date.getDate()-day);
		return sd.format(date);
		}
		return null;
	}
}
