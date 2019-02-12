package com.lpcoder.agile.base.forj.check.ruler.detail.map;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.MapUtil;

import java.util.Map;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.MAP_VALUE_NOT_CONTAINS_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class MapValueNotContainsRuler extends BaseRuler<Map> {

    private Object norm;

    public MapValueNotContainsRuler(Object norm) {
        init(norm, MAP_VALUE_NOT_CONTAINS_FAIL.getCode(), MAP_VALUE_NOT_CONTAINS_FAIL.getDesc());
    }

    public MapValueNotContainsRuler(Object norm, long failCode, String failDesc) {
        init(norm, failCode, failDesc);
    }

    private void init(Object norm, long failCode, String failDesc) {
        this.norm = norm;
        init(failCode, failDesc);
    }

    @Override
    public void check(Map checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (MapUtil.isValueNotContains(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm));
    }

}
