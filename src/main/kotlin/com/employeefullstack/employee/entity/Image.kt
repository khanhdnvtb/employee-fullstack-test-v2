package com.employeefullstack.employee.entity

import jakarta.persistence.*

@Entity
@Table(name = "image")
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product?,

    @Column(columnDefinition = "TEXT", nullable = false)
    val src: String
)
