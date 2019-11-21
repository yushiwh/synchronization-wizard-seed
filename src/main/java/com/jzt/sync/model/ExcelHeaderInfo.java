/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: ExcelHeaderInfo
 * Author:   nick
 * Date:     2019/11/21 9:23
 * Description: excel表头信息
 * History:
 */
package com.jzt.sync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 〈excel表头信息〉
 *
 * @author nick
 * @create 2019/11/21
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ExcelHeaderInfo {
    /**
     * 标题的首行坐标
     */

    private int firstRow;
    /**
     * 标题的末行坐标
     */

    private int lastRow;
    /**
     * 标题的首列坐标
     */

    private int firstCol;
    /**
     * 标题的首行坐标
     */

    private int lastCol;
    /**
     * 标题
     */

    private String title;
}