package com.lpcoder.agile.base.forj.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import com.lpcoder.agile.base.forj.constant.DateFormatCnst;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author: liurenpeng
 * @date: Created in 2017-11-3
 */
public class JsonUtil {

    private static final Gson gson;

    private JsonUtil() {
    }

    static {
        gson = new GsonBuilder().disableHtmlEscaping().registerTypeAdapter(Byte.class, new NumberTypeAdapter<Byte>() {
            @Override
            protected Byte valueOf(String string) {
                return Byte.valueOf(string);
            }
        }).registerTypeAdapter(Short.class, new NumberTypeAdapter<Short>() {
            @Override
            protected Short valueOf(String string) {
                return Short.valueOf(string);
            }
        }).registerTypeAdapter(Integer.class, new NumberTypeAdapter<Integer>() {
            @Override
            protected Integer valueOf(String string) {
                return Integer.valueOf(string);
            }
        }).registerTypeAdapter(Long.class, new NumberTypeAdapter<Long>() {
            @Override
            protected Long valueOf(String string) {
                return Long.valueOf(string);
            }
        }).registerTypeAdapter(Float.class, new NumberTypeAdapter<Float>() {
            @Override
            protected Float valueOf(String string) {
                return Float.valueOf(string);
            }
        }).registerTypeAdapter(Double.class, new NumberTypeAdapter<Double>() {
            @Override
            protected Double valueOf(String string) {
                return Double.valueOf(string);
            }
        }).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
    }

    public static  <T> T fromJson(String json, Class<T> classOfT){
        return gson.fromJson(json,classOfT);
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    private static abstract class NumberTypeAdapter<T extends Number> extends TypeAdapter<T> {

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public T read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String string = in.nextString();
                return valueOf(string);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        /**
         * 调用具体Number实现类的valueOf方法
         */
        protected abstract T valueOf(String string);
    }

    private static class DateTypeAdapter extends TypeAdapter<Date> {
        private final DateFormat standardDateFormat = buildStandardDateFormat();
        private final DateFormat standardTimeFormat = buildStandardTimeFormat();
        private final DateFormat isoFormat = buildIsoFormat();

        private DateFormat buildStandardDateFormat() {
            return new SimpleDateFormat(DateFormatCnst.STANDARD_DATE_FORMAT, Locale.CHINA);
        }

        private DateFormat buildStandardTimeFormat() {
            return new SimpleDateFormat(DateFormatCnst.STANDARD_DATETIME_FORMAT, Locale.CHINA);
        }

        private DateFormat buildIsoFormat() {
            DateFormat iso8601Format = new SimpleDateFormat(DateFormatCnst.ISO_8601_FORMAT, Locale.US);
            iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
            return iso8601Format;
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            return deserializeToDate(in.nextString());
        }

        private synchronized Date deserializeToDate(String json) {
            if (StringUtils.isEmpty(json)) {
                return null;
            }

            /**
             * Gson原生序列化结果 例如 Nov 5, 2017 8:13:10 AM
             */
            final String gsonNativeContainsFlag = ",";
            if (json.contains(gsonNativeContainsFlag)) {
                Gson gson = new Gson();
                return gson.fromJson("\"" + json + "\"", Date.class);
            }

            /**
             * ISO8601 例如 2017-11-05T08:13:10.786Z
             */
            final String iso8601EndsWithFlag = "Z";
            if (json.endsWith(iso8601EndsWithFlag)) {
                try {
                    return isoFormat.parse(json);
                } catch (ParseException e) {
                    throw new JsonSyntaxException(json, e);
                }
            }

            /**
             * standardTimeFormat 例如 2017-11-05 08:13:10
             */
            final String standardTimeFormatFlag = ":";
            if (json.contains(standardTimeFormatFlag)) {
                try {
                    return standardTimeFormat.parse(json);
                } catch (ParseException e) {
                    throw new JsonSyntaxException(json, e);
                }
            }

            /**
             * standardDateFormat 例如 2017-11-05
             */
            final String standardDateFormatFlag = "-";
            if (json.contains(standardDateFormatFlag)) {
                try {
                    return standardDateFormat.parse(json);
                } catch (ParseException e) {
                    throw new JsonSyntaxException(json, e);
                }
            }

            /**
             * 时间戳
             */
            try {
                return new Date(Long.valueOf(json));
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(json, e);
            }
        }

        @Override
        public synchronized void write(JsonWriter out, Date value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value.getTime());
        }
    }
}


