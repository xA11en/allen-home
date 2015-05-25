package com.tingyun.auto.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.framework.browser.DriverBrowser;

/**
* @author :chenjingli 
* @version ：2015-5-21 下午3:14:56 
* @decription: 自动生成page和step部分固定代码方便脚本写入
 */
public class CodeFactory {
	public static void main(String[] args) {
		try {
			writeFile("Test", 5);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Logger log = LoggerFactory.getLogger(CodeFactory.class); 
	
	//自定义生成路径
	public static final String filePath = System.getProperty("user.dir")+"/src/main/java/com/tingyun/auto/page/rpc/report/";
	
	public static final String name = ".java";
	//自定义包
	public static final String packagePath = "com.tingyun.auto.page.rpc.report";
	
	public static StringBuffer sb = new StringBuffer();
	/**
	* @author : chenjingli
	* @decription 是生成page页
	* @return
	* @param className 传一个像生成的class类名 标准格式 首字母大写
	* GlobalPage 这个可以自定义修改
	 */
	public static StringBuffer createPage(String className,int number){
		log.info("开始创建page页信息*****************************");
		sb.append("package "+packagePath+";\r\n");
		sb.append("\r\n");
		sb.append("import org.openqa.selenium.WebElement;\r\n");
		sb.append("import org.openqa.selenium.support.FindBy;\r\n");
		sb.append("import com.tingyun.auto.framework.browser.DriverBrowser;\r\n");
		sb.append("import com.tingyun.auto.page.GlobalPage;\r\n");
		sb.append("\r\n");
		sb.append("public class "+className+" extends GlobalPage {\r\n");
		sb.append("\tpublic "+className+"(DriverBrowser driverBrowser) {\r\n");
		sb.append("\t\tsuper(driverBrowser);\r\n");
		sb.append("\t}\r\n");
		for (int i = 0; i < number; i++) {
			sb.append("\t@FindBy(xpath= )\r\n");
			sb.append("\tprivate WebElement ;\r\n");
			sb.append("\r\n");
		}
		sb.append("\t}");
		log.info(className,"{}--文件创建结束*****************************");
		return sb;
	} 
	
	private static void writeFile(String className,int num) throws IOException {
		log.info("开始创建文件：" + filePath);

		File file = new File(filePath+className+name);
		if (!file.exists()) {
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true);
			out.write(createPage(className, num).toString(). getBytes("utf-8"));
			out.close();
			log.info("文件创建完成！路径：" + filePath+className+name);
		} else {
			file.delete();
			log.info("文件删除：" + filePath);
		}

	}
	

}
