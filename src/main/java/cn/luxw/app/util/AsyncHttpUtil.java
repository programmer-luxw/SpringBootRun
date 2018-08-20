//package cn.luxw.app.util;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Map;
//
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.HttpClientUtils;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.google.common.collect.Maps;
//import com.google.common.io.CharStreams;
//import com.mashape.unirest.http.Headers;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//
//public class AsyncHttpUtil {
//	private static final Logger logger = LoggerFactory.getLogger(AsyncHttpUtil.class);
//
//	public static String post(String url, Map<String, Object> params) {
//		String result = null;
//		try {
//			result = Unirest.post(url).fields(params).asString().getBody();
//		} catch (UnirestException e) {
//			logger.error("AsyncHttpUtil.post方法,请求出现异常 ", e);
//		} finally {
//			try {
//				Unirest.shutdown();
//			} catch (IOException e) {
//				logger.error("AsyncHttpUtil.post方法,关闭Unirest出现异常 ", e);
//			}
//		}
//		return result;
//	}
//
//	public static String post(String url, String param) {
//		String result = null;
//		try {
//			result = Unirest.post(url).header("accept", "application/json").body(param).asString().getBody();
//		} catch (UnirestException e) {
//			logger.error("AsyncHttpUtil.post方法,请求出现异常 ", e);
//		} finally {
//			try {
//				Unirest.shutdown();
//			} catch (IOException e) {
//				logger.error("AsyncHttpUtil.post方法,关闭Unirest出现异常 ", e);
//			}
//		}
//		return result;
//	}
//
//	public static String postAsync(String url, Map<String, Object> params) {
//		String result = null;
//		try {
//			 HttpResponse<InputStream>  re = Unirest.get(url).fields(params).asBinary();
//			 System.out.println("+++++++++++++++++++"+re.getCode());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				Unirest.shutdown();
//			} catch (IOException e) {
//				logger.error("AsyncHttpUtil.get方法,关闭Unirest出现异常 ", e);
//			}
//		}
//
//		return result;
//
//	}
//
//	public static String get(String url, Map<String, Object> params) {
//		String result = null;
//		try {
//			result = Unirest.get(url).fields(params).asString().getBody();
//		} catch (UnirestException e) {
//			logger.error("AsyncHttpUtil.get方法,请求出现异常 ", e);
//		} finally {
//			try {
//				Unirest.shutdown();
//			} catch (IOException e) {
//				logger.error("AsyncHttpUtil.get方法,关闭Unirest出现异常 ", e);
//			}
//		}
//		return result;
//	}
//
//	public static void main(String[] args) throws Exception {
//		long start = System.currentTimeMillis();
//		String url1 = "http://apk.91.com/soft/Controller.ashx?Action=download&id=486611911&tpl=zhidian1";
////		Map<String, Object> params = Maps.newHashMap();
////		String url = "http://localhost:8080/WifiUpateServer/tool/list.shtml";
////		String cc = postAsync(url1, params);
////		System.out.println(cc);
//
//		
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet(url1);
//		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//		System.out.println(httpResponse.getStatusLine().getStatusCode());
//		System.out.println("时间:"+(System.currentTimeMillis() -start)/1000);
//		
//		HttpClientUtils.closeQuietly(httpResponse);
//		HttpClientUtils.closeQuietly(httpClient);
//	}
//	
//
//}
