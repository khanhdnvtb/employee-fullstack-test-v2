package com.employeefullstack.employee.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
data class ProductDTO(

    val id: Long? = null,

    @field:NotBlank(message = "Title is required")
    val title: String,

    @field:NotBlank(message = "Handle is required")
    val handle: String,

    @field:NotBlank(message = "Vendor is required")
    val vendor: String,

    @field:NotBlank(message = "Product type is required")
    @JsonProperty("product_type")
    val productType: String,

    val variants: List<VariantDTO>? = null,

    @JsonProperty("images")
    val images: List<FeaturedImageDTO>? = null
)
