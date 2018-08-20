package cn.luxw.app.util.https;

import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class Test {

	public static void main(String[] args) throws Exception {
		/*
		* 信任当前证书的方法
		* */
		String zhenshu = "";
		URL url = new URL("");

		CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
		// 关联证书的对象
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(null);
		String certificateAlias = Integer.toString(0);
		// 核心逻辑,信任什么证书,从Assets读取拷贝进去的证书
		keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(url.openStream()));
		SSLContext sslContext = SSLContext.getInstance("TLS");
		// 信任关联器
		final TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		// 初始化证书对象
		trustManagerFactory.init(keyStore);
		sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

	}

}
