package com.lpcoder.agile.base.forj.check;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-11
 */
public enum CheckResultCodeEnum {

    /**
     * object
     */
    OBJ_NOT_NULL_FAIL(-10000L, "不能为Null"),
    OBJ_EQ_FAIL(-10001L, "必须等同于%s"),
    OBJ_NOT_EQ_FAIL(-10002L, "必须不等于%s"),

    /**
     * string
     */
    STR_NOT_EMPTY_FAIL(-11000L, "不能为空"),
    STR_LENGTH_EQ_FAIL(-11001L, "的长度必须等于%d"),
    STR_LENGTH_GT_FAIL(-11002L, "的长度必须大于%d"),
    STR_LENGTH_GTE_FAIL(-11003L, "的长度必须大于或等于%d"),
    STR_LENGTH_LT_FAIL(-11004L, "的长度必须小于%d"),
    STR_LENGTH_LTE_FAIL(-11005L, "的长度必须小于或等于%d"),
    STR_EQ_FAIL(-11006L, "的值必须是%s"),
    STR_ID_CARD_FAIL(-11007L, "必须符合身份证格式"),
    STR_EMAIL_FAIL(-11008L, "必须符合邮箱格式"),
    STR_PHONE_FAIL(-11009L, "必须符合手机号格式"),
    STR_STANDARD_DATE_FAIL(-11010L, "必须符合yyyy-MM-dd格式"),
    STR_STANDARD_DATETIME_FAIL(-11011L, "必须符合yyyy-MM-dd HH:mm:ss格式"),
    STR_NUM_FAIL(-11012L, "必须符合数字格式"),
    STR_NOT_NULL_FAIL(-11013L, "不能为Null"),
    STR_URL_FAIL(-11014L, "必须符合URL规则"),
    STR_ALL_LETTER_FAIL(-11015L, "必须符合全字母规则"),
    STR_EMPTY_FAIL(-11016L, "必须为空"),
    STR_NOT_EQ_FAIL(-11017L, "的值必须不是%s"),
    STR_CONTAINS_FAIL(-11018L, "的值必须包含%s"),
    STR_NOT_CONTAINS_FAIL(-11019L, "的值必须不包含%s"),

    /**
     * date
     */
    DATE_EQ_FAIL(-12000L, "必须是%tc(时间戳:%tQ)"),
    DATE_AFTER_FAIL(-12001L, "必须晚于%tc(时间戳:%tQ)"),
    DATE_AFTER_OR_EQ_FAIL(-12002L, "必须晚于或等于%tc(时间戳:%tQ)"),
    DATE_BEFORE_FAIL(-12003L, "必须早于%tc(时间戳:%tQ)"),
    DATE_BEFORE_OR_EQ_FAIL(-12004L, "必须早于或等于%tc(时间戳:%tQ)"),
    DATE_NOT_NULL_FAIL(-12005L, "不能为Null"),
    DATE_NOT_EQ_FAIL(-12006L, "必须不是%tc(时间戳:%tQ)"),

    /**
     * array
     */
    ARR_NOT_EMPTY_FAIL(-13000L, "不能为空"),
    ARR_LENGTH_EQ_FAIL(-13001L, "的长度必须等于%d"),
    ARR_LENGTH_GT_FAIL(-13002L, "的长度必须大于%d"),
    ARR_LENGTH_GTE_FAIL(-13003L, "的长度必须大于或等于%d"),
    ARR_LENGTH_LT_FAIL(-13004L, "的长度必须小于%d"),
    ARR_LENGTH_LTE_FAIL(-13005L, "的长度必须小于或等于%d"),
    ARR_NOT_CONTAINS_NULL_FAIL(-13006L, "中不能有空值"),
    ARR_NOT_CONTAINS_DUP_FAIL(-13007L, "中不能有重复值"),
    ARR_NOT_NULL_FAIL(-13008L, "不能为Null"),
    ARR_CONTAINS_FAIL(-13009L, "必须包含有%s"),
    ARR_NOT_CONTAINS_FAIL(-13010L, "必须不包含%s"),

    /**
     * collection
     */
    COLL_NOT_EMPTY_FAIL(-14000L, "不能为空"),
    COLL_SIZE_EQ_FAIL(-14001L, "的长度必须等于%d"),
    COLL_SIZE_GT_FAIL(-14002L, "的长度必须大于%d"),
    COLL_SIZE_GTE_FAIL(-14003L, "的长度必须大于或等于%d"),
    COLL_SIZE_LT_FAIL(-14004L, "的长度必须小于%d"),
    COLL_SIZE_LTE_FAIL(-14005L, "的长度必须小于或等于%d"),
    COLL_NOT_CONTAINS_NULL_FAIL(-14006L, "中不能有空值"),
    COLL_NOT_CONTAINS_DUP_FAIL(-14007L, "中不能有重复值"),
    COLL_NOT_NULL_FAIL(-14008L, "不能为Null"),
    COLL_CONTAINS_FAIL(-14009L, "必须包含有%s"),
    COLL_NOT_CONTAINS_FAIL(-14010L, "必须不包含%s"),

