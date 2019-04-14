package com.lpcoder.agile.base.forj.util;

import com.lpcoder.agile.base.forj.helper.clazz.ClassHelper;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: liurenpeng
 * @date: Created in 17-11-21
 */
public class BeanUtil {

    private static final Byte BYTE_DEFAULT_VALUE = 0;
    private static final Short SHORT_DEFAULT_VALUE = 0;
    private static final Integer INT_DEFAULT_VALUE = 0;
    private static final Long LONG_DEFAULT_VALUE = 0L;
    private static final Float FLOAT_DEFAULT_VALUE = 0.0F;
    private static final Double DOUBLE_DEFAULT_VALUE = 0.0D;
    private static final Boolean BOOLEAN_DEFAULT_VALUE = false;
    private static final Character CHAR_DEFAULT_VALUE = ' ';
    private static final String STRING_DEFAULT_VALUE = "";
    private static final Date DATE_DEFAULT_VALUE = new Date(0);

    private static final Set<Class> DEFAULTABLE_CLASS_SET = new HashSet<Class>() {{
        add(Byte.class);
        add(Short.class);
        add(Integer.class);
        add(Long.class);
        add(Float.class);
        add(Double.class);
        add(Boolean.class);
        add(Character.class);
        add(String.class);
        add(Date.class);
    }};

    private BeanUtil() {
    }

    /**
     * 为该实体及其从所有父类中继承而来的指定类型({@link #DEFAULTABLE_CLASS_SET})的具有get&set方法且值为null的字段置默认值
     * @param bean
     * @throws Exception
     */
    public static void setDefaultValue(Object bean) throws Exception {
        ClassHelper.getHierarchyFields(bean.getClass()).values().stream()
                .filter(field -> DEFAULTABLE_CLASS_SET.contains(field.getType()))
                .filter(field -> null != ClassHelper.getGetterMethod(bean.getClass(), field)
                        && null == ClassHelper.getFieldValue(bean, field))
                .filter(field -> null != ClassHelper.getSetterMethod(bean.getClass(), field))
                .forEach(field -> ClassHelper.setFieldValue(bean, field,
                        getDefaultValueByClass(field.getType())));
    }

    private static Object getDefaultValueByClass(Class clz) {
        if (clz.equals(Byte.class)) {
            return BYTE_DEFAULT_VALUE;
        } else if (clz.equals(Short.class)) {
            return SHORT_DEFAULT_VALUE;
        } else if (clz.equals(Integer.class)) {
            return INT_DEFAULT_VALUE;
        } else if (clz.equals(Long.class)) {
            return LONG_DEFAULT_VALUE;
        } else if (clz.equals(Float.class)) {
            return FLOAT_DEFAULT_VALUE;
        } else if (clz.equals(Double.class)) {
            return DOUBLE_DEFAULT_VALUE;
        } else if (clz.equals(Boolean.class)) {
            return BOOLEAN_DEFAULT_VALUE;
        } else if (clz.equals(Character.class)) {
            return CHAR_DEFAULT_VALUE;
        } else if (clz.equals(String.class)) {
            return STRING_DEFAULT_VALUE;
        } else if (clz.equals(Date.class)) {
            return DATE_DEFAULT_VALUE;
        } else {
            return null;
        }
    }

}
