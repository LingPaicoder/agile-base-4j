package com.lpcoder.agile.base.forj.check.ruler;

import com.lpcoder.agile.base.forj.check.CheckException;

import java.util.Arrays;

/**
 * @author: liurenpeng
 * @date: Created in 2017-10-13
 */
@FunctionalInterface
public interface Ruler<T> {

    /**
     * 校验T
     */
    void check(T checkTarget);

    /**
     * 或操作
     */
    @SuppressWarnings("unchecked")
    default Ruler<T> or(Ruler<T>... rulers) {
        return checkTarget -> {
            try {
                check(checkTarget);
            } catch (CheckException e) {
                ofAll(rulers).check(checkTarget);
            }
        };
    }

    /**
     * Ruler整合
     */
    @SafeVarargs
    static <T> Ruler<T> ofAll(Ruler<T>... rulers) {
        return (checkTarget -> Arrays.stream(rulers).forEach(ruler -> ruler.check(checkTarget)));
    }

}
