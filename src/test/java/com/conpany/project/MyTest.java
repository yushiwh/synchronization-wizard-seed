package com.conpany.project;

import com.jzt.sync.util.Java8;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyTest extends Tester {
    private static final Logger logger = LoggerFactory.getLogger(MyTest.class);


    @Autowired
    private Java8 java8;

    @Test
    public void getData() throws Exception {
        java8.getData();
    }

}
