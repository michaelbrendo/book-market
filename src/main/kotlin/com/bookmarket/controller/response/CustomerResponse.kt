package com.bookmarket.controller.response

import com.bookmarket.Enums.CustomerStatus

data class CustomerResponse (
    val id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)
