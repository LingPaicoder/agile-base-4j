package com.lpcoder.agile.base.forj.check.ruler.detail.date;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.DateUtil;

import java.util.Date;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.DATE_AFTER_OR_EQ_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class DateAfterOrEqRuler extends BaseRuler<Date> {

    private Date norm;

    public DateAfterOrEqRuler(Date norm) {
        init(norm, DATE_AFTER_OR_EQ_FAIL.getCode(), DATE_AFTER_OR_EQ_FAIL.getDesc());
    }

    public DateAfterOrEqRuler(Date norm, long failCode, String failDesc) {
        init(norm, failCode, failDesc);
    }

    private void init(Date norm, long failCode, String failDesc) {
        this.norm = norm;
        init(failCode, failDesc);
    }

    @Override
    public void check(Date checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (DateUtil.isAfterOrEq(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm, norm));
    }

}
