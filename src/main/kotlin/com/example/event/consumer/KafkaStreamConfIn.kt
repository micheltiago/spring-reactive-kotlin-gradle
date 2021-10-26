package com.example.event.consumer

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface KafkaStreamConfIn {

    @Input(IN)
    fun confKafkaChannel(): SubscribableChannel?

    companion object {
        const val IN = "myConsumerConfChannelIn"
    }

}