package com.jzt.sync.configurer;

import com.jzt.sync.core.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author libiao
 * @create 2019/2/21 10:59
 * @description 异常拦截器
 */
@Configuration
@Aspect
@Slf4j
public class ExceptionHandle {
    //git test
    @Around("execution(public * com.jzt.sync.controller.*.*(..))")
    public Object exceptionAop(ProceedingJoinPoint pjp) throws Throwable {
        Object obj = null;
        ServiceException ex = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            //自定义异常，无需关注
            if (!(e instanceof ServiceException)) {
                log.error(e.getMessage(), e);
                ex = new ServiceException("哎哟～  一不小心出错了", e);
            } else {
                log.error(e.getMessage(),e);
                ex = (ServiceException) e;
            }
        }
        //如果存在异常
        if (ex != null){
            throw ex;
        }
        return obj;

    }


}
