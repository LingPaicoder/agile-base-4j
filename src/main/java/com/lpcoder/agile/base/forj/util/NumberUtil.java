package com.lpcoder.agile.base.forj.util;

/**
 * @author: liurenpeng
 * @date: Created in 17-11-24
 */
public class NumberUtil {

    private NumberUtil() {
    }

    public static boolean isNotNull(Number target) {
        return null != target;
    }

    /**
     * byte
     */

    public static boolean isEq(Byte target, Byte norm) {
        return target.byteValue() == norm.byteValue();
    }

    public static boolean isGt(Byte target, Byte norm) {
        return target > norm;
    }

    public static boolean isGte(Byte target, Byte norm) {
        return target >= norm;
    }

    public static boolean isLt(Byte target, Byte norm) {
        return target < norm;
    }

    public static boolean isLte(Byte target, Byte norm) {
        return target <= norm;
    }

    /**
     * short
     */

    public static boolean isEq(Short target, Short norm) {
        return target.shortValue() == norm.shortValue();
    }

    public static boolean isGt(Short target, Short norm) {
        return target > norm;
    }

    public static boolean isGte(Short target, Short norm) {
        return target >= norm;
    }

    public static boolean isLt(Short target, Short norm) {
        return target < norm;
    }

    public static boolean isLte(Short target, Short norm) {
        return target <= norm;
    }

    /**
     * int
     */

    public static boolean isEq(Integer target, Integer norm) {
        return target.intValue() == norm.intValue();
    }

    public static boolean isGt(Integer target, Integer norm) {
        return target > norm;
    }

    public static boolean isGte(Integer target, Integer norm) {
        return target >= norm;
    }

    public static boolean isLt(Integer target, Integer norm) {
        return target < norm;
    }

    public static boolean isLte(Integer target, Integer norm) {
        return target <= norm;
    }

    /**
     * long
     */

    public static boolean isEq(Long target, Long norm) {
        return target.longValue() == norm.longValue();
    }

    public static boolean isGt(Long target, Long norm) {
        return target > norm;
    }

    public static boolean isGte(Long target, Long norm) {
        return target >= norm;
    }

    public static boolean isLt(Long target, Long norm) {
        return target < norm;
    }

    public static boolean isLte(Long target, Long norm) {
        return target <= norm;
    }

    /**
     * float
     */

    public static boolean isEq(Float target, Float norm) {
        return target.floatValue() == norm.floatValue();
    }

    public static boolean isGt(Float target, Float norm) {
        return target > norm;
    }

    public static boolean isGte(Float target, Float norm) {
        return target >= norm;
    }

    public static boolean isLt(Float target, Float norm) {
        return target < norm;
    }

    public static boolean isLte(Float target, Float norm) {
        return target <= norm;
    }

    /**
     * double
     */

    public static boolean isEq(Double target, Double norm) {
        return target.doubleValue() == norm.doubleValue();
    }

    public static boolean isGt(Double target, Double norm) {
        return target > norm;
    }

    public static boolean isGte(Double target, Double norm) {
        return target >= norm;
    }

    public static boolean isLt(Double target, Double norm) {
        return target < norm;
    }

    public static boolean isLte(Double target, Double norm) {
        return target <= norm;
    }

}
