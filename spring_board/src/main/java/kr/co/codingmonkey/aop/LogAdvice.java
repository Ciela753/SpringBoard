package kr.co.codingmonkey.aop;

import java.util.Arrays;
import java.util.StringJoiner;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	@Before("execution(* kr.co.codingmonkey.service.SampleService*.*(..)) ")
	public void logAdvice(JoinPoint jp) {
		log.info("=======================================");
	}
	
	@Around("execution(* kr.co.codingmonkey.service.*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = null;
		obj = pjp.proceed();
		long end = System.currentTimeMillis();
		Object[] args = pjp.getArgs();
		String[] strs = new String[args.length];
		for(int i=0; i<args.length;i++) {
			strs[i] = args[i].toString();
		}
		
		String str = String.join(",", strs);
		log.info(String.format("%s.%s(%s) :: %dms", 
				pjp.getTarget().getClass().getSimpleName(), 
				pjp.getSignature().getName(),
				str,
				end-start));
		return obj;
	}
}
