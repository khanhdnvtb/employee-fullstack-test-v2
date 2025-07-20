package com.employeefullstack.employee.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class FeaturedImageDTO(
    val id: Long? = null,

    @field:NotNull(message = "productId is required")
    val productId: Long,

    @field:NotBlank(message = "src of image is required")
    val src: String,

    val variantIds: List<Long>? = null
)
