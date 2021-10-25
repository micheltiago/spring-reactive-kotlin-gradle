package com.example.dto

import com.example.enums.CustomerRelationType

data class CustomerAssetDto(
    var suid: String? = null,
    var relationType: CustomerRelationType? = null
)