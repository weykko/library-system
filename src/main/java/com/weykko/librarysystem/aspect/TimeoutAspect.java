package com.weykko.librarysystem.aspect;

import com.weykko.librarysystem.exception.TimeoutException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class TimeoutAspect {

    @Value("${timeout.default-interval}")
    private int defaultInterval;

    private final Map<String, Long> lastInvocationTimes = new ConcurrentHashMap<>();

    @Around("@annotation(timeout)")
    public Object timeoutMethod(ProceedingJoinPoint joinPoint, Timeout timeout) throws Throwable {
        long interval = timeout.value() > 0
                ? timeout.value()
                : defaultInterval;

        String methodKey = getMethodKey(joinPoint);

        long now = System.currentTimeMillis();
        long lastInvocation = lastInvocationTimes.getOrDefault(methodKey, 0L);

        if (now - lastInvocation < interval * 1000) {
            throw new TimeoutException(interval);
        }

        lastInvocationTimes.put(methodKey, now);

        return joinPoint.proceed();
    }

    private String getMethodKey(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().toLongString();
    }
}
