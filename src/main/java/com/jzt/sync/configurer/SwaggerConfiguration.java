/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: SwaggerConfiguration
 * Author:   nick
 * Date:     2019/8/28 15:18
 * Description: 配置Swagger2
 * History:
 */
package com.jzt.sync.configurer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈配置Swagger2〉
 *
 * @author nick
 * @create 2019/8/28
 * @since 1.0.0
 */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration {
//    /**
//     * swagger 信息
//     *
//     * @return 页面信息
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("种子项目Swagger2的API接口文档")
//                .description("Swagger2的API")
//                .termsOfServiceUrl("")
//                .version("1.0.0")
//                .contact(new Contact("Nick", "", "")).build();
//    }
//
//  //  @Bean
//    public Docket customImplementation() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.jzt.sync.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(this.apiInfo());
//        //.globalOperationParameters(parameters);
//    }
//}