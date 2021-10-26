package com.example.consumer

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface KafkaStreamProcessor {

    @Input(INPUT)
    fun confKafkaChannel(): SubscribableChannel?

    companion object {
        const val INPUT = "confKafkaChannel"
    }

}