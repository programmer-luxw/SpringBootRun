//package cn.luxw.app.util.wx;
//
//
//import java.util.List;
//
//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.Email;
//import org.apache.commons.mail.SimpleEmail;
//
//import com.google.common.base.Charsets;
//import com.google.common.base.Splitter;
//import com.google.common.collect.Lists;
//import com.zhidian3g.weixin.constant.ConfigUtil;
//
//public class EmailUtil {
//	private static final String HOST = "smtp.exmail.qq.com";
//	private static final String USERNAME = "service1@ewing.com.cn";
//	private static final String PASSWORD = "sc123321";
//
//	public static List<String> sendSimpleEmail(String subjectMsg, String bodyMsg) {
//		String errorAddrStr = "";
//		List<String> errorAddrs = Lists.newArrayList();
//		for (String emailAddr : getReceivers()) {//循环发送，防止邮箱地址出错而发送失败
//			try {
//				errorAddrStr = emailAddr;
//				Email email = new SimpleEmail();
//				email.setHostName(HOST);
//				email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
//				email.setSSLOnConnect(Boolean.TRUE);
//				email.setCharset(Charsets.UTF_8.name());
//				email.setFrom(USERNAME);
//				email.setSubject(subjectMsg);
//				email.setMsg(bodyMsg);
//				email.addTo(emailAddr);
//				email.send();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				errorAddrs.add(errorAddrStr);
//				continue;
//			}
//		}
//		return errorAddrs;
//	}
//
//	/**
//	 * 暂时写死
//	 * 
//	 * @return
//	 */
//	public static List<String> getReceivers() {
//		 String emailAdrrStr = ConfigUtil.getEmailAddr();
//		 List<String> lists = Splitter.on("|").omitEmptyStrings().trimResults().splitToList(emailAdrrStr);
//		return lists;
//
//	}
//
//	public static void main(String[] args) {
////		List<String> b = EmailUtil.sendSimpleEmail("微信帐号余额不足", "微信帐号余额不足，请到商户平台充值！！！");
////		System.out.println(b.size()+"================"+b);
//	}
//
//}
