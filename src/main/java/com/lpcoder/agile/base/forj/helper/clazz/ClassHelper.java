package com.lpcoder.agile.base.forj.helper.clazz;

import com.lpcoder.agile.base.forj.util.StringUtil;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

/**
 * @author: liurenpeng
 * @date: Created in 18-1-2
 */
public class ClassHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassHelper.class);

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) throws ClassNotFoundException {
        return Class.forName(className, isInitialized, getClassLoader());
    }

    /**
     * 得到方法参数名称数组(由于java没有提供获得参数名称的api,这里利用javassist来实现)
     */
    public static String[] getMethodParamNames(Class<?> clazz, Method method) throws NotFoundException {
        Class<?>[] paramTypes = method.getParameterTypes();
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(clazz));
        CtClass cc = pool.get(clazz.getName());
        String[] paramTypeNames = new String[method.getParameterTypes().length];
        for (int i = 0; i < paramTypes.length; i++) {
            paramTypeNames[i] = paramTypes[i].getName();
        }
        CtMethod cm = cc.getDeclaredMethod(method.getName(), pool.get(paramTypeNames));
        // 使用javaassist的反射方法获取方法的参数名
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                .getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            throw new RuntimeException("class:" + clazz.getName()
                    + ", have no LocalVariableTable, please use javac -g:{vars} to compile the source file");
        }
        int startIndex = getStartIndex(attr);
        String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(startIndex + i + pos);
        }
        return paramNames;
    }

    private static int getStartIndex(LocalVariableAttribute attr) {
        int startIndex = 0;
        for (int i = 0; i < attr.length(); i++) {
            if ("this".equals(attr.variableName(i))) {
                startIndex = i;
                break;
            }
        }
        return startIndex;
    }

    /**
     * 获取指定包下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (null != url) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        //class文件方式加载
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClassByClassFile(classSet, packagePath, packageName);
                    } else if ("jar".equals(protocol)) {
                        //jar包方式加载
                        addClassByJarFile(classSet, url);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    /**
     * class文件方式加载
     */
    private static void addClassByClassFile(Set<Class<?>> classSet,
                                            String packagePath,
                                            String packageName) throws ClassNotFoundException {
        //获取所有目录和以.class结尾的文件
        File[] files = new File(packagePath).listFiles((file) ->
                (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
        if (null == files) {
            return;
        }
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                //文件
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                //目录
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClassByClassFile(classSet, subPackagePath, subPackageName);
            }

        }
    }

    /**
     * jar包方式加载
     */
    private static void addClassByJarFile(Set<Class<?>> classSet, URL url)
            throws IOException, ClassNotFoundException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        if (null != jarURLConnection) {
            JarFile jarFile = jarURLConnection.getJarFile();
            if (null != jarFile) {
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry jarEntry = jarEntries.nextElement();
                    String jarEntryName = jarEntry.getName();
                    if (jarEntryName.endsWith(".class")) {
                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                        doAddClass(classSet, className);
                    }
                }
            }
        }
    }

    /**
     * 将Class对象添加到classSet中
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) throws ClassNotFoundException {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

    /**
     * 获取该类及其所有超类的所有域
     */
    public static Map<String, Field> getHierarchyFields(Class<?> cls) {
        return getHierarchyFields(cls, new HashMap<>());
    }

    private static Map<String, Field> getHierarchyFields(Class<?> cls, Map<String, Field> result) {
        String objectClassName = "java.lang.Object";
        if (objectClassName.equals(cls.getName())) {
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
