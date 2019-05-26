package cn.luxw.app.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.google.common.base.Strings;

public class XssUtils {

	/**
	 * 过滤非法字符
	 * 
	 * @param obj
	 * @return
	 */
	public static String filter(String str) {
		if (Strings.isNullOrEmpty(str)) {
			return "";
		}
		return Jsoup.clean(str, Whitelist.none());

	}

	/**
	 * 判断字符是否合法
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isValid(String content) {
		boolean flag = false;
		if (content == null || "".equals(content)) {
			flag = true;
		} else {
			flag = Jsoup.isValid(content, Whitelist.none());
		}
		return flag;
	}
	public static void main(String[] args) {
		System.out.println("==="+filter("ooo><jajf"));
	}

}
