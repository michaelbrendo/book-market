package com.bookmarket.extension

import com.bookmarket.Enums.BookStatus
import com.bookmarket.Enums.CustomerStatus
import com.bookmarket.controller.request.PostBookRequest
import com.bookmarket.controller.request.PostCustomerRequest
import com.bookmarket.controller.request.PutBookRequest
import com.bookmarket.controller.request.PutCustomerRequest
import com.bookmarket.controller.response.BookResponse
import com.bookmarket.controller.response.CustomerResponse
import com.bookmarket.model.BookModel
import com.bookmarket.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ACTIVE)
}

fun PutCustomerRequest.toCustomerModel(previousCustomer: CustomerModel): CustomerModel {
    return CustomerModel(id = previousCustomer.id, name = this.name, email = this.email, previousCustomer.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ACTIVE,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousBook: BookModel): BookModel {
    return BookModel(
        id = previousBook.id,
        name = this.name ?: previousBook.name,
        price = this.price ?: previousBook.price,
        status = previousBook.status,
        customer = previousBook.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(id = this.id, name = this.name, email = this.email, status = this.status)
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}