//package cn.luxw.app.config.global;
//
//import java.util.Objects;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.offerslook.api.common.constant.http.HttpErrorMessage;
//import com.offerslook.api.common.constant.http.HttpErrorPage;
//import com.offerslook.api.common.domain.resp.RespStatus;
//import com.offerslook.api.common.domain.resp.Response;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * web.xml错误信息的处理
// * 
// * @author July
// *
// */
//@Slf4j
//@RestController
//@RequestMapping(HttpErrorPage.PATH)
//public class GlobalErrorPageHandler /* implements ErrorController */ {
//	
//	@RequestMapping(HttpErrorPage.ERROR_401)
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)
//	public Response page401(HttpServletRequest request, HttpServletResponse response) {
//		log.debug("[page401] API Key is wrong");
//		String requestURI = Objects.toString(request.getAttribute(HttpErrorPage.SAVED_REQUEST));
//		Response resp = new Response(RespStatus.API_KEY_WRONG, Response.getErrorMsg(requestURI, HttpStatus.UNAUTHORIZED.value(), HttpErrorMessage.API_KEY_WRONG));
//		return resp;
//	}
//	
//	@RequestMapping(HttpErrorPage.ERROR_403) //
//	@ResponseStatus(HttpStatus.FORBIDDEN)
//	public Response page403(HttpServletRequest request, HttpServletResponse response) {
//		log.debug("[page403] No permissions visited");
//		String requestURI = Objects.toString(request.getAttribute(HttpErrorPage.SAVED_REQUEST));
//		Response resp = new Response(RespStatus.NO_AUTHENTICATION, Response.getErrorMsg(requestURI, HttpStatus.FORBIDDEN.value(), HttpErrorMessage.NO_AUTHENTICATION));
//		return resp;
//	}
//	
//	@RequestMapping(HttpErrorPage.ERROR_404)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public Response page404(HttpServletRequest request, HttpServletResponse response) {
//		log.debug("[page404] Page Not Found");
//		String requestURI = Objects.toString(request.getAttribute(HttpErrorPage.SAVED_REQUEST));
//		Response resp = new Response(RespStatus.RESOURCE_NOT_FOUND, Response.getErrorMsg(requestURI, HttpStatus.NOT_FOUND.value(), HttpErrorMessage.NO_RESOURCE));
//		return resp;
//	}
//
//	@RequestMapping(HttpErrorPage.ERROR_500)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public Response page500(HttpServletRequest request, HttpServletResponse response) {
//		log.debug("[page500] Internal Server Error");
//		String requestURI = Objects.toString(request.getAttribute(HttpErrorPage.SAVED_REQUEST));
//		Response resp = new Response(RespStatus.SERVER_ERROR, Response.getErrorMsg(requestURI, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpErrorMessage.SERVER_ERROR));
//		return resp;
//	}
//}
