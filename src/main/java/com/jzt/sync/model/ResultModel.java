package com.jzt.sync.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ResultModel
 * @Deacription TODO
 * @Author fangnanji
 * @Date 2020/9/24 12:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultModel<T> implements Serializable {
    private String code;

    private String msg;

    private String resultcode;

    /**
     * @Author fangnanji
     * @Description //shuju
     * @Date 10:08 2020/8/7
     * @Param
     * @return
     **/
    private T datas;


}
