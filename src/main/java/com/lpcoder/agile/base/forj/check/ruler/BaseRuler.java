package com.lpcoder.agile.base.forj.check.ruler;

/**
 * @author: liurenpeng
 * @date: Created in 18-6-27
 */
public abstract class BaseRuler<T> implements Ruler<T> {

    protected long failCode;
    protected String failDesc;

    protected void init(long failCode, String failDesc) {
        this.failCode = failCode;
        this.failDesc = failDesc;
    }

}
