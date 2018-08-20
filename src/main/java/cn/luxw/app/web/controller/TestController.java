package cn.luxw.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import cn.luxw.app.service.TestService;
import cn.luxw.app.service.UserService;

@RestController
@RequestMapping("/index")
public class TestController {
	private UserService userService;

	public TestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("http")
	public String http() throws UnirestException {
//		userService.xx("xxx");
		String url = "http://192.168.7.6:8092/NetworkAPI/v/infos/env";
		int httpResp = Unirest.get(url).asString().getStatus();
		System.err.println("======"+httpResp);
		return "SUCCESS";
	}
	
	@GetMapping("https")
	public String https() throws UnirestException {
//		userService.xx("xxx");
		String url = "https://www.baidu.com";
		int httpResp = Unirest.get(url).asString().getStatus();
		System.err.println("======"+httpResp);
		return "SUCCESS555";
	}
	
	@GetMapping("https2")
	public String https2() throws UnirestException {
//		userService.xx("xxx");
		String url = "https://www.12306.cn/mormhweb";
		int httpResp = Unirest.get(url).asString().getStatus();
		System.err.println("======"+httpResp);
		return "SUCCESS555";
	}
	
	
	public static void main(String[] args) {
		//http://www.12306.cn/mormhweb/
		Thread t = new Thread(()-> {System.out.println("xxx");} );
		System.out.println(t.isDaemon());
	}
}
