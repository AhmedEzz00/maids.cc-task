package com.example.library_management_system.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionsAspect {
    Logger logger = LoggerFactory.getLogger(LogTimeAspect.class);

    @AfterThrowing(value="execution(* com.example.library_management_system.service..*(..))",throwing = "ex")
    public void LogException(JoinPoint joinPoint, RuntimeException ex) {
        StringBuilder sb= new StringBuilder("");
        sb.append("error occured at: ").append(joinPoint.getSignature()
                .getDeclaringTypeName()).append(".")
                .append(joinPoint.getSignature().getName()).append("\tException: ")
                .append(ex.getMessage());
        logger.error("Exception occured");
        logger.info(sb.toString());
    }
}
