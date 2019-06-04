//package cn.luxw.app.config.global;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import com.offerslook.api.common.constant.http.HttpErrorMessage;
//import com.offerslook.api.common.domain.resp.RespStatus;
//import com.offerslook.api.common.domain.resp.Response;
//import com.offerslook.api.common.domain.thread.ThreadLocalHolder;
//import com.offerslook.api.common.exception.APIResourceNotFountException;
//
//import cn.hutool.core.collection.CollUtil;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//	/**
//	 *SpringMvc注解参数校验
//	 * @param request
//	 * @param e
//	 * @return
//	 */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Response methodParamValid(HttpServletRequest request, MethodArgumentNotValidException e) {
//		List<String> errorMsgList = e.getBindingResult().getAllErrors().stream().filter(FieldError.class::isInstance)//
//				.map(this::mapErrorMsg).collect(Collectors.toList());
//		if (CollUtil.isEmpty(errorMsgList)) {
//			errorMsgList.add(e.getBindingResult().getAllErrors().stream().findFirst().orElseGet(() -> new ObjectError("", "No Error")).getDefaultMessage());
//		}
//		return new Response(RespStatus.PARAM_ERROR, Response.getErrorMsg(request.getRequestURI(),HttpStatus.BAD_REQUEST.value(), errorMsgList.toString()));
//	}
//	/**
//	 * 资源没找到
//	 * @param request
//	 * @param e
//	 * @return
//	 */
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ExceptionHandler(APIResourceNotFountException.class)
//	public Response resourceNotFound(HttpServletRequest request, APIResourceNotFountException e) {
//		return new Response(RespStatus.RESOURCE_NOT_FOUND, Response.getErrorMsg(request.getRequestURI(),HttpStatus.NOT_FOUND.value(), e.getMessage()));
//	}
//	
//	/**
//	 * json格式不正确
//	 * @param request
//	 * @return
//	 */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public Response jsonFormatError(HttpServletRequest request) {
//		return new Response(RespStatus.PARAM_ERROR, Response.getErrorMsg(request.getRequestURI(),HttpStatus.BAD_REQUEST.value(), "JSON format error"));
//	}
//	
//	/**
//	 * 路径参数非法(eg:数字填字符串)
//	 * @param request
//	 * @return
//	 */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler({MethodArgumentTypeMismatchException.class,MissingServletRequestParameterException.class})
//	public Response badRequest(HttpServletRequest request) {
//		return new Response(RespStatus.BAD_REQUEST, Response.getErrorMsg(request.getRequestURI(),HttpStatus.BAD_REQUEST.value(), "Request parameter error"));
//	}
//	
//	/**
//	 * 405 没有该HTTP method类型的方法
//	 * @param request
//	 * @param e
//	 * @return
//	 */
//	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	public Response methodNotAllowed(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
//		return new Response(RespStatus.METHOD_NOT_ALLOWED, Response.getErrorMsg(request.getRequestURI(),HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage()));
//	}
//	
//	/**
//	 * 严重错误
//	 * @param request
//	 * @param ex
//	 * @return
//	 */
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(Exception.class)
//	public Response resolveException(HttpServletRequest request, Exception e) {
//		Integer pid = ThreadLocalHolder.getAffId();
//		Integer affId = ThreadLocalHolder.getPid();
//		log.error("[Internal Server Error] pid:{},affId:{},path:{}",pid,affId,Objects.toString(request.getRequestURI()),e);
//		return new Response(RespStatus.INTERNAL_SERVER_ERROR, Response.getErrorMsg(request.getRequestURI(),HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpErrorMessage.SERVER_ERROR));
//	}
//
//
//
//	/**
//	 * error提示信息
//	 * @param objectError
//	 * @return
//	 */
//	private String mapErrorMsg(ObjectError objectError) {
//		FieldError error = (FieldError) objectError;
//		String rejectedValue = Objects.toString(error.getRejectedValue(), "");// old value
//		String defMsg = Objects.toString(error.getDefaultMessage(),"");
//		if (rejectedValue.contains("@")) {// 排除类上面的注解提示
//			return defMsg;// 自定义注解，自己去确定错误字段
//		} else {
//			if (defMsg.contains(":")) {
//				return (error.getField() + " " + defMsg);
//			} else {
//				return (error.getField() + " " + defMsg + ":" + rejectedValue);
//			}
//		}
//	}
//
//}
