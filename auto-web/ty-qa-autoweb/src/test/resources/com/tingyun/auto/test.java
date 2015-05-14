package com.tingyun.auto;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

public class test {
	public static void main(String[] args) {
//		//readValue("src/main/resources/testdate.properties", "demo");
//		ResourceBundle bundleSele = ResourceBundle.getBundle("selenium");
////		// System.setProperty("webdriver.firefox.bin", bundleSele.getString("browser"));
////
////			File pathToFirefoxBinary = new File(bundleSele.getString("browser"));
////			FirefoxBinary firefoxbin = new FirefoxBinary(pathToFirefoxBinary);
////			FirefoxProfile firefoxProfile = new FirefoxProfile();
//	        
//		System.setProperty("webdriver.firefox.bin", bundleSele.getString("selenium.browser.firefox"));
//	      RemoteWebDriver  webDriver = new FirefoxDriver();
//	      webDriver.get("http://www.baidu.com");
//	      webDriver.quit();
//		String os = System.getProperties().getProperty("os.name");
//		 
//
//		String arch = System.getProperties().getProperty("os.arch");
//		System.out.println(os + arch);
//		if(system.equalsIgnor	eCase("windows")){
//			String arch = System.getenv("PROCESSOR_ARCHITECTURE");
//			String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
//			if(arch.endsWith("64")|| wow64Arch != null && wow64Arch.endsWith("64")){
//			return "64";
//			}else{
//			return "32";
//			}
	}
	 public static String readValue(String filepath,String key) {   
    	 Properties props = new Properties();   
         try {   
             InputStream in = new BufferedInputStream(new FileInputStream(   
            		 filepath));   
             System.out.println(in);
             props.load(in);   
             String value = props.getProperty(key);   
             System.out.println(key +"键的值是："+ value);   
             return value;   
         } catch (Exception e) {   
             e.printStackTrace();   
             return null;   
         }   
    } 
}
