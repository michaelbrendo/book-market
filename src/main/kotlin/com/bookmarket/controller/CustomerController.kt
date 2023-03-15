package com.bookmarket.controller

import com.bookmarket.controller.request.PostCustomerRequest
import com.bookmarket.controller.request.PutCustomerRequest
import com.bookmarket.extension.toCustomerModel
import com.bookmarket.model.CustomerModel
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAllCustomer(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getAllCustomer(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel {
        return customerService.findCustomerById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        customerService.createCustomer(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun putCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        customerService.updateCustomer(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int) {
        customerService.deleteCustomer(id)
    }

}