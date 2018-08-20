package cn.luxw.app.util;

import java.util.UUID;

public class UuidCreater {

	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}
	 /**
     * 生成34位UUID
     * @param str String
     * @return String
     */
    public static String getUUID(String str) {
        if(str.length()!=2){
            throw new RuntimeException("输入的参数的位数必须为两位！");
        }
        return str + getUuid();
        
    }
}
