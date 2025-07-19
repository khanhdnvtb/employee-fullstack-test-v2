package com.employeefullstack.employee.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductDTO(
    val id: Long? = null,
    val title: String? = null,
    val handle: String? = null,
    val vendor: String? = null,
    @JsonProperty("product_type")
    val productType: String? = null,
    val variants: List<VariantDTO>? = null,
    @JsonProperty("images")
    val images: List<FeaturedImageDTO>? = null
) 