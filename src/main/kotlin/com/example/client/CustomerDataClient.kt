package com.example.client

import com.example.dto.CustomerResponseDto
import com.example.exception.MyFoundException
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class CustomerDataClient(
    private val client: WebClient,
    @Value("\${endpoints.costumer.data.url}") private val url: String? = null
) {

    private var log = LogManager.getLogger(this::class.java)

    fun getCustomer(suid: String): Mono<CustomerResponseDto> {
        log.info("${this.url}/customers?suid=$suid")
        return this.client
            .get()
            .uri("${this.url}/customers?suid={suid}", suid)
            .retrieve()
            .onStatus({ HttpStatus.NOT_FOUND == it }, { Mono.error(MyFoundException("Customer $suid not found.")) })
            .onStatus(
                { HttpStatus.OK != it },
                { Mono.error(Exception("Error when execute a request for customer-data")) })
            .bodyToMono(CustomerResponseDto::class.java)
            .doOnNext { log.info("Success when request customer id $suid") }
            .doOnError { log.error("Error when request customer id $suid", it) }
    }

}