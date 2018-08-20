package cn.luxw.app.config;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
@Configuration
@ConditionalOnClass({ B.class, C.class })
@ConditionalOnProperty(prefix = "spring.a.b", havingValue = "true",name = "test", matchIfMissing = false)
public class A {
	
	
	 public final static void main(String[] args) throws Exception {
		 	//客户端证书
		 	KeyStore keyStore = KeyStore.getInstance("PKCS12");
	        keyStore.load(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\jiaoyiping.p12")), "123456".toCharArray());
		 
	        // Trust own CA and all self-signed certs
		 	//信任JDK自带的和自签名的证书
	        SSLContext sslcontext = SSLContexts.custom()
	        		//服务器没有提供证书，只能信任所有的
	        		.loadTrustMaterial(new TrustStrategy() {
	                    @Override
	                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	                        return true;
	                    }
	                })
	        		//加载服务端提供的truststore(如果服务器提供truststore的话就不用忽略对服务器端证书的校验了)
	        		/*.loadTrustMaterial(new File("D:\\truststore.jks"), "123456".toCharArray(),
	                 new TrustSelfSignedStrategy())*/
	        		
	        		//加载客户端证书
	        		 .loadKeyMaterial(keyStore, "xxx".toCharArray())
	                .build();
	        
	        //JDK 8(March 2014 to present)：TLSv1.2 (default)，TLSv1.1，TLSv1，SSLv3
	        //JDK 7(July 2011 to present)：TLSv1.2，TLSv1.1，TLSv1 (default)，SSLv3
	        //JDK 6(2006 to end of public updates 2013)：TLS v1.1 (JDK 6 update 111 and above)，TLSv1 (default)，SSLv3
	        // Allow TLSv1 protocol only
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1"},
	                null,
	                SSLConnectionSocketFactory.getDefaultHostnameVerifier());// 客户端验证服务器身份的策略
	        
	        // Create a registry of custom connection socket factories for supported
	        // protocol schemes.
	        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
	                .<ConnectionSocketFactory> create()
	                .register("http", PlainConnectionSocketFactory.INSTANCE)
	                .register("https", new SSLConnectionSocketFactory(sslcontext))
	                .build();
	        
	        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
	        connManager.setMaxTotal(100);
	        connManager.setDefaultMaxPerRoute(10);
	        // 个性化设置某个url的连接
	        connManager.setMaxPerRoute(new HttpRoute(new HttpHost("www.y.com",80)), 20);
	        
	        
	        CloseableHttpClient httpclient = HttpClients.custom()
	        		.setConnectionManager(connManager)
	                .build();
	        
	       /* CloseableHttpClient httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();*/
	        try {

	            HttpGet httpget = new HttpGet("https://httpbin.org/");

	            System.out.println("Executing request " + httpget.getRequestLine());

	            CloseableHttpResponse response = httpclient.execute(httpget);
	            try {
	                HttpEntity entity = response.getEntity();

	                System.out.println("----------------------------------------");
	                System.out.println(response.getStatusLine());
	                EntityUtils.consume(entity);
	            } finally {
	                response.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	    }

}
