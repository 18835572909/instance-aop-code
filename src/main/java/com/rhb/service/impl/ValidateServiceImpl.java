package com.rhb.service.impl;

import com.rhb.service.ValidateService;

/**
 * @author renhuibo  2019-08-24 22:45:53
 * @Description
 */
public class ValidateServiceImpl implements ValidateService{

	/* 
	 * 
	 */
	@Override
	public boolean isPass(boolean pass) {
		System.out.println("\nAOP增强校验实现类调用");
		return pass;
	}

}
