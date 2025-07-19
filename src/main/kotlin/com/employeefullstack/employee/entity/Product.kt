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
    
    val title: String? = null,
    val handle: String? = null,
    val vendor: String? = null,
    
    @Column(name = "product_type")
    val productType: String? = null,
    
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val variants: MutableList<Variant> = mutableListOf(),
    
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val images: MutableList<Image> = mutableListOf()
) 