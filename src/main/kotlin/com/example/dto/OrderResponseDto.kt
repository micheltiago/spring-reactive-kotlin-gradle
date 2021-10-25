package com.example.dto

data class OrderResponseDto(
    var id: String? = null,
    var customerId: String? = null,
    var organization: String? = null,
    var agency: String? = null,
    var items: List<ItemDto>? = null
)