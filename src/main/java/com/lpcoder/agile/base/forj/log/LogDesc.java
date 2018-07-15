package com.lpcoder.agile.base.forj.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liurenpeng
 * @description: action/service日志描述,{@link BaseLoggerDecorator}会自动将注解内容追加到log中,默认为方法名
 * @date: Created in 17-11-15
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDesc {

    String value() default "";

}
