package cn.luxw.app.util.wx;


import java.util.UUID;

/**
 * 随机生成32位字符串
 * @author luxw
 * @version
 *    1.0
 */
public class UuidUtil {
	
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
	
}
