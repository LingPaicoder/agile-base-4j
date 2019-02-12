package com.lpcoder.agile.base.forj.util;

import com.lpcoder.agile.base.forj.check.CheckUtil;
import com.lpcoder.agile.base.forj.check.ruler.summary.StrRuler;
import com.lpcoder.agile.base.forj.enumeration.GenderEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: liurenpeng
 * @date: Created in 2017-10-13
 */
public class IdCardUtil {

    /**
     * 中国公民身份证号码最小长度。
     */
    private static final int CHINA_ID_MIN_LENGTH = 15;

    /**
     * 中国公民身份证号码最大长度。
     */
    private static final int CHINA_ID_MAX_LENGTH = 18;

    /**
     * 每位加权因子
     */
    private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 最低年限
     */
    private static final int MIN_YEAR = 1930;

    /**
     * 台湾身份证号正则
     */
    private static final String TW_ID_CARD_REGEX = "^[a-zA-Z][0-9]{9}";

    /**
     * 澳门身份证号正则
     */
    private static final String MC_ID_CARD_REGEX = "^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?";

    /**
     * 香港身份证号正则
     */
    private static final String HKIdCardRegex = "^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?";

    /**
     * 验证身份证是否合法
     */
    public static boolean validateIdCard(String idCard) {
        if (null == idCard) {
            return false;
        }
        try {
            String card = idCard.trim();
            return (validateIdCard18(card) || validateIdCard15(card));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将15位身份证号码转换为18位 (调用前建议先通过validateCard校验合法性) //TODO 优化代码
     */
    public static String convert15CardTo18(String idCard) {
        String idCard18;
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            throw new IllegalArgumentException("idCard length is not 15");
        }
        if (StringUtil.isNotNum(idCard)) {
            throw new IllegalArgumentException("idCard is not num");
        }
        // 获取出生年月日
        String birthday = idCard.substring(6, 12);
        Date birthDate;
        try {
            birthDate = DateUtil.parseDate(birthday, "yyMMdd");
        } catch (ParseException e) {
            throw new IllegalArgumentException("parse birthday error", e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthDate);
        // 获取出生年(完全表现形式,如：2010)
        String sYear = String.valueOf(cal.get(Calendar.YEAR));
        idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
        // 转换字符数组
        char[] cArr = idCard18.toCharArray();
        if (cArr != null) {
            int[] iCard = convertCharToInt(cArr);
            int iSum17 = getPowerSum(iCard);
            // 获取校验位
            String sVal = getCheckCode18(iSum17);
            if (StringUtil.isNotEmpty(sVal)) {
                idCard18 += sVal;
            } else {
                throw new IllegalArgumentException("get check code error");
            }
        }
        return idCard18;
    }


    /**
     * 验证18位身份编码是否合法
     */
    private static boolean validateIdCard18(String idCard) {
        boolean result = false;
        if (null == idCard) {
            return result;
        }
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            // 前17位
            String code17 = idCard.substring(0, 17);
            // 第18位
            String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
            if (StringUtil.isNum(code17)) {
                char[] cArr = code17.toCharArray();
                if (cArr != null) {
                    int[] iCard = convertCharToInt(cArr);
                    int iSum17 = getPowerSum(iCard);
                    // 获取校验位
                    String val = getCheckCode18(iSum17);
                    if (val.length() > 0) {
                        if (val.equalsIgnoreCase(code18)) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 验证15位身份编码是否合法
     */
    private static boolean validateIdCard15(String idCard) {
        boolean result = false;
        if (null == idCard) {
            return result;
        }
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return result;
        }
        if (StringUtil.isNum(idCard)) {
            String proCode = idCard.substring(0, 2);
            if (!IdCardCityCodeEnum.valid(Integer.valueOf(proCode))) {
                return result;
            }
            String birthCode = idCard.substring(6, 12);
            Date birthDate;
            try {
                birthDate = new SimpleDateFormat("yy").parse(birthCode
                        .substring(0, 2));
            } catch (ParseException e) {
                return result;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthDate);
            if (!validDate(cal.get(Calendar.YEAR),
                    Integer.valueOf(birthCode.substring(2, 4)),
                    Integer.valueOf(birthCode.substring(4, 6)))) {
                return result;
            }
        } else {
            return result;
        }
        result = true;
        return result;
    }

    /**
     * 验证10位身份编码是否合法
     */
    private static boolean validateIdCard10(String idCard) {
        if (null == idCard) {
            return false;
        }
        // 台湾
        if (idCard.matches(TW_ID_CARD_REGEX)) {
            return validateTWCard(idCard);
        }
        // 澳门
        if (idCard.matches(MC_ID_CARD_REGEX)) {
            return true;
            // TODO 补充澳门身份证号校验方法
        }
        // 香港
        if (idCard.matches(HKIdCardRegex)) {
            return validateHKCard(idCard);
        }
        return false;
    }

    /**
     * 验证台湾身份证号码
     */
    private static boolean validateTWCard(String idCard) {
        if (null == idCard) {
            return false;
        }
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = IdCardTWFirstCodeEnum.valueOf(start).id;
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iFlag = 8;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iFlag;
            iFlag--;
        }
        int checkCode = sum % 10 == 0 ? 0 : (10 - sum % 10);
        return checkCode == Integer.valueOf(end);
    }

    // * 验证香港身份证号码 说明：
    // * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，
    // * 对应数字58 前2位英文字符A-Z分别对应数字10-35 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
    // * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效

    /**
     * 验证香港身份证号码 //TODO 修复部分特殊身份证无法检查的bug
     */
    private static boolean validateHKCard(String idCard) {
        if (null == idCard) {
            return false;
        }
        String card = idCard.replaceAll("\\(|\\)", "");
        Integer sum;
        final int cardMaxLength = 9;
        if (card.length() == cardMaxLength) {
            sum = (card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 9
                    + (card.substring(1, 2).toUpperCase().toCharArray()[0] - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 8;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        Integer iFlag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iFlag;
            iFlag--;
        }
        final String charAStr = "A";
        if (charAStr.equals(end.toUpperCase())) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.valueOf(end);
        }
        return (sum % 11 == 0);
    }

    /**
     * 将字符数组转换成数字数组
     */
    private static int[] convertCharToInt(char[] ca) {
        int len = ca.length;
        int[] iArr = new int[len];
        try {
            for (int i = 0; i < len; i++) {
                iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("convertCharToInt error", e);
        }
        return iArr;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     */
    private static int getPowerSum(int[] iArr) {
        int iSum = 0;
        if (POWER.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                for (int j = 0; j < POWER.length; j++) {
                    if (i == j) {
                        iSum = iSum + iArr[i] * POWER[j];
                    }
                }
            }
        }
        return iSum;
    }

    /**
     * 将power和值与11取模获得余数进行校验码判断
     */
    private static String getCheckCode18(int iSum) {
        String sCode;
        switch (iSum % 11) {
            case 10:
                sCode = "2";
                break;
            case 9:
                sCode = "3";
                break;
            case 8:
                sCode = "4";
                break;
            case 7:
                sCode = "5";
                break;
            case 6:
                sCode = "6";
                break;
            case 5:
                sCode = "7";
                break;
            case 4:
                sCode = "8";
                break;
            case 3:
                sCode = "9";
                break;
            case 2:
                sCode = "x";
                break;
            case 1:
                sCode = "0";
                break;
            case 0:
                sCode = "1";
                break;
            default:
                sCode = "";
                break;
        }
        return sCode;
    }

    /**
     * 根据身份编号获取年龄
     */
    public static int getAgeByIdCard(String idCard) {
        CheckUtil.check(idCard, StrRuler.notNull(), StrRuler.idCard());
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        int birthYear = getYearByIdCard(idCard);
        int birthMonth = getMonthByIdCard(idCard);
        int birthDay = getDateByIdCard(idCard);

        Calendar cal = Calendar.getInstance();
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH) + 1;
        int currDay = cal.get(Calendar.DATE);

        if (birthYear >= currYear) {
            return 0;
        }
        if (birthMonth > currMonth) {
            return currYear - birthYear - 1;
        }
        if (birthMonth < currMonth) {
            return currYear - birthYear;
        }
        if (birthDay >= currDay) {
            return currYear - birthYear - 1;
        }
        return currYear - birthYear;
    }

    /**
     * 根据身份编号获取生日 yyyy-MM-dd
     */
    public static String getStandardBirthByIdCard(String idCard) {
        String birth = getNoHyphenBirthByIdCard(idCard);
        if (StringUtil.isNotEmpty(birth)) {
            birth = birth.substring(0, 4) + "-" + birth.substring(4, 6) +
                    "-" + birth.substring(6, 8);
        }
        return birth;
    }

    /**
     * 根据身份编号获取生日 yyyyMMdd
     */
    public static String getNoHyphenBirthByIdCard(String idCard) {
        String birthday = "";
        if (StringUtil.isEmpty(idCard)) {
            return birthday;
        }
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            birthday = idCard.substring(6, 14);
        }
        return birthday;
    }

    /**
     * 根据身份编号获取生日年 yyyy
     */
    public static short getYearByIdCard(String idCard) {
        short birthdayYear = 0;
        if (StringUtil.isEmpty(idCard)) {
            return birthdayYear;
        }
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            birthdayYear = Short.valueOf(idCard.substring(6, 10));
        }
        return birthdayYear;
    }

    /**
     * 根据身份编号获取生日月 MM
     */
    public static short getMonthByIdCard(String idCard) {
        short birthdayMonth = 0;
        if (StringUtil.isEmpty(idCard)) {
            return birthdayMonth;
        }
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            birthdayMonth = Short.valueOf(idCard.substring(10, 12));
        }
        return birthdayMonth;
    }

    /**
     * 根据身份编号获取生日天 dd
     */
    public static short getDateByIdCard(String idCard) {
        short birthdayDay = 0;
        if (StringUtil.isEmpty(idCard)) {
            return birthdayDay;
        }
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            birthdayDay = Short.valueOf(idCard.substring(12, 14));
        }
        return birthdayDay;
    }

    /**
     * 根据身份编号获取性别
     */
    public static GenderEnum getGenderByIdCard(String idCard) {
        if (StringUtil.isEmpty(idCard)) {
            return GenderEnum.UNKNOWN;
        }
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            String sCardNum = idCard.substring(16, 17);
            if (Integer.parseInt(sCardNum) % 2 != 0) {
                return GenderEnum.MAN;
            } else {
                return GenderEnum.WOMAN;
            }
        }
        return GenderEnum.UNKNOWN;
    }

