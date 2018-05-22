package com.voidwhile.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
	 /** 默认的格式化方式 */
    private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String formatCurrentDate = dateFormat.format(currentDate).toString();

        return formatCurrentDate;
    }

    public static String getCurrentDate() {
        String format = "yyyy-MM-dd";
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        if (format == null || "".equals(format.trim())) {
            format = defaultFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getCurrentTime() {
        String format = "yyyyMMddHHmmss";
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        if (format == null || "".equals(format.trim())) {
            format = defaultFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    public static String getCurrent() {
        String format = "yyyyMMdd";
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        if (format == null || "".equals(format.trim())) {
            format = defaultFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    public static String toString(Object obj){
    	if (obj==null) {
			return null;
		} else {
			return obj.toString();
		}
    }
    
    public static Long toLong(String s){
    	if (s==null) {
			return null;
		} else {
			return Long.parseLong(s);
		}
    }
    public static Integer toInteger(String s){
    	if (s==null) {
    		return null;
    	} else {
    		return Integer.parseInt(s);
    	}
    }
}
