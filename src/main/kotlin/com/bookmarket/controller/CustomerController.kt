package com.bookmarket.controller

import com.bookmarket.controller.request.PostCustomerRequest
import com.bookmarket.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("customers")
class CustomerController {
    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getCustomer(): MutableList<CustomerModel> {
        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer: PostCustomerRequest){
        val id = UUID.randomUUID()
        customers.add(CustomerModel("$id", customer.name, customer.email))
    }

}