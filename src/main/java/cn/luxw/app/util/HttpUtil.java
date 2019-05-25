package cn.luxw.app.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import kong.unirest.Unirest;


/**
 * http帮助类
 * @author luxw
 * @version
 *    1.0,2014-7-23 下午1:43:48
 */
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	private static final String GET_PATH = "C:\\get.dat";
	private static final String POST_PATH = "C:\\post.dat";
	private static final int HTTP_TIMEOUT = 20000;
	

	/**
	 * get请求简单封装
	 * 
	 * @param uri
	 * @param headers
	 * @return
	 */
	public static String get(String url, Map<String, Object> params) {
		logger.info("HttpUtil.get \n参数\r url:{},\n参数\r params:{}", url, params);
		String result = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpClient = HttpClients.createDefault();
			StringBuilder sb = new StringBuilder(url);
			if (params!=null&&params.size()!=0) {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					nameValuePairs.add(new BasicNameValuePair(entry.getKey(), ObjectUtils.toString(entry.getValue())));
				}
				sb.append("?");
				sb.append(URLEncodedUtils.format(nameValuePairs, Consts.UTF_8));
			}
			HttpGet httpGet = new HttpGet(sb.toString());
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_TIMEOUT).setConnectTimeout(HTTP_TIMEOUT).build();
			httpGet.setConfig(requestConfig);
			httpResponse = httpClient.execute(httpGet);
			logger.info("http status:{} ", httpResponse.getStatusLine());
			if (httpResponse.getEntity() != null) {
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			logger.error("执行http get方法出错", e);
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
		}

//		FileUtil.writeStringToFile(result, GET_PATH);
		return result;
	}

	public static String post(String url, String jsonParams) {
		String result = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_TIMEOUT).setConnectTimeout(HTTP_TIMEOUT).build();
			httpPost.setConfig(requestConfig);
			//JSON字符串
			StringEntity entity = new StringEntity(jsonParams, Consts.UTF_8);
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			logger.info("http status: {}", httpResponse.getStatusLine());
			if (httpResponse.getEntity() != null) {
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			logger.error("执行http post方法出错", e);
			
		}finally {
			HttpClientUtils.closeQuietly(httpResponse);
//			HttpClientUtils.closeQuietly(httpClient);
		}
//		FileUtil.writeStringToFile(result, POST_PATH);
		return result;
	}

	/**
	 * 简单封装post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String, Object> params) {
		logger.info("HttpUtil.get \n参数\r url:{},\n参数\r params:{}", url, params);
		String result = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_TIMEOUT).setConnectTimeout(HTTP_TIMEOUT).build();
			httpPost.setConfig(requestConfig);
			if (params!=null&&params.size()!=0) {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					nameValuePairs.add(new BasicNameValuePair(entry.getKey(), ObjectUtils.toString(entry.getValue())));
				}
				HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
				if (httpEntity != null) {
					httpPost.setEntity(httpEntity);
				}
			}
			httpResponse = httpClient.execute(httpPost);
			logger.info("http status: {}", httpResponse.getStatusLine());
			if (httpResponse.getEntity() != null) {
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception ex) {
			logger.error("",ex);
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
		}
//		FileUtil.writeStringToFile(UnicodeUtil.unicodeToString(result), POST_PATH);
		return result;
	}
	
	public static void main(String[] args) {
		// Sending a JSON object
		Unirest.post("http://httpbin.org/authors/post")
		        .header("accept", "application/json")
		        .header("Content-Type", "application/json")
		        .body("")
		        .asJson();
	}

}
