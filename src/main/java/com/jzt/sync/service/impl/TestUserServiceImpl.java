package com.jzt.sync.service.impl;

import com.jzt.sync.configurer.datasource.DataSources;
import com.jzt.sync.configurer.datasource.RoutingDataSource;
import com.jzt.sync.core.AbstractService;
import com.jzt.sync.dao.TestUserMapper;
import com.jzt.sync.model.TestUser;
import com.jzt.sync.service.TestUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author CodeGenerator
 * @date 2019/06/17
 */
@Service
@Transactional
public class TestUserServiceImpl extends AbstractService<TestUser> implements TestUserService {
    @Resource
    private TestUserMapper testUserMapper;


    /**
     * 默认就是主库，可以写成 @RoutingDataSource(DataSources.MASTER_DB) //主库
     * 从库为@RoutingDataSource(DataSources.SLAVE_DB)  从库
     *
     * @return
     */

    @RoutingDataSource(DataSources.SLAVE_DB)  //从库
    //@RoutingDataSource(DataSources.MASTER_DB) //主库
    @Override
    public List<TestUser> getTestUsers() {
        return testUserMapper.testMs();
    }
}
