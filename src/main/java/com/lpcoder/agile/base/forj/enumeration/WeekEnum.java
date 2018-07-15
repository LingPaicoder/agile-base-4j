package com.lpcoder.agile.base.forj.enumeration;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-2
 */
public enum WeekEnum {

    SUNDAY(1,"周日"),
    MONDAY(2,"周一"),
    TUESDAY(3,"周二"),
    WEDNESDAY(4,"周三"),
    THURSDAY(5,"周四"),
    FRIDAY(6,"周五"),
    SATURDAY(7,"周六");

    private final int id;
    private final String desc;

    WeekEnum(int id, String desc){
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static WeekEnum valOf(String desc) {
        for (WeekEnum e : WeekEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        throw new IllegalArgumentException();
    }

    public static WeekEnum valOf(int id) {
        for (WeekEnum e : WeekEnum.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new IllegalArgumentException();
    }

    public static boolean valid(String desc){
        for (WeekEnum e : WeekEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return true;
            }
        }
        return false;
    }

    public static boolean valid(int id){
        for (WeekEnum e : WeekEnum.values()) {
            if (e.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
