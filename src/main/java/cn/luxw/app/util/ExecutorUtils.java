package cn.luxw.app.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorUtils {

	/**
	 * 固定线程池
	 * 
	 * @param nThreads
	 * @return
	 */
	public static ExecutorService newFixedThreadPool(int nThreads) {
		return Executors.newFixedThreadPool(nThreads);
	}

	/**
	 * 带缓存可以回收的线程池(默认60秒)
	 * 
	 * @return
	 */
	public static ExecutorService newCachedThreadPool() {
		return Executors.newCachedThreadPool();
	}

	/**
	 * 单个线程的线程池
	 * 
	 * @return
	 */
	public static ExecutorService newSingleThreadExecutor() {
		return Executors.newSingleThreadExecutor();
	}

	
	/**
	 * 定时执行任务线程池
	 * @param corePoolSize
	 * @return
	 */
	public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
		return Executors.newScheduledThreadPool(corePoolSize);
	}
	
	
	public static void main(String[] args) {
		ScheduledExecutorService ses = newScheduledThreadPool(1);
		MyTask task = new ExecutorUtils.MyTask();
		ses.scheduleAtFixedRate(task, 2, 5, TimeUnit.SECONDS);
		
	}
	
	 static class MyTask implements Runnable{
		@Override
		public void run() {
			
			System.out.println("呵呵");
			
		}
		
	}
}
