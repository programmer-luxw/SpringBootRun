package cn.luxw.app.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.luxw.app.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	
	/**
     * Cacheable
     * value：缓存key的前缀。
     * key：缓存key的后缀。
     * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false。
     */
    @Override
    @Cacheable(value = "foo", key = "#p0", sync = true)
    public String findOne(String name) {
    	System.err.println("======name========"+name);
        return "SUCCESS";
    }
}
