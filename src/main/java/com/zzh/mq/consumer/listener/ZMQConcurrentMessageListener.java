package com.zzh.mq.consumer.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import java.util.List;

/**
 * Created by jean on 17/1/13.
 */
public abstract class ZMQConcurrentMessageListener extends ZMQBaseMessageListener implements MessageListenerConcurrently {

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        beforeConsume(msgs);
        return consume(msgs, context);
    }

    public ConsumeConcurrentlyStatus consume(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt messageExt : msgs) {
            doConsume(messageExt, context);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    public abstract void doConsume(MessageExt messageExt, ConsumeConcurrentlyContext consumeConcurrentlyContext);

}
