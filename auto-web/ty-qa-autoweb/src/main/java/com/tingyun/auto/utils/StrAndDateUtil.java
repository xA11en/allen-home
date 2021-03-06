package com.tingyun.auto.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
* @author :chenjingli 
* @version ：2015-5-27 上午11:39:52 
* @decription:
 */
public class StrAndDateUtil {
	//取1天和两周的时间
	public static  String randowStringTime(String flag,int day){//开始和结束时间
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		if(flag.equals("start")){
			date.setDate(date.getDate()-day);
		return sd.format(date);
		}else if(flag.equals("end")){
		date.setDate(date.getDate()-day);
		return sd.format(date);
		}
		return null;
	}
	
    /** 
     * 随机指定范围内N个不重复的数 
     * 最简单最基本的方法 
     * @param min 指定范围最小值 
     * @param max 指定范围最大值 
     * @param n 随机数个数 
     */  
    public static String randowNumbers(int min, int max, int n){  
        if (n > (max - min + 1) || max < min) {  
               return null;  
           }
        StringBuilder sb = new StringBuilder();
        int[] result = new int[n];  
        int count = 0;  
        while(count < n) {  
            int num = (int) (Math.random() * (max - min)) + min;  
            boolean flag = true;  
            for (int j = 0; j < n; j++) {  
                if(num == result[j]){  
                    flag = false;  
                    break;  
                }  
            }  
            if(flag){  
                result[count] = num;  
                count++;  
            }  
        }  
        for (int i : result) {
			sb.append(i);
		}
        return sb.toString();  
    }  
		/**
		 * 
		* @author : chenjingli
		* @decription  判断字符串是否为null 
		* @return
		 */
    public static boolean isBlank(String str) {
        int length;
 
        if ((str == null) ||("".equals(str)) || ((length = str.length()) == 0)) {
             return true;
         }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
             }
         }
 
         return true;
     }
    /**
    * @author : chenjingli
    * @decription 判断字符串不为null
    * @return
     */
    public static boolean isNotBlank(String str) {
        int length;

        if ((str == null) ||("".equals(str)) || ((length = str.length()) == 0)) {
            return false;
       }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }	  
    /**
    * @author : chenjingli
    * @decription 获取文本中的数字
    * @return
     */
    public static String getTextNum(String textNum){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(textNum); 
		return m.replaceAll("").trim();
	}
    
    
    /**
    * @author : chenjingli
    * @decription  MD5加密
    * @return
     */
    /**
    *
    * @param plainText
    *            明文
    * @return 32位密文
    */
   public static String encryption(String plainText) {
       String re_md5 = new String();
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(plainText.getBytes());
           byte b[] = md.digest();

           int i;

           StringBuffer buf = new StringBuffer("");
           for (int offset = 0; offset < b.length; offset++) {
               i = b[offset];
               if (i < 0)
                   i += 256;
               if (i < 16)
                   buf.append("0");
               buf.append(Integer.toHexString(i));
           }

           re_md5 = buf.toString();

       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       return re_md5;
   }
	
    public static void main(String[] args) {
		System.out.println(StrAndDateUtil.getTextNum("订单号：201507150041"));
	}
	
}
