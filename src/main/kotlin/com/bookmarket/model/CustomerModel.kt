package com.bookmarket.model

import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String
)