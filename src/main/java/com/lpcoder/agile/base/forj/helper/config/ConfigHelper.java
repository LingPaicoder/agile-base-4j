package com.lpcoder.agile.base.forj.helper.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: liurenpeng
 * @date: Created in 17-11-28
 */
public class ConfigHelper {

    private ConfigHelper() {
    }

    private static Properties prop = null;

    public static void init(String path) throws IOException {
        prop = new Properties();
        prop.load(new FileInputStream(new File(path)));
    }

    public static String getProp(String key) {
        return prop.getProperty(key);
    }

}
