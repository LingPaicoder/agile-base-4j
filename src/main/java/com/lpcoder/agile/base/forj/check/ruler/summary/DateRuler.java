package com.lpcoder.agile.base.forj.check.ruler.summary;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;
import com.lpcoder.agile.base.forj.check.ruler.detail.date.DateAfterOrEqRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.date.DateAfterRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.date.DateBeforeOrEqRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.date.DateBeforeRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.date.DateEqRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.date.DateNotNullRuler;

import java.util.Date;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class DateRuler {

    public static Ruler<Date> eq(Date norm) {
        return new DateEqRuler(norm);
    }

    public static Ruler<Date> eq(Date norm, long failCode, String failDesc) {
        return new DateEqRuler(norm, failCode, failDesc);
    }

    public static Ruler<Date> after(Date norm) {
        return new DateAfterRuler(norm);
    }

    public static Ruler<Date> after(Date norm, long failCode, String failDesc) {
        return new DateAfterRuler(norm, failCode, failDesc);
    }

    public static Ruler<Date> afterOrEq(Date norm) {
        return new DateAfterOrEqRuler(norm);
    }

    public static Ruler<Date> afterOrEq(Date norm, long failCode, String failDesc) {
        return new DateAfterOrEqRuler(norm, failCode, failDesc);
    }

    public static Ruler<Date> before(Date norm) {
        return new DateBeforeRuler(norm);
    }

    public static Ruler<Date> before(Date norm, long failCode, String failDesc) {
        return new DateBeforeRuler(norm, failCode, failDesc);
    }

    public static Ruler<Date> beforeOrEq(Date norm) {
        return new DateBeforeOrEqRuler(norm);
    }

    public static Ruler<Date> beforeOrEq(Date norm, long failCode, String failDesc) {
        return new DateBeforeOrEqRuler(norm, failCode, failDesc);
    }

    public static Ruler<Date> notNull() {
        return new DateNotNullRuler();
    }

    public static Ruler<Date> notNull(long failCode, String failDesc) {
        return new DateNotNullRuler(failCode, failDesc);
    }

}
