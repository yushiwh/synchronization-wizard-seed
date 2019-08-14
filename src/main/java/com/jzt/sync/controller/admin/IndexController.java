/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: IndexController
 * Author:   nick
 * Date:     2019/8/13 14:57
 * Description: 首页登录页面跳转
 * History:
 */
package com.jzt.sync.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 〈首页登录页面跳转〉
 *
 * @author nick
 * @create 2019/8/13
 * @since 1.0.0
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 功能描述: <br>
     */
    @GetMapping("login")
    public String login() {
        return "views/login";
    }

    /**
     * 功能描述: <br>
     */
    @GetMapping("index")
    public String index() {
        return "views/index";
    }

}