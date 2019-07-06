package com.jzt.sync.service;

import com.jzt.sync.core.Service;
import com.jzt.sync.model.Version;


/**
 * Created by CodeGenerator on 2019/06/17.
 */
public interface VersionService extends Service<Version> {

    /**
     * 得到最近一次的版本号
     *
     * @return
     */
    public Version getLastVersion();

}
