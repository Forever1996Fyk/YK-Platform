package com.yk.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: YK-Platform
 * @description: 基于 JDK 8 时间工具类
 * @author: YuKai Fan
 * @create: 2020-06-02 22:40
 **/
public final class TimeUtils {

    /**
     * 获取默认时间格式: yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_DATE_PATTERN_LINE.formatter;

    /**
     * 私有构造函数 防止实例化
     */
    private TimeUtils() {
    }

    /**
     * String 转时间
     * @param timeStr
     * @return
     */
    public static LocalDateTime parseTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * String 转时间
     * @param timeStr
     * @return
     */
    public static Date parseTimeToDate(String timeStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
    }

    /**
     * 时间转 String
     * @param time
     * @return
     */
    public static String parseTime(LocalDateTime time) {
        return DEFAULT_DATETIME_FORMATTER.format(time);
    }

    /**
     * 时间转 String
     * @return
     */
    public static String parseTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dataStr = sdf.format(date);
        return dataStr;
    }

    /**
     * 时间转 String
     * @return
     */
    public static String parseTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataStr = sdf.format(date);
        return dataStr;
    }

    /**
     * 时间转 String
     *
     * @param time
     * @param format 时间格式
     * @return
     */
    public static String parseTime(LocalDateTime time, TimeFormat format) {
        return format.formatter.format(time);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDatetime() {
        return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 获取当前时间时间戳
     *
     * @return
     */
    public static Long getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now()).getTime();
    }

    /**
     * 时间转 long
     * @param time
     * @return
     */
    public static long parseTimeToLong(LocalDateTime time) {
        return time.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 时间转 long
     * @param time
     * @param format
     * @return
     */
    public static long parseTimeToLong(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String str = parseTime(time);
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * String 转 long
     * @param timeStr
     * @param format
     * @return
     */
    public static long parseTimeToLong(String timeStr, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(timeStr, formatter);
        return dateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * String 转时间
     *
     * @param timeStr
     * @param format  时间格式
     * @return
     */
    public static LocalDateTime parseTime(String timeStr, TimeFormat format) {
        return LocalDateTime.parse(timeStr, format.formatter);
    }

    /**
     * 时间格式
     */
    public enum TimeFormat {
        /**
         * 短时间格式
         */
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),
        SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),
        SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),

        /**
         * 长时间格式
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
        LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
        LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),

        /**
         * 长时间格式 带毫秒
         */
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS");

        private transient DateTimeFormatter formatter;

        TimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }
}