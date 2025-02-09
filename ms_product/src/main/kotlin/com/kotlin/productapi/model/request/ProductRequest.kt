package com.kotlin.productapi.model.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class ProductRequest(
    @field:NotBlank(message = "Name is required")
    val name: String,
    @field:Min(value = 1, message = "Price must be greater than 0")
    val price: Double,
    val description: String) {
}