    /**
     * 根据身份编号获取户籍省份
     */
    public static String getProvinceByIdCard(String idCard) {
        String sProvince = "";
        if (StringUtil.isEmpty(idCard)) {
            return sProvince;
        }
        int len = idCard.length();
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            String sProvinceNum = idCard.substring(0, 2);
            sProvince = IdCardCityCodeEnum.valOf(Integer.valueOf(sProvinceNum)).getDesc();
        }
        return sProvince;
    }

    /**
     * 验证小于当前日期
     */
    private static boolean validDate(int iYear, int iMonth, int iDate) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if (iYear < MIN_YEAR || iYear >= year) {
            return false;
        }
        int datePerMonth = DateUtil.getDayNumByMonth(iYear, iMonth);
        return (iDate >= 1) && (iDate <= datePerMonth);
    }


    /**
     * 根据身份证号，自动获取对应的星座
     */
    public static String getConstellationById(String idCard) {
        if (!validateIdCard(idCard)) {
            return "";
        }
        int month = IdCardUtil.getMonthByIdCard(idCard);
        int day = IdCardUtil.getDateByIdCard(idCard);
        if (0 == month || 0 == day) {
            return "";
        }

        boolean isAquarius = (month == 1 && day >= 20) || (month == 2 && day <= 18);
        if (isAquarius) {
            return "水瓶座";
        }
        boolean isPisces = (month == 2 && day >= 19) || (month == 3 && day <= 20);
        if (isPisces) {
            return "双鱼座";
        }
        boolean isAries = (month == 3 && day > 20) || (month == 4 && day <= 19);
        if (isAries) {
            return "白羊座";
        }
        boolean isTaurus = (month == 4 && day >= 20) || (month == 5 && day <= 20);
        if (isTaurus) {
            return "金牛座";
        }
        boolean isGemini = (month == 5 && day >= 21) || (month == 6 && day <= 21);
        if (isGemini) {
            return "双子座";
        }
        boolean isCancer = (month == 6 && day > 21) || (month == 7 && day <= 22);
        if (isCancer) {
            return "巨蟹座";
        }
        boolean isLeo = (month == 7 && day > 22) || (month == 8 && day <= 22);
        if (isLeo) {
            return "狮子座";
        }
        boolean isVirgo = (month == 8 && day >= 23) || (month == 9 && day <= 22);
        if (isVirgo) {
            return "处女座";
        }
        boolean isLibra = (month == 9 && day >= 23) || (month == 10 && day <= 23);
        if (isLibra) {
            return "天秤座";
        }
        boolean isScorpio = (month == 10 && day > 23) || (month == 11 && day <= 22);
        if (isScorpio) {
            return "天蝎座";
        }
        boolean isSagittarius = (month == 11 && day > 22) || (month == 12 && day <= 21);
        if (isSagittarius) {
            return "射手座";
        }
        boolean isCapricorn = (month == 12 && day > 21) || (month == 1 && day <= 19);
        if (isCapricorn) {
            return "魔羯座";
        }
        return "";
    }

    /**
     * 根据身份证号，自动获取对应的生肖
     */
    public static String getZodiacById(String idCard) {
        if (!validateIdCard(idCard)) {
            return "";
        }
        String[] sSX = {"猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗"};
        int year = IdCardUtil.getYearByIdCard(idCard);
        if (0 == year) {
            return "";
        }
        int end = 3;
        int x = (year - end) % 12;

        String retValue = sSX[x];
        return retValue;
    }


    /**
     * 根据身份证号，自动获取对应的天干地支
     */
    public static String getChineseEraById(String idCard) {
        if (!validateIdCard(idCard)) {
            return "";
        }
        String[] sTG = {"癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "任"};
        String[] sDZ = {"亥", "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌"};

        int year = IdCardUtil.getYearByIdCard(idCard);
        if (0 == year) {
            return "";
        }
        int i = (year - 3) % 10;
        int j = (year - 3) % 12;

        String retValue = sTG[i] + sDZ[j];
        return retValue;
    }

    /**
     * 枚举
     */

    enum IdCardCityCodeEnum {
        BEIJING(11, "北京"),
        TIANJIN(12, "天津"),
        HEBEI(13, "河北"),
        SHANXI_ONETONE(14, "山西"),
        NEIMENGGU(15, "内蒙古"),
        LIAONING(21, "辽宁"),
        JILIN(22, "吉林"),
        HEILONGJIANG(23, "黑龙江"),
        SHANGHAI(31, "上海"),
        JIANGSU(32, "江苏"),
        ZHEJIANG(33, "浙江"),
        ANHUI(34, "安徽"),
        FUJIAN(35, "福建"),
        JIANGXI(36, "江西"),
        SHANDONG(37, "山东"),
        HENAN(41, "河南"),
        KUBEI(42, "湖北"),
        HUNAN(43, "湖南"),
        GUANGDONG(44, "广东"),
        GUANGXI(45, "广西"),
        HAINAN(46, "海南"),
        CHONGQING(50, "重庆"),
        SICHUAN(51, "四川"),
        GUIZHOU(52, "贵州"),
        YUNNAN(53, "云南"),
        XIZANG(54, "西藏"),
        SHANXI_THREETONE(61, "陕西"),
        GANSU(62, "甘肃"),
        QINGHAI(63, "青海"),
        NINGXIA(64, "宁夏"),
        XINJIANG(65, "新疆"),
        TAIWAN(71, "台湾"),
        XIANGGANG(81, "香港"),
        AOMEN(82, "澳门"),
        GUOWAI(91, "国外");

        private final int id;
        private final String desc;

        IdCardCityCodeEnum(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }

        public static IdCardCityCodeEnum valOf(String desc) {
            for (IdCardCityCodeEnum e : IdCardCityCodeEnum.values()) {
                if (e.getDesc().equals(desc)) {
                    return e;
                }
            }
            throw new IllegalArgumentException();
        }

        public static IdCardCityCodeEnum valOf(int id) {
            for (IdCardCityCodeEnum e : IdCardCityCodeEnum.values()) {
                if (e.getId() == id) {
                    return e;
                }
            }
            throw new IllegalArgumentException();
        }

        public static boolean valid(String desc) {
            for (IdCardCityCodeEnum e : IdCardCityCodeEnum.values()) {
                if (e.getDesc().equals(desc)) {
                    return true;
                }
            }
            return false;
        }

        public static boolean valid(int id) {
            for (IdCardCityCodeEnum e : IdCardCityCodeEnum.values()) {
                if (e.getId() == id) {
                    return true;
                }
            }
            return false;
        }

    }

    enum IdCardTWFirstCodeEnum {
        A(10), B(11), C(12), D(13), E(14), F(15), G(16), H(17), J(18), K(19), L(20), M(21), N(22),
        P(23), Q(24), R(25), S(26), T(27), U(28), V(29), X(30), Y(31), W(32), Z(33), I(34), O(35);
        final int id;

        IdCardTWFirstCodeEnum(int id) {
            this.id = id;
        }
    }

}