/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: RestTemplateController
 * Author:   nick
 * Date:     2020/1/19 8:38
 * Description: RestTemplate的测试
 * History:
 */
package com.jzt.sync.controller;

import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 〈RestTemplate的测试〉
 *
 * @author nick
 * @create 2020/1/19
 * @since 1.0.0
 */
public class RestTemplateController {
    @Resource(name = "OKHttp3")
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateController.class);

    @PostMapping("/orderReturnState")
    public Result orderReturnState(@RequestBody String jsonParam) {
        String url = "";
        logger.info("~~~~开始调用订单状态更新~~~~~传入参数" + jsonParam);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.toString());
        HttpEntity<String> entity = new HttpEntity<>(jsonParam, headers);
        logger.info(StringUtils.join("调用的地址-->", url));
        logger.info(StringUtils.join("参数-->", entity.toString()));
        ResponseEntity<String> exchange =
                restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String response = exchange.getBody();
        logger.info(StringUtils.join("返回值-->", response));
        return ResultGenerator.genSuccessResult().putMsg(response);
    }


}