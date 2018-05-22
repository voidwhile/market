package com.voidwhile.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Encoder;

public class CommonUtils {
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
    
    /**
     * [把图片转为base64格式的字符串]
     *
     * @param imgagePath
     * @return
     */
    public static String getImageStr(String imgagePath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgagePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    
    public static String getStackTrace(Throwable throwable)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            pw.close();
        }
    }
    
}
