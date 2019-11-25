/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: TestLocalDateTimeController
 * Author:   nick
 * Date:     2019/11/25 9:23
 * Description: 测试LocalDateTime
 * History:
 */
package com.jzt.sync.controller;

import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.LocalDateTimeTest;
import com.jzt.sync.model.ResultMap;
import com.jzt.sync.model.Version;
import com.jzt.sync.util.IdWorker;
import com.jzt.sync.util.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 〈测试LocalDateTime〉
 *
 * @author nick
 * @create 2019/11/25
 * @since 1.0.0
 */
@RestController
@RequestMapping("/localdatetime")
public class TestLocalDateTimeController {

    /**
     * http://localhost:8082/localdatetime/get/2019-12-01%2014:34:11
     * @param acceptTime
     * @return
     */
    @GetMapping("/get/{acceptTime}")
    public Result get(@PathVariable String acceptTime) {
        LocalDateTimeTest localDateTimeTest = new LocalDateTimeTest();
        //   LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(acceptTime, df);

        localDateTimeTest.setId(1L);
        localDateTimeTest.setUpdateTime(ldt);
        localDateTimeTest.setGmtModified(ldt);
        return ResultGenerator.genSuccessResult(localDateTimeTest);
    }
}