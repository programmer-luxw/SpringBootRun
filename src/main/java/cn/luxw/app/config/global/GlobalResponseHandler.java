//package cn.luxw.app.config.global;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import com.offerslook.api.common.domain.resp.Response;
//
//
//
///**
// * 成功响应信息统一处理
// * @author July
// *
// */
////@Order(1)
//@ControllerAdvice(basePackages = {"com.offerslook.api.affiliate.web.user","com.offerslook.api.affiliate.web.offer"})
//public class GlobalResponseHandler implements ResponseBodyAdvice<Object>{
//
//	@Override
//	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//		return true;
//	}
//
//	@Override
//	public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//		Response respone  = new Response();
//		respone.setData(data);
//		return respone;
//	}
//
//	
//	
//}
