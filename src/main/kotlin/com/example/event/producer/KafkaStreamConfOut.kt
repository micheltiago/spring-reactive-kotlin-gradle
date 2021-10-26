package com.example.event.producer

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface KafkaStreamConfOut {

    @Output(OUTPUT)
    fun output(): MessageChannel

    companion object {
        const val OUTPUT = "myConsumerConfChannelOut"
    }

}