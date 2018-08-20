package cn.luxw.app.util.wx;


import java.util.ResourceBundle;

public class ConfigUtil {

	private static final String BUNDLE_NAME = "com.zhidian3g.weixin.constant.weixin";
	private static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

	private ConfigUtil() {
	}

	public static String getAppId() {
		return bundle.getString("APP_ID");
	}

	public static String getApiKey() {
		return bundle.getString("API_KEY");
	}

	public static String getMchId() {
		return bundle.getString("MCH_ID");
	}

	public static String getCertPath() {
		return bundle.getString("CERT_PATH");
	}

	public static String getCertPass() {
		return bundle.getString("CERT_PASS");
	}

	public static String getClientIp() {
		return bundle.getString("CLIENT_IP");
	}
	
	
	public static String getWebReqKey(){
		return bundle.getString("WEB_REQ_KEY");
	}
	
	
	public static String getEmailAddr(){
		return bundle.getString("EMAIL_ADDR");
	}
	
	

	// private static Properties props = new Properties();
	// static {
	// // 1，加载配置文件
	// InputStream in = null;
	// try {
	// in = ConfigUtil.class.getResourceAsStream("weixin.properties");
	// props.load(in);
	// } catch (IOException e) {
	// throw new RuntimeException(e);
	// } finally {
	// if (in != null) {
	// try {
	// in.close();
	// } catch (IOException e) {
	// throw new RuntimeException(e);
	// }
	// }
	// }
	//
	// }

}
