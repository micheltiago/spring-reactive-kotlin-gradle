package com.example.service

import com.example.client.AssetsClient
import com.example.client.CustomerDataClient
import com.example.client.OrderClient
import com.example.dto.AssetResponseDto
import com.example.dto.CustomerResponseDto
import com.example.dto.OrderResponseDto
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AssetsService(
    private val clientAsset: AssetsClient,
    private val clientData: CustomerDataClient,
    private val clientOrder: OrderClient
) {

    private var log = LogManager.getLogger(this::class.java)

    fun findCustomerBySuid(suid: String): Mono<CustomerResponseDto> {
        log.info("findCustomerBySuid :$suid")
        return this.clientData
            .getCustomer(suid)
    }

    fun findOrderByOrderId(orderId: String): Mono<OrderResponseDto> {
        log.info("findOrderByOrderId :$orderId")
        return this.clientOrder
            .getOrder(orderId)
    }

    fun findAssetsBySuid(suid: String): Mono<AssetResponseDto> {
        log.info("findAssetsBySuid :$suid")
        return this.clientAsset
            .hasAssets(suid)
            .flatMap { transform(it) }
    }

    private fun transform(it: Boolean): Mono<AssetResponseDto> {
        return if (it)
            Mono.just(
                AssetResponseDto(
                    organization = "SIMMMMMMMMMMMMMMMMMMMMMMMM",
                )
            )
        else
            Mono.just(
                AssetResponseDto(
                    organization = "NAOOOOOOOOOO",
                )
            )
    }

}

