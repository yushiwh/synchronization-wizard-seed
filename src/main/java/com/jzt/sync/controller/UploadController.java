/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: UploadController
 * Author:   yushi
 * Date:     2019/6/18 17:17
 * Description: 文件上传
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.controller;

import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.TestUser;
import com.jzt.sync.util.FileUploadUtils;
import com.jzt.sync.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


/**
 * 〈文件上传〉
 *
 * @author yushi
 * @create 2019/6/18
 * @since 1.0.0
 */
@Controller
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);


    @Value("${tool.path.absolutePath}")
    private String absolutePath;
    @Value("${tool.path.uploadRelative}")
    private String uploadRelative;
    @Value("${tool.path.domain}")
    private String imgDomain;


    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@Valid TestUser version, @RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return ResultGenerator.genSuccessResult("上传失败，请选择文件");
        }

        //控制10M
        String url = FileUploadUtils.uploadFile(file, absolutePath, uploadRelative, 10240L, null);
        //返回路径 /syncwizard.dev.yyjzt.com/upload/20190619/15609094490471a7.rar
        //上传成功后插入version自动升级表
        version.setId(IdWorker.getId());
        //拼接下载地址 http://10.4.9.170:2212/download/20190619/15609094490471a7.rar
        String releaseUrl = imgDomain + url.substring(url.indexOf("/upload/") + 8);
        logger.info("ReleaseUrl==" + releaseUrl);

        return ResultGenerator.genSuccessResult(releaseUrl);
    }


}