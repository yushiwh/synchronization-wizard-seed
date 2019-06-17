package com.jzt.sync.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.TestUser;
import com.jzt.sync.service.TestUserService;
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
public class TestUserController {
    @Resource
    private TestUserService testUserService;

    @PostMapping("/add")
    public Result add(TestUser testUser) {
        testUserService.save(testUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        testUserService.deleteById(Long.parseLong(id + ""));
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TestUser testUser) {
        testUserService.update(testUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TestUser testUser = testUserService.findById(Long.parseLong(id + ""));
        return ResultGenerator.genSuccessResult(testUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TestUser> list = testUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
