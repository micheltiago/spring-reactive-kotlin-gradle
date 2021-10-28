package com.example.service

import com.example.dto.ProductDto
import com.example.entities.Product
import com.example.repository.ProductRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(
    private val repository: ProductRepository
) {

    private var log = LogManager.getLogger(this::class.java)

    fun save(product: ProductDto): Mono<ProductDto> {
        this.log.info("Save : Product $product")
        return Mono.just(product)
            .map { this.converterDtoToEntity(it) }
            .flatMap { this.repository.save(it) }
            .map { this.converterEntityToDto(it) }
    }

    fun delete(id: String): Mono<Void> {
        return this
            .repository.deleteById(id)
    }

    fun find(productType: String?): Flux<ProductDto> {
        return (productType?.let { repository.findByProductType(it) }
            ?: repository.findAll())
            .map { converterEntityToDto(it) }
    }

    private fun converterEntityToDto(it: Product?): ProductDto {
        return ProductDto(
            id = it!!.id,
            name = it!!.name,
            productType = it!!.productType,
            description = it!!.description
        )
    }

    private fun converterDtoToEntity(it: ProductDto?): Product {
        return Product(
            name = it!!.name,
            productType = it!!.productType,
            description = it!!.description,
        )
    }

}