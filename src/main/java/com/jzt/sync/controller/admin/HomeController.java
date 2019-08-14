/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: HomeController
 * Author:   nick
 * Date:     2019/8/14 14:20
 * Description:
 * History:
 */
package com.jzt.sync.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * 〈〉
 *
 * @author nick
 * @create 2019/8/14
 * @since 1.0.0
 */
@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 功能描述: <br>
     */
    @GetMapping("home/console")
    public String login() {
        return "views/home/console.html";
    }

}