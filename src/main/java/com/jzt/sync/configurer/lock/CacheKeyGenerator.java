package com.jzt.sync.configurer.lock;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/3/14 0014. - 星期四
 * nickName louyedaren
 */
public interface CacheKeyGenerator {

	/**
	 * 获取AOP参数,生成指定缓存Key
	 *
	 * @param pjp PJP
	 * @return 缓存KEY
	 */
	String getLockKey(ProceedingJoinPoint pjp);
}
