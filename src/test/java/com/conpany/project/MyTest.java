package com.conpany.project;

import com.jzt.sync.controller.UserController;
import com.jzt.sync.util.LocalDateTimeUtil;
import com.jzt.sync.util.java8;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MyTest extends Tester {
    private static final Logger logger = LoggerFactory.getLogger(MyTest.class);


    @Autowired
    private java8 java8;

    @Test
    public void getData() throws Exception {
        java8.getData();
    }

}
