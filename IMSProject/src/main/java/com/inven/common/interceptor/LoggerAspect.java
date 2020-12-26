package com.inven.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
	static String name = "";
	static String type = "";
	
	@Around("execution(* com..controller.*Controller.*(..)) || execution(* com..service.*Impl.*(..)) || execution(* com..mapper.*Mapper.*(..)) ")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		type = joinPoint.getSignature().getDeclaringTypeName();
		
		if (type.contains("Controller")) name = "Controller  \t:  ";
		else if (type.contains("Service")) name = "ServiceImpl  \t:  ";
		else if (type.contains("Mapper")) name = "Mapper  \t\t:  ";
		
		log.debug(name + type + "." + joinPoint.getSignature().getName() + "()");
		
		return joinPoint.proceed();
	}
}