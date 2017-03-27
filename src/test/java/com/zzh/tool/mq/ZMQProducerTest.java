package com.zzh.tool.mq;

import com.zzh.mq.message.ZMQMessage;
import com.zzh.mq.producer.ZMQProducer;
import org.junit.Test;

/**
 * Created by jean on 17/1/13.
 */
public class ZMQProducerTest {

    @Test
    public void testProduce() {
        ZMQProducer zmqProducer = new ZMQProducer("localhost:9876", "Producer");
        zmqProducer.start();
        zmqProducer.send(new ZMQMessage("PushTopicTest").setData("Hi Rocket MQ,中智汇"));
        zmqProducer.shutdown();
    }

}
