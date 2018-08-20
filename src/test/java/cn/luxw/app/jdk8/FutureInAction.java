package cn.luxw.app.jdk8;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/7 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class FutureInAction {
    public static void main(String[] args) throws InterruptedException {

    	ExecutorService es1 = Executors.newFixedThreadPool(2);
    	ExecutorService es2 = Executors.newSingleThreadExecutor();
    	ListeningExecutorService es = MoreExecutors.listeningDecorator(es1);
    	ListenableFuture<String> future = es.submit(()->{
    		  try {
    			  System.out.println("------===---");
                  Thread.sleep(3000);
                  return "I am finished.";
              } catch (InterruptedException e) {
                  return "Error";
              }
          });
    	
//    	future.addListener(listener, executor);
    	//future.addListener(listener, es);
    	Futures.addCallback(future, new FutureCallbackImpl(), es2);
    	System.out.println("======aaa========");
    	es.shutdown();
    	es1.shutdown();
    	//es2.shutdown();
    	
    	
    	//es1.shutdown();
    	//es2.shutdown();
    	System.out.println("======bbb========");
    	
     /*   Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        //.....
        //....
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        System.out.println(future.get());*/

      /*  String value = block(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println(value);*/
    }
    
    /**
	 * 回调方法
	 * 
	 * @author luxw
	 * @version 1.0,2014-9-18 上午11:44:52
	 */
	private static class FutureCallbackImpl implements FutureCallback<String> {

		@Override
		public void onSuccess(String result) {
			System.err.println("==onSuccess=="+result);
			
		}

		@Override
		public void onFailure(Throwable t) {
			System.err.println("==onFailure=="+t);
			
		}
		
	}

    private static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        t.start();

        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };

        return future;
    }


    private interface Future<T> {

        T get();

        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }
}
