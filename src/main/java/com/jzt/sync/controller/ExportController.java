/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: ExportController
 * Author:   nick
 * Date:     2019/11/21 9:22
 * Description: 导出excel
 * History:
 */
package com.jzt.sync.controller;

import com.jzt.sync.service.TtlProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * 〈导出excel〉
 *
 * @author nick
 * @create 2019/11/21
 * @since 1.0.0
 */
@RestController
@RequestMapping("/excelUtils")
public class ExportController {
    @Autowired
    private TtlProductInfoService productInfoService;

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        this.productInfoService.export(response, "商品信息" + new Random().nextInt(1000));
    }
}