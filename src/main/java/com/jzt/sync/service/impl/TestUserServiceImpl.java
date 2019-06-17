package com.jzt.sync.service.impl;

import com.jzt.sync.core.AbstractService;
import com.jzt.sync.dao.TestUserMapper;
import com.jzt.sync.model.TestUser;
import com.jzt.sync.service.TestUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/06/17.
 */
@Service
@Transactional
public class TestUserServiceImpl extends AbstractService<TestUser> implements TestUserService {
    @Resource
    private TestUserMapper testUserMapper;

}
