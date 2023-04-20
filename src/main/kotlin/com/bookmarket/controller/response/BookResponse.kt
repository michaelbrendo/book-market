package com.bookmarket.controller.response

import com.bookmarket.Enums.BookStatus
import com.bookmarket.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    val id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerModel? = null,

    var status: BookStatus? = null
)
