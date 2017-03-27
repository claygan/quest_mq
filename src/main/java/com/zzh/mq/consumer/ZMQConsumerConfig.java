package com.zzh.mq.consumer;

import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * Created by jean on 17/1/13.
 */
public class ZMQConsumerConfig {
    private String group;
    private String nameSvrs;
    private String topics;
    private ConsumeFromWhere consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
    private MessageModel messageModel = MessageModel.CLUSTERING;

    private MessageListener messageListener;

    public ZMQConsumerConfig(String nameSvrs, String group) {
        this.nameSvrs = nameSvrs;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public ZMQConsumerConfig setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getNameSvrs() {
        return nameSvrs;
    }

    public ZMQConsumerConfig setNameSvrs(String nameSvrs) {
        this.nameSvrs = nameSvrs;
        return this;
    }

    public String getTopics() {
        return topics;
    }

    public ZMQConsumerConfig setTopics(String topics) {
        this.topics = topics;
        return this;
    }

    public ConsumeFromWhere getConsumeFromWhere() {
        return consumeFromWhere;
    }

    public ZMQConsumerConfig setConsumeFromWhere(ConsumeFromWhere consumeFromWhere) {
        this.consumeFromWhere = consumeFromWhere;
        return this;
    }

    public MessageModel getMessageModel() {
        return messageModel;
    }

    public ZMQConsumerConfig setMessageModel(MessageModel messageModel) {
        this.messageModel = messageModel;
        return this;
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }
}
