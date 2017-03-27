package com.zzh.mq.exception;

/**
 * exception for ZMQ
 * <p>
 * Created by jean on 17/1/13.
 */
public class ZMQException extends RuntimeException {

    public ZMQException(String msg) {
        super(msg);
    }

    public ZMQException(String msg, Throwable e) {
        super(msg, e);
    }

    public ZMQException(Throwable e) {
        super(e);
    }
}
