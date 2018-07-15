package com.lpcoder.agile.base.forj.util;

import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-9
 */
public class MapUtil extends MapUtils {

    private MapUtil() {
    }

    public static boolean isNotNull(Map target) {
        return null != target;
    }

    public static boolean isSizeEq(Map target, int norm) {
        return target.size() == norm;
    }

    public static boolean isSizeGt(Map target, int norm) {
        return target.size() > norm;
    }

    public static boolean isSizeGte(Map target, int norm) {
        return target.size() >= norm;
    }

    public static boolean isSizeLt(Map target, int norm) {
        return target.size() < norm;
    }

    public static boolean isSizeLte(Map target, int norm) {
        return target.size() <= norm;
    }

    public static boolean isKeyNotContainsNull(Map target) {
        return ArrayUtil.isNotContainsNull(target.keySet().toArray());
    }

}
