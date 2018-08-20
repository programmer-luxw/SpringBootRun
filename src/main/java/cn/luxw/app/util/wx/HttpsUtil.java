package cn.luxw.app.util.wx;


import java.io.File;
import java.io.FileInputStream;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;

public class HttpsUtil {

	
	private static final Logger logger = LoggerFactory.getLogger(HttpsUtil.class);
	// private final Logger wxLogger = LoggerFactory.getLogger("wxPay");

	// 表示请求器是否已经做了初始化工作
	private static boolean hasInit = Boolean.FALSE;
	// 连接超时时间，默认10秒
	private static final int socketTimeout = 10000;
	// 传输超时时间，默认30秒
	private static final int connectTimeout = 30000;
	// 请求器的配置
	private static RequestConfig requestConfig;
	// HTTP请求器
	private static CloseableHttpClient httpClient;

	private HttpsUtil() {
	}

	static{
		System.out.println("。。。。HttpsUtil。。。static代码块。。。。");
		init();
	}
	private static void init() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream inputStream = new FileInputStream(new File(ConfigUtil.getCertPath()));// 加载本地的证书进行https加密传输
			try {
				keyStore.load(inputStream, ConfigUtil.getCertPass().toCharArray());// 设置证书密码
			} catch (CertificateException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
			}

			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, ConfigUtil.getCertPass().toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

			// 根据默认超时限制初始化requestConfig
			requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

			hasInit = Boolean.TRUE;

		} catch (Exception e) {
			System.out.println("===================");
			throw new RuntimeException(e);
		}

	}

	public static String sendPost(String apiUrl, Object xmlObj) {
		if (!hasInit || httpClient == null || requestConfig == null) {
			init();
		}
		String result = null;
		
		HttpPost httpPost = new HttpPost(apiUrl);
		String postDataXML = XmlUtil.toXML(xmlObj);
		logger.debug("HTTPS_POST请求的数据是:\n{}", postDataXML);
		
		StringEntity postEntity = new StringEntity(postDataXML, Charsets.UTF_8);
		httpPost.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.XML_UTF_8.toString());
		httpPost.setEntity(postEntity);

		// 设置请求器的配置
		httpPost.setConfig(requestConfig);

		logger.debug("executing request:{}", httpPost.getRequestLine());
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, Charsets.UTF_8);

		} catch (ConnectionPoolTimeoutException e) {
			logger.debug("http get throw ConnectionPoolTimeoutException(wait time out)");

		} catch (ConnectTimeoutException e) {
			logger.debug("http get throw ConnectTimeoutException");

		} catch (SocketTimeoutException e) {
			logger.debug("http get throw SocketTimeoutException");

		} catch (Exception e) {
			logger.debug("http get throw Exception");

		} finally {
			httpPost.abort();
		}
		logger.debug("HTTPS_POST返回的数据是:\n{}", result);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
//		PayQueryReqData payQueryReqData = new PayQueryReqData("1251880101201507251108456139");
////		String result = sendPost(Const.PAY_QUERY_URL, payQueryReqData);
////		System.out.println(result);
////		PayQueryResData payQueryResData = XmlUtil.getObjectFromXML(result, PayQueryResData.class);
////		System.out.println(payQueryResData);
//		
//		String billNo = RandomUtil.createBillNo(1L,"");
//		System.out.println(billNo);
//		Files.append(billNo+"\r\n", new File("c:/billNo.txt"), Charsets.UTF_8);
////		
//		PayReqData payReqData = new PayReqData("oZ0NHxAK7h5Epw9wE5SbpOSIvVo0",100,"1251880101201507251209466883",Const.ACTIVITY_WISH_STANDARD);
//		String result = sendPost(Const.PAY_URL, payReqData);
//		System.out.println(result);
//		PayResData payResData = XmlUtil.getObjectFromXML(result, PayResData.class);
		
		
		
	}
}
