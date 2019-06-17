package com.jzt.sync.util;

import org.apache.commons.lang3.StringUtils;

public class YvanUtil{
    public static String mysqlLike(String likeContent) {
        if (StringUtils.isBlank(likeContent)) {
            return "";
        }
        return "'%" + likeContent.trim()
                                 .replace("'", "\\'")
                                 .replace("/", "//")
                                 .replace("_", "/_")
                                 .replace("%", "/%") +
               "%' escape '/'";
    }
}
