package com.lpcoder.agile.base.forj.check.ruler.detail.array;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.ArrayUtil;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.ARR_NOT_CONTAINS_DUP_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class ArrNotContainsDupRuler extends BaseRuler<Object[]> {


    public ArrNotContainsDupRuler() {
        init(ARR_NOT_CONTAINS_DUP_FAIL.getCode(), ARR_NOT_CONTAINS_DUP_FAIL.getDesc());
    }

    public ArrNotContainsDupRuler(long failCode, String failDesc) {
        init(failCode, failDesc);
    }

    @Override
    public void check(Object[] checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (ArrayUtil.isNotContainsDup(checkTarget)) {
            return;
        }
        throw new CheckException(failCode, failDesc);
    }

}
