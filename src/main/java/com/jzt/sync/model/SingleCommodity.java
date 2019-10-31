/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: SingleCommodity
 * Author:   nick
 * Date:     2019/10/31 10:47
 * Description: 单条记录
 * History:
 */
package com.jzt.sync.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈单条记录〉
 *
 * @author nick
 * @create 2019/10/31
 * @since 1.0.0
 */
@Data
public class SingleCommodity implements Serializable {
    /**
     * 编号
     */
    private Long id;
    /**
     * 药店编号
     */
    private int shopId;
    /**
     * 商品编号
     */
    private int commodityId;
    /**
     * 商品价格
     */
    private int constPrice;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastTime;
}