package com.example.event.consumer

import com.example.dto.EventConsumerKafkaDto
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
@EnableBinding(KafkaStreamConfIn::class)
class KafkaStreamConsumer() {

    @StreamListener(
        value = KafkaStreamConfIn.IN,
        condition = "headers['productType']=='INDIVIDUAL_SAVINGS_ACCOUNT'"
    )
    fun loadEvent(event: Message<EventConsumerKafkaDto>) {
        println("Consumindo fila Kafka: $event")
    }

}