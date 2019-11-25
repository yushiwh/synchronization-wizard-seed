/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: classLocalDateTimeConverterextendsJsonSerializer
 * Author:   nick
 * Date:     2019/11/25 9:26
 * Description:
 * History:
 */
package com.jzt.sync.configurer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 〈将LocalDateTime字段以时间戳的方式返回给前端 添加日期转化类〉
 *
 * @author nick
 * @create 2019/11/25
 * @since 1.0.0
 */
public class DateDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (StringUtils.isBlank(jsonParser.getText())) {
            return null;
        }
        LocalDate localDate = LocalDate.parse(jsonParser.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }
}