package com.lpcoder.agile.base.forj.check;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;

import java.util.Arrays;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-11
 */
public class CheckUtil {

    private CheckUtil() {
    }

    @SafeVarargs
    public static <T> CheckHandler check(T target, String description, Ruler<T>... rulers) {
        return new CheckHandler().check(target, description, rulers);
    }

    public static class CheckHandler {
        @SuppressWarnings("unchecked")
        public <T> CheckHandler check(T target, String description, Ruler<T>... rulers) {
            try {
                Arrays.stream(rulers).forEach(ruler -> ruler.check(target));
                return this;
            } catch (CheckException e) {
                throw new CheckException(e.getCode(), description + e.getDesc(), e);
            }
        }
    }

}
