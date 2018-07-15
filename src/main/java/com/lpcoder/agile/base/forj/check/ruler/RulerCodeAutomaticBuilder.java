package com.lpcoder.agile.base.forj.check.ruler;

import com.lpcoder.agile.base.forj.check.CheckResultCodeEnum;
import com.lpcoder.agile.base.forj.util.CollectionUtil;
import com.lpcoder.agile.base.forj.util.StringUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: liurenpeng
 * @description: ruler类代码自动生成工具
 * @date: Created in 17-11-24
 */
public class RulerCodeAutomaticBuilder {

    public static void main(String[] args) {
        RulerCodeAutomaticBuilder builder = new RulerCodeAutomaticBuilder();
        if (CollectionUtil.isEmpty(builder.rulerClassNameSet)) {
            throw new IllegalArgumentException("rulerClassNameSet is empty");
        }

        printRulerClass(builder);
        //printFactoryClass(builder);
    }

    private static void printFactoryClass(RulerCodeAutomaticBuilder builder) {
        for (String factoryClassName : builder.factoryClassNameSet) {
            String basePackDirStr = builder.baseDir + File.separator + "summary";
            File basePackDir = new File(basePackDirStr);
            if (!basePackDir.exists()) {
                mkDir(basePackDir);
            }

            String factoryClassFileName = basePackDirStr + File.separator +
                    factoryClassName + ".java";
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileWriter(factoryClassFileName), true);
                builder.printFactoryClassFile(factoryClassName, writer);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != writer) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("----------RULER FACTORY ALL END");
    }

    private static void printRulerClass(RulerCodeAutomaticBuilder builder) {
        for (String rulerClassName : builder.rulerClassNameSet) {
            String basePackDirStr = builder.baseDir + File.separator + "detail" + File.separator +
                    builder.rulerClassNameToPackageNameMap.get(rulerClassName);
            File basePackDir = new File(basePackDirStr);
            if (!basePackDir.exists()) {
                mkDir(basePackDir);
            }
            String classFileName = basePackDirStr + File.separator + rulerClassName + ".java";
            System.out.println("-----classFileName:" + classFileName);
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileWriter(classFileName), true);
                builder.printRulerClassFile(rulerClassName, writer);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != writer) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("----------RULER CLASS ALL END.共生成" +
                builder.rulerClassNameSet.size() + "个类");
    }

    private static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }

    private String rulerClassName;
    private String factoryClassName;
    private PrintWriter writer;
    private final String baseDir = "/home/lrp/IdeaProjects/utils/base-utils" + File.separator + "temp";
    private final String blankLineStr = "";

    //--------------------------------------RULER 相关-------------------------------------

    private final String basePackageStr = "package com.lpcoder.agile.base.forj.check.ruler.";

    /**
     * ruler类名集合
     */
    private Set<String> rulerClassNameSet = new LinkedHashSet<>();
    /**
     * ruler类与其对应的错误CodeEnum的映射关系
     */
    private Map<String, CheckResultCodeEnum> rulerClassNameToCheckResultCodeEnumMap = new HashMap<>();
    /**
     * ruler类与其是否允许target为null的映射关系
     */
    private Map<String, Boolean> isRulerCanBeNullMap = new HashMap<>();
    /**
     * ruler类与其是否含有norm的映射关系(不含value为null,含有value为norm的类型),目前norm的数量最多为1
     */
    private Map<String, String> isRulerContainsNormMap = new HashMap<>();
    /**
     * ruler类与相应工具类名的映射,例如ArrLengthLtRuler->ArrayUtil
     */
    private Map<String, String> rulerClassNameToUtilClassNameMap = new HashMap<>();
    /**
     * ruler类与相应工具方法名的映射,例如LongGtRuler->isGt
     */
    private Map<String, String> rulerClassNameToUtilMethodNameMap = new HashMap<>();
    /**
     * ruler类与其所在包名的映射,例如LongGtRuler->number
     */
    private Map<String, String> rulerClassNameToPackageNameMap = new HashMap<>();
    /**
     * ruler类与其校验对象类型的映射,如ArrLengthLtRuler->Object[]
     */
    private Map<String, String> rulerClassNameToTargetClassNameMap = new HashMap<>();
    /**
     * ruler类与其校验对象权限定类名的映射,引包用,如DateAfterRuler->java.util.Date
     */
    private Map<String, String> rulerClassNameToTargetAbsolutelyClassNameMap = new HashMap<>();

    //--------------------------------------FACTORY 相关-------------------------------------

    /**
     * ruler类与其所在工厂类类名的映射,例如LongGtRuler->NumRuler
     */
    private Map<String, String> rulerClassNameToFactoryClassNameMap = new HashMap<>();
    /**
     * ruler类与其所在工厂类方法名的映射,例如LongGtRuler->gt
     */
    private Map<String, String> rulerClassNameToFactoryMethodNameMap = new HashMap<>();
    /**
     * factory类名集合
     */
    private Set<String> factoryClassNameSet = new HashSet<>();
    /**
     * factory与 需要引入的Ruler import字符串集合的映射 空行/r/n表示
     */
    private Map<String, Set<String>> factoryToImportRulerStrSetMap = new HashMap<>();
    /**
     * factory与 和Ruler相关的工厂方法的代码列表的映射 空行/r/n表示
     */
    private Map<String, List<String>> factoryToRulerMethodCodeListMap = new HashMap<>();


    private RulerCodeAutomaticBuilder() {
        // RULER
        buildRulerClassNameSet();
        buildIsRulerCanBeNullMap();
        buildIsRulerContainsNormMap();
        buildRulerClassNameToUtilClassNameMap();
        buildRulerClassNameToPackageNameMap();
        buildRulerClassNameToTargetClassNameMap();
        buildRulerClassNameToUtilMethodNameMap();
        buildRulerClassNameToTargetAbsolutelyClassNameMap();
        // FACTORY
        buildRulerClassNameToFactoryClassNameMap();
        buildRulerClassNameToFactoryMethodNameMap();
        buildFactoryToImportRulerStrSetMap();
        buildFactoryToRulerMethodCodeListMap();
    }

    //--------------------------------RULER BUILD METHOD--------------------------------

    private void buildRulerClassNameSet() {
        for (CheckResultCodeEnum checkResultCodeEnum : CheckResultCodeEnum.values()) {
            if ("SUCCESS".equals(checkResultCodeEnum.toString())) {
                continue;
            }
            String rulerClassName = convertCodeEnumToRulerClassName(checkResultCodeEnum.toString());
            rulerClassNameSet.add(rulerClassName);
            rulerClassNameToCheckResultCodeEnumMap.put(rulerClassName, checkResultCodeEnum);
        }
    }

    private void buildIsRulerCanBeNullMap() {
        for (String rulerClassName : rulerClassNameSet) {
            if (rulerClassName.contains("NotNull")) {
                isRulerCanBeNullMap.put(rulerClassName, false);
                continue;
            }
            isRulerCanBeNullMap.put(rulerClassName, true);
        }
    }

    private void buildIsRulerContainsNormMap() {
        for (String rulerClassName : rulerClassNameSet) {
            if (rulerClassName.contains("NotNull")) {
                continue;
            }
            if (rulerClassName.contains("Length") || rulerClassName.contains("Size")) {
                isRulerContainsNormMap.put(rulerClassName, "int");
                continue;
            }
            if (rulerClassName.contains("StrEq")) {
                isRulerContainsNormMap.put(rulerClassName, "String");
                continue;
            }
            if (rulerClassName.contains("Date")) {
                if (rulerClassName.contains("Str")) {
                    continue;
                }
                isRulerContainsNormMap.put(rulerClassName, "Date");
                continue;
            }
            if (rulerClassName.contains("Byte")) {
                isRulerContainsNormMap.put(rulerClassName, "Byte");
                continue;
            }
            if (rulerClassName.contains("Short")) {
                isRulerContainsNormMap.put(rulerClassName, "Short");
                continue;
            }
            if (rulerClassName.contains("Int")) {
                isRulerContainsNormMap.put(rulerClassName, "Integer");
                continue;
            }
            if (rulerClassName.contains("Long")) {
                isRulerContainsNormMap.put(rulerClassName, "Long");
                continue;
            }
            if (rulerClassName.contains("Float")) {
                isRulerContainsNormMap.put(rulerClassName, "Float");
                continue;
            }
            if (rulerClassName.contains("Double")) {
                isRulerContainsNormMap.put(rulerClassName, "Double");
                continue;
            }
            isRulerContainsNormMap.put(rulerClassName, null);
        }
    }

    private void buildRulerClassNameToUtilClassNameMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String utilClassName;
            if (rulerClassName.startsWith("Obj")) {
                utilClassName = "ObjectUtil";
            } else if (rulerClassName.startsWith("Str")) {
                utilClassName = "StringUtil";
            } else if (rulerClassName.startsWith("Date")) {
                utilClassName = "DateUtil";
            } else if (rulerClassName.startsWith("Arr")) {
                utilClassName = "ArrayUtil";
            } else if (rulerClassName.startsWith("Coll")) {
                utilClassName = "CollectionUtil";
            } else if (rulerClassName.startsWith("Map")) {
                utilClassName = "MapUtil";
            } else {
                utilClassName = "NumberUtil";
            }
            rulerClassNameToUtilClassNameMap.put(rulerClassName, utilClassName);
        }
    }

    private void buildRulerClassNameToPackageNameMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String utilClassName;
            if (rulerClassName.startsWith("Obj")) {
                utilClassName = "object";
            } else if (rulerClassName.startsWith("Str")) {
                utilClassName = "string";
            } else if (rulerClassName.startsWith("Date")) {
                utilClassName = "date";
            } else if (rulerClassName.startsWith("Arr")) {
                utilClassName = "array";
            } else if (rulerClassName.startsWith("Coll")) {
                utilClassName = "collection";
            } else if (rulerClassName.startsWith("Map")) {
                utilClassName = "map";
            } else {
                utilClassName = "number";
            }
            rulerClassNameToPackageNameMap.put(rulerClassName, utilClassName);
        }
    }

    private void buildRulerClassNameToTargetClassNameMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String utilClassName;
            if (rulerClassName.startsWith("Obj")) {
                utilClassName = "Object";
            } else if (rulerClassName.startsWith("Str")) {
                utilClassName = "String";
            } else if (rulerClassName.startsWith("Date")) {
                utilClassName = "Date";
            } else if (rulerClassName.startsWith("Arr")) {
                utilClassName = "Object[]";
            } else if (rulerClassName.startsWith("Coll")) {
                utilClassName = "Collection";
            } else if (rulerClassName.startsWith("Map")) {
                utilClassName = "Map";
            } else if (rulerClassName.startsWith("Byte")) {
                utilClassName = "Byte";
            } else if (rulerClassName.startsWith("Short")) {
                utilClassName = "Short";
            } else if (rulerClassName.startsWith("Int")) {
                utilClassName = "Integer";
            } else if (rulerClassName.startsWith("Long")) {
                utilClassName = "Long";
            } else if (rulerClassName.startsWith("Float")) {
                utilClassName = "Float";
            } else if (rulerClassName.startsWith("Double")) {
                utilClassName = "Double";
            } else {
                throw new IllegalArgumentException("no target class name matching");
            }
            rulerClassNameToTargetClassNameMap.put(rulerClassName, utilClassName);
        }
    }

    private void buildRulerClassNameToUtilMethodNameMap() {
        for (CheckResultCodeEnum checkResultCodeEnum : CheckResultCodeEnum.values()) {
            if ("SUCCESS".equals(checkResultCodeEnum.toString())) {
                continue;
            }
            String rulerClassName = convertCodeEnumToRulerClassName(checkResultCodeEnum.toString());
            String utilMethodName = convertCodeEnumToUtilMethodName(checkResultCodeEnum.toString());
            rulerClassNameToUtilMethodNameMap.put(rulerClassName, utilMethodName);
        }
    }

    private void buildRulerClassNameToTargetAbsolutelyClassNameMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String utilClassName;
            if (rulerClassName.startsWith("Obj")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Str")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Date")) {
                utilClassName = "java.util.Date";
            } else if (rulerClassName.startsWith("Arr")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Coll")) {
                utilClassName = "java.util.Collection";
            } else if (rulerClassName.startsWith("Map")) {
                utilClassName = "java.util.Map";
            } else if (rulerClassName.startsWith("Byte")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Short")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Int")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Long")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Float")) {
                utilClassName = "";
            } else if (rulerClassName.startsWith("Double")) {
                utilClassName = "";
            } else {
                throw new IllegalArgumentException("no target class name matching");
            }
            rulerClassNameToTargetAbsolutelyClassNameMap.put(rulerClassName, utilClassName);
        }
    }

    //--------------------------------FACTORY BUILD METHOD--------------------------------

    private void buildRulerClassNameToFactoryClassNameMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String factoryClassName;
            if (rulerClassName.startsWith("Obj")) {
                factoryClassName = "ObjRuler";
            } else if (rulerClassName.startsWith("Str")) {
                factoryClassName = "StrRuler";
            } else if (rulerClassName.startsWith("Date")) {
                factoryClassName = "DateRuler";
            } else if (rulerClassName.startsWith("Arr")) {
                factoryClassName = "ArrRuler";
            } else if (rulerClassName.startsWith("Coll")) {
                factoryClassName = "CollRuler";
            } else if (rulerClassName.startsWith("Map")) {
                factoryClassName = "MapRuler";
            } else if (rulerClassName.startsWith("Byte")) {
                factoryClassName = "ByteRuler";
            } else if (rulerClassName.startsWith("Short")) {
                factoryClassName = "ShortRuler";
            } else if (rulerClassName.startsWith("Int")) {
                factoryClassName = "IntRuler";
            } else if (rulerClassName.startsWith("Long")) {
                factoryClassName = "LongRuler";
            } else if (rulerClassName.startsWith("Float")) {
                factoryClassName = "FloatRuler";
            } else if (rulerClassName.startsWith("Double")) {
                factoryClassName = "DoubleRuler";
            } else {
                throw new IllegalArgumentException("no factoryClassName match");
            }
            rulerClassNameToFactoryClassNameMap.put(rulerClassName, factoryClassName);
            factoryClassNameSet.add(factoryClassName);
        }
    }

    private void buildRulerClassNameToFactoryMethodNameMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String factoryMethodName = convertCodeEnumToFactionMethodName(
                    rulerClassNameToCheckResultCodeEnumMap.get(rulerClassName).name());
            rulerClassNameToFactoryMethodNameMap.put(rulerClassName, factoryMethodName);
        }
    }

    private void buildFactoryToImportRulerStrSetMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String factoryClassName = rulerClassNameToFactoryClassNameMap.get(rulerClassName);

            if (!factoryToImportRulerStrSetMap.containsKey(factoryClassName)) {
                factoryToImportRulerStrSetMap.put(factoryClassName, new HashSet<>());
            }
            Set<String> importStrSet = factoryToImportRulerStrSetMap.get(factoryClassName);

            if (rulerClassNameToTargetAbsolutelyClassNameMap.get(rulerClassName).length() > 0) {
                importStrSet.add("import " +
                        rulerClassNameToTargetAbsolutelyClassNameMap.get(rulerClassName) + ";");
                importStrSet.add(blankLineStr);
            }

            importStrSet.add("import com.lpcoder.agile.base.forj.check.ruler.detail." +
                    rulerClassNameToPackageNameMap.get(rulerClassName) + "." +
                    rulerClassName + ";");
        }
    }

    private void buildFactoryToRulerMethodCodeListMap() {
        for (String rulerClassName : rulerClassNameSet) {
            String normClass = isRulerContainsNormMap.get(rulerClassName);
            boolean containsNorm = StringUtil.isNotEmpty(normClass);

            String factoryClassName = rulerClassNameToFactoryClassNameMap.get(rulerClassName);

            if (!factoryToRulerMethodCodeListMap.containsKey(factoryClassName)) {
                factoryToRulerMethodCodeListMap.put(factoryClassName, new LinkedList<>());
            }
            List<String> rulerMethodCodeList = factoryToRulerMethodCodeListMap.get(factoryClassName);

            // 采用默认code和desc
            rulerMethodCodeList.add("    public static Ruler<" +
                    rulerClassNameToTargetClassNameMap.get(rulerClassName) + "> " +
                    rulerClassNameToFactoryMethodNameMap.get(rulerClassName) + "(" +
                    (containsNorm ? normClass + " norm" : "") +
                    ") {");
            rulerMethodCodeList.add("        return new " + rulerClassName + "(" +
                    (containsNorm ? "norm" : "") +
                    ");");
            rulerMethodCodeList.add("    }");
            rulerMethodCodeList.add(blankLineStr);

            // 用户自定义code和desc
            rulerMethodCodeList.add("    public static Ruler<" +
                    rulerClassNameToTargetClassNameMap.get(rulerClassName) + "> " +
                    rulerClassNameToFactoryMethodNameMap.get(rulerClassName) + "(" +
                    (containsNorm ? normClass + " norm, " : "") +
                    "long failCode, String failDesc" +
                    ") {");
            rulerMethodCodeList.add("        return new " + rulerClassName + "(" +
                    (containsNorm ? "norm, " : "") + "failCode, failDesc);");
            rulerMethodCodeList.add("    }");
            rulerMethodCodeList.add(blankLineStr);
        }
    }

    //--------------------------------CONVERT METHOD--------------------------------

    /**
     * 将CodeEnum 转换成 FactionMethodName
     */
    private String convertCodeEnumToFactionMethodName(String codeEnumStyleStr) {
        if (!codeEnumStyleStr.contains("FAIL")) {
            throw new IllegalArgumentException("codeEnumStyleStr must contains FAIL");
        }
        codeEnumStyleStr = codeEnumStyleStr.replace("_FAIL", "");
        String[] elements = codeEnumStyleStr.split("_");
        StringBuilder sb = new StringBuilder();
        if (elements.length < 2) {
            throw new IllegalArgumentException("elements.length < 2");
        }
        for (int i = 1; i < elements.length; i++) {
            if (1 == i) {
                sb.append(elements[i].toLowerCase());
            } else {
                sb.append(convertToFirstCharUpper(elements[i]));
            }
        }
        return sb.toString();
    }

    /**
     * 将CodeEnum 转换成 UtilMethodName
     */
    private String convertCodeEnumToUtilMethodName(String codeEnumStyleStr) {
        if (!codeEnumStyleStr.contains("FAIL")) {
            throw new IllegalArgumentException("codeEnumStyleStr must contains FAIL");
        }
        String[] elements = codeEnumStyleStr.split("_");
        StringBuilder sb = new StringBuilder("is");
        for (int i = 1; i < elements.length - 1; i++) {
            sb.append(convertToFirstCharUpper(elements[i]));
        }
        return sb.toString();
    }

    /**
     * 将CodeEnum 转换成 RulerClassName
     */
    private String convertCodeEnumToRulerClassName(String codeEnumStyleStr) {
        if (!codeEnumStyleStr.contains("FAIL")) {
            throw new IllegalArgumentException("codeEnumStyleStr must contains FAIL");
        }
        codeEnumStyleStr = codeEnumStyleStr.replace("FAIL", "RULER");
        String[] elements = codeEnumStyleStr.split("_");
        StringBuilder sb = new StringBuilder();
        for (String element : elements) {
            sb.append(convertToFirstCharUpper(element));
        }
        return sb.toString();
    }

    /**
     * 将字符串转换成首字母大写的格式
     */
    private String convertToFirstCharUpper(String element) {
        return element.substring(0, 1).toUpperCase() + element.substring(1).toLowerCase();
    }

    //--------------------------------RULER PRINT METHOD--------------------------------

    private void printRulerClassFile(String rulerClassName, PrintWriter writer) {
        this.rulerClassName = rulerClassName;
        this.writer = writer;
        printPackage();
        printImport();
        printClassAnnotation();
        printClassStartLine();
        printField();
        printConstructor();
        printUserDefinedFailCodeAndDescConstructor();
        printInit();
        printCheck();
        printClassEndLine();
    }

    private void printPackage() {
        writer.println(basePackageStr + "detail." +
                rulerClassNameToPackageNameMap.get(rulerClassName) + ";");
        writer.println();
    }

    private void printImport() {
        writer.println("import com.lpcoder.agile.base.forj.check.CheckException;");
        writer.println("import com.lpcoder.agile.base.forj.check.ruler.BaseRuler;");
        writer.println("import com.lpcoder.agile.base.forj.util." +
                rulerClassNameToUtilClassNameMap.get(rulerClassName) + ";");

        writer.println();
        if (rulerClassNameToTargetAbsolutelyClassNameMap.get(rulerClassName).length() > 0) {
            writer.println("import " +
                    rulerClassNameToTargetAbsolutelyClassNameMap.get(rulerClassName) + ";");
            writer.println();
        }
        writer.println("import static com.lpcoder.agile.base.forj.check.CheckResultCodeEnum.*;");
        writer.println();
    }

    private void printClassAnnotation() {
        writer.println("/**");
        writer.println(" * @author: liurenpeng");
        writer.println(" * @date: Created in 2017-11-12");
        writer.println(" */");
    }

    private void printClassStartLine() {
        writer.println("public class " + rulerClassName + " extends BaseRuler<" +
                rulerClassNameToTargetClassNameMap.get(rulerClassName) +
                "> {");
        writer.println();
    }

    private void printField() {
        String normClass = isRulerContainsNormMap.get(rulerClassName);
        boolean containsNorm = StringUtil.isNotEmpty(normClass);
        if (containsNorm) {
            writer.println("    private " + normClass + " norm;");
        }
        writer.println();
    }

    private void printConstructor() {
        String normClass = isRulerContainsNormMap.get(rulerClassName);
        boolean containsNorm = StringUtil.isNotEmpty(normClass);
        writer.println("    public " + rulerClassName + "(" +
                (containsNorm ? normClass + " norm" : "") +
                ") {");
        writer.println("        init(" +
                (containsNorm ? "norm, " : "") +
                rulerClassNameToCheckResultCodeEnumMap.get(rulerClassName).toString() + ".getCode(), " +
                rulerClassNameToCheckResultCodeEnumMap.get(rulerClassName).toString() + ".getDesc());");
        writer.println("    }");
        writer.println();
    }

    private void printUserDefinedFailCodeAndDescConstructor() {
        String normClass = isRulerContainsNormMap.get(rulerClassName);
        boolean containsNorm = StringUtil.isNotEmpty(normClass);
        writer.println("    public " + rulerClassName + "(" +
                (containsNorm ? normClass + " norm, " : "") +
                "long failCode, String failDesc" +
                ") {");

        writer.println("        init(" +
                (containsNorm ? "norm, " : "") + "failCode, failDesc);");
        writer.println("    }");
        writer.println();
    }

    private void printInit() {
        String normClass = isRulerContainsNormMap.get(rulerClassName);
        boolean containsNorm = StringUtil.isNotEmpty(normClass);
        if (containsNorm) {
            writer.println("    private void init(" +
                    normClass + " norm, " +
                    "long failCode, String failDesc) {");
            writer.println("        this.norm = norm;");
            writer.println("        init(failCode, failDesc);");
            writer.println("    }");
            writer.println();
        }


    }

    private void printCheck() {
        String normClass = isRulerContainsNormMap.get(rulerClassName);
        boolean containsNorm = StringUtil.isNotEmpty(normClass);
        writer.println("    @Override");
        writer.println("    public void check(" +
                rulerClassNameToTargetClassNameMap.get(rulerClassName) + " checkTarget) {");
        if (isRulerCanBeNullMap.get(rulerClassName)) {
            writer.println("        if (null == checkTarget) {");
            writer.println("            return;");
            writer.println("        }");
        }
        writer.println("        if (" +
                rulerClassNameToUtilClassNameMap.get(rulerClassName) + "." +
                rulerClassNameToUtilMethodNameMap.get(rulerClassName) +
                "(checkTarget" +
                (containsNorm ? ", norm" : "") + ")) {");
        writer.println("            return;");
        writer.println("        }");

        int numOfPlaceHolder = StringUtil.getNumOfSubStr(
                rulerClassNameToCheckResultCodeEnumMap.get(rulerClassName).getDesc(),
                "%", true
        );
        if (numOfPlaceHolder > 0) {
            StringBuilder sb = new StringBuilder("        throw new CheckException(" +
                    "failCode, String.format(");
            sb.append("failDesc");
            for (int i = 0; i < numOfPlaceHolder; i++) {
                sb.append(", norm");
            }
            sb.append("));");
            writer.println(sb.toString());
        } else {
            writer.println("        throw new CheckException(failCode, failDesc);");

        }
        writer.println("    }");
        writer.println();
    }

    private void printClassEndLine() {
        writer.println("}");
    }

    //--------------------------------FACTORY PRINT METHOD--------------------------------

    private void printFactoryClassFile(String factoryClassName, PrintWriter writer) {
        this.factoryClassName = factoryClassName;
        this.writer = writer;
        printFactoryPackage();
        printFactoryImport();
        printFactoryAnnotation();
        printFactoryStartLine();
        printFactoryMethod();
        printFactoryEndLine();
    }

    private void printFactoryPackage() {
        writer.println("package com.lpcoder.agile.base.forj.check.ruler.summary;");
        writer.println();
    }

    private void printFactoryImport() {
        writer.println("import com.lpcoder.agile.base.forj.check.ruler.Ruler;");
        for (String importStr : factoryToImportRulerStrSetMap.get(factoryClassName)) {
            writer.println(importStr);
        }
        writer.println();
    }

    private void printFactoryAnnotation() {
        writer.println("/**");
        writer.println(" * @author: liurenpeng");
        writer.println(" * @date: Created in 2017-11-12");
        writer.println(" */");
    }

    private void printFactoryStartLine() {
        writer.println("public class " + factoryClassName + " {");
        writer.println();
    }

    private void printFactoryConstructor() {
        writer.println("    private " + factoryClassName + "() {");
        writer.println("    }");
        writer.println();
    }

    private void printFactoryMethod() {
        for (String methodCodeStr : factoryToRulerMethodCodeListMap.get(factoryClassName)) {
            writer.println(methodCodeStr);
        }
    }

    private void printFactoryEndLine() {
        writer.println("}");
    }

}
