package com.jzt.sync.service;

import com.jzt.sync.model.TtlProductInfoPo;
import com.jzt.sync.model.Version;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 导出excel
 *
 * @author nick
 * @create 2019-11-21 9:27
 */

public interface TtlProductInfoService extends Service<TtlProductInfoPo> {
    List<TtlProductInfoPo> listProduct(Map<String, Object> map);

    /**
     * 导出文件的方法
     *
     * @param response
     * @param fileName
     */
    void export(HttpServletResponse response, String fileName);
}
