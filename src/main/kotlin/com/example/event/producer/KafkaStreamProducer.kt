package com.example.event.producer

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.messaging.MessageChannel
import org.springframework.stereotype.Service

@Service
@EnableBinding(KafkaStreamConfOut::class)
class KafkaStreamProducer(kafkaStream: KafkaStreamConfOut) {
    val messageChannel: MessageChannel = kafkaStream.output()
}