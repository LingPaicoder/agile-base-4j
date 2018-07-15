package com.lpcoder.agile.base.forj.check.ruler.summary;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.ByteEqRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.ByteGtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.ByteGteRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.ByteLtRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.ByteLteRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.number.ByteNotNullRuler;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class ByteRuler {

    public static Ruler<Byte> eq(Byte norm) {
        return new ByteEqRuler(norm);
    }

    public static Ruler<Byte> eq(Byte norm, long failCode, String failDesc) {
        return new ByteEqRuler(norm, failCode, failDesc);
    }

    public static Ruler<Byte> gt(Byte norm) {
        return new ByteGtRuler(norm);
    }

    public static Ruler<Byte> gt(Byte norm, long failCode, String failDesc) {
        return new ByteGtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Byte> gte(Byte norm) {
        return new ByteGteRuler(norm);
    }

    public static Ruler<Byte> gte(Byte norm, long failCode, String failDesc) {
        return new ByteGteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Byte> lt(Byte norm) {
        return new ByteLtRuler(norm);
    }

    public static Ruler<Byte> lt(Byte norm, long failCode, String failDesc) {
        return new ByteLtRuler(norm, failCode, failDesc);
    }

    public static Ruler<Byte> lte(Byte norm) {
        return new ByteLteRuler(norm);
    }

    public static Ruler<Byte> lte(Byte norm, long failCode, String failDesc) {
        return new ByteLteRuler(norm, failCode, failDesc);
    }

    public static Ruler<Byte> notNull() {
        return new ByteNotNullRuler();
    }

    public static Ruler<Byte> notNull(long failCode, String failDesc) {
        return new ByteNotNullRuler(failCode, failDesc);
    }

}
