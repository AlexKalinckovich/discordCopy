package com.example.demo.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotNullParametersAspect {

    @Around(value = "@annotation(NotNullParameters)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        boolean isNotNull = true;
        for (Object arg : args) {
            if (arg == null) {
                isNotNull = false;
                break;
            }
        }

        if (isNotNull) {
            return joinPoint.proceed(args);
        }
        return joinPoint.proceed();
    }
}
