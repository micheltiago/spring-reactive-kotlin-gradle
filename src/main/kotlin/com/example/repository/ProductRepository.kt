package com.example.repository

import com.example.entities.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ProductRepository: ReactiveMongoRepository<Product,String>{

    fun findByProductType(productType: String) : Flux<Product>

}