package com.employeefullstack.employee.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "variant")
data class Variant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product,

    val title: String? = null,
    val option1: String? = null,
    val option2: String? = null,
    val option3: String? = null,
    val sku: String? = null,
    val requiresShipping: Boolean? = null,
    val taxable: Boolean? = null,
    val available: Boolean? = null,
    val price: BigDecimal? = null,

    @Column(name = "compare_at_price")
    val compareAtPrice: BigDecimal? = null,

    val grams: Int? = null
)
