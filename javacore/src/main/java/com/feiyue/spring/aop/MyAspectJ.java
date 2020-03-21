package com.feiyue.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspectJ {

    @Pointcut("execution(public void com.feiyue.spring.aop.TestController.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) {

        try {
            System.out.println("before execute");
            joinPoint.proceed();
            System.out.println("after execute");
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }
    }
}
