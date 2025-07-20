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
import jakarta.validation.Valid
import org.springframework.validation.BindingResult

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

        var newProduct = ProductDTO(
            title = "",
            handle = "",
            vendor = "",
            productType = ""
        )

        model.addAttribute("product", newProduct)
        model.addAttribute("productTypes", ProductType.values())
        return "fragments/product-form"
    }

    @PostMapping("/products")
    fun create(
        @Valid @ModelAttribute productDTO: ProductDTO,
        bindingResult: BindingResult,
        model: Model,
    ): String {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.allErrors.map { it.defaultMessage })
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
