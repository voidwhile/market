package com.voidwhile.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String format = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 字符串转换日期
	 */
	public static Date strToDate(String date,String format) {
		DateFormat f = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = f.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return d;
	}
	
	public static String dateToString(Date time){ 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	    String ctime = sdf.format(time); 
	    return ctime; 
	} 
	
    public static long getDistanceDays(String str1, String str2) throws Exception{  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        Date one;  
        Date two;  
        long days=0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            diff = time2 - time1;
            days = diff / (1000 * 60 * 60 * 24);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return days;  
    }
    
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        res = simpleDateFormat.format(new Date(Long.parseLong(s+"000")));
        return res;
    }
    
    /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    public static void main(String[] args) throws Exception {
		Date date1 = new Date();
		//System.out.println(date1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		//System.out.println(cal.getTime());
		cal.add(Calendar.YEAR, 1);
		//System.out.println(cal.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String onetime = "2017-03-04";
		String twotime = sdf.format(new Date());
		long num = getDistanceDays(twotime, onetime);
		//System.out.println(num);
		
		System.out.println(stampToDate("1488431758"));
	}
}
