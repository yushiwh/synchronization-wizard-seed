package com.jzt.sync.controller;


import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.TestUser;
import com.jzt.sync.service.TestUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/3/14 0014. - 星期四
 * nickName louyedaren
 */
@RestController
public class TestController {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TestUserService testUserService;

    @ApiOperation(value = "测试", notes = "查看服务器是否正常返回")
    @RequestMapping("test")
    public String test() {
        return "system is ok ";
    }


}
