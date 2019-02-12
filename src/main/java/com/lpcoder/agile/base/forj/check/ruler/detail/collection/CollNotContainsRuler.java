package com.lpcoder.agile.base.forj.check.ruler.detail.collection;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.CollectionUtil;

import java.util.Collection;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.COLL_NOT_CONTAINS_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class CollNotContainsRuler extends BaseRuler<Collection> {

    private Object norm;

    public CollNotContainsRuler(Object norm) {
        init(norm, COLL_NOT_CONTAINS_FAIL.getCode(), COLL_NOT_CONTAINS_FAIL.getDesc());
    }

    public CollNotContainsRuler(Object norm, long failCode, String failDesc) {
        init(norm, failCode, failDesc);
    }

    private void init(Object norm, long failCode, String failDesc) {
        this.norm = norm;
        init(failCode, failDesc);
    }

    @Override
    public void check(Collection checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (CollectionUtil.isNotContains(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm));
    }

}
