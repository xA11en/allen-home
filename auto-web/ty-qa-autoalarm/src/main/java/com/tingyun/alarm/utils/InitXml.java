package com.tingyun.alarm.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InitXml {
	
	public List<String> getXML(){
		File file = new File((this.getClass().getResource("/conf").getPath()));
		File[] files = file.listFiles();
		if(files.length == 0){
			return null;
		}
		List<String> confs = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
		confs.add(files[i].getName());
		}
		return confs;
	}
	
	
	public static void main(String[] args) {
		InitXml i = new InitXml();
		List<String> s = i.getXML();
		for (String string : s) {
			System.out.println(string);
		}
//		 for (int j = 0; j < s.length; j++) {
//			System.out.println(s[j]);
//		}
	}
}
