package com.lpcoder.agile.base.forj.check;

import lombok.Getter;

/**
 * @author: liurenpeng
 * @date: Created in 18-6-27
 */
public class CheckException extends RuntimeException {

    /**
     * 校验结果码
     */
    @Getter
    private long code;

    /**
     * 校验结果描述
     */
    @Getter
    private String desc;

    public CheckException(long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public CheckException(long code, String desc, CheckException e) {
        super(e);
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getMessage() {
        return "code=" + code + ", desc=" + desc;
    }

}
