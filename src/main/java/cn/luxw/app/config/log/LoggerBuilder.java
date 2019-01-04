package cn.luxw.app.config.log;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;

/**
 * https://blog.csdn.net/jasonlibint/article/details/81942790
 * https://blog.csdn.net/weixin_42258128/article/details/81942796
 * @author JULY
 *
 */
@Component
public class LoggerBuilder {
	private static final Map<String,Logger> LOG_CONTAINER = new ConcurrentHashMap<>();
	public static final String FILE_PREFIX = "FILE-";
	
    public Logger getLogger(String name) {
    	Assert.notNull(name, "name cannot be null");
		return LOG_CONTAINER.computeIfAbsent(name, k-> build(k));
    }
 
    private static Logger build(String name) {
		RollingFileAppender<ILoggingEvent> errorAppender = new LogAppender().getAppender(name,Level.ERROR);
        RollingFileAppender<ILoggingEvent> infoAppender = new LogAppender().getAppender(name,Level.INFO);
        RollingFileAppender<ILoggingEvent> warnAppender = new LogAppender().getAppender(name,Level.WARN);
        RollingFileAppender<ILoggingEvent> debugAppender = new LogAppender().getAppender(name,Level.DEBUG);
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger(FILE_PREFIX.concat(name));
       
        logger.setLevel(Level.DEBUG);
        logger.setAdditive(false); //设置不向上级打印信息
        logger.addAppender(errorAppender);
        logger.addAppender(infoAppender);
        logger.addAppender(warnAppender);
        logger.addAppender(debugAppender);
 
        return logger;
    }

}
