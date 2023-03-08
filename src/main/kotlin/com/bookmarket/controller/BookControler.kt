package com.bookmarket.controller

import com.bookmarket.controller.request.PostBookRequest
import com.bookmarket.extension.toBookModel
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookControler(
    val customerService: CustomerService,
    val bookService: BookService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody request: PostBookRequest) {
        val customer = customerService.getCustomerById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }


}