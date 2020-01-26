package com.binderror.streaming.validation;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Spring provides a default interface - Processor, but I have chosen to create my own interface for each streaming job.
 * The interface should hold all the @Input and @Output annotations. The input and output names do not have to map to Kafka topic
 * names and the application.yml allows mapping the input and output name to the kafka topic name.
 *
 * Spring-cloud-stream generates a proxy implementation for this interface and connects it to a binder that integrates with the actual topic
 */
public interface Validation {
    String INPUT_CHANNEL = "validationinput";
    String OUTPUT_CHANNEL = "validationoutput";

    @Input(Validation.INPUT_CHANNEL)
    SubscribableChannel input();

    @Output(Validation.OUTPUT_CHANNEL)
    MessageChannel output();
}
