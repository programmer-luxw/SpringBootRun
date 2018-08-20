package cn.luxw.app.util.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class TimeUtil {
	public static final String dtLong = "yyyyMMddHHmmss";
	public static final String simple = "yyyy-MM-dd HH:mm:ss";
	public static final String dtShort = "yyyy_MM_dd";
	public static final long multiple = 1000;
	
	public static void sleep(int timeout){
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取UTC当前 秒数
	 * 
	 * @return
	 */
	private static long getUtcTimestamp(DateTime dt) {
		return toUtcTimestamp(dt.getMillis());
	}

	/**
	 * 获取UTC当前日期
	 * 
	 * @return
	 */
	private static String getShortDate(DateTime dt) {
		return dt.toString(dtShort);
	}

	/**
	 * 获取对应时区所在的午夜时间
	 * 
	 * @param dt
	 * @param zoneId
	 * @return
	 */
	private static DateTime getMidnightWithZone(DateTime dt, String zoneId) {
		return dt.withZone(getZone(zoneId)).withTimeAtStartOfDay();
	}

	/**
	 * 午夜时间转化成UTC时间
	 * 
	 * @param dateTime
	 * @return
	 */
	private static DateTime midnighToUtcDateTime(DateTime dateTime) {
		return dateTime.withZone(DateTimeZone.UTC);
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
		return currentTimeMillis / multiple;
	}

	// =============================================================

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
	// return toUtcTimestamp(dt.getMillis());
	// }

	public static DateTime convToUtcDateTime(String timeStr) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(simple).withZoneUTC();
		DateTime dt = DateTime.parse(timeStr, dtf);
		System.out.println("设置UTC时间为：" + dt.toString());
		return dt;
	}

	public static boolean isValidDate(String shortDate) {
		SimpleDateFormat format = new SimpleDateFormat(dtShort);
		try {
			format.setLenient(Boolean.FALSE.booleanValue());
			format.parse(shortDate);
			return Boolean.TRUE;
		} catch (ParseException e) {
			return Boolean.FALSE;
		}
	}

	public static Map<String, Object> getParamMap(String customerTime, Integer partnerId, String zoneId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("partnerId", partnerId);

		// 先做整点时区
		DateTime nowDateTime = null;
		if (Strings.isNullOrEmpty(customerTime)) {
			nowDateTime = DateTime.now(DateTimeZone.UTC); // 获取UTC当前时间，只使用一个
		} else {
			nowDateTime = TimeUtil.convToUtcDateTime(customerTime);
		}

		Long endAt = getUtcTimestamp(nowDateTime);
		params.put("endAt", endAt);// UTC时间不变

		// partner时区的午夜时间
		DateTime midDateTime = getMidnightWithZone(nowDateTime, zoneId);
		Long startAt = getUtcTimestamp(midDateTime);
		params.put("startAt", startAt);

		// 判断startAt时间戳,转换成UTC时间日期，判断startAt的的值是在 那张点击表中
		String tbClickUtcSuffix = getShortDate(nowDateTime);
		params.put("tbClickUtcSuffix", tbClickUtcSuffix);

		// 午夜时间转化成UTC时间
		DateTime midToUtcDateTime = midnighToUtcDateTime(midDateTime);
		String tbClickMidSuffix = getShortDate(midToUtcDateTime);
		params.put("tbClickMidSuffix", tbClickMidSuffix);

		// 判断是否当天最后一次执行, 先计算出partner对应的UTC时间的小时时刻，与当前小时时刻比较
		int standard = midToUtcDateTime.getHourOfDay();
		int preStandard = midToUtcDateTime.plusHours(-1).getHourOfDay();

		int hourOfDay = nowDateTime.getHourOfDay();
		int minuteOfHour = nowDateTime.getMinuteOfHour();
		if ((hourOfDay == standard && minuteOfHour < 30) || (hourOfDay == preStandard && hourOfDay > 30)) {// 最后一次执行
		// params.put("finalAt", 1);
			if (hourOfDay == standard && minuteOfHour < 30) {// 重新计算午夜时间
				DateTime yesterdayMidDateTime = midDateTime.plusDays(-1);
				startAt = getUtcTimestamp(yesterdayMidDateTime);
				params.put("startAt", startAt);

				DateTime yesterdayMidToUtcDateTime = midnighToUtcDateTime(yesterdayMidDateTime);
				tbClickMidSuffix = getShortDate(yesterdayMidToUtcDateTime);
				params.put("tbClickMidSuffix", tbClickMidSuffix);
			}
		} else {
			// params.put("finalAt", 0);
		}
		System.out.println("参数：" + params);
		return params;
	}
	
	
	public static Map<String, Object> getParamMap(DateTime cusDateTime, Integer partnerId, String zoneId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("partnerId", partnerId);

		// 先做整点时区
		DateTime nowDateTime = null;
		if (cusDateTime==null) {
			nowDateTime = DateTime.now(DateTimeZone.UTC); // 获取UTC当前时间，只使用一个
		} else {
			nowDateTime = cusDateTime;
		}

		Long endAt = getUtcTimestamp(nowDateTime);
		params.put("endAt", endAt);// UTC时间不变

		// partner时区的午夜时间
		DateTime midDateTime = getMidnightWithZone(nowDateTime, zoneId);
		Long startAt = getUtcTimestamp(midDateTime);
		params.put("startAt", startAt);

		// 判断startAt时间戳,转换成UTC时间日期，判断startAt的的值是在 那张点击表中
		String tbClickUtcSuffix = getShortDate(nowDateTime);
		params.put("tbClickUtcSuffix", tbClickUtcSuffix);

		// 午夜时间转化成UTC时间
		DateTime midToUtcDateTime = midnighToUtcDateTime(midDateTime);
		String tbClickMidSuffix = getShortDate(midToUtcDateTime);
		params.put("tbClickMidSuffix", tbClickMidSuffix);

		// 判断是否当天最后一次执行, 先计算出partner对应的UTC时间的小时时刻，与当前小时时刻比较
		int standard = midToUtcDateTime.getHourOfDay();
		int preStandard = midToUtcDateTime.plusHours(-1).getHourOfDay();

		int hourOfDay = nowDateTime.getHourOfDay();
		int minuteOfHour = nowDateTime.getMinuteOfHour();
		if ((hourOfDay == standard && minuteOfHour < 30) || (hourOfDay == preStandard && hourOfDay > 30)) {// 最后一次执行
		// params.put("finalAt", 1);
			if (hourOfDay == standard && minuteOfHour < 30) {// 重新计算午夜时间
				DateTime yesterdayMidDateTime = midDateTime.plusDays(-1);
				startAt = getUtcTimestamp(yesterdayMidDateTime);
				params.put("startAt", startAt);

				DateTime yesterdayMidToUtcDateTime = midnighToUtcDateTime(yesterdayMidDateTime);
				tbClickMidSuffix = getShortDate(yesterdayMidToUtcDateTime);
				params.put("tbClickMidSuffix", tbClickMidSuffix);
			}
		} else {
			// params.put("finalAt", 0);
		}
		System.out.println("参数：" + params);
		return params;
	}

	public static void main(String[] args) {
		// {tbClickMidSuffix=2016_01_11, partnerId=20, endAt=1452610876, tbClickUtcSuffix=2016_01_12, finalAt=0, startAt=1452528000}
//		getParamMap(null, 20, "Asia/Shanghai");

		/*
		 * <select id="getPartnerById" parameterType="int" resultType="string"> SELECT timezone FROM `partner` WHERE id = #{partnerId} LIMIT 1 </select>
		 */
		String s = "2016_10_1";
			System.out.println(isValidDate(s));
			
			DateTime dt = new DateTime(2011, 1, 1, 1, 1);
			String tbClickUtcSuffix = getShortDate(dt);
			System.out.println(tbClickUtcSuffix);
			System.out.println(System.currentTimeMillis());
			System.out.println(DateTimeUtils.currentTimeMillis());
		
			
	}

}
