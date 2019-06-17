package com.jzt.sync.configurer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/3/29 0029. - 星期五
 * nickName louyedaren
 * SpringBoot Web项目中如果自定义了WebMvcConfig extends WebMvcConfigurationSupport,此配置会失效，
 * 解决方式 https://www.jianshu.com/p/25d4b959110c
 *
 */
@JsonComponent
public class JsonSerializerManage {

	@Bean
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		//忽略value为null 时 key的输出
		objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

		/**
		 * 序列换成json时,将所有的long变成string
		 * 因为js中得数字类型不能包含所有的java long值
		 */
		SimpleModule module = new SimpleModule();
		SimpleModule simpleModule = module.addSerializer(Long.class, ToStringSerializer.instance);
		module.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(module);
		return objectMapper;
	}

}
