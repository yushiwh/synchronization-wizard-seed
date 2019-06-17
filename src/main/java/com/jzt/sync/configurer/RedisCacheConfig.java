package com.jzt.sync.configurer;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/2/26 0026. - 星期二
 * nickName louyedaren
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

	public final static  long REDIS_Tll = 1;   //设置过期时间为1天
	@Bean
	public <K, V> RedisTemplate<K, V> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<K, V> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}


	@Bean
    public EhCacheCacheManager ehcacheManager() {

        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        //cacheManagerFactoryBean.setCacheManagerName("ehcache");
        //这里暂时借用shiro的ehcache配置文件
        Resource r = new ClassPathResource("/ehcache.xml");
        cacheManagerFactoryBean.setConfigLocation(r);
        cacheManagerFactoryBean.setShared(true);
        return  new EhCacheCacheManager(cacheManagerFactoryBean.getObject());
    }

    @Bean
    public RedisCacheManager redisManager(RedisConnectionFactory redisConnectionFactory) {

        	RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofHours(1)); // 设置缓存有效期一天
	        return RedisCacheManager
				.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
    }



	@Bean
    @Primary
	public CacheManager cacheManager(EhCacheCacheManager ehcacheManager,RedisCacheManager redisManager) {
	    CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
        Collection<CacheManager> managers = new ArrayList<>();
        managers.add(ehcacheManager);
 		managers.add(redisManager);
		compositeCacheManager.setCacheManagers(managers);
        return compositeCacheManager;
	}

}