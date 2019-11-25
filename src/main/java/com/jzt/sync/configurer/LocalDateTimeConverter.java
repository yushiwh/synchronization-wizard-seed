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
import com.fasterxml.jackson.databind.JsonSerializer;
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
public class LocalDateTimeConverter extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }


}