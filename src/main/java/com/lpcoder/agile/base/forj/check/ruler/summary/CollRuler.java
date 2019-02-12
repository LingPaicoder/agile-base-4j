package com.lpcoder.agile.base.forj.check.ruler.summary;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollContainsRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollNotContainsDupRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollNotContainsNullRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollNotContainsRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollNotEmptyRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollNotNullRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollSizeEqRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollSizeGtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollSizeGteRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollSizeLtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.collection.CollSizeLteRuler;

import java.util.Collection;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class CollRuler {

    public static Ruler<Collection> notEmpty() {
        return new CollNotEmptyRuler();
    }

    public static Ruler<Collection> notEmpty(long failCode, String failDesc) {
        return new CollNotEmptyRuler(failCode, failDesc);
    }

    public static Ruler<Collection> sizeEq(int norm) {
        return new CollSizeEqRuler(norm);
    }

    public static Ruler<Collection> sizeEq(int norm, long failCode, String failDesc) {
        return new CollSizeEqRuler(norm, failCode, failDesc);
    }

    public static Ruler<Collection> sizeGt(int norm) {
        return new CollSizeGtRuler(norm);
    }

    public static Ruler<Collection> sizeGt(int norm, long failCode, String failDesc) {
        return new CollSizeGtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Collection> sizeGte(int norm) {
        return new CollSizeGteRuler(norm);
    }

    public static Ruler<Collection> sizeGte(int norm, long failCode, String failDesc) {
        return new CollSizeGteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Collection> sizeLt(int norm) {
        return new CollSizeLtRuler(norm);
    }

    public static Ruler<Collection> sizeLt(int norm, long failCode, String failDesc) {
        return new CollSizeLtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Collection> sizeLte(int norm) {
        return new CollSizeLteRuler(norm);
    }

    public static Ruler<Collection> sizeLte(int norm, long failCode, String failDesc) {
        return new CollSizeLteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Collection> notContainsNull() {
        return new CollNotContainsNullRuler();
    }

    public static Ruler<Collection> notContainsNull(long failCode, String failDesc) {
        return new CollNotContainsNullRuler(failCode, failDesc);
    }

    public static Ruler<Collection> notContainsDup() {
        return new CollNotContainsDupRuler();
    }

    public static Ruler<Collection> notContainsDup(long failCode, String failDesc) {
        return new CollNotContainsDupRuler(failCode, failDesc);
    }

    public static Ruler<Collection> notNull() {
        return new CollNotNullRuler();
    }

    public static Ruler<Collection> notNull(long failCode, String failDesc) {
        return new CollNotNullRuler(failCode, failDesc);
    }

    public static Ruler<Collection> contains(Object norm) {
        return new CollContainsRuler(norm);
    }

    public static Ruler<Collection> contains(Object norm, long failCode, String failDesc) {
        return new CollContainsRuler(norm, failCode, failDesc);
    }

    public static Ruler<Collection> notContains(Object norm) {
        return new CollNotContainsRuler(norm);
    }

    public static Ruler<Collection> notContains(Object norm, long failCode, String failDesc) {
        return new CollNotContainsRuler(norm, failCode, failDesc);
    }

}
