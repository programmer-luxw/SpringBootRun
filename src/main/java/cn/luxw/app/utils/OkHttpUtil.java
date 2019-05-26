package cn.luxw.app.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {

	private static OkHttpClient client = null;
	static {
		client = new OkHttpClient.Builder()
				//.sslSocketFactory(sslSocketFactory(), x509TrustManager())//
//				.hostnameVerifier(verifiedAllHostname)
				//.retryOnConnectionFailure(false)//是否开启缓存
				.retryOnConnectionFailure(false)//
				.connectionPool(pool())//
				.connectTimeout(30, TimeUnit.SECONDS)//
				.readTimeout(30, TimeUnit.SECONDS)//
				.writeTimeout(30, TimeUnit.SECONDS)//
				//.protocols(Arrays.asList(Protocol.HTTP_1_1))
//				.addInterceptor(new NbTokenInterceptor())//拦截器
				.build();//
	}

	// @Bean
	public X509TrustManager x509TrustManager() {
		return new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			}
			@Override
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			}
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
	}

	// @Bean
	public SSLSocketFactory sslSocketFactory() {
		try {
			// 信任任何链接
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { x509TrustManager() }, new SecureRandom());
			return sslContext.getSocketFactory();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	  private static SSLSocketFactory getSslSocketFactory() {
//	        SSLSocketFactory ssl = null;
//	        try {
//	            System.out.println(ResourceUtils.getURL("classpath:").getPath());
//
//
//	            ClassPathResource selfcertPath = new ClassPathResource(SELFCERTPATH);
//	            ClassPathResource trustcaPath = new ClassPathResource(TRUSTCAPATH);
//	            KeyStore selfCert = KeyStore.getInstance("pkcs12");
//	            selfCert.load(selfcertPath.getInputStream(), SELFCERTPWD.toCharArray());
//	            KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
//	            kmf.init(selfCert, SELFCERTPWD.toCharArray());
//
//	            // 导入CA证书
//	            KeyStore caCert = KeyStore.getInstance("jks");
//	            caCert.load(trustcaPath.getInputStream(), TRUSTCAPWD.toCharArray());
//	            TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
//	            tmf.init(caCert);
//
//	            SSLContext sc = SSLContext.getInstance("TLS");
//	            sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
//	            ssl = sc.getSocketFactory();
//
//	        }catch (Exception e){
//	            e.printStackTrace();
//	        }
//
//	        return ssl;
//	    }


	// @Bean
	public static ConnectionPool pool() {
		return new ConnectionPool(10, 5, TimeUnit.MINUTES);
	}

	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	public static String post(String url, String json) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		//client.newCall(request).enqueue(callback)
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				return response.body().string();
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String get(String url) {
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String post(String url, Map<String, String> params) {
		FormBody.Builder builder = new FormBody.Builder();
		if (params != null && params.size() > 0) {
			params.forEach((k,v)->builder.add(k, v));
		}
		Request request = new Request.Builder().url(url).post(builder.build()).build();
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				return response.body().string();
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	public class RetryIntercepter implements Interceptor {

	    public int maxRetry;//最大重试次数
	    private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

	    public RetryIntercepter(int maxRetry) {
	        this.maxRetry = maxRetry;
	    }

	    @Override
	    public Response intercept(Chain chain) throws IOException {
	        Request request = chain.request();
	        System.out.println("retryNum=" + retryNum);
	        Response response = chain.proceed(request);
	        while (!response.isSuccessful() && retryNum < maxRetry) {
	            retryNum++;
	            System.out.println("retryNum=" + retryNum);
	            response = chain.proceed(request);
	        }
	        return response;
	    }
	}

}
