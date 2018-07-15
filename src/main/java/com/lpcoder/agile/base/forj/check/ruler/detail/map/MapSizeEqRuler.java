package com.lpcoder.agile.base.forj.check.ruler.detail.map;

import com.lpcoder.agile.base.forj.check.CheckException;
import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;
import com.lpcoder.agile.base.forj.util.MapUtil;

import java.util.Map;

import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;
import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.MAP_SIZE_EQ_FAIL;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-12
 */
public class MapSizeEqRuler extends BaseRuler<Map> {

    private int norm;

    public MapSizeEqRuler(int norm) {
        init(norm, MAP_SIZE_EQ_FAIL.getCode(), MAP_SIZE_EQ_FAIL.getDesc());
    }

    public MapSizeEqRuler(int norm, long failCode, String failDesc) {
        init(norm, failCode, failDesc);
    }

    private void init(int norm, long failCode, String failDesc) {
        this.norm = norm;
        init(failCode, failDesc);
    }

    @Override
    public void check(Map checkTarget) {
        if (null == checkTarget) {
            return;
        }
        if (MapUtil.isSizeEq(checkTarget, norm)) {
            return;
        }
        throw new CheckException(failCode, String.format(failDesc, norm));
    }

}
