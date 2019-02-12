package com.lpcoder.agile.base.forj.check.ruler.summary;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapKeyContainsRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapKeyNotContainsNullRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapKeyNotContainsRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapNotEmptyRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapNotNullRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapSizeEqRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapSizeGtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapSizeGteRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapSizeLtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapSizeLteRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapValueContainsRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.map.MapValueNotContainsRuler;

import java.util.Map;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class MapRuler {

    public static Ruler<Map> notEmpty() {
        return new MapNotEmptyRuler();
    }

    public static Ruler<Map> notEmpty(long failCode, String failDesc) {
        return new MapNotEmptyRuler(failCode, failDesc);
    }

    public static Ruler<Map> sizeEq(int norm) {
        return new MapSizeEqRuler(norm);
    }

    public static Ruler<Map> sizeEq(int norm, long failCode, String failDesc) {
        return new MapSizeEqRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> sizeGt(int norm) {
        return new MapSizeGtRuler(norm);
    }

    public static Ruler<Map> sizeGt(int norm, long failCode, String failDesc) {
        return new MapSizeGtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> sizeGte(int norm) {
        return new MapSizeGteRuler(norm);
    }

    public static Ruler<Map> sizeGte(int norm, long failCode, String failDesc) {
        return new MapSizeGteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> sizeLt(int norm) {
        return new MapSizeLtRuler(norm);
    }

    public static Ruler<Map> sizeLt(int norm, long failCode, String failDesc) {
        return new MapSizeLtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> sizeLte(int norm) {
        return new MapSizeLteRuler(norm);
    }

    public static Ruler<Map> sizeLte(int norm, long failCode, String failDesc) {
        return new MapSizeLteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> keyNotContainsNull() {
        return new MapKeyNotContainsNullRuler();
    }

    public static Ruler<Map> keyNotContainsNull(long failCode, String failDesc) {
        return new MapKeyNotContainsNullRuler(failCode, failDesc);
    }

    public static Ruler<Map> notNull() {
        return new MapNotNullRuler();
    }

    public static Ruler<Map> notNull(long failCode, String failDesc) {
        return new MapNotNullRuler(failCode, failDesc);
    }

    public static Ruler<Map> keyContains(Object norm) {
        return new MapKeyContainsRuler(norm);
    }

    public static Ruler<Map> keyContains(Object norm, long failCode, String failDesc) {
        return new MapKeyContainsRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> keyNotContains(Object norm) {
        return new MapKeyNotContainsRuler(norm);
    }

    public static Ruler<Map> keyNotContains(Object norm, long failCode, String failDesc) {
        return new MapKeyNotContainsRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> valueContains(Object norm) {
        return new MapValueContainsRuler(norm);
    }

    public static Ruler<Map> valueContains(Object norm, long failCode, String failDesc) {
        return new MapValueContainsRuler(norm, failCode, failDesc);
    }

    public static Ruler<Map> valueNotContains(Object norm) {
        return new MapValueNotContainsRuler(norm);
    }

    public static Ruler<Map> valueNotContains(Object norm, long failCode, String failDesc) {
        return new MapValueNotContainsRuler(norm, failCode, failDesc);
    }

}
