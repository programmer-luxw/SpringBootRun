package cn.luxw.app.config.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 这个类的作用是给日志通过level设置过滤器
 * 
 * @author July
 */
public class LogLevelFilter {
	/**
	 * 通过level设置过滤器
	 * @param level
	 * @return
	 */
	public LevelFilter getLevelFilter(Level level) {
		LevelFilter levelFilter = new LevelFilter();
		levelFilter.setLevel(level);
		levelFilter.setOnMatch(FilterReply.ACCEPT);
		levelFilter.setOnMismatch(FilterReply.DENY);
		return levelFilter;
	}
}
