package com.bookmarket.controller

import com.bookmarket.controller.request.PostBookRequest
import com.bookmarket.controller.request.PutBookRequest
import com.bookmarket.controller.response.BookResponse
import com.bookmarket.extension.toBookModel
import com.bookmarket.extension.toResponse
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
        val customer = customerService.findCustomerById(request.customerId)
        bookService.createBook(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(): List<BookResponse> {
        return bookService.findAll().map { it.toResponse() }
    }

    @GetMapping("/actives")
    fun findActivesBooks(): List<BookResponse> = bookService.findActives().map { it.toResponse() }

    @GetMapping("/{id}")
    fun findBookById(@PathVariable id: Int): BookResponse = bookService.findBookById(id).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBookById(@PathVariable id: Int) {
        return bookService.deleteBookById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateBookById(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findBookById(id)
        bookService.updateBookById(book.toBookModel(bookSaved))
    }
}
