package com.rhb.service.impl;

import org.springframework.stereotype.Service;
import com.rhb.annotation.Log;
import com.rhb.pojo.User;
import com.rhb.service.HelloService;

/**
 * @author renhuibo  2019-08-23 01:02:55
 * @Description
 */
@Service
@Log("annotation-test-class")
public class HelloServiceImpl implements HelloService{

	@Override
	@Log(value="annotation-test-method")
	public User makeUser(User user,boolean throwException) {
		if(throwException)
			throw new RuntimeException();
		System.out.println("\nJoinPoint-Method: "+user);
		return user;
	}
	
}
