package com.tingyun.auto.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现FTP 客户端的各种操作
 *
 *
 * @author
 * @version 2008-06-10 Ftp.java
 *
 */
public class Smb {

	/**
	 * slf4j logback
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(Smb.class);
	private static String SMBFAILPATH = ShareSmbConfig.getSmbFailPicPath();
	private static String SMBPATH = ShareSmbConfig.getSmbPath();
	private static String LOCALFAILPATH = ShareSmbConfig.getLocalFailPicPath();
	private static String LOCALPATH = ShareSmbConfig.getLocalPath();
	private static String path = System.getProperty("user.dir");

	/**
	  * 上传文件到共享服务器
	 * @throws IOException 
	  */
	public static void upload(String remoteUrl,String localFilePath){  
        InputStream in = null;  
        OutputStream out = null;  
        try { 
            File localFile = new File(localFilePath);  
            String fileName = localFile.getName();  
            SmbFile remoteFile = new SmbFile(remoteUrl+"/"+fileName);  
            in = new BufferedInputStream(new FileInputStream(path+localFile));  
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));  
            byte []buffer = new byte[1024];  
            while((in.read(buffer)) != -1){  
                out.write(buffer);  
                buffer = new byte[1024];  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                out.close();  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	 public static void smbPut(String localFilePath){  
	        InputStream in = null;  
	        OutputStream out = null;
	            File localFile = new File(localFilePath);
	            File[] localFiles = localFile.listFiles();
	            for (int i = 0; i < localFiles.length; i++) {
	            	if(localFiles[i].getName().contains("failScreenShot")){
	            		  File[] loclaFileJpg = localFiles[i].listFiles();
	            		  for (int j = 0; j < loclaFileJpg.length; j++) {
	            			  upload(SMBFAILPATH, 
	            					  LOCALFAILPATH+"/"+loclaFileJpg[j].getName());
						}
	            	}
	            	
	            	if(localFiles[i].getName().endsWith("css")){
	            		  upload(SMBPATH, 
            					  LOCALPATH+"/"+localFiles[i].getName());
	            	}
	            	if(localFiles[i].getName().endsWith("js")){
	            		 upload(SMBPATH, 
	            				 LOCALPATH+"/"+localFiles[i].getName());
	            	}
	            	if(localFiles[i].getName().endsWith("html")){
	            		 upload(SMBPATH, 
	            				 LOCALPATH+"/"+localFiles[i].getName());
	            	}
	            }
	        
	            
	    }  
	 
//	 public static void main(String[] args) {
//		 String str = System.getProperty("user.dir");
//		 com.tingyun.auto.Smb.smbPut(str+LOCALPATH);
//	}

}