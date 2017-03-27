package com.zzh.mq.serializer;

import com.zzh.mq.util.JsonUtil;

import java.lang.reflect.Type;

/**
 * Created by jean on 17/1/13.
 */
public class JsonSerializer implements ZMQSerializer {

    public byte[] serialize(Object object) {
        return JsonUtil.serialize(object);
    }

    public Object deserialize(byte[] bytes, Type type) {
        return JsonUtil.deserialize(bytes, type);
    }
}
