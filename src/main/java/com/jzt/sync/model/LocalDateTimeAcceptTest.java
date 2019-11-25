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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jzt.sync.configurer.LocalDateTimeConverter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 〈接收前端传入〉
 *
 * @author nick
 * @create 2019/11/25
 * @since 1.0.0
 */
@Data
public class LocalDateTimeAcceptTest implements Serializable {

    /**
     * 前端出入
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime acceptTime;
}

