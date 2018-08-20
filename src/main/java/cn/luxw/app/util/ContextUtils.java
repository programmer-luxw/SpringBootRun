package cn.luxw.app.util;

import org.springframework.web.context.ContextLoader;

public class ContextUtils {

	public static String getRealPath(){
		String realPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		return realPath;
	}
	
	
}
