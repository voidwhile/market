package com.voidwhile.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 周处理工具类
 * @author: xiaowei
 * @Create Date: 2014年12月11日 下午1:21:07
 *
 * @Version: v1.0
 */
public class WeekUtil {

	/**
	 * @MethodName: getWeekOfYear
	 * @Description: 取得当前日期是多少周
	 * @param date
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午1:20:09
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * @MethodName: getMaxWeekNumOfYear
	 * @Description: 得到某一年周的总数
	 * @param year
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午1:19:59
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}

	/**
	 * @MethodName: getFirstDayOfWeek
	 * @Description: 得到某年某周的第一天
	 * @param year
	 * @param week
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午1:19:00
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * @MethodName: getLastDayOfWeek
	 * @Description: 得到某年某周的最后一天
	 * @param year
	 * @param week
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午1:19:05
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * @MethodName: getFirstDayOfWeek
	 * @Description: 取得当前日期所在周的第一天
	 * @param date
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午1:19:14
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * @MethodName: getLastDayOfWeek
	 * @Description: 取得当前日期所在周的最后一天
	 * @param date
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午1:19:23
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

}
