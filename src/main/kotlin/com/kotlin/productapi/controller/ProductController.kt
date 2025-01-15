package com.kotlin.productapi.controller

import com.kotlin.productapi.model.request.ProductRequest
import com.kotlin.productapi.model.response.ProductReponse
import com.kotlin.productapi.service.ProductService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @GetMapping("/{productId}")
    fun findProductById(@PathVariable productId: Long): ProductReponse {
        return productService.findProductById(productId)
    }

    @GetMapping
    fun findAllProducts(): MutableList<ProductReponse> {
        return productService.findAllProduct()
    }

    @PostMapping
    fun createProduct(@RequestBody @Valid request: ProductRequest): ProductReponse {
        return productService.createNewProduct(request)
    }

    @PutMapping("/{productId}")
    fun updateProduct(@RequestBody @Valid request: ProductRequest, @PathVariable productId: Long): ProductReponse {
        return productService.updateProduct(productId ,request)
    }
}