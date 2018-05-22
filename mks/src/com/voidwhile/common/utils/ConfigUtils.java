package com.voidwhile.common.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
 * <p/>郑州云峰计算机科技有限公司版权所有
 * <p/>创 建 人：王朝伟
 * <p/>创建日期：2013-06-25
 * <p/>创建时间：20:15:31
 * <p/>功能描述：配置文件读取工具类
 * <p/>-----------------------------------------------------------
 * <p/>修改历史
 * <p/>修改人                修改时间                修改原因
 * <p/>-----------------------------------------------------------
 */
public class ConfigUtils {
	/**
	 * 加载当前classloader下的默认配置文件并获取属性
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
        String val = getProperty("config.properties",key).trim();
        return val;
	}
	
	/**
	 * 加载当前classloader下的配置文件并获取属性
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getProperty(String fileName,String key){
		Properties properties = new Properties();
        InputStream is = ConfigUtils.class.getClassLoader().getResourceAsStream(fileName);//加载文件内容
        try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        String val = properties.getProperty(key);
        return val;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(ConfigUtils.getProperty("uploadURL"));
	}

}
