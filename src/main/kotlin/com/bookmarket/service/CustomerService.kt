package com.bookmarket.service

import com.bookmarket.model.CustomerModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAllCustomer(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun getCustomer(id: UUID): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun postCustomer(customer: CustomerModel) {
        customers.add(customer)
    }

    fun putCustomer(customer: CustomerModel) {
        customers.filter { it.id == customer.id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun deleteCustomer(id: UUID) {
        customers.removeIf { it.id == id }
    }

}