package com.example.dto

import java.time.LocalDate

data class PersonalDto(
    var suid: String? = null,
    var birthDate: LocalDate? = null,
    var name: NameDto? = null,
)