package com.lpcoder.agile.base.forj.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author: liurenpeng
 * @date: Created in 18-1-2
 */
public class ClassUtil {

    private static String OBJECT_CLASS_NAME = "java.lang.Object";

    private ClassUtil() {
    }

    /**
     * 获取该类及其所有超类的所有域
     */
    public static Map<String, Field> getHierarchyFields(Class<?> cls) {
        return getHierarchyFields(cls, new HashMap<>());
    }

    private static Map<String, Field> getHierarchyFields(Class<?> cls, Map<String, Field> result) {
        if (OBJECT_CLASS_NAME.equals(cls.getName())) {
            return result;
        } else {
            Stream.of(cls.getDeclaredFields())
                    .filter(it -> !result.containsKey(it.getName()))
                    .forEach(it -> result.put(it.getName(), it));
            return getHierarchyFields(cls.getSuperclass(), result);
        }
    }

    /**
     * 仅限public修饰的，以is/get+FieldName格式命名的getter函数
     */
    public static Method getGetterMethod(Class<?> cls, Field field) {
        try {
            return cls.getMethod("get" + StringUtil.upperFirstLetter(field.getName()));
        } catch (Exception e) {
            try {
                return cls.getMethod("is" + StringUtil.upperFirstLetter(field.getName()));
            } catch (Exception e1) {
                return null;
            }
        }
    }

    /**
     * 仅限public修饰的，以set+FieldName格式命名的setter函数
     */
    public static Method getSetterMethod(Class<?> cls, Field field) {
        try {
            return cls.getMethod("set" + StringUtil.upperFirstLetter(field.getName()), field.getType());
        } catch (Exception e) {
            return null;
        }
    }

    public static Object getFieldValue(Object bean, Field field) {
        try {
            return getGetterMethod(bean.getClass(), field).invoke(bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldValue(Object bean, Field field, Object value) {
        try {
            getSetterMethod(bean.getClass(), field).invoke(bean, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
