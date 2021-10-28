package com.example.controller

import com.example.dto.ProductDto
import com.example.service.ProductService
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductController(
    private val service: ProductService
) {
    private var log = LogManager.getLogger(this::class.java)

    @PostMapping
    fun cadastrar(@Valid @RequestBody dto: ProductDto): Mono<ResponseEntity<ProductDto>> {
        this.log.info("Post : Product $dto")
        return this
            .service.save(dto)
            .map { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @GetMapping("/types")
    fun find(@RequestParam("productType", required = false) productType: String?): Flux<ProductDto> {
        return this
            .service.find(productType)
    }

    @DeleteMapping("/{id}")
    fun edit(@PathVariable("id") id: String): Mono<ResponseEntity<Void>> {
        return this
            .service.delete(id)
            .map { ResponseEntity.status(HttpStatus.OK).body(it) }
    }

}