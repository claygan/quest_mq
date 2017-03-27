package com.zzh.mq.serializer;

import java.lang.reflect.Type;

/**
 * Created by jean on 17/1/13.
 */
public interface ZMQSerializer<T> {

    public byte[] serialize(Object object);

    public T deserialize(byte[] bytes, Type type);
}
