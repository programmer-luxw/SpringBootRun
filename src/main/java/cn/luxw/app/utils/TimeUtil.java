package cn.luxw.app.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Locale;

public class TimeUtil {
	
	/**
	 * 获取UTC当前 秒数
	 * 
	 * @return
	 */
	public static Long getUtcTimestamp() {
		return Instant.now().getEpochSecond();
	}
	
	/**
	 * 获取偏移量
	 * @param zondId
	 * @return
	 */
	public static String getZoneOffset(String zondId) {
		try {
			ZoneOffset z = ZoneId.of(zondId).getRules().getOffset(Instant.now());
			return "GMT ".concat(z.getId());
		} catch (Exception e) {
			//LOG.error("getZoneOffset error:",e);
		}
		return "";
		
	}
	
	/**
     * 判断时区是否为半时区
     *
     * @param zoneId
     * @return
     */
    public static boolean isHalfZone(String zoneId) {
        ZoneId zone = ZoneId.of(zoneId);
        int seconds = zone.getRules().getOffset(LocalDateTime.now()).getTotalSeconds() % 3600;
        return (seconds != 0);
    }
    public static String format(Instant instant, ZoneId zoneId, String pattern, Locale locale){
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);
		zonedDateTime.format(java.time.format.DateTimeFormatter.ofPattern(pattern));
		return zonedDateTime.format(java.time.format.DateTimeFormatter.ofPattern(pattern, locale));
	}
    
    private static final String YYYY_MM = "yyyy_MM";
	private static final String DEFAULT_TIMEZONE = "UTC";
    public static String getFormatDate(long startTime) {
		return format(Instant.ofEpochSecond(startTime), ZoneId.of(DEFAULT_TIMEZONE), YYYY_MM);
	}
    public static String format(Instant instant, ZoneId zoneId, String pattern){
		return format(instant, zoneId, pattern, Locale.getDefault(Locale.Category.FORMAT));
	}

}
