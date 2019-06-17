package com.jzt.sync.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.jzt.sync.constant.DateTimeConts;


/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/3/16 0016. - 星期六
 * nickName louyedaren
 */
public class DateTimeUtil {
    /**
     * 获取指定日期的开始时间
     *
     * @param date 年月日 "2019-3-16"
     * @return
     */
    public static DateTime getBeginOfDay(String date) {
        return DateUtil.beginOfDay(DateUtil.parseDate(date));
    }

    /**
     * 获取指定日期的结束时间
     *
     * @param date 年月日 "2019-3-16"
     * @return
     */
    public static DateTime getEndOfDay(String date) {
        //MySQL数据库对于毫秒大于500的数据进行进位
        return DateUtil.parseDateTime(date + DateTimeConts.endTimeOfDayStr);
    }

}
