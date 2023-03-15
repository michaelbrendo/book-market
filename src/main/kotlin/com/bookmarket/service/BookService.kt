package com.bookmarket.service

import com.bookmarket.Enums.BookStatus
import com.bookmarket.model.BookModel
import com.bookmarket.model.CustomerModel
import com.bookmarket.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun createBook(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(): List<BookModel> {
        return bookRepository.findAll().toList()
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ACTIVE)
    }

    fun findBookById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun deleteBookById(id: Int) {
        val book = findBookById(id)

        book.status = BookStatus.DELETED
        updateBookById(book)
    }

    fun updateBookById(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)

        for (books in books) {
            if (books.status == BookStatus.ACTIVE) {
                books.status = BookStatus.DELETED
            }
        }
        bookRepository.saveAll(books)
    }
}
