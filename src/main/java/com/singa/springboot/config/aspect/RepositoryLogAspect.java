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
 * @filename RepositoryLogAspect.java
 * @time 2019年1月14日 下午2:17:33
 * @copyright(C) 2019 深圳市北辰德科技股份有限公司
 */
public class RepositoryLogAspect {

	public static final Logger log = LoggerFactory.getLogger("trackCall");
	
	@Pointcut("execution(public * com.singa.springboot.repository.*.*(..))")
	public void repositoryAspect() {
		
	}
	
	@Before("repositoryAspect()")
    public void doBefore(JoinPoint joinPoint){
		log.debug("[repository]开始   - " + joinPoint.getSignature().getDeclaringTypeName() + "." 
							 + joinPoint.getSignature().getName() + "\n" + 
				"传入参数 " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@After("repositoryAspect()")
    public void doAfter(JoinPoint joinPoint){
		log.debug("[repository]结束   - " + joinPoint.getSignature().getDeclaringTypeName() + "." 
				 + joinPoint.getSignature().getName());
    }
	
	@AfterReturning(pointcut = "repositoryAspect()", returning = "result")
	public void doAfterReturning(Object result) {
		log.debug("[repository]返回值 - " + (result == null ? "" : result.toString()));
	}
	
	@AfterThrowing(pointcut = "repositoryAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
		log.warn("[repository]异常   - " + e.getMessage(), e);
	}
	
}

