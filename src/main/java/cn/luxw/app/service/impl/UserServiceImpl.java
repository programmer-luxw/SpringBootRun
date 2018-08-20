package cn.luxw.app.service.impl;

import org.springframework.stereotype.Service;

import cn.luxw.app.service.TestService;
import cn.luxw.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private TestService testService;
	
	public UserServiceImpl(TestService testService) {
		this.testService = testService;
	}

	@Override
	public String xx(String name) {
		return testService.findOne(name);
	}
	
	

}
