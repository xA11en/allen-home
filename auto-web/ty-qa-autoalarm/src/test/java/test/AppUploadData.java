package test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tingyun.alarm.entity.AlarmEvents;
import com.tingyun.alarm.entity.AlarmSQL;
import com.tingyun.alarm.entity.AppParmaters;
import com.tingyun.alarm.service.MobileAgentSimulatorNew;
import com.tingyun.alarm.utils.DBUtils;

public class AppUploadData  {

//	private String key;
//	private String thinkTime;
//	private String mode;
//	private String count;
//	private String period;
//	private String responseTime;
//	private String interactionTime;
//	private String viewLoadTime;
//	private String imageProcessTime;
//	private String dataStorageTime;
//	private String networkVisitTime;
//	private String jsonProcessTime;
//	private String httpErrorPercentage;
//	private String networkErrorPercentage;
//	private String deviceIndex;
	
	public static List<AppParmaters> mainUpload() {
		List<AppParmaters> parmaters = new ArrayList<AppParmaters>();
		//在这里添加参数
		parmaters.add(new AppParmaters(
				"196c21138a5b496397806b06e3ca86bf","6000","fixed","100","1","200","51","51","51","51","51","51","0.0","0.0","1"));
//		parmaters.add(new AppParmaters(
//				"46ee0d1a497741eca1375feafdec074e","6000","fixed","10","5","200","51","51","51","51","51","51","0.0","0.0","2"));
		
		return parmaters;
	}
	

	public static void main(String[] args) {
		try {
			Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());  
			startTimestamp = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(new Date()));
			System.out.println("开始时间："+startTimestamp);
			new MobileAgentSimulatorNew().main(new AppParmaters(
					"46ee0d1a497741eca1375feafdec074e","60000","fixed","100","4","200","51","51","51","51","51","51","0.0","0.0","1"));
			
			Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());  
			endTimestamp = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("上传结束时间："+endTimestamp);
//			
			Thread.sleep(1);
			
			System.out.println("******************************************开始建立数据连接查询结果*************************************");
			
			List<AlarmEvents> alarmEvents = 
					new QueryRunner().query
					(DBUtils.getConnection(), AlarmSQL.SELECT_EVENTS_SQL,
							new BeanListHandler<AlarmEvents>(AlarmEvents.class),3009,3009,1,1,startTimestamp,endTimestamp);
			if(alarmEvents.size() == 0){
				System.out.println("test fail ！ 没数据！！");
			}
			for (AlarmEvents alarm : alarmEvents) {
				if(alarm.getEvent_level() ==1 && alarm.getStatus() ==1){
					System.out.println("触发的是警告通知，已触发--------------------test pass !!"+"数据库条数为： "+alarmEvents.size());
				}else if(alarm.getEvent_level() ==2 && alarm.getStatus() ==1){
					System.out.println("触发的是严重通知，已触发--------------------test pass !!"+"数据库条数为： "+alarmEvents.size());
				}else{
					System.out.println("test fail !!"+" event_level = "+ alarm.getEvent_level()+"......."+" status = "+alarm.getStatus());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

