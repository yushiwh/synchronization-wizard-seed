package com.jzt.sync.controller.swagger;


import com.jzt.sync.model.SwaggerUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Api(tags = "用户管理")
@RestController
public class SwaggerUserController {

    @ApiOperation("创建用户")
    @PostMapping("/swaggerUsers")
    public SwaggerUser create(@RequestBody @Valid SwaggerUser user) {
        return user;
    }

    @ApiOperation("用户详情")
    @GetMapping("/swaggerUsers/{id}")
    public SwaggerUser findById(@PathVariable Long id) {
        return new SwaggerUser("bbb", 21, "上海", "aaa@bbb.com");
    }

    @ApiOperation("用户列表")
    @GetMapping("/swaggerUsers")
    public List<SwaggerUser> list(@ApiParam("查看第几页") @RequestParam int pageIndex,
                                  @ApiParam("每页多少条") @RequestParam int pageSize) {
        List<SwaggerUser> result = new ArrayList<>();
        result.add(new SwaggerUser("aaa", 50, "北京", "aaa@ccc.com"));
        result.add(new SwaggerUser("bbb", 21, "广州", "aaa@ddd.com"));
        return result;
    }

    @ApiIgnore
    @DeleteMapping("/swaggerUsers/{id}")
    public String deleteById(@PathVariable Long id) {
        return "delete user : " + id;
    }

}