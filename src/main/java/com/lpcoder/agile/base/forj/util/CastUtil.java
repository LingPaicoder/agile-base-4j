package com.lpcoder.agile.base.forj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * @author: liurenpeng
 * @date: Created in 18-5-24
 */
public class CastUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CastUtil.class);

    public static String castString(Object origin) {
        return CastUtil.castString(origin, "");
    }

    public static String castString(Object origin, String defaultValue) {
        return origin != null ? StringUtil.getString(origin) : defaultValue;
    }

    public static boolean castBoolean(Object origin) {
        return CastUtil.castBoolean(origin, false);
    }

    public static boolean castBoolean(Object origin, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (origin != null) {
            booleanValue = Boolean.parseBoolean(castString(origin));
        }
        return booleanValue;
    }

    public static char castChar(Object origin) {
        String s = CastUtil.castString(origin);
        if (s.length() == 0) {
            throw new IllegalArgumentException(StringUtil.join(
                    "castChar error. Cannot convert empty to char. ",
                    "origin:", String.valueOf(origin)));
        }
        return s.charAt(0);
    }

    public static float castFloat(Object origin) {
        return CastUtil.castFloat(origin, 0.0f);
    }

    public static float castFloat(Object origin, float defaultFloat) {
        return doNumCast(origin, defaultFloat, Float::parseFloat);
    }

    public static double castDouble(Object origin) {
        return CastUtil.castDouble(origin, 0);
    }

    public static double castDouble(Object origin, double defaultValue) {
        return doNumCast(origin, defaultValue, Double::parseDouble);
    }

    public static long castLong(Object origin) {
        return CastUtil.castLong(origin, 0);
    }

    public static long castLong(Object origin, long defaultValue) {
        return doNumCast(origin, defaultValue, Long::parseLong);
    }

    public static int castInt(Object origin) {
        return CastUtil.castInt(origin, 0);
    }

    public static int castInt(Object origin, int defaultValue) {
        return doNumCast(origin, defaultValue, Integer::parseInt);
    }

    public static short castShort(Object origin) {
        return CastUtil.castShort(origin, (short) 0);
    }

    public static short castShort(Object origin, short defaultShort) {
        return doNumCast(origin, defaultShort, Short::parseShort);
    }

    public static byte castByte(Object origin) {
        return CastUtil.castByte(origin, (byte) 0);
    }

    public static byte castByte(Object origin, byte defaultByte) {
        return doNumCast(origin, defaultByte, Byte::parseByte);
    }

    private static <T> T doNumCast(Object origin, T defaultValue, Function<String, T> caster) {
        T result = defaultValue;
        if (origin != null) {
            String strValue = castString(origin);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    result = caster.apply(strValue);
                } catch (NumberFormatException e) {
                    LOG.error(StringUtil.join("cast error. NumberFormatException occurred.",
                            " origin:", strValue, " use defaultValue:", defaultValue));
                }
            }
        }
        return result;
    }

}
