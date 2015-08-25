package com.tingyun.auto.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.utils.ExcelReader;



/**
* @author :chenjingli 
* @version ：2015-5-21 下午3:14:56 
* @decription: 自动生成page和step部分固定代码方便脚本写入  代码生成工具类
 */
public class CodeFactory{
	
	private static List<String> locationType;
	private static List<String> locationPath;
	private static List<String> elementName;
	private int count=0;
	
	public static final String className = "";//要生成的类名类名首字母要大写
	public static final String packagePath = "com.tingyun.auto.utils";//自定义包 首先保证step和page这个路径结构相同 
	
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
	private static final String EXCEL_PATH =  "E:\\test.xlsx";
	private static final String NAME =  "Test";
	public static StringBuffer createPage(String className){
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
		sb.append("private static Logger logger = LoggerFactory.getLogger("+className+".class);\r\n");
		sb.append("\r\n");
		sb.append("\tpublic "+className+"(DriverBrowser driverBrowser) {\r\n");
		sb.append("\t\tsuper(driverBrowser);\r\n");
		sb.append("\t}\r\n");
		ExcelReader eh = new ExcelReader(EXCEL_PATH,"Sheet1");
		List<List<String>> listString = eh.getListData();
		int count = listString.size();
			for (int j = 0; j < count; j++) {
				 List<String> list = new ArrayList<String>();
				 for (int k = 0; k < eh.getLastRowNumber(); k++) {
					 list.add(listString.get(j).get(k));
				}
				 sb.append("\t@FindBy("+list.get(0)+"="+list.get(1)+" )\r\n");
				 sb.append("\tprivate WebElement "+list.get(2)+";\r\n");
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
	private static void writeFile(String className) {
		String pageClassName = className+"Page";
		log.info("开始创建page,path====={},classsname======={}",filePathPage,pageClassName);
		File file= new File(filePathPage+pageClassName+name);
		try {
			createFile(file, pageClassName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void createFile(File file, String className)throws IOException{
		if (!file.exists()) {
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true);
			out.write(createPage(className).toString(). getBytes("utf-8"));
			sb.delete(0, sb.length());
			out.close();
			log.info("文件创建完成！路径：" +className+name);
		} else {
			file.delete();
			createFile(file, className);
			log.info("文件删除：" +className );
		}
	}
	
	
	public static void main(String[] args) {
		writeFile(NAME);
	}
}
