package cn.luxw.app.util;

/*
 * @(#)DataFormatUtils.java 2010-10-10
 * 
 * Copyright 2010 BianJing,All rights reserved.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/** 用于默认模式的常量，如：日期格式是yyyy-MM-dd，时间格式是HH:mm:ss */
	public static final int DEFAULT = DateFormat.DEFAULT;

	/** 用于 MEDIUM 模式的常量，如：日期格式是 yyyy-MM-dd，时间格式是 HH:mm:ss */
	public static final int MEDIUM = DateFormat.MEDIUM;

	/** 用于 LONG 模式的常量，如：日期格式是 yyyy年MM月dd日，时间格式是 上/下午hh时mm分ss秒 */
	public static final int LONG = DateFormat.LONG;

	/** 用于 FULL 模式的常量，如：日期格式是 yyyy年MM月dd日 星期几，时间格式是 上/下午hh时mm分ss秒 CST */
	public static final int FULL = DateFormat.FULL;

	/** 用于 SHORT 模式的常量，如：日期格式是 yy-MM-dd，时间格式是 上/下午h:mm */
	public static final int SHORT = DateFormat.SHORT;

	
	public static void main(String[] args) {
	}
	/**
	 * 根据本地Locale将Date类型的数据格式化成指定日期+时间模式的字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @param dateStyle
	 *            日期模式
	 * @param timeStyle
	 *            时间模式
	 * @return 格式化后的Date字符串
	 */
	public static String formatDateToDateTimeStr(Date date, int dateStyle, int timeStyle) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(dateStyle, timeStyle);

		return dateFormat.format(date);
	}

	/**
	 * 根据本地Locale将Date类型的数据格式化成指定日期模式的字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @param dateStyle
	 *            日期模式
	 * @return 格式化后的Date字符串
	 */
	public static String formatDateToDateStr(Date date, int dateStyle) {
		DateFormat dateFormat = DateFormat.getDateInstance(dateStyle);

		return dateFormat.format(date);
	}

	/**
	 * 根据本地Locale将Date类型的数据格式化成指定时间模式的字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @param timeStyle
	 *            时间模式
	 * @return 格式化后的Time字符串
	 */
	public static String formatDateToTimeStr(Date date, int timeStyle) {
		DateFormat dateFormat = DateFormat.getTimeInstance(timeStyle);
		return dateFormat.format(date);
	}

	
	/**
	 * 将指定日期类型的数据格式化成指定格式的字符串<br/>
	 * 日期格式参考:"yyyy-MM-dd HH:mm:ss,SS"或"yyyy-MM-dd hh:mm:ss,SS"
	 * 
	 * @param date
	 *            要格式化的日期
	 * @param pattern
	 *            日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDateToPatternStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 根据本地Locale将Date类型的数据格式化成DateFormat.DEFAULT模式的日期+星期+时间字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @return 格式化后的Date字符串(yyyy-MM-dd week HH:mm:ss)
	 */
	public static String formatDateToDateWeekTimeStr(Date date) {
		String dateWeek = formatDateToDateStr(date, FULL);
		String time = formatDateToTimeStr(date, DEFAULT);

		return dateWeek + " " + time;
	}

	/**
	 * 根据本地Locale将Date类型的数据格式化成DateFormat.DEFAULT模式的日期+时间字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @return 格式化后的Date字符串(yyyy-MM-dd HH:mm:ss)
	 */
	public static String formatDateToDefaultDateTimeStr(Date date) {
		return formatDateToDateTimeStr(date, DEFAULT, DEFAULT);
	}

	/**
	 * 根据本地Locale将Date类型的数据格式化成DateFormat.DEFAULT模式的日期字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @return 格式化后的Date字符串(yyyy-MM-dd)
	 */
	public static String formatDateToDefaultDateStr(Date date) {
		return formatDateToDateStr(date, DEFAULT);
	}

	/**
	 * 根据本地Locale将Date类型的数据格式化成DateFormat.DEFAULT模式的时间字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @return 格式化后的Date字符串(HH:mm:ss)
	 */
	public static String formatDateToDefaultTimeStr(Date date) {
		return formatDateToTimeStr(date, DEFAULT);
	}

	/**
	 * 根据本地Locale将String类型的日期数据格式化成指定日期+时间模式的字符串
	 * 
	 * @param source
	 *            日期字符串
	 * @param dateStyle
	 *            日期模式
	 * @param timeStyle
	 *            时间模式
	 * @return 格式化后的日期字符串
	 */
	public static String formatStrToDateTimeStr(String source, int dateStyle, int timeStyle) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(dateStyle, timeStyle);

		Date date = null;
		try {
			date = dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateFormat.format(date);
	}

	/**
	 * 根据本地Locale将String类型的日期数据格式化成指定日期模式的字符串
	 * 
	 * @param source
	 *            日期字符串
	 * @param dateStyle
	 *            日期模式
	 * @return 格式化后的日期字符串
	 */
	public static String formatStrToDateStr(String source, int dateStyle) {
		DateFormat dateFormat = DateFormat.getDateInstance(dateStyle);

		Date date = null;
		try {
			date = dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateFormat.format(date);
	}

	/**
	 * 根据本地Locale将String类型的日期数据格式化成DateFormat.DEFAULT模式的日期+时间字符串
	 * 
	 * @param source
	 *            日期字符串
	 * @return 格式化后的日期字符串
	 */
	public static String formatStrToDateTimeStr(String source) {
		return formatStrToDateTimeStr(source, DEFAULT, DEFAULT);
	}

	/**
	 * 根据本地Locale将String类型的日期数据格式化成DateFormat.DEFAULT模式的日期字符串
	 * 
	 * @param source
	 *            日期字符串
	 * @return 格式化后的日期字符串
	 */
	public static String formatStrToDateStr(String source) {
		return formatStrToDateStr(source, DEFAULT);
	}

	/**
	 * 将日期字符串转换成指定格式的String类型数据<br/>
	 * 日期格式参考:"yyyy-MM-dd HH:mm:ss,SS"或"yyyy-MM-dd hh:mm:ss,SS"
	 * 
	 * @param source
	 *            日期字符串
	 * @param pattern
	 *            日期格式<br/>
	 *            注意：source的日期格式一定要与pattern的日期格式完全一致，<br/>
	 *            否则转换时可能会产生异常或转换出来的日期与原日期不一致或报错。<br/>
	 *            如：formatStrToDateStr("2008-08-08 8:08:08.08","yyyy-MM-dd HH:mm:ss")
	 * @return 格式化后的日期字符串
	 */
	public static String formatStrToDateStr(String source, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return sdf.format(date);
	}

	/**
	 * 将DateFormat.DEFAULT模式的字符串(日期+时间模式)解析成的Date类型的数据
	 * 
	 * @param source
	 *            日期+时间格式的字符串，如：2010-10-10 10:10:10
	 * @return java.util.Date
	 */
	public static Date parseStrToDateTime(String source) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DEFAULT, DEFAULT);

		Date date = null;
		try {
			date = dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将DateFormat.DEFAULT模式的字符串(日期模式)解析成的Date类型的数据
	 * 
	 * @param source
	 *            日期格式的字符串，如：2010-10-10
	 * @return java.util.Date
	 */
	public static Date parseStrToDate(String source) {
		DateFormat dateFormat = DateFormat.getDateInstance(DEFAULT);

		Date date = null;
		try {
			date = dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将日期字符串转换成指定格式的Date类型数据<br/>
	 * 日期格式参考:"yyyy-MM-dd HH:mm:ss,SS"或"yyyy-MM-dd hh:mm:ss,SS"
	 * 
	 * @param source
	 *            日期字符串
	 * @param pattern
	 *            日期格式<br/>
	 *            注意：source的日期格式一定要与pattern的日期格式完全一致，<br/>
	 *            否则转换时可能会产生异常或转换出来的日期与原日期不一致或报错。<br/>
	 *            如：parseStrToDate("2008-08-08","yyyy-MM-dd")
	 * @return 日期数据类型
	 */
	public static Date parseStrToDate(String source, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将Date类型的数据转换成Calendar类型的数据
	 * 
	 * @param date
	 *            java.util.Date
	 * @return java.util.Calendar
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar;
	}

	/**
	 * 将Calendar类型的数据转换成Date类型的数据
	 * 
	 * @param calendar
	 *            java.util.Calendar
	 * @return java.util.Date
	 */
	public static Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstDate
	 *            日期一
	 * @param secondDate
	 *            日期二
	 * @return 日期二减日期一相隔的天数
	 */
	public static int getDaysBetweenTwoDate(Date firstDate, Date secondDate) {
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));

		return nDay;
	}

	/**
	 * 指定时间加、减指定的时间单位
	 * 
	 * @param calendarFinal
	 *            Calendar类中代表时间的字段常量<br/>
	 *            &nbsp;&nbsp; Calendar.YEAR 年<br/>
	 *            &nbsp;&nbsp; Calendar.MONTH 月(月份从0开始)<br/>
	 *            &nbsp;&nbsp; Calendar.DATE 日<br/>
	 *            &nbsp;&nbsp; Calendar.HOUR 时<br/>
	 *            &nbsp;&nbsp; Calendar.MINUTE 分<br/>
	 *            &nbsp;&nbsp; Calendar.SECOND 秒<br/>
	 * @param time
	 *            指定时间
	 * @param times
	 *            相加或减的时间，相加传正数，相减传负数
	 * @return 相加或相减后的时间
	 */
	public static Date addOrMinusTimes(int calendarFinal, Date time, int times) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(calendarFinal, times);

		return calendar.getTime();
	}

	/**
	 * 指定日期加、减指定的天数
	 * 
	 * @param date
	 *            指定日期
	 * @param days
	 *            相加或减的天数，相加传正数，相减传负数
	 * @return 相加或相减后的日期
	 */
	public static Date addOrMinusDays(Date date, int days) {
		return addOrMinusTimes(Calendar.DATE, date, days);
	}

	/**
	 * 当前日期加、减指定的天数
	 * 
	 * @param days
	 *            相加或减的天数，相加传正数，相减传负数
	 * @return 相加或相减后的日期
	 */
	public static Date addOrMinusDays(int days) {
		return addOrMinusDays(new Date(), days);
	}

	/**
	 * 指定日期加、减指定的月份数
	 * 
	 * @param date
	 *            指定日期
	 * @param months
	 *            相加或减的月份数，相加传正数，相减传负数
	 * @return 相加或相减后的日期
	 */
	public static Date addOrMinusMonths(Date date, int months) {
		return addOrMinusTimes(Calendar.MONTH, date, months);
	}

	/**
	 * 当前日期加、减指定的月份数
	 * 
	 * @param months
	 *            相加或减的月份数，相加传正数，相减传负数
	 * @return 相加或相减后的日期
	 */
	public static Date addOrMinusMonths(int months) {
		return addOrMinusMonths(new Date(), months);
	}

	/**
	 * 验证字符串是否为正确的日期格式<br/>
	 * 日期格式参考:"yyyy-MM-dd HH:mm:ss,SS"或"yyyy-MM-dd hh:mm:ss,SS"
	 * 
	 * @param source
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return boolean true：正确，false：错误
	 */
	public static boolean validateDateFormat(String source, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);

		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		return source.equals(sdf.format(date));
	}
}
