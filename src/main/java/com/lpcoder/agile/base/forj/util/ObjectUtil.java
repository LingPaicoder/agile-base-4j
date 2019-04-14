package com.lpcoder.agile.base.forj.util;

import java.util.Objects;

/**
 * @author: liurenpeng
 * @date: Created in 17-11-24
 */
public class ObjectUtil {

    private ObjectUtil() {
    }

    public static boolean isNotNull(Object target) {
        return null != target;
    }

    public static boolean isEq(Object target, Object norm) {
        return Objects.equals(target, norm);
    }

    public static boolean isNotEq(Object target, Object norm) {
        return !isEq(target, norm);
    }
}
