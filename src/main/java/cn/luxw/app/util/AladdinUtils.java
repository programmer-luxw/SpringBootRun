package cn.luxw.app.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.google.common.base.Strings;
import com.google.common.io.Files;

public final class AladdinUtils {
//	private static final int COOKIE_MAX_AGE = 31536000;
//	private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	private static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	private static final String NUMBER_CHAR = "0123456789";

	public static String cleanCode(String str) {
		return cleanCode(str, Boolean.valueOf(true));
	}

	public static String cleanCode(String str, Boolean isCleanHtml) {
		if (Strings.isNullOrEmpty(str))
			return str;
		if (isCleanHtml.booleanValue())
			str = cleanHtml(str);
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		str = str.replaceAll("['　　']+", " ");
		return str;
	}

	public static String cleanHtml(String str) {
		return Jsoup.clean(str, Whitelist.none());
	}

	public static String cleanHtml(String str, Whitelist whitelist) {
		if (Strings.isNullOrEmpty(str))
			return str;
		return Jsoup.clean(str, whitelist);
	}

	public static String addZero(int source, int format) {
		if (format <= 0) {
			throw new IllegalArgumentException("元数据前补零长度必须大于0");
		}

		return String.format(new StringBuilder().append("%0").append(format).append("d").toString(), new Object[] { Integer.valueOf(source) });
	}

	public static String randZhChars(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("随机生成中文字符长度必须大于0");
		}
		int delta = 20902;
		StringBuilder sbuf = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sbuf.append((char) (19968 + new Random().nextInt(delta)));
		}
		return sbuf.toString();
	}

	public static String randAllChars(int length) {
		return generateCharacters("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
	}

	public static String randLetterChars(int length) {
		return generateCharacters("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
	}

	public static String randNumberChars(int length) {
		return generateCharacters("0123456789", length);
	}

	public static String getIpAddr(HttpServletRequest request) {
		if (request == null)
			return "0.0.0.0";
		try {
			String ip = request.getHeader("x-forwarded-for");
			if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
				ip = request.getRemoteAddr();
			}

			if (ip.contains("0:0:0:0:0:0:0:1"))
				;
			return "127.0.0.1";
		} catch (Exception ex) {
		}
		return "0.0.0.0";
	}

	public static String getIpRandom() {
		String[] ips = { "58.82.96.", "116.76.10.", "210.12.18.", "61.235.238.", "211.92.136.", "211.146.127." };

		Random random = new Random(System.nanoTime());
		int index = Math.abs(random.nextInt()) % ips.length;
		return new StringBuilder().append(ips[index]).append(Math.abs(random.nextInt()) % 255).toString();
	}

	public static boolean isInnerIP(String ipAddress) {
		boolean isInnerIp = false;
		long ipNum = getIpNum(ipAddress);
		long aBegin = getIpNum("10.0.0.0");
		long aEnd = getIpNum("10.255.255.255");
		long bBegin = getIpNum("172.16.0.0");
		long bEnd = getIpNum("172.31.255.255");
		long cBegin = getIpNum("192.168.0.0");
		long cEnd = getIpNum("192.168.255.255");
		isInnerIp = (isInner(ipNum, aBegin, aEnd)) || (isInner(ipNum, bBegin, bEnd)) || (isInner(ipNum, cBegin, cEnd)) || (ipAddress.equals("127.0.0.1"));
		return isInnerIp;
	}

	public static int getNumberAtFirst(long number, int index) {
		String str = Long.toString(number);
		return str.charAt(index - 1) - '0';
	}

	public static String createDirs(String root, String[] folders) {
		StringBuilder path = new StringBuilder();
		path.append(root).append("/");
		for (String folder : folders) {
			path.append(folder).append("/");
		}
		File folder = new File(path.toString().replace("\\\\", "/"));
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder.getAbsolutePath().replace("\\\\", "/");
	}

	public static void hashDirs(String path) {
		String[] dirs = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		for (String dir : dirs)
			for (String _dir : dirs)
				createDirs(new StringBuilder().append(path).append('/').append(dir).append(_dir).toString(), new String[0]);
	}

	public static String getFileExtension(String fileName) {
		if (Strings.isNullOrEmpty(fileName))
			return "";
		return Files.getFileExtension(fileName);
	}

	public static String dateToString(Date date, String format) {
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			throw new RuntimeException("日期转换为字符串", e);
		}
	}

	public static Date stringToDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("字符串转换为日期", e);
		}
	}

	public static String urlDecode(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("URL解码错误", e);
		}
	}

	public static String urlEncode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("URL编码错误", e);
		}
	}

	public static String obscurePasswd(String password, String salt) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("^#").append(password).append(salt).append("#~");
		return sha(sbuf.toString());
	}

	public static String md5(String inputStr) {
		return encrypt(inputStr, "md5");
	}

	public static String sha(String inputStr) {
		return encrypt(inputStr, "sha-1");
	}

	public static boolean isAjax(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if ((header != null) && ("XMLHttpRequest".equals(header))) {
			return true;
		}
		return false;
	}

	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("Referer");
	}

	public static String getURI(HttpServletRequest request) {
		return request.getRequestURI();
	}

	public static String getPrefix(HttpServletRequest request) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(request.getScheme());
		urlBuilder.append("://");
		urlBuilder.append(request.getServerName());
		urlBuilder.append(request.getServerPort() == 80 ? "" : new StringBuilder().append(":").append(request.getServerPort()).toString());
		urlBuilder.append(request.getContextPath());
		return urlBuilder.toString();
	}

	public static String buildUrl(HttpServletRequest request, String uri) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getPrefix(request));
		urlBuilder.append(uri);
		return urlBuilder.toString();
	}

	private static long getIpNum(String ipAddress) {
		String[] ip = ipAddress.split("\\.");
		long a = Integer.parseInt(ip[0]);
		long b = Integer.parseInt(ip[1]);
		long c = Integer.parseInt(ip[2]);
		long d = Integer.parseInt(ip[3]);

		long ipNum = a * 256L * 256L * 256L + b * 256L * 256L + c * 256L + d;
		return ipNum;
	}

	private static boolean isInner(long userIp, long begin, long end) {
		return (userIp >= begin) && (userIp <= end);
	}

	private static String encrypt(String text, String algorithmName) {
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
			byte[] s = m.digest();

			return hex(s);
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return encryptText;
	}

	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(Integer.toHexString(arr[i] & 0xFF | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	private static byte[] int2Byte(int intValue) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = ((byte) (intValue >> 8 * (3 - i) & 0xFF));
		}
		return b;
	}

	private static int byte2Int(byte[] b) {
		int intValue = 0;
		for (int i = 0; i < b.length; i++) {
			intValue += ((b[i] & 0xFF) << 8 * (3 - i));
		}
		return intValue;
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
	
	public static void main(String[] args) {
		Random r = new Random();
		System.out.println(r.nextInt(5));
	}
	
	
}