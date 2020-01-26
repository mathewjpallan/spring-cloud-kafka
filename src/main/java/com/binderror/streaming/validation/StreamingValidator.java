package com.binderror.streaming.validation;

import com.binderror.streaming.BaseStreaming;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * This is a placeholder job for validation. This demos a job where the input and output are bound at compile time.
 *
 */
@EnableBinding(Validation.class)
public class StreamingValidator extends BaseStreaming {

    @StreamListener(Validation.INPUT_CHANNEL)
    @SendTo(Validation.OUTPUT_CHANNEL)
    public String process(String s) {
        /* Assume everything is valid for now :)
           Thd dedupe validator demos how you can conditionally send messages to different destinations
           and the same approach can be used for validation errors in real
         */
        return s;
    }

}
