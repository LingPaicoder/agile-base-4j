package com.lpcoder.agile.base.forj.log;

import org.slf4j.Logger;

/**
 * @author: liurenpeng
 * @description: Logger装饰器接口.功能:参数自动收集;LogDesc;logId
 * @date: Created in 2017-10-11
 */
public interface LoggerDecorator extends Logger {
    /**
     * 设置logId
     */
    LoggerDecorator setLogId(String logId);

    /**
     * 设置前缀.可通过拦截器由{@link LogDesc#value()}提供
     */
    LoggerDecorator setBusinessPrefix(String prefix);

    /**
     * 注册参数
     */
    LoggerDecorator register(String key, Object val);

    /**
     * 清除
     */
    LoggerDecorator clear();
    
}
