package com.lpcoder.agile.base.forj.util;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.util.Pair;

import static com.lpcoder.agile.base.forj.check.CheckUtil.check;
import static com.lpcoder.agile.base.forj.check.ruler.summary.LongRuler.gt;
import static com.lpcoder.agile.base.forj.check.ruler.summary.LongRuler.lte;

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

    public static List<Pair<Long, Long>> divideInterval(Long minNum, Long maxNum, Long step) {
        List<Long> partition = partitionNum(minNum, maxNum, step);
        if (partition.size() == 1) {
            return Collections.singletonList(new Pair<>(partition.get(0), partition.get(0)));
        }
        List<Pair<Long, Long>> rst = new LinkedList<>();
        for (int i = 0; i < partition.size() - 1; i++) {
            rst.add(new Pair<>(partition.get(i), partition.get(i + 1)));
        }
        return rst;
    }

    public static List<Long> partitionNum(Long minNum, Long maxNum, Long step) {
        check(minNum, gt(0L), lte(maxNum));
        List<Long> rst = new LinkedList<>();
        Long currId = minNum;
        while (currId < maxNum) {
            rst.add(currId);
            currId += step;
        }
        rst.add(maxNum);
        return rst;
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

    public static boolean isContains(Collection target, Object norm) {
        return ArrayUtil.contains(target.toArray(), norm);
    }

    public static boolean isNotContains(Collection target, Object norm) {
        return !isContains(target, norm);
    }

}
