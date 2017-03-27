package com.zzh.mq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zzh.mq.exception.ZMQException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by jean on 17/1/13.
 */
public class ZMQConsumer implements IZMQConsumer {

    private DefaultMQPushConsumer defaultMQPushConsumer;

    public ZMQConsumer(ZMQConsumerConfig zmqConsumerConfig) {
        checkInitParams(zmqConsumerConfig);
        defaultMQPushConsumer = new DefaultMQPushConsumer(zmqConsumerConfig.getGroup());
        defaultMQPushConsumer.setConsumeFromWhere(zmqConsumerConfig.getConsumeFromWhere());
        defaultMQPushConsumer.setMessageModel(zmqConsumerConfig.getMessageModel());
        defaultMQPushConsumer.setNamesrvAddr(zmqConsumerConfig.getNameSvrs());
        try {
            defaultMQPushConsumer.subscribe(zmqConsumerConfig.getTopics(), null);
            defaultMQPushConsumer.registerMessageListener(zmqConsumerConfig.getMessageListener());
        } catch (MQClientException e) {
            throw new ZMQException(e);
        }
    }


    public void start() {
        try {
            defaultMQPushConsumer.start();
        } catch (MQClientException e) {
            throw new ZMQException(e);
        }
    }

    public void shutdown() {
        defaultMQPushConsumer.shutdown();
    }

    public void checkInitParams(ZMQConsumerConfig zmqConsumerConfig) {
        if (zmqConsumerConfig == null) {
            throw new ZMQException("config is null!!");
        }
        if (StringUtils.isEmpty(zmqConsumerConfig.getTopics())) {
            throw new ZMQException("topis is empty!!");
        }
        if (StringUtils.isEmpty(zmqConsumerConfig.getGroup())) {
            throw new ZMQException("group is empty!!");
        }
        if (StringUtils.isEmpty(zmqConsumerConfig.getNameSvrs())) {
            throw new ZMQException("nameSvr is empty!!");
        }
        if (zmqConsumerConfig.getMessageListener() == null) {
            throw new ZMQException("message listener is null!!");
        }
    }
}
