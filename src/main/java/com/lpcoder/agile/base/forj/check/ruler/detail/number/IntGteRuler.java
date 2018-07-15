package com.lpcoder.agile.base.forj.check.ruler.detail.number;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.NumberUtil;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.INT_GTE_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class IntGteRuler extends BaseRuler<Integer> {

    private Integer norm;

    public IntGteRuler(Integer norm) {
        init(norm, INT_GTE_FAIL.getCode(), INT_GTE_FAIL.getDesc());
    }

    public IntGteRuler(Integer norm, long failCode, String failDesc) {
        init(norm, failCode, failDesc);
    }

    private void init(Integer norm, long failCode, String failDesc) {
        this.norm = norm;
        init(failCode, failDesc);
    }

    @Override
    public void check(Integer checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (NumberUtil.isGte(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm));
    }

}
