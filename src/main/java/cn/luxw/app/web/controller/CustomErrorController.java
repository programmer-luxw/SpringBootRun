package cn.luxw.app.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

/**
 * web.xml错误信息的处理
 * 
 * @author July
 */
@Slf4j
@RestController
@RequestMapping(CustomErrorController.PATH)
public class CustomErrorController /*implements ErrorController*/  {
	
	public static final String PATH = "/errors";
	public static final String PATH_401 = "/401";
	public static final String PATH_403 = "/403";
	public static final String PATH_404 = "/404";
	public static final String PATH_500 = "/500";
	

	
	/**
	 * ApiNotSupportReqPathException
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(PATH_404)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound404(HttpServletRequest request, HttpServletResponse response) {
		
		
		log.info("[notFound404] URL:, 404 error not found~ip:");
		
		return "404----5555";
	}

	

}
