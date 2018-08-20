package cn.luxw.app.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlUtil {
	
	public static String getImgSrc(String text){
		Document doc = Jsoup.parse(text);
		return doc.getElementsByTag("img").attr("src");
	}
}
