package cn.luxw.app.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Strings;

public class Times {
	  public static final String dtLong = "yyyyMMddHHmmss";
	  public static final String simple = "yyyy-MM-dd HH:mm:ss";
	  public static final String dtShort = "yyyyMMdd";

	
	
	/**
	 * 获取UTC当前时间毫秒数
	 * 
	 * @return
	 */
	public static long getUtcTimestamp() {
		return DateTime.now(DateTimeZone.UTC).getMillis();
	}
	
	
	/**
	 * 获取指定时区,指定时间的UTC时间毫秒数
	 * @param zoneId
	 * @param dateTimeStr
	 * @return
	 */
	public static long getUtcTimestamp(String zoneId,String dateTimeStr) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(simple);
		DateTime dt =  DateTime.parse(dateTimeStr, dtf).withZoneRetainFields(getZone(zoneId));
		return dt.getMillis();
	}
	

	/**
	 * 获取指定时区 午夜零点的UTC时间毫秒数
	 * 
	 * @param zoneId
	 * @return //dt.dayOfMonth().roundHalfFloorCopy()
	 */
	public static long getZoneDateMidnight(String zoneId) {
		return new DateTime(getZone(zoneId)).withTimeAtStartOfDay().getMillis();
	}

	/**
	 * 获取指定时区 当前时间毫秒数
	 * 
	 * @param zoneId
	 * @return
	 */
	public static long getZoneNowTimestamp(String zoneId) {
		return DateTime.now(getZone(zoneId)).getMillis();
	}
	
	
	/**
	 * 输出UTC的毫秒数指定时区的时间 字符串(yyyy-MM-dd HH:mm:ss,暂不抽取公用方法,打印用)
	 * @param utcTimestamp utc时间毫秒数
	 * @param zoneId 时区
	 * @return
	 */
	public static String toString(long utcTimestamp,String zoneId){
		if(Strings.isNullOrEmpty(zoneId)){
			zoneId = "UTC";
		}
		return new DateTime(utcTimestamp, getZone(zoneId)).toString(simple);
	}
	
	
	public static String toString(long utcTimestamp){
		return new DateTime(utcTimestamp).toString(simple);
	}
	
	
	
	
	/**
	 * 获取时区对象
	 * @param zoneId  支持:"Asia/Shanghai" 或者 "+08:00" 两种方式
	 * @return
	 */
	private static DateTimeZone getZone(String zoneId){
		return DateTimeZone.forID(zoneId); 
	}
	

	public static void testNST() {
		// DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		// DateTime x = dtf.parseDateTime("30/03/2008 03:00:00");
		// 相同时间的某个时区
		// dt.withZoneRetainFields(DateTimeZone.forID("Europe/London"));
		// http://www.iana.org/time-zones
		// http://www.joda.org/joda-time/timezones.html
		DateTimeZone romeTimeZone = DateTimeZone.forID("Europe/Rome");

		DateTime dayOfDstChange = new DateTime(2008, 3, 30, 0, 0, romeTimeZone); // Day when DST
		DateTime dayAfter = dayOfDstChange.plusDays(1);

		// How many hours in this day? Should be 23 rather than 24 on day of Daylight Saving Time "springing ahead" to lose one hour.
		Hours hoursObjectForDay = Hours.hoursBetween(dayOfDstChange.withTimeAtStartOfDay(), dayAfter.withTimeAtStartOfDay());
		System.out.println("Expect 23 hours, got: " + hoursObjectForDay.getHours()); // Extract an int from object.

		// What time is 3 hours after midnight on day of DST change?
		DateTime threeHoursAfterMidnightOnDayOfDst = dayOfDstChange.withTimeAtStartOfDay().plusHours(3);
		System.out.println("Expect 4 AM (04:00) for threeHoursAfterMidnightOnDayOfDst: " + threeHoursAfterMidnightOnDayOfDst);

		// What time is 3 hours after midnight on day _after_ DST change?
		DateTime threeHoursAfterMidnightOnDayAfterDst = dayAfter.withTimeAtStartOfDay().plusHours(3);
		System.out.println("Expect 3 AM (03:00) for threeHoursAfterMidnightOnDayAfterDst: " + threeHoursAfterMidnightOnDayAfterDst);
	}

	public static void main(String[] args) {
		DateTimeZone romeTimeZone = DateTimeZone.forID("Europe/Rome");
		DateTime dayOfDstChange = new DateTime(2008, 8, 30, 0, 0, romeTimeZone); // Day when DST
		
		System.out.println(dayOfDstChange.toString());
//		getUtcTimestamp("+07:00", "2015-12-25 04:00:00");
//		getUtcTimestamp("+08:00", "2015-12-25 04:00:00");

	}

}
