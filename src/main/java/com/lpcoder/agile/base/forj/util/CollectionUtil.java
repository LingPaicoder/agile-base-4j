package com.lpcoder.agile.base.forj.util;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: liurenpeng@daojia.com
 * @date: Created in 14:53 2017-10-11
 */
public class CollectionUtil extends CollectionUtils {

    private CollectionUtil() {
    }

    public static Collection collect(Collection collection, String propertyName) {
        return CollectionUtils.collect(collection,
                new BeanToPropertyValueTransformer(propertyName));
    }

    public static <T> T firstObj(Collection<T> collection) {
        return Optional.ofNullable(collection)
                .filter(Objects::nonNull)
                .map(c -> {
                    Iterator<T> iterator = c.iterator();
                    if (iterator.hasNext()) {
                        return iterator.next();
                    } else {
                        return null;
                    }
                })
                .orElse(null);
    }

    public static boolean isNotNull(Collection target) {
        return null != target;
    }

    public static boolean isSizeEq(Collection target, int norm) {
        return target.size() == norm;
    }

    public static boolean isSizeGt(Collection target, int norm) {
        return target.size() > norm;
    }

    public static boolean isSizeGte(Collection target, int norm) {
        return target.size() >= norm;
    }

    public static boolean isSizeLt(Collection target, int norm) {
        return target.size() < norm;
    }

    public static boolean isSizeLte(Collection target, int norm) {
        return target.size() <= norm;
    }

    public static boolean isNotContainsNull(Collection target) {
        return ArrayUtil.isNotContainsNull(target.toArray());
    }

    public static boolean isNotContainsDup(Collection target) {
        return ArrayUtil.isNotContainsDup(target.toArray());
    }

}
