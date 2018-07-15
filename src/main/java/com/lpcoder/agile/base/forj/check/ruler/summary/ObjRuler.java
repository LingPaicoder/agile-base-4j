package com.lpcoder.agile.base.forj.check.ruler.summary;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;
import com.lpcoder.agile.base.forj.check.ruler.detail.object.ObjNotNullRuler;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class ObjRuler {

    public static Ruler<Object> notNull() {
        return new ObjNotNullRuler();
    }

    public static Ruler<Object> notNull(long failCode, String failDesc) {
        return new ObjNotNullRuler(failCode, failDesc);
    }

}
