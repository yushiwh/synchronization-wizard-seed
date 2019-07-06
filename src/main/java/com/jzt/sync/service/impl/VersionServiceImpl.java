package com.jzt.sync.service.impl;

import com.jzt.sync.configurer.datasource.DataSources;
import com.jzt.sync.configurer.datasource.RoutingDataSource;
import com.jzt.sync.core.AbstractService;
import com.jzt.sync.dao.VersionMapper;
import com.jzt.sync.model.Version;
import com.jzt.sync.service.VersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by nick on 2019/06/17.
 */
@Service
@Transactional
public class VersionServiceImpl extends AbstractService<Version> implements VersionService {
    @Resource
    private VersionMapper versionMapper;

     @RoutingDataSource(DataSources.SLAVE_DB) // 从库
   // @RoutingDataSource(DataSources.MASTER_DB) //主库
    @Override
    public Version getLastVersion() {
        return versionMapper.getLastVersion();
    }
}
