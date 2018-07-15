package com.lpcoder.agile.base.forj.check.ruler.detail.object;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.ObjectUtil;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.OBJ_NOT_NULL_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class ObjNotNullRuler extends BaseRuler<Object> {


    public ObjNotNullRuler() {
        init(OBJ_NOT_NULL_FAIL.getCode(), OBJ_NOT_NULL_FAIL.getDesc());
    }

    public ObjNotNullRuler(long failCode, String failDesc) {
        init(failCode, failDesc);
    }

    @Override
    public void check(Object checkTarget) {
        if (ObjectUtil.isNotNull(checkTarget)) {
            return;
        }
        throw new CheckException(failCode, failDesc);
    }

}
