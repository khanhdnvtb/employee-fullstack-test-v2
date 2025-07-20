package com.employeefullstack.employee.entity

import com.employeefullstack.employee.constant.ProductType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val handle: String,

    @Column(nullable = false)
    val vendor: String,

    @Column(name = "product_type", nullable = false)
    val productType: String,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val variants: MutableList<Variant> = mutableListOf(),

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val images: MutableList<Image> = mutableListOf()
)
