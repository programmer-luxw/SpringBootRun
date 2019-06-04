package cn.luxw.app.config.http;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.CharMatcher;

import kong.unirest.Unirest;


//@Order(value = 5)
//@Configuration//
public class StartupRunner implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(StartupRunner.class);

	private static final int socketTimeout = 120000;
	private static final int connectionTimeout = 60000;
//	private static final int maxConnTotal = 0;
//	private static final int maxConnPerRoute = 0;
	//@Autowired
	//private Runnable  issuedJobService;

	@Override
	public void run(String... args) throws Exception {
		LOG.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作  <<<<<<<<<<<<<");
		initUnirestHttpsConfig();
//		issuedJobService.execute();
		//new Thread(issuedJobService).start();
	}

	/**
	 * 初始化Unirest 发送https签名,信任所有
	 * 
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	private void initUnirestHttpsConfig() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		LOG.info(">>>>>>>>>>>>>>> 初始化Unirest  发送https签名,信任所有  <<<<<<<<<<<<<");
		//信任所有的证书
		//TrustStrategy strategy = (certificate, authType) -> true;
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustAllStrategy()).build();
		
		//RequestConfig requestConfig = RequestConfig.custom()//
				//.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

		//JDK 8(March 2014 to present)：TLSv1.2 (default)，TLSv1.1，TLSv1，SSLv3
        //JDK 7(July 2011 to present)：TLSv1.2，TLSv1.1，TLSv1 (default)，SSLv3
        //JDK 6(2006 to end of public updates 2013)：TLS v1.1 (JDK 6 update 111 and above)，TLSv1 (default)，SSLv3
        // Allow TLSv1 protocol only
		LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				   sslContext,
	                new String[] { "TLSv1"},
	                null,
	                SSLConnectionSocketFactory.getDefaultHostnameVerifier());// 客户端验证服务器身份的策略
		
		//获取默认的，都会检查
		//LayeredConnectionSocketFactory sslsf1 = new SSLConnectionSocketFactory(SSLContext.getDefault());
		// 异步
//		CloseableHttpAsyncClient asyncHttpclient = HttpAsyncClients.custom()//
//				.setSSLContext(sslContext)//
//				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)//
//				.setDefaultRequestConfig(requestConfig)//
//		.setMaxConnTotal(maxConnTotal).setMaxConnPerRoute(maxConnPerRoute)//
//				.build();
//		Unirest.setAsyncHttpClient(asyncHttpclient);
		   
       /* Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();*/
        
       // PoolingHttpClientConnectionManager cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);

		// 同步
		CloseableHttpClient httpclient = HttpClients.custom()//
				//.setSSLContext(sslContext)//
				.setSSLSocketFactory(sslsf)
				//校验名称,这个对象就是信任所有的主机,也就是信任所有https的请求
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)//
				//.setDefaultRequestConfig(requestConfig)//
				.build();
				//.setMaxConnTotal(maxConnTotal).setMaxConnPerRoute(maxConnPerRoute)//
		Unirest.config().httpClient(httpclient);
		//Unirest.get("").asString().getBody();
	}
	
	
	@Bean
    public RestTemplate customRestTemplate() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 1000);
        return new RestTemplate(httpRequestFactory);
    }
	
	public static void main(String[] args) {
		String mo = "159 66- 我 9 93311";
		String r = CharMatcher.inRange('0', '9').retainFrom(mo);
		//String r = CharMatcher.digit().retainFrom(mo);
		//String r = mo.replaceAll("\\s*|-", "");
		System.out.println(r);
	}

}