package com.tingyun.auto.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
* @author :chenjingli 
* @version ：2015-5-21 下午3:14:56 
* @decription: 自动生成page和step部分固定代码方便脚本写入  代码生成工具类
 */
public class CodeFactory {
	public static void main(String[] args) {
		try {
			writeFile(className);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static final int count = 10;//@findby生成个数
	public static final String className = "";//要生成的类名类名首字母要大写
	public static final String packagePath = "com.tingyun.auto.page.rpc.report";//自定义包 首先保证step和page这个路径结构相同 
	
	//修改以上内容
	
	private static Logger log = LoggerFactory.getLogger(CodeFactory.class); 
	//自定义生成路径 page and step
	public static final String filePathPage = System.getProperty("user.dir")+"/src/main/java/"+packagePath.replace(".","/")+"/";
	public static final String name = ".java";
	
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
		sb.append("import static org.testng.Assert.*;\r\n");
		sb.append("import org.slf4j.Logger;\r\n");
		sb.append("import org.slf4j.LoggerFactory;\r\n");
		sb.append("import org.openqa.selenium.support.FindBy;\r\n");
		sb.append("import com.tingyun.auto.framework.browser.DriverBrowser;\r\n");
		sb.append("import com.tingyun.auto.common.GlobalPage;\r\n");
		sb.append("\r\n");
		sb.append("public class "+className+" extends GlobalPage {\r\n");
		sb.append("\r\n");
		sb.append("private static Logger logger = LoggerFactory.getLogger("+className+"Page.class);\r\n");
		sb.append("\r\n");
		sb.append("\tpublic "+className+"(DriverBrowser driverBrowser) {\r\n");
		sb.append("\t\tsuper(driverBrowser);\r\n");
		sb.append("\t}\r\n");
		for (int i = 0; i < count; i++) {
			sb.append("\t@FindBy(xpath= )\r\n");
			sb.append("\tprivate WebElement ;\r\n");
			sb.append("\r\n");
		}
		sb.append("\t}");
		log.info(className,"{}--文件创建结束*****************************");
		return sb;
		
	} 
	
	public static StringBuffer createStep(String className){
		log.info("开始创建step页信息*****************************");
		String stepPackage = packagePath.replace("page", "step");
		sb.append("package "+stepPackage+";\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("import static org.testng.Assert.*;\r\n");
		sb.append("import org.testng.TestNGException;\r\n");
		sb.append("import org.testng.annotations.AfterClass;\r\n");
		sb.append("import org.slf4j.Logger;\r\n");
		sb.append("import org.slf4j.LoggerFactory;\r\n");
		sb.append("import org.testng.annotations.BeforeClass;\r\n");
		sb.append("import org.testng.annotations.Test;\r\n");
		sb.append("import com.tingyun.auto.framework.browser.DriverBrowser;\r\n");
		sb.append("import com.tingyun.auto.step.GlobalStep;\r\n");
		sb.append("\r\n");
		sb.append("public class "+className+" extends GlobalStep {\r\n");
		sb.append("\r\n");
		sb.append("private static Logger logger = LoggerFactory.getLogger("+className+"Step.class);\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("\tpublic static DriverBrowser driverBrowser;\r\n");
		sb.append("\r\n");
		sb.append("\t@BeforeClass(description=)\r\n");
		sb.append("\t@Test(description=)\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("\t@AfterClass\r\n");
		sb.append("\tpublic void afterClass(){\r\n");
		sb.append("\t\tdriverBrowser.quit();\r\n");
		sb.append("\t}\r\n");
		sb.append("}");
		log.info(className,"{}--文件创建结束*****************************");
		return sb;
		
	} 
	private static void writeFile(String className) throws IOException {
		for (int i = 0; i < 2; i++) {
			File file = null;
			if(i==0){
				String pageClassName = className+"Page";
				log.info("开始创建page,path====={},classsname======={}",filePathPage,pageClassName);
				file= new File(filePathPage+pageClassName+name);
				createFile(file, pageClassName,i);
			}
			if(i==1){
				String stepPath = filePathPage.replace("page", "step");
				String stepClassName = className+"Step";
				log.info("开始创建step,path====={},classsname======={}",stepPath,stepClassName);
				file = new File(stepPath+stepClassName+name);
				createFile(file, stepClassName,i);
			}
		}

	}
	
	public static void createFile(File file, String className,int number)throws IOException{
		if (!file.exists()) {
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true);
			if(number==0){
				out.write(createPage(className,number).toString(). getBytes("utf-8"));
			}else if(number==1){
				out.write(createStep(className).toString(). getBytes("utf-8"));
			}
			sb.delete(0, sb.length());
			out.close();
			log.info("文件创建完成！路径：" +className+name);
		} else {
			file.delete();
			log.info("文件删除：" +className );
		}
	}
}
