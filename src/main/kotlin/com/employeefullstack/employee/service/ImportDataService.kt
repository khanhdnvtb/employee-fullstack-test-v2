package com.employeefullstack.employee.service

import com.employeefullstack.employee.dto.*
import com.employeefullstack.employee.entity.Image
import com.employeefullstack.employee.entity.Product
import com.employeefullstack.employee.entity.Variant
import com.employeefullstack.employee.repository.ImageRepository
import com.employeefullstack.employee.repository.ProductRepository
import com.employeefullstack.employee.repository.VariantRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@Service
class ImportDataService(
    private val productRepository: ProductRepository,
    private val variantRepository: VariantRepository,
    private val imageRepository: ImageRepository
) {
    
    private val restTemplate = RestTemplate()
    
    companion object {
        private const val DATA_URL = "https://famme.no/products.json"
    }
    
    @Scheduled(
        initialDelayString = "\${import.initial-delay}",
        fixedDelayString = "\${import.fixed-delay}"
    )
    @Transactional
    fun fetchAndSave() {
        val dto = restTemplate.getForObject(DATA_URL, ShopProductDTO::class.java)
        if (dto?.products == null) return
        
        val products = dto.products
        val limit = minOf(products.size, 50)
        val limited = products.take(limit)
        
        for (productDTO in limited) {
            val product = Product(
                title = productDTO.title,
                handle = productDTO.handle,
                vendor = productDTO.vendor,
                productType = productDTO.productType
            )
            productRepository.save(product)
            
            productDTO.images?.let { images ->
                for (imgDto in images) {
                    val img = Image(
                        product = product,
                        src = imgDto.src
                    )
                    imageRepository.save(img)
                }
            } ?: run {
                println("Product: ${productDTO.title} has NO images")
            }
            
            productDTO.variants?.let { variants ->
                for (variantDTO in variants) {
                    val variant = Variant(
                        product = product,
                        title = variantDTO.title,
                        option1 = variantDTO.option1,
                        option2 = variantDTO.option2,
                        option3 = variantDTO.option3,
                        sku = variantDTO.sku,
                        requiresShipping = variantDTO.requiresShipping == true,
                        taxable = variantDTO.taxable == true,
                        available = variantDTO.available == true,
                        price = variantDTO.price?.takeIf { it.isNotEmpty() }?.let { BigDecimal(it) },
                        compareAtPrice = variantDTO.compareAtPrice?.takeIf { it.isNotEmpty() }?.let { BigDecimal(it) },
                        grams = variantDTO.grams
                    )
                    variantRepository.save(variant)
                }
            }
        }
    }
} 