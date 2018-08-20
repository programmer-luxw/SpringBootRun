package cn.luxw.app.util.secure;


import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil{
    // 加密  
	/**
	 * 
	 * @param text  需要加密的字符串
	 * @param key  私钥AES需要16位
	 * @param IVString 随机16位IV值
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("restriction")
	public static String Encrypt(String text, String key,String IVString) throws Exception {  
        if (key == null) {  
            System.out.print("Key为空null");  
            return null;  
        }  
        // 判断Key是否为16位  
        if (key.length() != 16) {  
            System.out.print("Key长度不是16位");  
            return null;  
        }  
       /* byte[] data1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        System.out.println(data1.length);*/
        byte[] raw = key.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"  实例化加密类，参数为加密方式，要写全
        IvParameterSpec iv = new IvParameterSpec(IVString.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度  
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);   //初始化，此方法可以采用三种方式，按服务器要求来添加。
        byte[] encrypted = cipher.doFinal(text.getBytes());  //加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE, 
        byte[] bytes = IVString.getBytes();
        byte [] newArr=new byte[encrypted.length+bytes.length];
        System.arraycopy(bytes, 0, newArr, 0, bytes.length);  
        System.arraycopy(encrypted, 0, newArr, bytes.length, encrypted.length);
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        byte[] result = Base64.getEncoder().encode(newArr);  
        return new String(result);
    }  
  
    // 解密  
    public static String Decrypt(String text, String key,String IVString) throws Exception {   
            // 判断Key是否正确  
            if (key == null) {  
                System.out.print("Key为空null");  
                return null;  
            }  
            // 判断Key是否为16位  
            if (key.length() != 16) {  
                System.out.print("Key长度不是16位");  
                return null;  
            }  
            byte[] raw = key.getBytes("ASCII");  
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
            IvParameterSpec iv = new IvParameterSpec(IVString 
                    .getBytes());  
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);  
            byte[] encrypted1 = Base64.getDecoder().decode(text);//先用base64解密  
            byte[] newArr=new byte[encrypted1.length-16];
            System.arraycopy(encrypted1, 16, newArr, 0, newArr.length); 
            //System.out.println("IVBASE64"+new Base64().encodeBase64String(IVString.getBytes()));  
                byte[] original = cipher.doFinal(newArr);  
                String originalString = new String(original);  
                return  new String(encrypted1).substring(0,16)+originalString;    
    }  
    
    public static String getRandomIVString() { //length表示生成字符串的长度  
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }     
        return sb.toString();     
     }   
  
    public static void main(String[] args) throws Exception {  
        /* 
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定 
         * 此处使用AES-128-CBC加密模式，key需要为16位。 
         */  
        String cKey = "MODULE46DA041C44";  
        // 需要加密的字串  
        String cSrc = "searchCBB:1D62$a6^&afD21&Ywrf3^fe*15DS";

        // 加密  
        long lStart = System.currentTimeMillis();  
        String randomIVString = getRandomIVString();
        String enString = AESUtil.Encrypt(cSrc, cKey,randomIVString);  
        System.out.println("加密后的字串是：" + enString);  
            System.out.println("随机生成IV是"+randomIVString);
        long lUseTime = System.currentTimeMillis() - lStart;  
        System.out.println("加密耗时：" + lUseTime + "毫秒");  
        // 解密  
        lStart = System.currentTimeMillis();  
        String DeString = AESUtil.Decrypt(enString, cKey);  
        System.out.println("解密后的字串是：" + DeString);  
        lUseTime = System.currentTimeMillis() - lStart;  
        System.out.println("解密耗时：" + lUseTime + "毫秒");  
        
    }

	private static String Decrypt(String text, String key) {
		   try {  
	            // 判断Key是否正确  
	            if (key == null) {  
	                System.out.print("Key为空null");  
	                return null;  
	            }  
	            // 判断Key是否为16位  
	            if (key.length() != 16) {  
	                System.out.print("Key长度不是16位");  
	                return null;  
	            }  
	            byte[] raw = key.getBytes("ASCII");  
	            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
	            byte[] encrypted1 = Base64.getDecoder().decode(text);//先用base64解密  
	            byte[] ivArr=new byte[16];
	            for (int i = 0; i < 16; i++) {
					
					ivArr[i]=encrypted1[i];
				}
	            IvParameterSpec iv = new IvParameterSpec(ivArr);  
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);	           
	            byte[] newArr=new byte[encrypted1.length-16];
	            System.arraycopy(encrypted1, 16, newArr, 0, newArr.length);
	            try {  
	                byte[] original = cipher.doFinal(newArr);  
	                String originalString = new String(original);  
	                return  new String(ivArr)+originalString;  
	            } catch (Exception e) {  
	                System.out.println(e.toString());  
	                return null;  
	            }  
	        } catch (Exception ex) {  
	            System.out.println(ex.toString());  
	            return null;  
	        }  
	}  
}  

