package com.tingyun.alarm.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tingyun.alarm.Main;


public class InitXml {
	
	public List<String> getXML(String type){
		File file = null;
		if(type.equals("APP")){
			 file = new File((this.getClass().getResource("/conf").getPath()));
			 File[] files = file.listFiles();
				if(files.length == 0){
					return null;
				}
				List<String> confs = new ArrayList<String>();
				for (int i = 0; i < files.length; i++) {
				confs.add("conf/"+files[i].getName());
				}
				return confs;
		}else if(type.equals("SERVER")){
			 file = new File((this.getClass().getResource("/confServer").getPath()));
			 File[] files = file.listFiles();
				if(files.length == 0){
					return null;
				}
				List<String> confs = new ArrayList<String>();
				for (int i = 0; i < files.length; i++) {
				confs.add("confServer/"+files[i].getName());
				}
				return confs;
		}
		return null;
	}
	
	public static void main(String[] args) {
//		InitXml i = new InitXml();
//		List<String> s = i.getXML();
//		for (String string : s) {
//			System.out.println(string);
//		}
//		 for (int j = 0; j < s.length; j++) {
//			System.out.println(s[j]);
//		}
	}
}
