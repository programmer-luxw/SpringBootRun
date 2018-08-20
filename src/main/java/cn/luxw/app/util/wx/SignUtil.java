package cn.luxw.app.util.wx;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;

/**
 *签名
 * @author luxw
 * @version
 *    1.0
 */
public class SignUtil {
	
	/**
	 * 测试用来签名
	 * @param data
	 * @param apiKey
	 * @return
	 */
	public static String getSign(Map<String,Object> data,String apiKey){
		String result = null;
		if(data != null && data.size() != 0){
			Map<String, Object> paramMap = new TreeMap<String, Object>(data);
			StringBuilder sb = new StringBuilder();
			for(Map.Entry<String, Object> entry :paramMap.entrySet()){ 
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			sb.append("key=").append(apiKey);
			result = getMd5(sb.toString(),"md5");
		}
		return result;
	}
	
	private static String getMd5(String text, String algorithmName){
		if ((text == null) || ("".equals(text.trim()))) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if ((algorithmName == null) || ("".equals(algorithmName.trim()))) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(text.getBytes("UTF8"));
			byte[] arr = m.digest();
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				sb.append(Integer.toHexString(arr[i] & 0xFF | 0x100).substring(1, 3));
			}
			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			
		} catch (UnsupportedEncodingException e) {
		}
		return encryptText;
	}
	
	public static String getSign(Object o,String apiKey){
		String signStr = null;
		Map<String, Object> map = Maps.newTreeMap();
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(Boolean.TRUE);
				String signString = field.getName();
				if(!Objects.equal("sign", signString) && field.get(o)!=null){//为NULL的不参与计算签名
					map.put(field.getName(),field.get(o));
				}
			}
			String requestStr = Joiner.on("&").withKeyValueSeparator("=").join(map).concat("&key=").concat(apiKey);
			
			signStr = Hashing.md5().hashString(requestStr, Charsets.UTF_8).toString().toUpperCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return signStr;
	}
	

	
	
	
}

