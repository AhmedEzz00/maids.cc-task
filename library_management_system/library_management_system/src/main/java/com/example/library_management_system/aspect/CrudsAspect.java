
package com.example.library_management_system.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrudsAspect {
    Logger logger = LoggerFactory.getLogger(LogTimeAspect.class);

    @Pointcut(value = "execution(* com.example.library_management_system.service..insert*(..))")
    public void forInserting(){}

   @Pointcut(value = "execution(* com.example.library_management_system.service..update*(..))")
   public void forUpdating(){}

   @Pointcut(value = "execution(* com.example.library_management_system.service..delete*(..))")
   public void forDeleting(){}

   @Pointcut(value = "forInserting() || forUpdating() || forDeleting()")
   public void forAll(){}

    @AfterReturning(pointcut = "forAll()", returning = "returnValue")
    public void crudReturn(JoinPoint joinPoint, Object returnValue){

        String returnedValue= returnValue.toString();
        logger.info(returnedValue);
    }
}

