package com.kotlin.productapi.controller

import com.kotlin.productapi.exeption.ProductNotFoundExeption
import com.kotlin.productapi.model.request.ProductRequest
import com.kotlin.productapi.model.request.UpdateProductRequest
import com.kotlin.productapi.model.response.ProductReponse
import com.kotlin.productapi.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
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

    @Operation(summary = "Get one product", description = "Returns a product by id")
    @GetMapping("/{productId}")
    fun findProductById(@PathVariable productId: Long): ProductReponse {
        return productService.findProductById(productId) ?: throw ProductNotFoundExeption(productId)
    }

    @Operation(summary = "Get all products", description = "Returns returns a list of products")
    @GetMapping
    fun findAllProducts(): MutableList<ProductReponse> {
        return productService.findAllProduct()
    }

    @Operation(summary = "Create a new product", description = "Returns a new product created")
    @PostMapping
    fun createProduct(@RequestBody @Valid request: ProductRequest): ProductReponse {
        return productService.createNewProduct(request)
    }

    @Operation(summary = "Update a product", description = "Returns the product change")
    @PutMapping("/{productId}")
    fun updateProduct(@RequestBody request: UpdateProductRequest, @PathVariable productId: Long): ProductReponse {
        return productService.updateProduct(productId ,request)
    }

    @Operation(summary = "Delete a product", description = "Returns only statusCode")
    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long) {
        productService.deleteProductById(productId)
    }
}