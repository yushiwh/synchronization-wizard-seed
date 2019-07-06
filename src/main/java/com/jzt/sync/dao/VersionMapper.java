package com.jzt.sync.dao;

import com.jzt.sync.core.Mapper;
import com.jzt.sync.model.Version;

public interface VersionMapper extends Mapper<Version> {
    Version getLastVersion();
}