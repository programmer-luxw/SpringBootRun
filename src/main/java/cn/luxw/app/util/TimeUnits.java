package cn.luxw.app.util;

import java.util.concurrent.TimeUnit;

public class TimeUnits {

	public static void sleep(long timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);// 秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
