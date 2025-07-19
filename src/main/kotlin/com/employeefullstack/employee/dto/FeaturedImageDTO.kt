package com.employeefullstack.employee.dto

data class FeaturedImageDTO(
    val id: Long? = null,
    val productId: Long? = null,
    val src: String? = null,
    val variantIds: List<Long>? = null
) 