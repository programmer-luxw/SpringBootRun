//package cn.luxw.app.dt;
//
//import java.nio.charset.Charset;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//
//import cn.luxw.app.dt.DingTalkUtil.DingTalkTextAtVO;
//import cn.luxw.app.dt.DingTalkUtil.DingTalkTextContentVO;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @Description 钉钉预警服务
// * @Author luxiaowen
// * @Date 2019/5/20
// * @Version 1.0
// **/
//@Slf4j
//@Service
//public class PushServiceImpl  {
//
//    private static final HttpHeaders headers = new HttpHeaders();
//    @Autowired
//    private RestTemplate restTemplate;
//    private String url= "";
//
//    static {
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
//        headers.setAcceptCharset(Lists.newArrayList(Charset.forName("UTF-8")));
//    }
//
//    public void  sendDingTalkMsg(String logKey, String msg) {
//        DingTalkTextRequest request = new DingTalkTextRequest();
//        request.setText(new DingTalkTextContentVO(msg));
//        // 暂时通知静霞-15817125016、晓辉-18718913647、陈炜-18613154009、卢晓文-18925051206
//        request.setAt(new DingTalkTextAtVO(Lists.newArrayList("15817125016","18925051206"), true));
//        this.post(url, JSON.toJSONString(request));
//    }
//
//
//    private void post(String url, String jsonStr) {
//        HttpEntity<String> entity = new HttpEntity<>(jsonStr, headers);
//        ResponseEntity<String> responseEntity = null;
//        try {
//            //log.info("{}开始推送，请求路由：{}，请求报文：{}", logKey, url, jsonStr);
//            responseEntity = restTemplate.postForEntity(url, entity, String.class);
//            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
//                String bodyStr = responseEntity.getBody();
//               // log.info("{}响应报文：{}", logKey, bodyStr);
//            } else {
//               // log.error("{}失败，失败原因：{}", logKey, responseEntity);
//            }
//        } catch (Exception e) {
//            log.error("sendDingTalkMsg异常", e);
//        }
//    }
//
//
//}
