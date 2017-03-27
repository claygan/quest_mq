package com.zzh.mq.message;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * MQ 消息
 * <p>
 * Created by jean on 17/1/13.
 */
public class ZMQMessage {
    private String topics;
    private String tag;
    private String keys;
    private Object data;

    public ZMQMessage(String topics) {
        this.topics = topics;
    }

    public String getTopics() {
        return topics;
    }

    public ZMQMessage setTopics(String topics) {
        this.topics = topics;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ZMQMessage setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getKeys() {
        return keys;
    }

    public ZMQMessage setKeys(String keys) {
        this.keys = keys;
        return this;
    }

    public ZMQMessage setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MessageExt buildMQMessage(byte[] bytes) {
        MessageExt messageExt = new MessageExt();
        messageExt.setTopic(topics);
        messageExt.setTags(tag);
        messageExt.setBody(bytes);
        messageExt.setKeys(keys);
        return messageExt;
    }
}
