package com.kotlin.productapi.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "ProductResponse", description = "Response body for getting a product")
data class ProductResponse(
    @Schema(description = "ID of the product", example = "1")
    val id: Long,

    @Schema(description = "Name of the product", example = "Product 1")
    val name : String,

    @Schema(description = "Price of the product", example = "100.0")
    val price: Double,

    @Schema(description = "Stock of the product", example = "10")
    val stock: Int,

    @Schema(description = "Description of the product", example = "This is a product")
    val description: String) {
}