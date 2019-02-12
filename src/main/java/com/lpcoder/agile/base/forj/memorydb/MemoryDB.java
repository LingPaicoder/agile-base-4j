package com.lpcoder.agile.base.forj.memorydb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import redis.clients.jedis.Jedis;

/**
 * 轻量级单点内存key-value数据库
 *
 * @author liurenpeng
 * @date Created in 19-2-12
 */
public class MemoryDB {

    private static final long NEVER_DEAD = -1;
    private Map<String, Value> map;

    public MemoryDB() {
        this.map = new ConcurrentHashMap<>();
    }

    public void set(String key, String value) {
        set(key, value, NEVER_DEAD);
    }

    public void set(String key, String value, long px) {
        map.put(key, new Value(value, System.currentTimeMillis() + px));
    }

    public void setEx(String key, String value) {
        setEx(key, value, NEVER_DEAD);
    }

    public void setEx(String key, String value, long px) {
        if (!exists(key)) {
            throw new MemoryDBException("key:" + key + " not exist");
        }
        set(key, value, px);
    }

    public void setNx(String key, String value) {
        setNx(key, value, NEVER_DEAD);
    }

    public void setNx(String key, String value, long px) {
        if (exists(key)) {
            throw new MemoryDBException("key:" + key + " already exist");
        }
        set(key, value, px);
    }

    public boolean exists(String key) {
        return map.containsKey(key);
    }

    public String get(String key) {
        Value value = map.get(key);
        return null == value ? null : value.data;
    }

    public String getSet(String key, String value) {
        return getSet(key, value, NEVER_DEAD);
    }

    public String getSet(String key, String value, long px) {
        String oldValue = get(key);
        set(key, value, px);
        return oldValue;
    }

    public void del(String key) {
        map.remove(key);
    }

    /*public long incr(String key) {

    }*/

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        //jedis
        Map map = new HashMap();
        map.remove("key");
    }

    @Data
    @AllArgsConstructor
    private class Value {
        private String data;
        private long deadTime;
    }

}
