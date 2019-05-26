package cn.luxw.app.utils;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 读取配置文件
 * @author July
 * @date 2016-1-6
 */
public class PropUtils {
	private static Logger LOG = LoggerFactory.getLogger(PropUtils.class);
	private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

	private static String getValue(String key) {
		try {
			return bundle.getString(key).trim();
		} catch (Exception ex) {
			LOG.error("获取config.properties键值对失败:{}",key);
			throw new RuntimeException("获取config.properties键值对失败",ex);
		}
		
	}

	public static boolean branchOpen() {
		 return Boolean.parseBoolean(getValue("custom.branch_open"));
	}
	
	
	
	public static void main(String[] args) {
//		System.out.println(branchOpen()+"!");
		
	}
}
