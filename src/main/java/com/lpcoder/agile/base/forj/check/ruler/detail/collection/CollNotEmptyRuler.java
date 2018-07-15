package com.lpcoder.agile.base.forj.check.ruler.detail.collection;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.CollectionUtil;

import java.util.Collection;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.COLL_NOT_EMPTY_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class CollNotEmptyRuler extends BaseRuler<Collection> {


    public CollNotEmptyRuler() {
        init(COLL_NOT_EMPTY_FAIL.getCode(), COLL_NOT_EMPTY_FAIL.getDesc());
    }

    public CollNotEmptyRuler(long failCode, String failDesc) {
        init(failCode, failDesc);
    }

    @Override
    public void check(Collection checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (CollectionUtil.isNotEmpty(checkTarget)) {
            return;
        }
        throw new CheckException(failCode, failDesc);
    }

}
