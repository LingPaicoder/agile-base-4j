package com.lpcoder.agile.base.forj.check.ruler.detail.string;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.StringUtil;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.STR_STANDARD_DATETIME_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class StrStandardDatetimeRuler extends BaseRuler<String> {


    public StrStandardDatetimeRuler() {
        init(STR_STANDARD_DATETIME_FAIL.getCode(), STR_STANDARD_DATETIME_FAIL.getDesc());
    }

    public StrStandardDatetimeRuler(long failCode, String failDesc) {
        init(failCode, failDesc);
    }

    @Override
    public void check(String checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (StringUtil.isStandardDatetime(checkTarget)) {
            return;
        }
        throw new CheckException(failCode, failDesc);
    }

}
