
/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: CommodityNew
 * Author:   nick
 * Date:     2019/10/31 14:54
 * Description:
 * History:
 */
package com.jzt.sync.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import java.util.List;

/**
 * 〈〉
 *
 * @author nick
 * @create 2019/10/31
 * @since 1.0.0
 */
@Data
@Document(collection = "CommodityNew")
public class CommodityNew implements Serializable {
    private Long id;
    private int shopId;
    private int commodityId;
    private List<Commodity> list;

}