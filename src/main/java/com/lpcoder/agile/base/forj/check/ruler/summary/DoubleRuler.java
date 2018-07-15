package com.lpcoder.agile.base.forj.check.ruler.summary;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.DoubleEqRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.DoubleGtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.DoubleGteRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.DoubleLtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.DoubleLteRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.DoubleNotNullRuler;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class DoubleRuler {

    public static Ruler<Double> eq(Double norm) {
        return new DoubleEqRuler(norm);
    }

    public static Ruler<Double> eq(Double norm, long failCode, String failDesc) {
        return new DoubleEqRuler(norm, failCode, failDesc);
    }

    public static Ruler<Double> gt(Double norm) {
        return new DoubleGtRuler(norm);
    }

    public static Ruler<Double> gt(Double norm, long failCode, String failDesc) {
        return new DoubleGtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Double> gte(Double norm) {
        return new DoubleGteRuler(norm);
    }

    public static Ruler<Double> gte(Double norm, long failCode, String failDesc) {
        return new DoubleGteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Double> lt(Double norm) {
        return new DoubleLtRuler(norm);
    }

    public static Ruler<Double> lt(Double norm, long failCode, String failDesc) {
        return new DoubleLtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Double> lte(Double norm) {
        return new DoubleLteRuler(norm);
    }

    public static Ruler<Double> lte(Double norm, long failCode, String failDesc) {
        return new DoubleLteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Double> notNull() {
        return new DoubleNotNullRuler();
    }

    public static Ruler<Double> notNull(long failCode, String failDesc) {
        return new DoubleNotNullRuler(failCode, failDesc);
    }

}
