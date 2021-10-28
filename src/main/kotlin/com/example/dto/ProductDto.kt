package com.example.dto

import javax.validation.constraints.NotEmpty

data class ProductDto(
    var id: String? = null,
    @get:NotEmpty
    var name: String? = null,
    @get:NotEmpty
    var productType: String? = null,
    var description: String? = null
)