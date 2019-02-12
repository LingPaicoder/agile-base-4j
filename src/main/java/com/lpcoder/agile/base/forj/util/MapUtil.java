package com.lpcoder.agile.base.forj.util;

import org.apache.commons.collections.MapUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-9
 */
public class MapUtil extends MapUtils {

    private MapUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> toHashMap(Pair<K, V>... pairs) {
        Map<K, V> result = new HashMap<>();
        Arrays.asList(pairs).forEach(pair -> result.put(pair.getKey(), pair.getValue()));
        return result;
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

    public static boolean isKeyContains(Map target, Object key) {
        return ArrayUtil.contains(target.keySet().toArray(), key);
    }

    public static boolean isKeyNotContains(Map target, Object key) {
        return !isKeyContains(target, key);
    }

    public static boolean isValueContains(Map target, Object key) {
        return ArrayUtil.contains(target.values().toArray(), key);
    }

    public static boolean isValueNotContains(Map target, Object key) {
        return !isValueContains(target, key);
    }

}
