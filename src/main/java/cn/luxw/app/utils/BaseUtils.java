package cn.luxw.app.utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

import com.google.common.base.Charsets;

public class BaseUtils {
	
	public void wrapping() throws IOException {  
		
		  String src = "This is the content of any resource read from somewhere" +  
		    " into a stream. This can be text, image, video or any other stream.";  
		  
		  // 编码器封装OutputStream, 文件/tmp/buff-base64.txt的内容是BASE64编码的形式  
		  try (OutputStream os = Base64.getEncoder().wrap(new FileOutputStream("/tmp/buff-base64.txt"))) {  
		    os.write(src.getBytes("utf-8"));  
		  }  
		  
		  // 解码器封装InputStream, 以及以流的方式解码, 无需缓冲  
		  // is being consumed. There is no need to buffer the content of the file just for decoding it.  
		  try (InputStream is = Base64.getDecoder().wrap(new FileInputStream("/tmp/buff-base64.txt"))) {  
		    int len;  
		    byte[] bytes = new byte[100];  
		    while ((len = is.read(bytes)) != -1) {  
		      System.out.print(new String(bytes, 0, len, "utf-8"));  
		    }  
		  }  
		}  
	
	/**
	 * 	base64编码  
	 * @param encodeStr
	 */
	public static String encode64(String encodeStr){
//		System.out.println(encodeStr.getBytes(Charsets.UTF_8));
		return Base64.getEncoder().encodeToString(encodeStr.getBytes(Charsets.UTF_8));  
	}
	
	
	/**
	 * base64解密
	 * @param str
	 * @return
	 */
	public static String decode64(String decodeStr){
		return new String(Base64.getDecoder().decode(decodeStr), Charsets.UTF_8);
	}

}
