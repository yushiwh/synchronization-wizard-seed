package com.jzt.sync.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.TestUser;
import com.jzt.sync.service.TestUserService;
import com.jzt.sync.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/06/17.
 */
@RestController
@RequestMapping("/test/user")
@Slf4j
public class TestUserController {

    private static final Logger logger = LoggerFactory.getLogger(TestUserController.class);

    @Resource
    private TestUserService testUserService;

    @PostMapping("/add")
    public Result add(TestUser testUser) {
        Long id = IdWorker.getId();
        testUser.setId(id);
        testUserService.save(testUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        testUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TestUser testUser) {
        testUserService.update(testUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TestUser testUser = testUserService.findById(id);
        return ResultGenerator.genSuccessResult(testUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TestUser> list = testUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        logger.info("测试的日志", list.size());
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
