package com.example.client

import com.example.dto.AssetResponseDto
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class AssetsClient(
    private val client: WebClient,
    @Value("\${endpoints.customer-assets.url}") private val url: String? = null
) {

    companion object {
        private const val STATUS = "ACTIVE"
        private const val PRODUCT_TYPE = "INDIVIDUAL_SAVINGS_ACCOUNT"
    }

    private var log = LogManager.getLogger(this::class.java)

    fun hasAssets(suid: String): Mono<Boolean> {
        log.info("${this.url}/assets?suid=$suid&productType=$PRODUCT_TYPE&status=$STATUS")
        return this.client
            .get()
            .uri("${this.url}/assets?suid={suid}&productType={productType}&status={status}", suid, PRODUCT_TYPE, STATUS)
            .retrieve()
            .bodyToFlux(AssetResponseDto::class.java)
            .doOnNext { log.info("The user already has a account! suid: $suid") }
            .doOnError { log.error("Error validating account! id $suid", it) }
            .hasElements()
    }

    fun getAssets(suid: String): Mono<AssetResponseDto> {
        log.info("${this.url}/assets?suid=$suid&productType=$PRODUCT_TYPE&status=$STATUS")
        return this.client
            .get()
            .uri("${this.url}/assets?suid={suid}&productType={productType}&status={status}", suid, PRODUCT_TYPE, STATUS)
            .retrieve()
            .bodyToFlux(AssetResponseDto::class.java)
            .doOnNext { log.info("The user has a account! suid: $suid") }
            .doOnError { log.error("Error get account! id $suid", it) }
            .next()
    }
}