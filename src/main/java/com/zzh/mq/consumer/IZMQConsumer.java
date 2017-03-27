package com.zzh.mq.consumer;

import com.alibaba.rocketmq.client.consumer.listener.MessageListener;

/**
 * Created by jean on 17/1/13.
 */
public interface IZMQConsumer {
    void start();
    void shutdown();
}
