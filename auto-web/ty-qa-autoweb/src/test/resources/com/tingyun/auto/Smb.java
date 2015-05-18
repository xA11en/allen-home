package com.tingyun.auto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;


public class Smb {


	/**
	  * 上传文件到服务器
	 * @throws IOException 
	  */
	public static void upload(String remoteUrl,String localFilePath){  
        InputStream in = null;  
        OutputStream out = null;  
        try {  
            File localFile = new File(localFilePath);  
            String fileName = localFile.getName();  
            SmbFile remoteFile = new SmbFile(remoteUrl+"/"+fileName);  
            in = new BufferedInputStream(new FileInputStream(localFile));  
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
	            			  System.out.println(loclaFileJpg[j].getName()+"jpg");
	            			  upload("smb://administrator:NBS2o13@192.168.9.42/NBT_ShareDisk/htmlandpic/failScreenShot", 
	            					  "E:/workspace/ty-qa-autoweb/test-output/html/failScreenShot"+"/"+loclaFileJpg[j].getName());
						}
	            	}
	            	
	            	if(localFiles[i].getName().endsWith("css")){
	            		System.out.println(localFiles[i].getName()+"css");
	            		  upload("smb://administrator:NBS2o13@192.168.9.42/NBT_ShareDisk/htmlandpic", 
            					  "E:/workspace/ty-qa-autoweb/test-output/html"+"/"+localFiles[i].getName());
	            	}
	            	if(localFiles[i].getName().endsWith("js")){
	            		System.out.println(localFiles[i].getName()+"js");
	            		 upload("smb://administrator:NBS2o13@192.168.9.42/NBT_ShareDisk/htmlandpic", 
           					  "E:/workspace/ty-qa-autoweb/test-output/html"+"/"+localFiles[i].getName());
	            	}
	            	if(localFiles[i].getName().endsWith("html")){
	            		System.out.println(localFiles[i].getName()+"html");
	            		 upload("smb://administrator:NBS2o13@192.168.9.42/NBT_ShareDisk/htmlandpic", 
           					  "E:/workspace/ty-qa-autoweb/test-output/html"+"/"+localFiles[i].getName());
	            	}
	            }
	        
	            
	    }  
	public static void main(String[] args) throws IOException {
		//服務器地址 格式為 smb://电脑用户名:电脑密码@电脑IP地址/IP共享的文件夹
		//  String remoteUrl = "smb://administrator:NBS2o13@192.168.9.42/NBT_ShareDisk"; 
		  String localFile = "E:/workspace/ty-qa-autoweb/test-output/html";  //本地要上传的文件
//		  File file = new File(localFile);
//		  Smb smb = Smb.getInstance(remoteUrl);
//		  smb.uploadFile(file);// 上传文件 
		smbPut(localFile);
		System.out.println("success");
	}
}
