package com.tingyun.auto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class FtpUtilTest {
	public static void main(String[] args) {
	System.out.println("2015/07/15 - 2016/01/15".split(" - ")[1].trim().replace("/", "-"));
	StringBuilder sb = new StringBuilder();
	sb.append("2014");sb.append("-");sb.append("01".substring(1,2));sb.append("-");sb.append("01".substring(1,2));
	System.out.println(sb.toString());
    }
 
    /**
     *
     * @param plainText
     *            明文
     * @return 32位密文
     */
    public String encryption(String plainText) {
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
	

}
