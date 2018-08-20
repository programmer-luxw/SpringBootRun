//package cn.luxw.app.util;
//
//
//import java.util.Map;
//
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class PropertiesUtils {
//	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
//	private static PropertiesConfiguration propConfig;
//
//	static{
//		try {
//			propConfig = new PropertiesConfiguration("time.properties");
//			propConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
//			propConfig.setAutoSave(true);
//		} catch (ConfigurationException e) {
//			logger.error(e.getMessage());
//		}
//	}
//	
//
//	
//	public static String getProperty(String key) {
//		return propConfig.getString(key);
//	}
//
//	
//	public static String[] getPropertyArray(String key) {
//		return propConfig.getStringArray(key);
//	}
//
//	public static void setProperty(String key, String value) {
//		propConfig.setProperty(key, value);
//	}
//
//
//	public static void setProperty(Map<String, String> map) {
//		for (String key : map.keySet()) {
//			propConfig.setProperty(key, map.get(key));
//		}
//	}
//
//	public static void main(String[] args) {
//		String key ="update_360";
//		String value = PropertiesUtils.getProperty(key);
//		System.out.println("前"+value);
//		PropertiesUtils.setProperty(key, "2011-2-1211111");
//		String value1 = PropertiesUtils.getProperty(key);
//		System.out.println("后"+value1);
//	}
//
//}
