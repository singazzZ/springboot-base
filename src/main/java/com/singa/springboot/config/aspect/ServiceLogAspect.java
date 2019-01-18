package com.singa.springboot.config.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
/**
 * @author Singa
 * @version 1.0.0
 * @filename ServiceLogAspect.java
 * @time 2019年1月14日 下午2:08:32
 * @copyright(C) 2019 深圳市北辰德科技股份有限公司
 */
public class ServiceLogAspect {
	
	public static final Logger log = LoggerFactory.getLogger("trackCall");
	
	@Pointcut("execution(public * com.singa.springboot.service.*.*(..))")
	public void serviceAspect() {
		
	}
	
	@Before("serviceAspect()")
    public void doBefore(JoinPoint joinPoint){
		log.debug("[service   ]开始   - " + joinPoint.getSignature().getDeclaringTypeName() + "." 
							 + joinPoint.getSignature().getName() + "\n" + 
				"传入参数 " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@After("serviceAspect()")
    public void doAfter(JoinPoint joinPoint){
		log.debug("[service   ]结束   - " + joinPoint.getSignature().getDeclaringTypeName() + "." 
				 + joinPoint.getSignature().getName());
    }
	
	@AfterReturning(pointcut = "serviceAspect()", returning = "result")
	public void doAfterReturning(Object result) {
		log.debug("[service   ]返回值 - " + (result == null ? "" : result.toString()));
	}
	
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
		log.warn("[service   ]异常   - " + e.getMessage(), e);
	}
}
