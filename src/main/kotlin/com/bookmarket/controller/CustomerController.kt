package com.bookmarket.controller

import com.bookmarket.controller.request.PostCustomerRequest
import com.bookmarket.controller.request.PutCustomerRequest
import com.bookmarket.controller.response.CustomerResponse
import com.bookmarket.extension.toCustomerModel
import com.bookmarket.extension.toResponse
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAllCustomer(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAllCustomer(name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findCustomerById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        customerService.createCustomer(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        val customerSaved = customerService.findCustomerById(id)
        customerService.updateCustomer(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int) {
        customerService.deleteCustomer(id)
    }
}
