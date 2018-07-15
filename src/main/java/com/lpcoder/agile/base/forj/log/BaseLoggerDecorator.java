package com.lpcoder.agile.base.forj.log;

import com.lpcoder.agile.base.forj.util.JsonUtil;
import com.lpcoder.agile.base.forj.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liurenpeng
 * @date: Created in 2017-10-11
 */
public class BaseLoggerDecorator implements LoggerDecorator {

    private static final ThreadLocal<Map<String, Object>> LOG_ARGS_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<String> BUSINESS_PREFIX_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<String> LOG_ID_THREAD_LOCAL = new ThreadLocal<>();

    private Logger logger;

    public static BaseLoggerDecorator getLogger(Class clazz) {
        return new BaseLoggerDecorator(clazz);
    }

    public BaseLoggerDecorator(Class clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public LoggerDecorator clear() {
        LOG_ARGS_THREAD_LOCAL.remove();
        LOG_ARGS_THREAD_LOCAL.set(new HashMap<>(8));
        BUSINESS_PREFIX_THREAD_LOCAL.remove();
        LOG_ID_THREAD_LOCAL.remove();
        return this;
    }

    @Override
    public LoggerDecorator register(String key, Object val) {
        if (null == LOG_ARGS_THREAD_LOCAL.get()) {
            LOG_ARGS_THREAD_LOCAL.set(new HashMap<>(8));
        }
        LOG_ARGS_THREAD_LOCAL.get().put(null == key ? "" : key, null == val ? "" : val);
        return this;
    }

    private String getLogArgs() {
        return "logArgs:" + JsonUtil.toJson(LOG_ARGS_THREAD_LOCAL.get());
    }

    @Override
    public LoggerDecorator setBusinessPrefix(String prefix) {
        BUSINESS_PREFIX_THREAD_LOCAL.set(prefix);
        return this;
    }

    private String getBusinessPrefix() {
        return StringUtil.isEmpty(BUSINESS_PREFIX_THREAD_LOCAL.get()) ?
                "noLogDesc" : BUSINESS_PREFIX_THREAD_LOCAL.get();
    }

    @Override
    public LoggerDecorator setLogId(String logId) {
        LOG_ID_THREAD_LOCAL.set(logId);
        return this;
    }

    private String getLogId() {
        return StringUtil.isEmpty(LOG_ID_THREAD_LOCAL.get()) ?
                "0" : LOG_ID_THREAD_LOCAL.get();
    }

    private String buildLogStr(String msg) {
        boolean needPrefixBracket = StringUtil.isNotEmpty(msg) && !msg.contains("【");
        boolean needSuffixBracket = StringUtil.isNotEmpty(msg) && !msg.contains("】");
        String logStr = StringUtil.join(
                needPrefixBracket ? "【 " : "",
                msg,
                needSuffixBracket ? " 】" : "",
                " - ", getLogArgs());
        return addLogIdAndBusinessPrefix(logStr);
    }

    private String addLogIdAndBusinessPrefix(String logStr) {
        String prefixBracket = "【";
        if (StringUtil.isEmpty(logStr) || !logStr.contains(prefixBracket)) {
            return logStr;
        }
        int index = logStr.indexOf("【");
        logStr = addBusinessPrefix(index, logStr);
        logStr = addLogId(index, logStr);
        return logStr;
    }

    private String addLogId(int index, String logStr) {
        StringBuilder sb = new StringBuilder(logStr);
        sb.insert(index, StringUtil.join("logId:", getLogId() + " "));
        return sb.toString();
    }

    private String addBusinessPrefix(int index, String logStr) {
        StringBuilder sb = new StringBuilder(logStr);
        sb.insert(index + 1, StringUtil.join(" ", getBusinessPrefix(), " -"));
        return sb.toString();
    }

    /**
     * 重写Logger方法
     **/

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        logger.trace(buildLogStr(msg));
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(buildLogStr(format), arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(buildLogStr(format), arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(buildLogStr(format), arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(buildLogStr(msg), t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, buildLogStr(msg));
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, buildLogStr(format), arg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, buildLogStr(format), arg1, arg2);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, buildLogStr(format), argArray);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, buildLogStr(msg), t);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.debug(buildLogStr(msg));
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(buildLogStr(format), arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(buildLogStr(format), arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(buildLogStr(format), arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(buildLogStr(msg), t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, buildLogStr(msg));
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, buildLogStr(format), arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, buildLogStr(format), arg1, arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        logger.debug(marker, buildLogStr(format), arguments);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, buildLogStr(msg), t);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        logger.info(buildLogStr(msg));
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(buildLogStr(format), arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(buildLogStr(format), arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(buildLogStr(format), arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(buildLogStr(msg), t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, buildLogStr(msg));
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, buildLogStr(format), arg);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, buildLogStr(format), arg1, arg2);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        logger.info(marker, buildLogStr(format), arguments);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, buildLogStr(msg), t);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.warn(buildLogStr(msg));
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(buildLogStr(format), arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(buildLogStr(format), arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(buildLogStr(format), arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(buildLogStr(msg), t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, buildLogStr(msg));
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, buildLogStr(format), arg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, buildLogStr(format), arg1, arg2);
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        logger.warn(marker, buildLogStr(format), arguments);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, buildLogStr(msg), t);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        logger.error(buildLogStr(msg));
    }

    @Override
    public void error(String format, Object arg) {
        logger.error(buildLogStr(format), arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(buildLogStr(format), arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(buildLogStr(format), arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(buildLogStr(msg), t);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, buildLogStr(msg));
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, buildLogStr(format), arg);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, buildLogStr(format), arg1, arg2);
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        logger.error(marker, buildLogStr(format), arguments);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, buildLogStr(msg), t);
    }
}
