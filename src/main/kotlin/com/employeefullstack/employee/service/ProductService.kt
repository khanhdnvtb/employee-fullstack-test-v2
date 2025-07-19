package com.employeefullstack.employee.service

import com.employeefullstack.employee.dto.*
import com.employeefullstack.employee.entity.Product
import com.employeefullstack.employee.entity.Variant
import com.employeefullstack.employee.entity.Image
import com.employeefullstack.employee.repository.ProductRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    
    fun findAll(): List<Product> {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
    }
    
    @Transactional
    fun save(product: Product): Product {
        product.variants.forEach { variant ->
            variant.product = product
        }
        product.images.forEach { image ->
            image.product = product
        }
        return productRepository.save(product)
    }
    
    fun fromDTO(productDTO: ProductDTO): Product {
        val product = Product(
            id = productDTO.id,
            title = productDTO.title,
            handle = productDTO.handle,
            vendor = productDTO.vendor,
            productType = productDTO.productType
        )
        
        // Variants
        productDTO.variants?.let { variantDTOs ->
            for (vdto in variantDTOs) {
                val v = Variant(
                    id = vdto.id,
                    title = vdto.title,
                    option1 = vdto.option1,
                    option2 = vdto.option2,
                    option3 = vdto.option3,
                    sku = vdto.sku,
                    requiresShipping = vdto.requiresShipping == true,
                    taxable = vdto.taxable == true,
                    available = vdto.available == true,
                    price = vdto.price?.takeIf { it.isNotEmpty() }?.let { BigDecimal(it) },
                    compareAtPrice = vdto.compareAtPrice?.takeIf { it.isNotEmpty() }?.let { BigDecimal(it) },
                    grams = vdto.grams,
                    product = product
                )
                product.variants.add(v)
            }
        }
        
        // Images
        productDTO.images?.let { imageDTOs ->
            for (idto in imageDTOs) {
                val img = Image(
                    id = idto.id,
                    product = product,
                    src = idto.src
                )
                product.images.add(img)
            }
        }
        
        return product
    }
} 