package cn.luxw.app.util.wx;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static void ssl(String url) {
		CloseableHttpClient httpclient = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
			try {
				// ����keyStore d:\\tomcat.keystore  
				trustStore.load(instream, "123456".toCharArray());
			} catch (CertificateException e) {
				e.printStackTrace();
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}
			// �����Լ���CA��������ǩ���֤��
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
			// ֻ����ʹ��TLSv1Э��
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// ����http����(get��ʽ)
			HttpGet httpget = new HttpGet(url);
			System.out.println("executing request" + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: " + entity.getContentLength());
					System.out.println(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
				httpget.releaseConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(httpclient != null)
				try {
					httpclient.close();
				} catch (IOException e) {
				}
		}
	}

	@SuppressWarnings("deprecation")
	public static void postFile(String url, File file, String uid){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
		    FileBody fileBoday = new FileBody(file);
		    StringBody uidStr = new StringBody(uid);
		    MultipartEntity reqEntity = new MultipartEntity();
		    reqEntity.addPart("avatarFile", fileBoday);
		    reqEntity.addPart("uid", uidStr);
		    httppost.setEntity(reqEntity);
		    HttpResponse response = httpclient.execute(httppost);
 
		    int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK){
				System.out.println("服务器正常响应.....");
		    	HttpEntity resEntity = response.getEntity();
		    	System.out.println(EntityUtils.toString(resEntity));//httpclient自带的工具类读取返回数据
		    	System.out.println(resEntity.getContent());   
		    	EntityUtils.consume(resEntity);
		    }
			    
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
			    try { 
			    	httpclient.getConnectionManager().shutdown(); 
			    } catch (Exception ignore) {
			    	
			    }
			}
	}
	
	public static String post(String url, Map<String, String> params) {
		// ����Ĭ�ϵ�httpClientʵ��.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// ����httppost  
		HttpPost httppost = new HttpPost(url);
		// �����������  
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();;
		if(params != null && params.size() > 0){
			for (String key : params.keySet()) {
				formparams.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(httppost != null)
				httppost.releaseConnection();
		}
		return null;
	}

	public static String getHttpclient(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = null;
		try {  
			httpget = new HttpGet(url);
			httpget.setHeader("Content-Type","	text/html; charset=utf-8");
			httpget.setHeader("User-Agent", "	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
			httpget.setHeader("Cache-Control", "no-cache");
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					 return EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(httpget != null)
				httpget.releaseConnection();
		}
		return null;
	}
	
	public static String get(String url, Map<String, Object> params){
		StringBuilder sb = new StringBuilder();
		if (params != null) {
			url = url + "?";
			for (Entry<String, Object> e : params.entrySet()) {
				try {
					sb.append(e.getKey() + "=" + URLEncoder.encode(e.getValue().toString().trim(),"utf-8") + "&");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
			url = url + sb.substring(0, sb.length() - 1);
		}
		
		HttpURLConnection con = null;
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		StringBuilder buffer = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static String post(String url, Map<String, Object> params, Object content, String contentType) {
		StringBuilder sb = new StringBuilder();
		String p = "";
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet()) {
				sb.append(e.getKey() + "=" + e.getValue().toString().trim() + "&");
			}
			p = sb.substring(0, sb.length() - 1);
		}
		
		HttpURLConnection con = null;
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", contentType);
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(p.trim());
			if(content != null) osw.write(content.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		StringBuilder buffer = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString();
	}
	
	public static String upload(String requestUrl, Map<String, Object> params, Map<String, String> attachments) {
		String twoHyphens = "--";
		String boundary =  "RQdzAAihJq7Xp1kjraqf";
		String lineEnd = "\r\n";
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(20000 /* milliseconds */);
			conn.setConnectTimeout(20000 /* milliseconds */);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			//conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//conn.setRequestProperty("Charsert", "UTF-8"); 
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

			if (params != null) {
				for (Entry<String, Object> entry : params.entrySet()) {
					dos.writeBytes(twoHyphens + boundary + lineEnd);
					dos.writeBytes("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + lineEnd);
					dos.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
					dos.writeBytes("Content-Length: " + entry.getValue().toString().length() + lineEnd);
					dos.writeBytes(lineEnd);
					dos.writeBytes(entry.getValue().toString() + lineEnd);
				}
			}
			
			if (attachments != null) {
				for (Entry<String, String> entry : attachments.entrySet()) {
					File file = new File(entry.getValue());
					dos.writeBytes(twoHyphens + boundary + lineEnd);
					dos.writeBytes("Content-Disposition: form-data; name=\"" + entry.getKey() + "\";filename=\"" + file.getName() + "\"" + lineEnd);
				    dos.writeBytes("Content-Type: image/jpeg" + lineEnd);
				    dos.writeBytes(lineEnd);
				    
				    BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
				    int bytes = 0;
					byte[] buffer = new byte[1024];
					while ((bytes = stream.read(buffer)) != -1) {
						dos.write(buffer, 0, bytes);
					}
				    dos.writeBytes(lineEnd);
					stream.close();
				}
			}
		    
		    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
		    dos.flush();
		    dos.close();
		    
		    StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append("\n");
			}
			return sb.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
}
