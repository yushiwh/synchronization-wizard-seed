package com.jzt.sync.configurer.lock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/3/14 0014. - 星期四
 * nickName louyedaren
 */
@Configuration
public class LockKeyGeneratorConfig {
	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}
}
