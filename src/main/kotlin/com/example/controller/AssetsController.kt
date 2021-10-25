package com.example.controller

import com.example.dto.AssetResponseDto
import com.example.dto.CustomerResponseDto
import com.example.dto.OrderResponseDto
import com.example.service.AssetsService
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/")
class AssetsController(
    private val service: AssetsService
) {

    private var log = LogManager.getLogger(this::class.java)

    @GetMapping("/customer/{suid}")
    fun getCustomerBySuid(@PathVariable("suid") suid: String): Mono<CustomerResponseDto> {
        log.info("/customer/$suid")
        return this.service
            .findCustomerBySuid(suid)
    }

    @GetMapping("/orders/{orderId}")
    fun getOrderByOrderId(@PathVariable("orderId") orderId: String): Mono<OrderResponseDto> {
        log.info("/orders/$orderId")
        return this.service
            .findOrderByOrderId(orderId)
    }

    @GetMapping("/assets/{suid}")
    fun getAssetBySuid(@PathVariable("suid") suid: String): Mono<AssetResponseDto> {
        log.info("/assets/$suid")
        return this.service
            .findAssetsBySuid(suid)
    }

}