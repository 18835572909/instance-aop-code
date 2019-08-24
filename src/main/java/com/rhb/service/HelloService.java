package com.rhb.service;

import com.rhb.pojo.User;

/**
 * @author renhuibo  2019-08-21 23:30:04
 * @Description
 */
public interface HelloService {
	
	User makeUser(User user,boolean throwException);
	
}
