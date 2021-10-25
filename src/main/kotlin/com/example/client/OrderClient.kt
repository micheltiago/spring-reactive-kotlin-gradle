package com.example.client

import com.example.dto.OrderResponseDto
import com.example.exception.MyFoundException
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class OrderClient(
    private val client: WebClient,
    @Value("\${endpoints.acquisition.orders.url}") private val url: String? = null
) {

    private var log = LogManager.getLogger(this::class.java)

    fun getOrder(orderId: String): Mono<OrderResponseDto> {
        log.info("${this.url}/orders/$orderId")
        return this.client
            .get()
            .uri("${this.url}/orders/{orderId}", orderId)
            .retrieve()
            .onStatus({ HttpStatus.NOT_FOUND == it }, { Mono.error(MyFoundException("Order $orderId not found.")) })
            .onStatus({ HttpStatus.OK != it }, { Mono.error(Exception("Error when execute a request for acquisition-orders")) })
            .bodyToMono(OrderResponseDto::class.java)
            .doOnNext { log.info("Success when request Order id $orderId") }
            .doOnError { log.error("Error when request Order id $orderId", it) }
    }

}