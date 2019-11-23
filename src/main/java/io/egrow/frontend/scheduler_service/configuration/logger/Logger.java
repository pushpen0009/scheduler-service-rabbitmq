package io.egrow.frontend.scheduler_service.configuration.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @Around("execution(* *(..)) && @annotation(io.egrow.frontend.scheduler_service.configuration.logger.MethodStats)")
    public Object log(ProceedingJoinPoint point) throws Throwable {

        long start = System.currentTimeMillis();
        Object result = point.proceed();

        String text = point.getSignature().getDeclaringTypeName() + "." +
                ((MethodSignature) point.getSignature()).getMethod().getName() + "() -- " +
                (System.currentTimeMillis() - start) + "ms";

        logger.info(text);

        return result;
    }
}