package cn.luxw.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;


public class DateUtil {
	
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String HH_MM_SS = "HH:mm:ss";

	public static final String getFormatDateTime(Date date) {
		return format(date, yyyy_MM_dd_HH_mm_ss);
	}
	
	public static final String getFormatDate(Date date) {
		return format(date, yyyy_MM_dd);
	}

	public static final String getFormatTime(Date date) {
		return format(date, HH_MM_SS);
		 
	}

	/**
	 * 将UNIX时间戳转换成系统可以处理的时间
	 * @param unixTimeStr
	 * @return
	 */
	public static String unixTimeToString(String unixTimeStr){
		Long timestamp = 0L;
		if(NumberUtils.isNumber(unixTimeStr)){
			 timestamp = Long.parseLong(unixTimeStr)*1000; 
		}
		return getFormatDateTime(new Date(timestamp));
	}
	
	
	/**
	 * 讲时间转换成UNIX时间戳
	 * @param timeStr
	 * @return
	 */
	public static long StrToUnixTime(String timeStr){
		return parse(timeStr,yyyy_MM_dd_HH_mm_ss).getTime()/1000;
	}
	
	public static final String format(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		String time = f.format(date);
		return time;
	}
	
	public static final Date parse(String date, String format){
		SimpleDateFormat ft = new SimpleDateFormat(format);
		try {
			return ft.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final String simpleShow(Date date) {
		long dayMilliseconds = 1000 * 60 * 60 * 24;  
		long hours = 1000 * 60 * 60;
		long minus = 1000 * 60;
		Date now = new Date();
		long time1 = now.getTime();
		long time2 = date.getTime();
		
		long sub = time1 - time2;
		long divide = sub / dayMilliseconds;
		if(divide > 0) {
			return format(date, yyyy_MM_dd);
		}
		divide = sub / hours;
		if(divide < 1) {
			divide = sub / minus;
			if(divide < 1) {
				return "刚刚";
			}
			return  divide + "分钟前";
		}
		return  divide + "小时前";
	}
	
	public static void main(String[] args) {
		String tt = unixTimeToString("0");
		System.out.println(tt);
	}
}
