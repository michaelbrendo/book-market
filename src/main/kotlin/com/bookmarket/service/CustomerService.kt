package com.bookmarket.service

import com.bookmarket.model.CustomerModel
import com.bookmarket.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAllCustomer(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }

    fun findCustomerById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun updateCustomer(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun deleteCustomer(id: Int) {
        val customer = findCustomerById(id)
        bookService.deleteByCustomer(customer)
        customerRepository.deleteById(id)
    }
}
