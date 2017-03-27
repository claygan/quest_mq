package com.zzh.mq.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.zzh.mq.message.ZMQMessage;

/**
 * Created by jean on 17/1/13.
 */
public interface IZMQProducer {
    SendResult send(ZMQMessage zmqMessage);

    SendResult send(ZMQMessage zmqMessage, long timeout);

    void sendOneway(ZMQMessage zmqMessage);

    void start();

    void shutdown();
}
