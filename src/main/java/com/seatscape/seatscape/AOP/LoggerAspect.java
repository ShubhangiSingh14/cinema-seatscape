package com.seatscape.seatscape.AOP;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Around("execution(* com.seatscape.seatscape..*.*(..))")
    public Object log(ProceedingJoinPoint joinpoint)throws Throwable{
        log.info(joinpoint.getSignature().toString() + "Execution Start");
        Instant startTime = Instant.now();
        Object returnObj = joinpoint.proceed();
        Instant endTime = Instant.now();
        log.info("Total Time Taken to execute the method " + joinpoint.getSignature().toString()+"is " + Duration.between(startTime, endTime).toMillis()+ "ms");
        log.info(joinpoint.getSignature().toString() + "Execution Ended");
        return returnObj;
    }
    @AfterThrowing(value = "execution(* com.seatscape.seatscape..*.*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinpoint, Exception ex){
        log.error(joinpoint.getSignature().toString() + " Threw Exception Due to "+ ex.getMessage());
    }
}
