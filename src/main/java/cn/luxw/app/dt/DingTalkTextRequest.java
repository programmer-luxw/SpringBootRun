package cn.luxw.app.dt;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 钉钉文本消息请求VO
 *
 * @author Waylon
 * @date 2019/3/19
 */
@Data
public class DingTalkTextRequest {
    private String msgtype = "text";

    private DingTalkTextContentVO text;

    private DingTalkTextAtVO at;
    
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
