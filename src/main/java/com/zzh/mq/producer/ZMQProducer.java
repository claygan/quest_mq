package com.zzh.mq.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.zzh.mq.exception.ZMQException;
import com.zzh.mq.message.ZMQMessage;
import com.zzh.mq.serializer.JsonSerializer;
import com.zzh.mq.serializer.ZMQSerializer;
import com.zzh.mq.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jean on 17/1/13.
 */
public class ZMQProducer implements IZMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZMQProducer.class);

    private DefaultMQProducer defaultMQProducer;

    private ZMQSerializer zmqSerializer;

    public ZMQProducer(String nameSvrs, String group) {
        defaultMQProducer = new DefaultMQProducer(group);
        defaultMQProducer.setNamesrvAddr(nameSvrs);
        if (zmqSerializer == null) {
            zmqSerializer = new JsonSerializer();
        }
    }

    public void start() {
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            throw new ZMQException(e);
        }
    }

    public void shutdown() {
        defaultMQProducer.shutdown();
    }

    public SendResult send(ZMQMessage zmqMessage) {
        MessageExt messageExt = buildMessageExt(zmqMessage);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("send msg,msgId:{},topic:{},keys:{},body:{}", messageExt.getMsgId(),
                    zmqMessage.getTopics(), String.valueOf(zmqMessage.getKeys()), JsonUtil.toJSONString(zmqMessage.getData()));
        }
        try {
            return defaultMQProducer.send(messageExt);
        } catch (Exception e) {
            String msg = "send msg fail,msgId:" + messageExt.getMsgId();
            LOGGER.error(msg, e);
            throw new ZMQException(msg, e);
        }
    }


    public SendResult send(ZMQMessage zmqMessage, long timeout) {
        MessageExt messageExt = buildMessageExt(zmqMessage);
        try {
            return defaultMQProducer.send(messageExt, timeout);
        } catch (Exception e) {
            String msg = "send msg fail,msgId:" + messageExt.getMsgId();
            LOGGER.error(msg, e);
            throw new ZMQException(msg, e);
        }
    }

    public void sendOneway(ZMQMessage zmqMessage) {
        MessageExt messageExt = buildMessageExt(zmqMessage);
        try {
            defaultMQProducer.sendOneway(messageExt);
        } catch (Exception e) {
            String msg = "send msg oneway fail,msgId:" + messageExt.getMsgId();
            LOGGER.error(msg, e);
            throw new ZMQException(msg, e);
        }
    }

    protected void checkMessage(ZMQMessage zmqMessage) {
        if (zmqMessage == null || zmqMessage.getData() == null) {
            throw new ZMQException("msg is null!!");
        }
        if (StringUtils.isEmpty(zmqMessage.getTopics())) {
            throw new ZMQException("topic is empty!!");
        }
    }

    protected MessageExt buildMessageExt(ZMQMessage zmqMessage) {
        checkMessage(zmqMessage);
        byte[] bytes = zmqSerializer.serialize(zmqMessage.getData());
        if (bytes == null) {
            throw new ZMQException("message serialize exception");
        }
        MessageExt messageExt = zmqMessage.buildMQMessage(bytes);
        return messageExt;
    }

    public void setZmqSerializer(ZMQSerializer zmqSerializer) {
        this.zmqSerializer = zmqSerializer;
    }
}
