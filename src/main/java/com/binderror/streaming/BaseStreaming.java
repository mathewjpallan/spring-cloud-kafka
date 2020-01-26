package com.binderror.streaming;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Base class that holds utility methods.
 * All streaming jobs will extend from this class.
 */
public class BaseStreaming {

    protected  <T> Message<T> createMessage(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
