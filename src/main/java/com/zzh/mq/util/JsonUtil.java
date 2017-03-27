package com.zzh.mq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * Created by jean on 17/1/13.
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
        throw new UnsupportedOperationException("This class can't be instanted!");
    }

    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return JSON.toJSONBytes(object);
        } catch (Exception e) {
            LOGGER.error("serialize fail", e);
        }
        return null;
    }

    public static <T> T deserialize(byte[] bytes, Type type) {
        if (bytes == null || type == null) {
            return null;
        }
        try {
            return JSON.parseObject(bytes, type);
        } catch (Exception e) {
            LOGGER.error("deserialize with typeReference fail", e);
        }
        return null;
    }

    public static <T> T deserialize(String json, Type type) {
        if (json == null || json.length() == 0 || type == null) {
            return null;
        }
        try {
            return JSON.parseObject(json, type);
        } catch (Exception e) {
            LOGGER.error("deserialize with typeReference fail", e);
        }
        return null;
    }

    public static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            LOGGER.error("toJSonString fail", e);
        }
        return null;
    }

    public static String toJSONString(byte[] bytes, Type type) {
        if (bytes == null || type == null) {
            return null;
        }
        try {
            Object obj = deserialize(bytes, type);
            if (obj == null) {
                return null;
            }
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            LOGGER.error("toJSonString fail", e);
        }
        return null;
    }
}
