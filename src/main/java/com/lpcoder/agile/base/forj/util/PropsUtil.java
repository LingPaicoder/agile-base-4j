package com.lpcoder.agile.base.forj.util;

import com.lpcoder.agile.base.forj.log.BaseLoggerDecorator;
import com.lpcoder.agile.base.forj.log.LoggerDecorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * @author: liurenpeng
 * @date: Created in 18-5-24
 */
public class PropsUtil {

    private static final LoggerDecorator LOGGER = BaseLoggerDecorator.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String configPath) throws IOException {
        Properties props;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(configPath));
            props = new Properties();
            props.load(fis);
        } catch (IOException e) {
            LOGGER.error("load config error.path=" + configPath, e);
            throw e;
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream error.", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取 String 类型的属性值（默认值为空字符串）
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取 String 类型的属性值（可指定默认值）
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取 int 类型的属性值（默认值为 0）
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    /**
     * 获取 int 类型的属性值（可指定默认值）
     */
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取 long 类型的属性值（默认值为 0）
     */
    public static long getLong(Properties props, String key) {
        return getLong(props, key, 0L);
    }

    /**
     * 获取 long 类型的属性值（可指定默认值）
     */
    public static long getLong(Properties props, String key, long defaultValue) {
        long value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castLong(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取 boolean 类型属性（默认值为 false）
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取 boolean 类型属性（可指定默认值）
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }

}
