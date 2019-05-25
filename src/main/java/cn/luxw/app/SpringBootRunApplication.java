package cn.luxw.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.context.WebApplicationContext;

import cn.luxw.app.config.A;

@EnableCaching
@MapperScan("cn.luxw.app.mapper")
@SpringBootApplication
public class SpringBootRunApplication {
	
	@Autowired
	private A a;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRunApplication.class, args);
	}
}
