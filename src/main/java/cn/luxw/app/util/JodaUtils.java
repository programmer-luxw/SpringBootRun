package cn.luxw.app.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class JodaUtils {
	public static void main(String[] args) {
		String time2 = DateTime.now().toString("yyyy-MM-dd");
		System.out.println(time2);
	}
	

	public void test() {
		DateTime in = new DateTime();

		System.out.println(DateTime.now()); // 国际标准时间

		System.out.println(in.getYear()); // 当年

		System.out.println(in.getMonthOfYear()); // 当月

		System.out.println(in.getDayOfMonth()); // 当月第几天

		System.out.println(in.getDayOfWeek());// 本周第几天

		System.out.println(in.getDayOfYear());// 本年第几天

		System.out.println(in.getHourOfDay());// 时

		System.out.println(in.getMinuteOfHour());// 分

		System.out.println(in.getMinuteOfDay());// 当天第几分钟

		System.out.println(in.getSecondOfMinute());// 秒

		System.out.println(in.getSecondOfDay());// 当天第几秒

		System.out.println(in.getWeekOfWeekyear());// 本年第几周

		System.out.println(in.getZone());// 所在时区

		System.out.println(in.dayOfWeek().getAsText()); // 当天是星期几，例如：星期五

		System.out.println(in.yearOfEra().isLeap()); // 当你是不是闰年，返回boolean值

		System.out.println(in.dayOfMonth().getMaximumValue());// 当月day里面最大的值
	}

	@Test
	public void test2() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");// 自定义日期格式
	
		String time1 = DateTime.now().toString(fmt);
		System.out.println(time1);

		String time2 = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		System.out.println(time2);
		
		

	}

	@Test
	public void test3() {
		DateTime dt = new DateTime(2014, 1, 1, 1, 1);
		int t = dt.dayOfYear().getMaximumValue();
		int tt = dt.dayOfYear().getMinimumValue();
		System.out.println(t);
		System.out.println(tt);
	}
	
	@Test
	public void test4() {
		DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		System.out.println(dateTime.plusDays(90).toString("yyyy-MM-dd HH:mm:ss"));
	}
	
	
	

}
