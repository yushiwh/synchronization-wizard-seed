package com.jzt.sync.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.Version;
import com.jzt.sync.service.VersionService;
import com.jzt.sync.util.IdWorker;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nick on 2019/06/17.
 */
@RestController
@RequestMapping("api/version")
public class VersionController {
    @Resource
    private VersionService versionService;


    /**
     * 增加版本
     * {
     * "appName":"aaaaa",
     * "applicationStart":"bbbbbb",
     * "forceUpdate":"1",
     * "minVersion":"0.0.1",
     * "releaseDate":"2019-06-17 13:00:00",
     * "releaseUrl":"http://localhost/WebApp/update/1.1.0.0.zip",
     * "releaseVersion":"1.0.1",
     * "updateMode":"1",
     * "versionDesc":"提示"
     * }
     *
     * @param version
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Version version) {
        Long id = IdWorker.getId();
        version.setId(id);
        versionService.save(version);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        versionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Version version) {
        versionService.update(version);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取最后发布时间的版本号
     *
     * @return
     */
    @GetMapping("/detail")
    public Result detail() {
        Version version = versionService.getLastVersion();
        return ResultGenerator.genSuccessResult(version);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Version> list = versionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
