package com.example.dto

import com.example.enums.AssetStatus

data class AssetResponseDto(
    var productType: String? = null,
    var organization: String? = null,
    var branch: String? = null,
    var status: AssetStatus? = null,
    var customers: List<CustomerAssetDto>? = null
)