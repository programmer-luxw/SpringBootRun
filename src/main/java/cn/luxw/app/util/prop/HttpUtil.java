//package cn.luxw.app.util.prop;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import kong.unirest.GetRequest;
//import kong.unirest.Unirest;
//
//
//
///**
// * http工具
// * @author Luxh
// *
// */
//public class HttpUtil {
//	
//	/**
//	 * 获取输入流
//	 * @param url
//	 * @return
//	 */
//	public static InputStream get(String url){
//		InputStream in = null;
//		try{
//			GetRequest request = Unirest.get(url);
//			if(request != null) {
//				in = request.as
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally {
//			try {
//				Unirest.shutDown();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return in;
//	}
//}
