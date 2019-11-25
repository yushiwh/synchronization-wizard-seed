/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: LocalDateTimeUtil
 * Author:   nick
 * Date:     2019/11/25 8:50
 * Description: LocalDateTime的例子
 * History:
 */
package com.jzt.sync.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * 〈LocalDateTime的例子〉
 *
 * @author nick
 * @create 2019/11/25
 * @since 1.0.0
 */
@Component
public class LocalDateTimeUtil {
    private Logger logger = LoggerFactory.getLogger(LocalDateTimeUtil.class);
    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.of(13, 51, 10);

    /**
     * LocalDate
     */
    public void getLocalDate() {
        //获取当前年月日
        logger.info("=================获取当前年月日==================");

        logger.info("LocalDate获取当前年月日-->" + localDate);

        //构造指定的年月日
        LocalDate localDate1 = LocalDate.of(2019, 9, 10);
        logger.info("LocalDate构造指定的年月日-->" + localDate1);

        System.out.println();

        //获取年、月、日、星期几
        logger.info("=================获取年、月、日、星期几==================");
        int year = localDate.getYear();
        logger.info("localDate.getYear()-->" + year);
        int year1 = localDate.get(ChronoField.YEAR);
        logger.info("localDate.get(ChronoField.YEAR)-->" + year1);
        Month month = localDate.getMonth();
        logger.info(" localDate.getMonth()-->" + month);
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        logger.info("localDate.get(ChronoField.MONTH_OF_YEAR)-->" + month1);
        int day = localDate.getDayOfMonth();
        logger.info("localDate.getDayOfMonth()-->" + day);
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        logger.info(" localDate.get(ChronoField.DAY_OF_MONTH)-->" + day1);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        logger.info("localDate.getDayOfWeek()-->" + dayOfWeek);
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
        logger.info("localDate.get(ChronoField.DAY_OF_WEEK)-->" + dayOfWeek1);


        System.out.println();

    }


    /**
     * LocalTime
     */
    public void getLocalTime() {
        logger.info("=================获取当前时间==================");
        //LocalTime
        logger.info("=================LocalTime==================");

        logger.info(" LocalTime.of(13, 51, 10)-->" + localTime);
        LocalTime localTime1 = LocalTime.now();
        logger.info(" LocalTime.now()-->" + localTime1);
        //获取小时
        int hour = localTime.getHour();
        logger.info("localTime.getHour()-->" + hour);
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        logger.info("localTime.get(ChronoField.HOUR_OF_DAY)-->" + hour1);
        //获取分
        int minute = localTime.getMinute();
        logger.info("localTime.getMinute()-->" + minute);
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        logger.info("localTime.get(ChronoField.MINUTE_OF_HOUR)-->" + minute1);
        //获取秒
        int second = localTime.getSecond();
        logger.info("localTime.getSecond()-->" + second);
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        logger.info("localTime.get(ChronoField.SECOND_OF_MINUTE)-->" + second1);
    }

    /**
     * LocalDateTime
     */
    public void getLocalDateTime() {
        logger.info("=================获取年月日时分秒，等于LocalDate+LocalTime==================");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
    }

    /**
     * 增加、减少年数、月数、天数等
     */
    public void getJS() {
        System.out.println();
        logger.info("=================增加、减少年数、月数、天数等==================");
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.SEPTEMBER, 10,
                14, 46, 56);
        logger.info("指定当前时间--->" + localDateTime);
        //增加一年
        localDateTime = localDateTime.plusYears(1);
        logger.info("localDateTime.plusYears(1)-->增加1年-->" + localDateTime);
        localDateTime = localDateTime.plus(1, ChronoUnit.YEARS);
        logger.info("localDateTime.plus(1, ChronoUnit.YEARS)-->增加1年-->" + localDateTime);
        //减少一个月
        localDateTime = localDateTime.minusMonths(1);
        logger.info("localDateTime.minusMonths(1)-->减少一个月-->" + localDateTime);
        localDateTime = localDateTime.minus(1, ChronoUnit.MONTHS);
        logger.info("localDateTime.minus(1, ChronoUnit.MONTHS)-->减少一个月-->" + localDateTime);

        //修改年为2019
        localDateTime = localDateTime.withYear(2020);
        logger.info("修改为2020年-->" + localDateTime);
        //修改为2022
        localDateTime = localDateTime.with(ChronoField.YEAR, 2022);
        logger.info("修改为2022年-->" + localDateTime);

    }

    /**
     * 格式化时间
     * DateTimeFormatter默认提供了多种格式化方式，
     * 如果默认提供的不能满足要求，可以通过DateTimeFormatter的ofPattern方法创建自定义格式化方式
     */
    public void getFormat() {
        System.out.println();
        logger.info("=================格式化时间==================");
        LocalDate localDate = LocalDate.of(2019, 9, 10);
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        logger.info("localDate.format(DateTimeFormatter.BASIC_ISO_DATE)-->格式化时间--->" + s1);
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        logger.info("localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)-->格式化时间--->" + s2);
        //自定义格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = localDate.format(dateTimeFormatter);
        logger.info("localDate.format(dateTimeFormatter)-->自定义格式化时间--->" + s3);

        System.out.println();
        logger.info("=================解析时间==================");
        LocalDate localDate1 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        logger.info("LocalDate.parse(\"20190910\", DateTimeFormatter.BASIC_ISO_DATE)-->解析时间--->" + localDate1);
        LocalDate localDate2 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);
        logger.info("LocalDate.parse(\"2019-09-10\", DateTimeFormatter.ISO_LOCAL_DATE)-->解析时间--->" + localDate2);

        System.out.println();
        logger.info("=================字符串转 LocalDateTime==================");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2019-12-01 19:45:23", df);
        logger.info("字符串转 LocalDateTime---->:"+ldt);
        logger.info("年:"+ldt.getYear());

    }


    public static void main(String[] args) {
        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        localDateTimeUtil.getLocalDate();
        localDateTimeUtil.getLocalTime();
        localDateTimeUtil.getLocalDateTime();
        localDateTimeUtil.getJS();
        localDateTimeUtil.getFormat();
    }


}