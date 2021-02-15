package com.pizzashop.repository.annotations.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecutionAspect {

    @Around("@annotation(com.pizzashop.repository.annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        Long executionTime = System.currentTimeMillis() - start;
        log.info("Method {} was executed in {} ms. Measured by Aspect Annotation @LogExecutionAspect", joinPoint.getSignature().getName(), executionTime);
        return proceed;
    }

}
