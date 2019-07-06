package com.jzt.sync.dao;


import com.jzt.sync.core.Mapper;
import com.jzt.sync.model.TestUser;

import java.util.List;

public interface TestUserMapper extends Mapper<TestUser> {

    public List<TestUser> testMs();
}