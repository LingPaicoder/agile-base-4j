package com.lpcoder.agile.base.forj.check.ruler.detail.number;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.NumberUtil;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.SHORT_NOT_EQ_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class ShortNotEqRuler extends BaseRuler<Short> {

    private Short norm;

    public ShortNotEqRuler(Short norm) {
        init(norm, SHORT_NOT_EQ_FAIL.getCode(), SHORT_NOT_EQ_FAIL.getDesc());
    }

    public ShortNotEqRuler(Short norm, long failCode, String failDesc) {
        init(norm, failCode, failDesc);
    }

    private void init(Short norm, long failCode, String failDesc) {
        this.norm = norm;
        init(failCode, failDesc);
    }

    @Override
    public void check(Short checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (NumberUtil.isNotEq(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm));
    }

}
