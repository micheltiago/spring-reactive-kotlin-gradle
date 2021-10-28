package com.example.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "product")
data class Product(
    @Id
    var id: String? = null,
    var name: String? = null,
    var productType: String? = null,
    var description: String? = null
)