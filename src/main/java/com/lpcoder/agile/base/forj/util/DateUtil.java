package com.lpcoder.agile.base.forj.util;

import com.lpcoder.agile.base.forj.constant.DateFormatCnst;
import com.lpcoder.agile.base.forj.enumeration.WeekEnum;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: liurenpeng@daojia.com
 * @date: Created in 14:53 2017-10-16
 */
public class DateUtil extends DateUtils {

    private DateUtil() {
    }

    /**
     * 获取日期字符串 yyyy-mm-dd
     */
    public static String getDateStr(Date date) {
        return formatDate(date, DateFormatCnst.STANDARD_DATE_FORMAT);
    }

    /**
     * 获取时间字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeStr(Date date) {
        return formatDate(date, DateFormatCnst.STANDARD_DATETIME_FORMAT);
    }

    /**
     * 格式化日期
     */
    public static String formatDate(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    /**
     * 根据yyyy-mm-dd获取时间
     */
    public static Date getDateByDateStr(String dateStr) throws ParseException {
        return parseDate(dateStr, DateFormatCnst.STANDARD_DATE_FORMAT);
    }

    /**
     * 根据yyyy-MM-dd HH:mm:ss获取时间
     */
    public static Date getDateByDateTimeStr(String timeStr) throws ParseException {
        return parseDate(timeStr, DateFormatCnst.STANDARD_DATETIME_FORMAT);
    }

    /**
     * 转为日期
     */
    public static Date parseDate(String dateStr, String format) throws ParseException {
        return DateUtils.parseDate(dateStr, format);
    }

    /**
     * 比较日期前后顺序
     */
    public static boolean compareDate(Date earlyDate, Date laterDate) {
        return earlyDate.before(laterDate);
    }

    /**
     * 获取系统当前日期字符串 yyyy-mm-dd
     */
    public static String getCurrentDateStr() {
        return getDateStr(new Date());
    }

    /**
     * 获取系统当前时间字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTimeStr() {
        return getDateTimeStr(new Date());
    }

    /**
     * 获取日期字符串 yyyy-MM-dd
     */
    public static String getDateStrByLong(long time) {
        return getDateStr(new Date(time));
    }

    /**
     * 获取时间字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeStrByLong(long time) {
        return getDateTimeStr(new Date(time));
    }

    /**
     * 获取当月天数
     */
    public static int getDayNumByMonth(int year, int month) {
        int monthLowerLimit = 1;
        int monthUpperLimit = 12;
        if (month < monthLowerLimit || month > monthUpperLimit) {
            throw new IllegalArgumentException("argument 'month' is " + month + ", not in 1-12");
        }
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    /**
     * 是否闰年
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 获取日期的星期
     */
    public static WeekEnum getWeek(Date date) {
        if (null == date) {
            throw new IllegalArgumentException("argument 'date' is null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        return WeekEnum.valOf(week);
    }

    public static boolean isNotNull(Date target) {
        return null != target;
    }

    public static boolean isEq(Date target, Date norm) {
        return target.getTime() == norm.getTime();
    }

    public static boolean isAfter(Date target, Date norm) {
        return target.getTime() > norm.getTime();
    }

    public static boolean isAfterOrEq(Date target, Date norm) {
        return target.getTime() >= norm.getTime();
    }

    public static boolean isBefore(Date target, Date norm) {
        return target.getTime() < norm.getTime();
    }

    public static boolean isBeforeOrEq(Date target, Date norm) {
        return target.getTime() <= norm.getTime();
    }

}