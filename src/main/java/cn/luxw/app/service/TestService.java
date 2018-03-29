package cn.luxw.app.service;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public interface TestService {

	
	
	
	final Map<String, String> newsEventMap = new HashMap<String, String>(){{  
			put("k1","v1");  
			put("k2","v2");  
			}};
	
	
		
		public static void main(String[] args) {
			System.out.println(newsEventMap);
		}
	
}
