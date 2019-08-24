package com.rhb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rhb.pojo.User;
import com.rhb.service.HelloService;
import com.rhb.service.ValidateService;

/**
 * @author renhuibo  2019-08-21 23:33:09
 * @Description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private HelloService service;
	
	@RequestMapping("/makeUser")
	public User makeUser(String uname,String psw,int age) {
		User user = new User();
		user.setAge(age);
		user.setPassword(psw);
		user.setUsername(uname);
		ValidateService validate = (ValidateService)service;
		if(validate.isPass(true)) {
			return service.makeUser(user,false);
		}else {
			return null;
		}
	}
}
