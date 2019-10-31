
/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: commodity
 * Author:   nick
 * Date:     2019/10/31 10:43
 * Description: 商品实体
 * History:
 */
package com.jzt.sync.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 〈商品实体〉
 *
 * @author nick
 * @create 2019/10/31
 * @since 1.0.0
 */
@Data

public class Commodity implements Serializable {
    //    private String id;
//    private String name;
    private int constPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastTime;

    //开始写比较规则
    public static class Comparators {
        //根据lasttime进行排序
        public static Comparator<Commodity> LASTTIME = new Comparator<Commodity>() {
            @Override
            public int compare(Commodity o1, Commodity o2) {
                int flag = o2.getLastTime().compareTo(o1.getLastTime());
                return flag;
            }
        };

    }


}