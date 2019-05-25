package cn.luxw.app.config.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.qos.logback.classic.Logger;

public class LogTest {
 
    public static void main(String[] args) {
        LoggerBuilder loggerBuilder = new LoggerBuilder();
        Logger logger = loggerBuilder.getLogger("9");
        logger.debug("log==========debug");
        logger.warn("log============warn");
        logger.info("log=============info");
        logger.error("log=============error");
    	
    	/* Map<String, String> map2 = new ConcurrentHashMap<>();
    	 String v = map2.computeIfAbsent("name", k -> genValue2(k));
    	 System.out.println("name1=="+v);
    	 
    	 v = map2.computeIfAbsent("addr", k -> genValue2(k));
    	 System.out.println("addr=="+v);
    	 v =map2.computeIfAbsent("email", k -> genValue2(k));
    	 System.out.println("email=="+v);
         v = map2.computeIfAbsent("mobile", k -> genValue2(k));
         System.out.println("mobile=="+v);
         
         v = map2.computeIfAbsent("name", k -> genValue2(k));
         System.out.println("name2=="+v);*/
    }
    
    
    
    static String genValue2(String str) {
        System.err.println("==="+str);
        return str + "2";
    }
}

