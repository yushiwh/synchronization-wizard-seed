/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: LocalDateTimeTest
 * Author:   nick
 * Date:     2019/11/25 9:24
 * Description:
 * History:
 */
package com.jzt.sync.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jzt.sync.configurer.DateDeserializer;
import com.jzt.sync.configurer.LocalDateTimeConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 〈〉
 *
 * @author nick
 * @create 2019/11/25
 * @since 1.0.0
 */
@Data
public class LocalDateTimeTest implements Serializable {
    private Long id;
    /**
     * 时间戳返回
     */
    @JsonSerialize(using = LocalDateTimeConverter.class)
    private LocalDateTime updateTime;

    /**
     * 指定格式返回
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime gmtModified;
}

