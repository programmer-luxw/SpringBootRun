package cn.luxw.app.config.log.back;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;

public class LoggerBuilder {
 
    private static final Map<String,Logger> container = new ConcurrentHashMap<>();
    private static final String LOG_PATH="E:/Tast-Jason/logback/idcicpWhois/whois/";
    public Logger getLogger(String name) {
    	Assert.notNull(name, "name cannot be null");
		return container.computeIfAbsent(name, k-> build(k));
    }
 
    private static Logger build(String name) {
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
 
 
        Logger logger = context.getLogger("FILE-" + name);
        logger.setAdditive(false);
        RollingFileAppender appender = new RollingFileAppender();
 
        appender.setContext(context);
        appender.setName("FILE-" + name);
        appender.setFile(OptionHelper.substVars(LoggerBuilder.LOG_PATH +format.format(new Date())+"/"+ name + ".log",context));
        appender.setAppend(true);
        appender.setPrudent(false);
        SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
        String fp = OptionHelper.substVars(LoggerBuilder.LOG_PATH + format.format(new Date())+"/"+ name + "/.%d{yyyy-MM-dd}.%i.log",context);
 
 
        policy.setMaxFileSize(FileSize.valueOf("128MB"));
        policy.setFileNamePattern(fp);
        policy.setMaxHistory(15);
        policy.setTotalSizeCap(FileSize.valueOf("32GB"));
        policy.setParent(appender);
        policy.setContext(context);
        policy.start();
 
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d %p (%file:%line\\)- %m%n");
        encoder.start();
 
        PatternLayoutEncoder encoder1 = new PatternLayoutEncoder();
        encoder1.setContext(context);
        encoder1.setPattern("%d %p (%file:%line\\)- %m%n");
        encoder1.start();
 
        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();
 
        /*设置动态日志控制台输出*/
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setContext(context);
        consoleAppender.setEncoder(encoder1);
        consoleAppender.start();
        logger.addAppender(consoleAppender);
 
        logger.addAppender(appender);
 
        return logger;
    }
}

