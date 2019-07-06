package com.jzt.sync.service;


import com.jzt.sync.core.Service;
import com.jzt.sync.model.TestUser;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/06/17.
 */
public interface TestUserService extends Service<TestUser> {

    public List<TestUser> getTestUsers();
}
