package com.example.dto

data class EventConsumerKafkaDto(
    val orderId: String,
    val productType: String,
    val suid: String,
    val organization: String,
    val brand: String,
    val customerId: String
)
