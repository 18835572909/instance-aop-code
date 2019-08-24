package com.rhb.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.rhb.annotation.Log;
import com.rhb.pojo.User;
import com.rhb.service.ValidateService;
import com.rhb.service.impl.ValidateServiceImpl;

/**
 * @author renhuibo  2019-08-21 23:36:04
 * @Description
 */
@Aspect
@Component
public class DefaultAspect {

	@DeclareParents(value="com.rhb.service.impl.HelloServiceImpl",defaultImpl=ValidateServiceImpl.class)
	public ValidateService validate;
	
	@Pointcut("execution(* com.rhb.service.impl.HelloServiceImpl.makeUser(..))")
	public void pointCut() {
		
	}
	
	@Before("pointCut() && args(user,throwException)")
	public  void before(JoinPoint joinPoint,User user,boolean throwException) {
		System.out.println("\nbefore...");
		System.out.println(user);
	}
	
	@After("pointCut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("\nafter...");
	}

	/**
	 * @author renhuibo  2019年8月24日
	 * @Description : 如果proceed()
	 */
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("\naround before...");
		Object obj = proceedingJoinPoint.proceed();
		System.out.println("around end...");
		return obj;
	}
	
	@AfterThrowing(pointcut="pointCut()",throwing="exception")
	public void afterThrowing(Throwable exception) {
		System.out.println("\naftertThrowing...s");
		System.out.println("Exception: "+exception);
	}
	
	@SuppressWarnings("unchecked")
	@AfterReturning(pointcut="pointCut()",returning="returnValue")
	public void afterReturning(JoinPoint joinPoint,User returnValue) {
		System.out.println("\nafterReturning...");
		
		Signature signature = joinPoint.getSignature();
		
		//连接点方法名称
		String methodName = signature.getName();
		System.out.println("切点方法： "+methodName);
		
		//连接点方法参数
		Object[] args = joinPoint.getArgs();
		System.out.println("切点参数： "+Arrays.toString(args));
		
		//链接点所属对象
		Object obj = joinPoint.getTarget();
		Class cla = obj.getClass();
		System.out.println("切点所属类： "+cla.getName());
		
		//获取连接点方法对象
		Class targetClass = obj.getClass();
		Method method = null;
		Method[] methods = targetClass.getMethods();
		for(Method m : methods) {
			if(m.getName().equals(methodName)){
				method = m;
				break;
			}
		}
		
		//连接点方法注解（对象注解同理）
		if(method.isAnnotationPresent(Log.class)) {
			Log log = method.getAnnotation(Log.class);
			System.out.println("切点方法注解值： "+log.value());
		}
		
		if(cla.isAnnotationPresent(Log.class)) {
			Log log = (Log) cla.getAnnotation(Log.class);
			System.out.println("切点所属类注解值： "+log.value());
		}
		
		System.out.println("返回值： "+returnValue);
	}
}
