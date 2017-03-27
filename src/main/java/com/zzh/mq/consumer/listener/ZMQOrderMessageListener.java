package com.zzh.mq.consumer.listener;

import com.alibaba.rocketmq.client.consumer.listener.*;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jean on 17/1/13.
 */
public abstract class ZMQOrderMessageListener extends ZMQBaseMessageListener implements MessageListenerOrderly {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZMQConcurrentMessageListener.class);

    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        beforeConsume(msgs);
        return consume(msgs, context);
    }


    public ConsumeOrderlyStatus consume(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        for (MessageExt messageExt : msgs) {
            doConsume(messageExt, context);
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }

    public abstract void doConsume(MessageExt messageExt, ConsumeOrderlyContext consumeOrderlyContext);
}
