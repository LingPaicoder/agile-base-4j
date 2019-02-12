package com.lpcoder.agile.base.forj.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: liurenpeng@daojia.com
 * @date: Created in 14:53 2017-10-16
 */
public class StringUtil extends StringUtils {

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("[1][3456789]\\d{9}");
    private static final Pattern NUM_PATTERN =
            Pattern.compile("\\d+");
    private static final Pattern STANDARD_DATE_FORMAT_PATTERN =
            Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
    private static final Pattern STANDARD_DATETIME_FORMAT_PATTERN =
            Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    private static final byte LOWER_CASE_A = 'a';
    private static final byte LOWER_CASE_Z = 'z';

    private static final Pattern LETTER_PATTERN = Pattern.compile("^[A-Za-z]+$");


    public static String getString(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    public static String upperFirstLetter(String target) {
        byte[] items = target.getBytes();
        if (items[0] > LOWER_CASE_A && items[0] < LOWER_CASE_Z) {
            items[0] = (byte) ((char) items[0] - 'a' + 'A');
            return new String(items);
        } else {
            return target;
        }
    }

    /**
     * 统计str中subStr出现次数
     */
    public static int getNumOfSubStr(String str, String subStr, boolean caseSensitive) {
        Pattern p = null;
        if (caseSensitive) {
            p = Pattern.compile(subStr);
        }
        if (!caseSensitive) {
            p = Pattern.compile(subStr, Pattern.CASE_INSENSITIVE);
        }
        Matcher m = p.matcher(str);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 统计str中subStr出现次数,大小写敏感
     */
    public static int getNumOfSubStr(String str, String subStr) {
        return getNumOfSubStr(str, subStr, true);
    }

    /**
     * 手机号格式校验
     */
    public static boolean isPhone(String txt) {
        return PHONE_PATTERN.matcher(txt).matches();
    }

    public static boolean isNotPhone(String txt) {
        return !isPhone(txt);
    }

    /**
     * 数字校验
     */
    public static boolean isNum(String txt) {
        return NUM_PATTERN.matcher(txt).matches();
    }

    public static boolean isNotNum(String txt) {
        return !isNum(txt);
    }

    /**
     * 字母校验
     */
    public static boolean isAllLetter(String txt) {
        return LETTER_PATTERN.matcher(txt).matches();
    }

    public static boolean isNotAllLetter(String txt) {
        return !isAllLetter(txt);
    }

    /**
     * url校验
     */
    public static boolean isUrl(String txt) {
        return txt.startsWith("http://") || txt.startsWith("https://");
    }

    /**
     * yyyy-MM-dd校验
     */
    public static boolean isStandardDate(String txt) {
        return STANDARD_DATE_FORMAT_PATTERN.matcher(txt).matches();
    }

    public static boolean isNotStandardDate(String txt) {
        return !isStandardDate(txt);
    }

    /**
     * yyyy-MM-dd HH:mm:ss校验
     */
    public static boolean isStandardDatetime(String txt) {
        return STANDARD_DATETIME_FORMAT_PATTERN.matcher(txt).matches();
    }

    public static boolean isNotStandardDatetime(String txt) {
        return !isStandardDatetime(txt);
    }

    /**
     * 邮箱格式校验
     */
    public static boolean isEmail(String txt) {
        return EMAIL_PATTERN.matcher(txt).matches();
    }

    public static boolean isNotEmail(String txt) {
        return !isEmpty(txt);
    }

    /**
     * 身份证号格式校验
     */
    public static boolean isIdCard(String txt) {
        return IdCardUtil.validateIdCard(txt);
    }

    public static boolean isNotIdcard(String txt) {
        return !isIdCard(txt);
    }

    public static boolean isNotNull(String target) {
        return null != target;
    }

    public static boolean isEq(String target, String norm) {
        return target.equals(norm);
    }

    public static boolean isNotEq(String target, String norm) {
        return isEq(target, norm);
    }

    public static boolean isContains(String target, String norm) {
        return target.contains(norm);
    }

    public static boolean isNotContains(String target, String norm) {
        return !isContains(target, norm);
    }

    public static boolean isLengthEq(String target, int norm) {
        return target.length() == norm;
    }

    public static boolean isLengthLt(String target, int norm) {
        return target.length() < norm;
    }

    public static boolean isLengthLte(String target, int norm) {
        return target.length() <= norm;
    }

    public static boolean isLengthGt(String target, int norm) {
        return target.length() > norm;
    }

    public static boolean isLengthGte(String target, int norm) {
        return target.length() >= norm;
    }


}
