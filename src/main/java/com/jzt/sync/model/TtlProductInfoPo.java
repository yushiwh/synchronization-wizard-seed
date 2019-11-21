/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: TtlProductInfoPo
 * Author:   nick
 * Date:     2019/11/21 9:23
 * Description:
 * History:
 */
package com.jzt.sync.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 〈〉
 *
 * @author nick
 * @create 2019/11/21
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@Table(name = "ttl_product_info")
public class TtlProductInfoPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "product_name")
    private String categoryName;
    @Column(name = "branch_id")
    private Long branchId;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "shop_id")
    private Long shopId;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "price")
    private Double price;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "sales_num")
    private Integer salesNum;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private String updateTime;
    @Column(name = "is_del")
    private Byte isDel;
}