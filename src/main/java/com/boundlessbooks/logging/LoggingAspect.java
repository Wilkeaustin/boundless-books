package com.boundlessbooks.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logs entry, exit, and exception details for application methods.
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Wraps controller, service, and repository methods with logging.
     *
     * @param joinPoint method being called
     * @return value returned by the method
     * @throws Throwable when the called method fails
     */
    @Around("execution(* com.boundlessbooks.controller..*(..)) || "
            + "execution(* com.boundlessbooks.service..*(..)) || "
            + "execution(* com.boundlessbooks.repository..*(..))")
    public Object logMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        Class<?> className = joinPoint.getSignature().getDeclaringType();
        String methodName = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(className);

        logger.info("ENTER: {}.{}", className.getSimpleName(), methodName);

        try {
            Object result = joinPoint.proceed();

            logger.info("EXIT: {}.{}", className.getSimpleName(), methodName);
            return result;
        } catch (Throwable exception) {
            logger.error(
                    "EXCEPTION: {}.{} - {}",
                    className.getSimpleName(),
                    methodName,
                    exception.getMessage(),
                    exception
            );

            throw exception;
        }
    }
}
