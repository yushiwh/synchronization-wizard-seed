package com.jzt.sync.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	//MD5密码加密工具
	public static String getMD5Str(String str){
	      MessageDigest messageDigest = null;
	      try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	      messageDigest.reset();
	      try {
			messageDigest.update(str.getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    byte[] byteArray = messageDigest.digest();
	    StringBuffer md5StrBuff = new StringBuffer();
	    for (int i = 0; i < byteArray.length; i++) {
	      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
	        md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
	      else
	        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
	    }
	    return md5StrBuff.toString();
	  }

    /**
     * 将时分秒格式的时间四舍五入到分
     *
     * @author houzhan.li
     * @param dateStr 要转换的时间
     * @return
     */
    public static String timeFormatToMM(String timeStr){

        if (timeStr != null && timeStr.length() > 0 && timeStr.indexOf(":") > 0) {


            int index1 = timeStr.indexOf(":");
            int index2 = timeStr.indexOf(":", index1 + 1);
            int hh = Integer.parseInt(timeStr.substring(0, index1));

            int mi = 0;
            int ss = 0;
            if (index2 > 0) {
                mi = Integer.parseInt(timeStr.substring(index1 + 1, index2));
                ss = Integer.parseInt(timeStr.substring(index2 + 1));
            }
            else {
                mi = Integer.parseInt(timeStr.substring(index1 + 1, timeStr.length()));
            }

            float formatS = (float) ss / 60;

            int formatSS = new BigDecimal(formatS).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

            return String.valueOf(hh * 60 + mi + formatSS);
        }

        return null;
    }
}
