package com.bookmarket.model

import com.bookmarket.Enums.BookStatus
import jakarta.persistence.*
import org.hibernate.annotations.ManyToAny
import java.math.BigDecimal

@Entity(name = "customer")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
)