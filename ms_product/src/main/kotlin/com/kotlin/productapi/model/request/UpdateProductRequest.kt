package com.kotlin.productapi.model.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UpdateProductRequest", description = "Request body for updating a product")
data class UpdateProductRequest(
    @Schema(description = "Name of the product", example = "Product 1")
    var name: String?,

    @Schema(description = "Price of the product", example = "100.0")
    var price: Double?,

    @Schema(description = "Stock of the product", example = "10")
    var stock: Int?,

    @Schema(description = "Description of the product", example = "This is a product")
    var description: String?){
}
