package cn.luxw.app.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class XssFilter {
 public static void main(String[] args) {
}
 
 
 /**
	 * 过滤非法字符
	 * @param obj
	 * @return
	 */
	public static Object filter(Object obj) {
		if (obj == null) {
			return "";
		}
		if(obj instanceof String) {
			String text = (String) obj;
			if("".equals(text)) {
				return "";
			}else {
				return Jsoup.clean(text, Whitelist.none());
			}
		}
		return obj;
		
	}
	
	/**
	 * 判断字符是否合法
	 * @param content
	 * @return
	 */
	public static boolean isValid(String content) {
		boolean flag = false;
		if(content==null||"".equals(content)) {
			flag = true; 
		}else {
			flag = Jsoup.isValid(content, Whitelist.none());
		}
		return flag;
	}
}
