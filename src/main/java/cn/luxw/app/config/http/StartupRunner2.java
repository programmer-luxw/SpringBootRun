//package cn.luxw.app.config.http;
//
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.util.concurrent.TimeUnit;
//
//import javax.net.ssl.SSLContext;
//
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.TrustAllStrategy;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.ssl.TrustStrategy;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//import com.mashape.unirest.http.Unirest;
//
////@Order(value = 5)
//@Configuration
//public class StartupRunner2 implements CommandLineRunner {
//	private static Logger LOG = LoggerFactory.getLogger(StartupRunner2.class);
//
//	//private static final int socketTimeout = 120000;
//	//private static final int connectionTimeout = 60000;
////	private static final int maxConnTotal = 0;
////	private static final int maxConnPerRoute = 0;
//	//@Autowired
//	//private Runnable  issuedJobService;
//
//	@Override
//	public void run(String... args) throws Exception {
//		LOG.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作  <<<<<<<<<<<<<");
//		initUnirestHttpsConfig();
//	}
//
//	/**
//	 * https://blog.csdn.net/qijiqiguai/article/details/76929203
//	 * @HttpClientBuilder
//	 * 初始化Unirest 发送https签名,信任所有
//	 * 
//	 * @throws KeyManagementException
//	 * @throws NoSuchAlgorithmException
//	 * @throws KeyStoreException
//	 */
//	private void initUnirestHttpsConfig() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
//		LOG.info(">>>>>>>>>>>>>>> 初始化Unirest  发送https签名,信任所有  <<<<<<<<<<<<<");
//		//System.setProperty("javax.net.debug", "all");
//		//信任所有的证书
//		//TrustStrategy strategy = (certificate, authType) -> true;
//		SSLContext sslContext = SSLContexts.custom()//.setProtocol("TLS")
//				.loadTrustMaterial(null, new TrustAllStrategy()).build();
//		/*
//		Registry registry =  RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.getSocketFactory())
//                .register("https", SSLConnectionSocketFactory.getSocketFactory())
//                .build();
//		
//		PoolingHttpCli entConnectionManager cm =new PoolingHttpClientConnectionManager(registry);*/
//		// 同步
//		CloseableHttpClient httpclient = HttpClients.custom()//
//				.setSSLContext(sslContext)//
//				//校验名称,这个对象就是信任所有的主机,也就是信任所有https的请求
//				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)//
//				//.setSSLHostnameVerifier(SSLConnectionSocketFactory.getDefaultHostnameVerifier())
//				.build();
//		
//		Unirest.setHttpClient(httpclient);
//		
//		LOG.info(">>>>>>>>>>>>>>>https初始化完毕  <<<<<<<<<<<<<");
//		//Unirest.get("").asString().getBody();
////		Unirest.setConcurrency(maxTotal, maxPerRoute);
//	}
//	
//	
//	@Bean
//    public RestTemplate customRestTemplate() {
//        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
//        httpRequestFactory.setReadTimeout(30 * 1000);
//        httpRequestFactory.setConnectTimeout(30 * 1000);
//        return new RestTemplate(httpRequestFactory);
//    }
//
//}