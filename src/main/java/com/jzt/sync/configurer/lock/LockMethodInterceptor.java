package com.jzt.sync.configurer.lock;

import com.jzt.sync.core.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/3/14 0014. - 星期四
 * nickName louyedaren
 */
@Aspect
@Configuration
@Slf4j
public class LockMethodInterceptor {



	@Autowired
	public LockMethodInterceptor(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
		this.redisLockHelper = redisLockHelper;
		this.cacheKeyGenerator = cacheKeyGenerator;
	}

	private final RedisLockHelper redisLockHelper;
	private final CacheKeyGenerator cacheKeyGenerator;


	@Around("execution(public * *(..)) && @annotation(com.jzt.sync.configurer.lock.CacheLock)")
	public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		CacheLock lock = method.getAnnotation(CacheLock.class);
		if (StringUtils.isEmpty(lock.prefix())) {
			throw new RuntimeException("lock key don't null...");
		}
		final String lockKey = cacheKeyGenerator.getLockKey(pjp);
		String value = UUID.randomUUID().toString();
		try {
			// 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
			final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
			if (!success) {
				throw new ServiceException("重复提交");
			}
			return pjp.proceed();
//			try {
//			} catch (Throwable throwable) {
//				log.error("aop CacheLock 切面失败",throwable);
//				throw new ServiceException("系统异常");
//			}
		} finally {
			// 1秒钟后释放锁
			redisLockHelper.unlock(lockKey, value,1,TimeUnit.SECONDS);
		}
	}
}
