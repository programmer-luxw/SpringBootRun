package cn.luxw.app.config.log;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
 
/**
 * 这个类是给日志动态提供appender
 */
public class LogAppender {
	
	private static final String LOG_HOME = "/data/logs/AffiliateAPI/"; 
	//public static final String FILE_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} %p - %m%n"; 
	private static final String FILE_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} - %m%n"; 
    /**
          *   通过传入的名字和级别，动态设置appender
     * @param name
     * @param level
     * @return
     */
    public RollingFileAppender<ILoggingEvent> getAppender(String name, Level level){
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        // <appender name="error" class="ch.qos.logback.core.rolling.RollingFileAppender">
        RollingFileAppender<ILoggingEvent> appender = new RollingFileAppender<ILoggingEvent>();
        appender.setContext(context);//先设置
//        ConsoleAppender consoleAppender = new ConsoleAppender();
        //这里设置级别过滤器
        LevelFilter levelFilter = new LogLevelFilter().getLevelFilter(level);
        levelFilter.start();
        appender.addFilter(levelFilter);
 
        appender.setName(LoggerBuilder.FILE_PREFIX.concat(name));
        appender.setFile(OptionHelper.substVars(LOG_HOME + name +"/"+ level.levelStr.toLowerCase() + ".log",context));
        appender.setAppend(true);
        appender.setPrudent(false);
 
        //设置文件创建时间及大小的类
       // SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        policy.setContext(context);//先设置
        //最大日志文件大小
        //policy.setMaxFileSize(FileSize.valueOf("128MB"));
        //文件名格式${LOG_HOME}/%d{yyyy-MM-dd}/error-%d{yyyy-MM-dd}.log.gz
        //String fp = OptionHelper.substVars(FILE_PATH + name +"/" + format.format(new Date())+"/"+ level.levelStr.toLowerCase() + "/.%d{yyyy-MM-dd}.%i.log",context);
        //String fp = OptionHelper.substVars(FILE_PATH + name +"/" + format.format(new Date())+"/"+ level.levelStr.toLowerCase() + "-%d{yyyy-MM-dd}.log.gz",context);
        String fp = OptionHelper.substVars(LOG_HOME + name +"/%d{yyyy-MM-dd}/"+ level.levelStr.toLowerCase() + "-%d{yyyy-MM-dd}.log.gz",context);
        policy.setFileNamePattern(fp);
        policy.setMaxHistory(15);
        //总大小限制
        //policy.setTotalSizeCap(FileSize.valueOf("5GB"));
        //设置父节点是appender
        policy.setParent(appender);
        policy.start();
 
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
        // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
        encoder.setContext(context);//先设置
        //设置格式
        //encoder.setPattern("%d %p (%file:%line\\)- %m%n");
        encoder.setPattern(FILE_PATTERN);
        encoder.start();
 
        //加入下面两个节点
        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();
        return appender;
    }
}
