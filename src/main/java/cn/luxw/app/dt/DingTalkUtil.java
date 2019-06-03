package cn.luxw.app.dt;

import java.io.IOException;
import java.util.List;

import cn.luxw.app.utils.OkHttpUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DingTalkUtil {
	
	
	
	 public static void sendDingTalkMsg(String logKey, String msg) {
//		 OkHttpUtil.post(url, json)
	
	 }
	

	@Data
	public class DingTalkTextRequest {
		private String msgtype = "text";
		private DingTalkTextContentVO text;
		private DingTalkTextAtVO at;
	}

	@Data
	@AllArgsConstructor
	public class DingTalkTextAtVO {
		/**
		 * 被@人的手机号
		 */
		private List<String> atMobiles;

		/**
		 * 当@所有人时:true,否则为:false
		 */
		private boolean isAtAll;
	}

	@Data
	@AllArgsConstructor
	public class DingTalkTextContentVO {
		/**
		 * 消息内容
		 */
		private String content;
	}

}
