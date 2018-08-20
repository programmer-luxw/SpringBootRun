package cn.luxw.app.util.time;

import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.google.common.collect.Maps;

public class CopyOfTimeUtil {
	public static final String dtLong = "yyyyMMddHHmmss";
	public static final String simple = "yyyy-MM-dd HH:mm:ss";
	public static final String dtShort = "yyyy_MM_dd";
	public static final long multiple = 1000;

	/**
	 * 获取UTC当前 秒数
	 * 
	 * @return
	 */
	public static long getUtcTimestamp() {
		return toUtcTimestamp(DateTime.now(DateTimeZone.UTC).getMillis());
	}

	/**
	 * 获取UTC当前日期
	 * 
	 * @return
	 */
	public static String getUtcDate() {
		return DateTime.now(DateTimeZone.UTC).toString(dtShort);
	}

	/**
	 * 获取所在时区午夜时刻 秒数
	 * 
	 * @param zoneId
	 * @return
	 */
	public static long getMidnightTimestamp(String zoneId) {
		return toUtcTimestamp(new DateTime(getZone(zoneId)).withTimeAtStartOfDay().getMillis());
	}

	/**
	 * 获取所在时区午夜时刻 对应UTC日期
	 * 
	 * @param zoneId
	 * @return
	 */
	public static String getMidnightUtcDate(String zoneId) {
		return new DateTime(getZone(zoneId)).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).toString(dtShort);
	}

	/**
	 * 获取时区
	 * 
	 * @param zoneId
	 *            支持:"Asia/Shanghai"
	 * @return
	 */
	private static DateTimeZone getZone(String zoneId) {
		return DateTimeZone.forID(zoneId);
	}

	/**
	 * 毫秒转换成秒
	 * 
	 * @param utcTimestamp
	 * @return
	 */
	private static long toUtcTimestamp(long currentTimeMillis) {
		return currentTimeMillis / 1000;
	}

	// ===========

	/**
	 * 获取指定时区,指定时间的UTC时间毫秒数
	 * 
	 * @param zoneId
	 * @param dateTimeStr
	 * @return
	 */
	// public static long getUtcTimestamp(String zoneId, String dateTimeStr) {
	// DateTimeFormatter dtf = DateTimeFormat.forPattern(simple);
	// DateTime dt = DateTime.parse(dateTimeStr, dtf).withZoneRetainFields(getZone(zoneId));
	// return getSecondTimestamp(dt.getMillis());
	// }

	/**
	 * 输出UTC的毫秒数指定时区的时间 字符串(yyyy-MM-dd HH:mm:ss,暂不抽取公用方法,打印用)
	 * 
	 * @param utcTimestamp
	 *            utc时间毫秒数
	 * @param zoneId
	 *            时区
	 * @return
	 */
	// public static String toString(long utcTimestamp, String zoneId) {
	// return new DateTime(utcTimestamp, getZone(zoneId)).toString(simple);
	// }

	public static void main(String[] args) {
		DateTime now = DateTime.now().withZone(DateTimeZone.UTC);
//		now.withZone(newZone)
		
		int hourOfDay = now.getHourOfDay();
		int minuteOfHour = now.getMinuteOfHour();
//		DateTime d = new DateTime().withTimeAtStartOfDay().plusDays(-1);
//		System.out.println(d);
		
		System.out.println(now);
	}
	
	private static Map<String,Object>  getParamMap(Integer partnerId,String zoneId){
		Map<String,Object>  params = Maps.newHashMap();
		params.put("partnerId", partnerId);
		//先做整点时区的
		DateTime now = DateTime.now();
		int hourOfDay = now.getHourOfDay();
		int minuteOfHour = now.getMinuteOfHour();
		//判断是否当天最后一次
		if((hourOfDay == 23 && minuteOfHour >30) || (hourOfDay == 0 && hourOfDay<30)){
			params.put("finalAt", 1);
		}else{
			params.put("finalAt", 0);
		}
		
		//开始时间和结束
		 Long startAt = 0L;
		 Long endAt = 0L;
		
		
		return null;
		
	}
	
	
	
	
	
}
