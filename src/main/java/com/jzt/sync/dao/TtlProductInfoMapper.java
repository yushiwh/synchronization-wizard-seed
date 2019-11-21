/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: TtlProductInfoMapper
 * Author:   nick
 * Date:     2019/11/21 9:26
 * Description:
 * History:
 */
package com.jzt.sync.dao;

import com.jzt.sync.core.Mapper;
import com.jzt.sync.model.TestUser;
import com.jzt.sync.model.TtlProductInfoPo;

import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author nick
 * @create 2019/11/21
 * @since 1.0.0
 */
public interface TtlProductInfoMapper extends Mapper<TtlProductInfoPo> {
      List<TtlProductInfoPo> listProduct(Map<String, Object> map);
}