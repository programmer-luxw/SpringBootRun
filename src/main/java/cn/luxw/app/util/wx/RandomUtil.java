package cn.luxw.app.util.wx;


import java.util.Random;

import com.google.common.base.Objects;

public class RandomUtil {
	private static final String NUMBER_CHAR = "0123456789";

	public static String randNumberChars(int length) {
		return generateCharacters(NUMBER_CHAR, length);
	}

	private static String generateCharacters(String inputStr, int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int slen = inputStr.length();
		for (int i = 0; i < length; i++) {
			sb.append(inputStr.charAt(random.nextInt(slen)));
		}
		return sb.toString();
	}

	public static String createBillNo(Long id,String insertDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(Math.abs(Objects.hashCode(UuidUtil.getUuid())));
		
		if(sb.length() < 10){
			sb.append(randNumberChars(10 -sb.length()));
		}
//		String nowTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
		sb.delete(10, sb.length()).insert(0, insertDate).insert(0, ConfigUtil.getMchId());
		return sb.toString();
	}
	
	
	
	
	public static void main(String[] args) {
		Long id = 1L;
		StringBuilder sb = new StringBuilder(String.valueOf(id));
		sb.append(Math.abs(Objects.hashCode(UuidUtil.getUuid())));
		System.out.println(sb.length());
		if(sb.length() < 10){
			System.out.println("+++++");
			sb.append(randNumberChars(10 -sb.length()));
		}
		sb.delete(10, sb.length());
		System.out.println(sb.toString()+"==="+sb.length());
	}
	
}
