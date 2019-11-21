/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: ExcelFormat
 * Author:   nick
 * Date:     2019/11/21 9:19
 * Description: 导出excel
 * History:
 */
package com.jzt.sync.constant;

/**
 * 〈导出excel〉
 *
 * @author nick
 * @create 2019/11/21
 * @since 1.0.0
 */
public enum ExcelFormat {
    FORMAT_INTEGER("INTEGER"),
    FORMAT_DOUBLE("DOUBLE"),
    FORMAT_PERCENT("PERCENT"),
    FORMAT_DATE("DATE");

    private String value;

    ExcelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}