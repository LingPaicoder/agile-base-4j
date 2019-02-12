package com.lpcoder.agile.base.forj.check.ruler.detail.map;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.MapUtil;

import java.util.Map;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.MAP_KEY_CONTAINS_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class MapKeyContainsRuler extends BaseRuler<Map> {

    private Object norm;

    public MapKeyContainsRuler(Object norm) {
        init(norm, MAP_KEY_CONTAINS_FAIL.getCode(), MAP_KEY_CONTAINS_FAIL.getDesc());
    }

    public MapKeyContainsRuler(Object norm, long failCode, String failDesc) {
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
        if (MapUtil.isKeyContains(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm));
    }

}
