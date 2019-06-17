package com.jzt.sync.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数据格式转换
 */
public class NumberFormatUtils {

    /**
     * 例如10元，1.1万， 有小数点的话，保留一位小数；
     * @param number
     * @return
     */
    public static final String format(BigDecimal number){
        if(number == null)
            return  "0.00";
        if(number.doubleValue()<10000){
            return  String.valueOf(number);
        }
        else{
            DecimalFormat df = new DecimalFormat("#.0");
            return df.format((float)number.doubleValue()/10000) + "万";
        }
    }


    /**
     * 例如10元，1.1万， 有小数点的话，保留一位小数；
     * @param number
     * @return
     */
    public static final String format(Integer number){
        if(number == null)
            return  "0";
        if(number.doubleValue()<10000){
            return  String.valueOf(number);
        }
        else{
            DecimalFormat df = new DecimalFormat("#.0");
            return df.format((float)number.doubleValue()/10000) + "万";
        }
    }

}
