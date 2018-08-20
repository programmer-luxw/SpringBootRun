package cn.luxw.app.util.prop;

import java.io.IOException;
import java.io.InputStream;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;


/**
 * http工具
 * @author Luxh
 *
 */
public class HttpUtil {
	
	/**
	 * 获取输入流
	 * @param url
	 * @return
	 */
	public static InputStream get(String url){
		InputStream in = null;
		try{
			GetRequest request = Unirest.get(url);
			if(request != null) {
				in = request.asBinary().getBody();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				Unirest.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return in;
	}
}
