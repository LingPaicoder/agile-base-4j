package com.lpcoder.agile.base.forj.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liurenpeng
 * @date: Created in 17-11-17
 */
public class ArrayUtil extends ArrayUtils {

    private ArrayUtil() {
    }

    public static boolean isNotNull(Object[] target) {
        return null != target;
    }

    public static boolean isContainsNull(Object[] target) {
        return ArrayUtil.contains(target, null);
    }

    public static boolean isNotContainsNull(Object[] target) {
        return !isContainsNull(target);
    }

    public static boolean isContainsDup(Object[] target) {
        Set<Object> targetSet = new HashSet<>();
        CollectionUtil.addAll(targetSet, target);
        return targetSet.size() != target.length;
    }

    public static boolean isNotContainsDup(Object[] target) {
        return !isContainsDup(target);
    }

    public static boolean isLengthEq(Object[] target, int norm) {
        return target.length == norm;
    }

    public static boolean isLengthGt(Object[] target, int norm) {
        return target.length > norm;
    }

    public static boolean isLengthGte(Object[] target, int norm) {
        return target.length >= norm;
    }

    public static boolean isLengthLt(Object[] target, int norm) {
        return target.length < norm;
    }

    public static boolean isLengthLte(Object[] target, int norm) {
        return target.length <= norm;
    }

}
