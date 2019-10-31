/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: MongoTest
 * Author:   nick
 * Date:     2019/10/30 16:04
 * Description: MongoTest
 * History:
 */
package com.jzt.sync.controller;

import com.jzt.sync.dao.MongoTestDao;
import com.jzt.sync.model.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈MongoTest〉
 *
 * @author nick
 * @create 2019/10/30
 * @since 1.0.0
 */

@RestController
public class MongoTestController {
    @Autowired
    private MongoTestDao mtdao;

    @GetMapping(value = "/test1")
    public void saveTest() throws Exception {
        MongoTest mgtest = new MongoTest();
        mgtest.setId(9999);
        mgtest.setAge(4444);
        mgtest.setName("nick");
        mtdao.saveTest(mgtest);
    }

    @GetMapping(value = "/test2")
    public MongoTest findTestByName() {
        MongoTest mgtest = mtdao.findTestByName("ceshi");
        System.out.println("mgtest is " + mgtest);
        return mgtest;
    }

    @GetMapping(value = "/test3")
    public void updateTest() {
        MongoTest mgtest = new MongoTest();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("ceshi2");
        mtdao.updateTest(mgtest);
    }

    @GetMapping(value = "/test4")
    public void deleteTestById() {
        mtdao.deleteTestById(11);
    }
}