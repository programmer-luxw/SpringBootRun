package cn.luxw.app.util.wx;


import java.util.concurrent.TimeUnit;

public class ThreadUtil {
	public static void sleep(long millis){
		try {
			TimeUnit.SECONDS.sleep(millis);//秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
