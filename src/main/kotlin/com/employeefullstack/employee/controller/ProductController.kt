package com.employeefullstack.employee.controller

import com.employeefullstack.employee.dto.*
import com.employeefullstack.employee.entity.Product
import com.employeefullstack.employee.service.ProductService
import com.employeefullstack.employee.constant.ProductType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import jakarta.servlet.http.HttpServletRequest

@Controller
class ProductController(
    private val productService: ProductService
) {
    
    @GetMapping("/")
    fun index(): String {
        return "index"
    }
    
    @GetMapping("/products")
    fun loadProducts(model: Model): String {
        model.addAttribute("products", productService.findAll())
        return "fragments/product-table"
    }
    
    @GetMapping("/products/new-form")
    fun newForm(model: Model): String {
        model.addAttribute("product", Product())
        model.addAttribute("productTypes", ProductType.values())
        return "fragments/product-form"
    }
    
    @PostMapping("/products")
    fun create(
        @ModelAttribute productDTO: ProductDTO,
        model: Model,
        request: HttpServletRequest
    ): String {
        val errors = mutableListOf<String>()
        
        if (productDTO.title.isNullOrBlank()) {
            errors.add("Title is required")
        }
        if (productDTO.handle.isNullOrBlank()) {
            errors.add("Handle is required")
        }
        if (productDTO.vendor.isNullOrBlank()) {
            errors.add("Vendor is required")
        }
        if (productDTO.productType.isNullOrBlank()) {
            errors.add("Product type is required")
        }
        
        if (errors.isNotEmpty()) {
            model.addAttribute("errors", errors)
            model.addAttribute("product", productDTO)
            model.addAttribute("productTypes", ProductType.values())
            return "fragments/product-form"
        }
        
        val product = productService.fromDTO(productDTO)
        productService.save(product)
        model.addAttribute("products", productService.findAll())
        return "fragments/product-table"
    }
} 