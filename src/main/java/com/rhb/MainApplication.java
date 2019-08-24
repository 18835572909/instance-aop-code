package com.rhb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author renhuibo  2019-08-21 23:26:41
 * @Description
 */
@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		new SpringApplication(MainApplication.class).run(args);
	}
}
