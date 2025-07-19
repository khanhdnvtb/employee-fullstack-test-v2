package com.employeefullstack.employee.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class VariantDTO(
    val id: Long? = null,
    val title: String? = null,
    val option1: String? = null,
    val option2: String? = null,
    val option3: String? = null,
    val sku: String? = null,
    val requiresShipping: Boolean? = null,
    val taxable: Boolean? = null,
    @JsonProperty("featured_image")
    val featuredImage: FeaturedImageDTO? = null,
    val available: Boolean? = null,
    val price: String? = null,
    val grams: Int? = null,
    val compareAtPrice: String? = null,
    val productId: Long? = null
) 