    /**
     * map
     */
    MAP_NOT_EMPTY_FAIL(-15000L, "不能为空"),
    MAP_SIZE_EQ_FAIL(-15001L, "的长度必须等于%d"),
    MAP_SIZE_GT_FAIL(-15002L, "的长度必须大于%d"),
    MAP_SIZE_GTE_FAIL(-15003L, "的长度必须大于或等于%d"),
    MAP_SIZE_LT_FAIL(-15004L, "的长度必须小于%d"),
    MAP_SIZE_LTE_FAIL(-15005L, "的长度必须小于或等于%d"),
    MAP_KEY_NOT_CONTAINS_NULL_FAIL(-15006L, "中不能有空值"),
    MAP_NOT_NULL_FAIL(-15007L, "不能为Null"),
    MAP_KEY_CONTAINS_FAIL(-15008L, "键中必须包含有%s"),
    MAP_KEY_NOT_CONTAINS_FAIL(-15009L, "键中必须不包含%s"),
    MAP_VALUE_CONTAINS_FAIL(-15010L, "值中必须包含有%s"),
    MAP_VALUE_NOT_CONTAINS_FAIL(-15011L, "值中必须不包含%s"),

    /**
     * byte
     */
    BYTE_EQ_FAIL(-16001L, "必须等于%d"),
    BYTE_GT_FAIL(-16002L, "必须大于%d"),
    BYTE_GTE_FAIL(-16003L, "必须大于或等于%d"),
    BYTE_LT_FAIL(-16004L, "必须小于%d"),
    BYTE_LTE_FAIL(-16005L, "必须小于或等于%d"),
    BYTE_NOT_NULL_FAIL(-16006L, "不能为Null"),
    BYTE_NOT_EQ_FAIL(-16007L, "必须不等于%d"),

    /**
     * short
     */
    SHORT_EQ_FAIL(-17001L, "必须等于%d"),
    SHORT_GT_FAIL(-17002L, "必须大于%d"),
    SHORT_GTE_FAIL(-17003L, "必须大于或等于%d"),
    SHORT_LT_FAIL(-17004L, "必须小于%d"),
    SHORT_LTE_FAIL(-17005L, "必须小于或等于%d"),
    SHORT_NOT_NULL_FAIL(-17006L, "不能为Null"),
    SHORT_NOT_EQ_FAIL(-17007L, "必须不等于%d"),

    /**
     * int
     */
    INT_EQ_FAIL(-18001L, "必须等于%d"),
    INT_GT_FAIL(-18002L, "必须大于%d"),
    INT_GTE_FAIL(-18003L, "必须大于或等于%d"),
    INT_LT_FAIL(-18004L, "必须小于%d"),
    INT_LTE_FAIL(-18005L, "必须小于或等于%d"),
    INT_NOT_NULL_FAIL(-18006L, "不能为Null"),
    INT_NOT_EQ_FAIL(-18007L, "必须不等于%d"),

    /**
     * long
     */
    LONG_EQ_FAIL(-19001L, "必须等于%d"),
    LONG_GT_FAIL(-19002L, "必须大于%d"),
    LONG_GTE_FAIL(-19003L, "必须大于或等于%d"),
    LONG_LT_FAIL(-19004L, "必须小于%d"),
    LONG_LTE_FAIL(-19005L, "必须小于或等于%d"),
    LONG_NOT_NULL_FAIL(-19006L, "不能为Null"),
    LONG_NOT_EQ_FAIL(-19007L, "必须不等于%d"),

    /**
     * float
     */
    FLOAT_EQ_FAIL(-20001L, "必须等于%f"),
    FLOAT_GT_FAIL(-20002L, "必须大于%f"),
    FLOAT_GTE_FAIL(-20003L, "必须大于或等于%f"),
    FLOAT_LT_FAIL(-20004L, "必须小于%f"),
    FLOAT_LTE_FAIL(-20005L, "必须小于或等于%f"),
    FLOAT_NOT_NULL_FAIL(-20006L, "不能为Null"),
    FLOAT_NOT_EQ_FAIL(-20007L, "必须不等于%f"),

    /**
     * double
     */
    DOUBLE_EQ_FAIL(-21001L, "必须等于%f"),
    DOUBLE_GT_FAIL(-21002L, "必须大于%f"),
    DOUBLE_GTE_FAIL(-21003L, "必须大于或等于%f"),
    DOUBLE_LT_FAIL(-21004L, "必须小于%f"),
    DOUBLE_LTE_FAIL(-21005L, "必须小于或等于%f"),
    DOUBLE_NOT_NULL_FAIL(-21006L, "不能为Null"),
    DOUBLE_NOT_EQ_FAIL(-20007L, "必须不等于%f");

    private long code;
    private String desc;

    CheckResultCodeEnum(long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public long getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
