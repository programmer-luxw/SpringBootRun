package cn.luxw.app.web.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public void test(MethodArgumentNotValidException e) {
		log.debug("[Global Error]:",e);
	}
}
