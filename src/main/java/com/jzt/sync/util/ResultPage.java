/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: ResultPage
 * Author:   yushi
 * Date:     2018/9/29 14:23
 * Description: 分页返回
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.util;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈分页返回〉
 *
 * @author yushi
 * @create 2018/9/29
 * @since 1.0.0
 */
@Data
public class ResultPage {
    /**
     * 总条数
     */
    private int totalCount;

    /**
     * 当前第几页
     */
    private int pageNum;
    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 每页几条
     */
    private int pageSize;

    /**
     * 数据
     */
    private Object dataList;


    public ResultPage(int totalCount, int pageNum, int pageSize, Object dataList) {
        this.totalCount = totalCount;
        this.pageNum = pageNum;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.dataList = dataList;
        this.pageSize = pageSize;
    }
}