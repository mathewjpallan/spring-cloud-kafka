package com.binderror.springcloudkafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
public class StreamingTrial {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String process(String s) {
        return s;
    }

}
