package com.binderror.streaming.dedupe;

import com.binderror.streaming.BaseStreaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * This is the streaming job for deduplication. This contains a placeholder implementation for a trial.
 */
@EnableBinding(Dedupe.class)
public class StreamingDedupe extends BaseStreaming {

    @Autowired
    private Dedupe dedupe;

    @StreamListener(Dedupe.INPUT_CHANNEL)
    public void process(String message) {
        //Demonstrative assumption that the message hello already exists and will be sent to the duplicate topic
        if (message.equalsIgnoreCase("hello")) {
            dedupe.duplicate().send(createMessage(message));
        } else {
            dedupe.unique().send(createMessage(message));
        }
    }

}
