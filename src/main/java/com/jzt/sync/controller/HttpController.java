/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: HttpController
 * Author:   yushi
 * Date:     2019/6/19 14:15
 * Description: Http发送请求
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.controller;

import com.alibaba.fastjson.JSON;
import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.User;
import com.jzt.sync.util.HttpResult;
import com.jzt.sync.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈Http发送请求〉
 *
 * @author yushi
 * @create 2019/6/19
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class HttpController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${b2b.login}")
    private String b2bLoginUrl;

    /**
     * http://localhost:8080/user/login?loginPwd=123456&loginName=13886103676
     *
     * @param loginName 用户名
     * @param loginPwd  密码
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam(value = "loginName", required = true) String loginName,
                        @RequestParam(value = "loginPwd", required = true) String loginPwd) {

        String b2bSendUrl = b2bLoginUrl + "?loginName=" + loginName + "&loginPwd=" + loginPwd + "";
        logger.info("请求b2b三方登录接口开始:请求url:" + b2bSendUrl);
        String response = HttpUtils.sendPost(b2bSendUrl, "utf-8");
        logger.info("请求b2b三方登录接口成功:返回值:" + response);

        //此处的HttpResult需要将对方的json拿过来后用GsonFormat进行转化实体  alt+s
        HttpResult httpResult = JSON.parseObject(response, HttpResult.class);

        // 对象复制
        User user = new User();
        BeanUtils.copyProperties(httpResult.getData().getUser(), user);

        logger.info("------下面是请求返回的数据----");
        logger.info("success-->" + httpResult.isSuccess());
        logger.info("用户名-->" + httpResult.getData().getUser().getLoginName());
        logger.info("token-->" + httpResult.getData().getToken());

        logger.info("------下面是复制后的数据----");
        logger.info("用户名-->" + user.getLoginName());
        logger.info("密码-->" + user.getLoginPwd());
        return ResultGenerator.genSuccessResult(user);
    }


}