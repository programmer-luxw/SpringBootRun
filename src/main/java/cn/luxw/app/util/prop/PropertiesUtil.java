package cn.luxw.app.util.prop;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 属性文件读取工具
 * @author Luxh
 *
 */
public class PropertiesUtil {
	
	private static Properties props = null;
	
	static{
		try {
			props = PropertiesLoaderUtils.loadAllProperties("email-template.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 获取属性值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return props.getProperty(key);
	}
	
	public static void main(String[] args) {
	String t = 	get("ab");
	System.out.println(t);
	}
}
