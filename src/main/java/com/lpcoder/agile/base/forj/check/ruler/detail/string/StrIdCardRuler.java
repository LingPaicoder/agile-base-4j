package com.lpcoder.agile.base.forj.check.ruler.detail.string;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.StringUtil;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.STR_ID_CARD_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class StrIdCardRuler extends BaseRuler<String> {


    public StrIdCardRuler() {
        init(STR_ID_CARD_FAIL.getCode(), STR_ID_CARD_FAIL.getDesc());
    }

    public StrIdCardRuler(long failCode, String failDesc) {
        init(failCode, failDesc);
    }

    @Override
    public void check(String checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (StringUtil.isIdCard(checkTarget)) {
            return;
        }
        throw new CheckException(failCode, failDesc);
    }

}
