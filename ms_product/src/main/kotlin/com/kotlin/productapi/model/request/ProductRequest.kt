package com.kotlin.productapi.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@Schema(name = "ProductRequest", description = "Request body for creating a new product")
data class ProductRequest(
    @Schema(description = "Name of the product", example = "Product 1")
    @field:NotBlank(message = "Name is required")
    val name: String,

    @Schema(description = "Price of the product", example = "100.0")
    @field:Min(value = 1, message = "Price must be greater than 0")
    val price: Double,

    @Schema(description = "Stock of the product", example = "10")
    @field:Min(value = 1, message = "Stock must be greater than 0")
    val stock: Int,

    @Schema(description = "Description of the product", example = "This is a product")
    val description: String) {
}