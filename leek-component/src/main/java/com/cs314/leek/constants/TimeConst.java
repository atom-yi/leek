package com.cs314.leek.constants;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 存放日期时间相关常量
 */
public class TimeConst {
    /**
     * 项目默认使用的时间格式
     */
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",
            Locale.ROOT);
}
