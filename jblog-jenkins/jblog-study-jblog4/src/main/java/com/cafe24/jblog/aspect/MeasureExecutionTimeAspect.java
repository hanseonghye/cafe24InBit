package com.cafe24.jblog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect { // 실행시간 측정 aspect

	@Around( "execution(* *..repository.*.*(..)) || execution(* *..controller.*.*(..)) || execution(* *..service.*.*(..))" ) //repository 패키지에 있는 모든 class의 모든 method
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch sw = new StopWatch();
		sw.start();
		// method 실행
		Object result = pjp.proceed();
		// after
		sw.stop();
		System.out.println("[ " + sw.getTotalTimeMillis() + "ms; in " + pjp.getTarget().getClass() + " ; "
				+ pjp.getSignature().getName()+" ]");
				// time;classname;methodname
		return result;
	}
}
