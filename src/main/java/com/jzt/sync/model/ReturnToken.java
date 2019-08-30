
/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: ReturnToken
 * Author:   nick
 * Date:     2019/8/29 16:20
 * Description: Token解密后的实体
 * History:
 */
package com.jzt.sync.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈Token解密后的实体〉
 *
 * @author nick
 * @create 2019/8/29
 * @since 1.0.0
 */
@Data
public class ReturnToken implements Serializable {

    private static final long serialVersionUID = -2261069919707020741L;
    private String username;
    private Long expires;

}