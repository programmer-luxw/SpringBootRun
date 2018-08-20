package cn.luxw.app.guava.base;

import org.junit.Test;

import com.google.common.net.UrlEscapers;

public class GGurl {
	
	@Test
	public void text1(){
		String url = "http://localhost:8080/indes.jsp?username=luxw&password=123456&china=中国";
		System.out.println( UrlEscapers.urlFragmentEscaper().escape(url));
		
	}
}
