package com.inven.common.logger;

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
	
	@Around("execution(* com..controller.*Controller.*(..)) or execution(* com..service.*Impl.*(..)) or execution(* com..mapper.*Mapper.*(..)) ")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		type = joinPoint.getSignature().getDeclaringTypeName();
		
		if (type.indexOf("Controller") > -1) name = "Controller  \t:  ";
		else if (type.indexOf("Service") > -1) name = "ServiceImpl  \t:  ";
		else if (type.indexOf("Mapper") > -1) name = "Mapper  \t\t:  ";
		
		log.debug(name + type + "." + joinPoint.getSignature().getName() + "()");
		
		return joinPoint.proceed();
	}
}