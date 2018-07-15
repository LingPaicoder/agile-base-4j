package com.lpcoder.agile.base.forj.check.ruler.detail.date;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.DateUtil;

import java.util.Date;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.DATE_NOT_NULL_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class DateNotNullRuler extends BaseRuler<Date> {


    public DateNotNullRuler() {
        init(DATE_NOT_NULL_FAIL.getCode(), DATE_NOT_NULL_FAIL.getDesc());
    }

    public DateNotNullRuler(long failCode, String failDesc) {
        init(failCode, failDesc);
    }

    @Override
    public void check(Date checkTarget) {
        if (DateUtil.isNotNull(checkTarget)) {
            return;
        }
        throw new CheckException(failCode, failDesc);
    }

}
