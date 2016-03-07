package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tingyun.alarm.MinorMain;
import com.tingyun.alarm.utils.HtmlMail;
import com.tingyun.test.TestJobExecutor;

public class AppUploadData  {
	public static void main(String[] args) throws IOException {
		System.out.println(new File(MinorMain.class.getClassLoader().getResource("alarmResult.txt").getPath()));
//		FileWriter fw = null; 
//		try { 
//			fw = new FileWriter( System.getProperty("user.dir")+"\\src\\main\\resources\\alarmResult.txt",true); // 第二个参数 true 表示写入方式是追加方式 
//			fw.write(new Date() + " : 哈哈" + "\r\n"); }
//		catch (Exception e) 
//		{ 
//			System.out.println("书写日志发生错误：" + e.toString()); 
//			}finally { 
//			try{ 
//			fw.close(); 
//				}
//			catch (IOException e){ // TODO 自动生成 catch 块 
//			e.printStackTrace();
//			}
//			} 
//		ExecutorService service = Executors.newFixedThreadPool(2); 
//		for (int i = 0; i <10; i++) {
//			 final int taskId = i;
//		service.execute(new Runnable() { 
//		public void run() {
//			System.out.println(taskId);
//		} 
//		});
//		}
//		service.shutdown();
//		while (true) {  
//			if (service.isTerminated()) {  
//				System.out.println("ok");  
//				break;  
//			}  
//		}  
	}
}
//		 try {  
//			  File f = new File("F://TEST.csv");   
//		        if (!f.exists()) {   
//		            f.createNewFile();   
//		        }else{
//		        	InputStreamReader read = new InputStreamReader(new FileInputStream(f),"gbk");   
//		            BufferedReader reader=new BufferedReader(read);   
//		            String line;   
//		            while ((line = reader.readLine()) == null) {   
//		                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"gbk");   
//		                BufferedWriter writer=new BufferedWriter(write);     
//		                // 添加新的数据行 
//		                writer.write("\"描述\"" + "," + "\"是否报警\"");
//		                writer.newLine();  
//		                writer.write("\"描述1\"" + "," + "\"是否报警2\"");
//		                
//		                writer.close();  
//		            }     
//		        }   
//		   
//		      } catch (FileNotFoundException e) {  
//		        // File对象的创建过程中的异常捕获 
//		        e.printStackTrace();  
//		      } catch (IOException e) {  
//		        // BufferedWriter在关闭对象捕捉异常 
//		        e.printStackTrace();  
//		      }  
//		}}