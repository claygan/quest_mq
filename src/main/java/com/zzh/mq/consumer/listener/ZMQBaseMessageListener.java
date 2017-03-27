package com.zzh.mq.consumer.listener;

import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jean on 17/1/22.
 */
public abstract class ZMQBaseMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZMQBaseMessageListener.class);

    public void beforeConsume(List<MessageExt> msgs) {
        if (LOGGER.isDebugEnabled()) {
            Set<String> ids = new HashSet<String>();
            for (MessageExt messageExt : msgs) {
                ids.add(messageExt.getMsgId());
            }
            LOGGER.debug("receive msg,msg size:{},ids:{}", msgs.size(), ids);
        }
    }
}
