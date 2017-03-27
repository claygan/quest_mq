package com.zzh.tool.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zzh.mq.consumer.IZMQConsumer;
import com.zzh.mq.consumer.listener.ZMQConcurrentMessageListener;
import com.zzh.mq.consumer.ZMQConsumer;
import com.zzh.mq.consumer.ZMQConsumerConfig;
import com.zzh.mq.util.JsonUtil;

import java.util.List;

/**
 * Created by jean on 17/1/13.
 */
public class ZMQConsumerTest {

    //    @Test
    public static void testConsume() {
        ZMQConsumerConfig zmqConsumerConfig = new ZMQConsumerConfig("localhost:9876", "defaultConsumerGroup");
        zmqConsumerConfig.setTopics("PushTopicTest");
        zmqConsumerConfig.setMessageListener(new ZMQConcurrentMessageListener() {

            public void doConsume(MessageExt messageExt, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(JsonUtil.deserialize(messageExt.getBody(), String.class));
            }
        });
        IZMQConsumer izmqConsumer = new ZMQConsumer(zmqConsumerConfig);
        izmqConsumer.start();
    }

    public static void main(String[] args) {
        testConsume();
    }
}
