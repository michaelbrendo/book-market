package com.bookmarket.model

import java.util.UUID

data class CustomerModel(
    val id: UUID? = UUID.randomUUID(),
    var name: String,
    var email: String
)