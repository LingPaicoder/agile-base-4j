package com.lpcoder.agile.base.forj.util;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author: liurenpeng
 * @date: Created in 17-12-7
 */
public class GuiceUtil {

    private GuiceUtil() {
    }

    private static Injector injector;

    public static Injector getInjector(){
        if (null == injector){
            synchronized (GuiceUtil.class){
                if (null == injector){
                    injector = Guice.createInjector();
                }
            }
        }
        return injector;
    }
}
