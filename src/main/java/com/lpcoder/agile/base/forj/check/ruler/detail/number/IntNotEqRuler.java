package com.lpcoder.agile.base.forj.check.ruler.detail.number;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.NumberUtil;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.INT_NOT_EQ_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class IntNotEqRuler extends BaseRuler<Integer> {

    private Integer norm;

    public IntNotEqRuler(Integer norm) {
        init(norm, INT_NOT_EQ_FAIL.getCode(), INT_NOT_EQ_FAIL.getDesc());
    }

    public IntNotEqRuler(Integer norm, long failCode, String failDesc) {
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
        if (NumberUtil.isNotEq(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm));
    }

}
