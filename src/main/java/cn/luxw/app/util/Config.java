package cn.luxw.app.util;

import java.util.ResourceBundle;


/**
 * 读取配置文件帮助类
 * @author July
 *
 */
public class Config {
	//private static final Logger logger = LoggerFactory.getLogger(Config.class);
	private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

	public static String getValue(String key) {
		return bundle.getString(key);
	}

	public static String getBasePath() {
		return getValue("BASE.PATH");
	}
	
	public static void main(String[] args) {
		System.out.println(getBasePath()+"!");
	}
}
