package com.br.order.model.request

import io.swagger.v3.oas.annotations.media.Schema
import org.jetbrains.annotations.NotNull

@Schema(name = "CreateOrderRequest", description = "Request object for created order")
data class CreateOrderRequest(
    @Schema(description = "Map of product IDs and their quantities", example = "{1: 2, 2: 1}")
    @field:NotNull(value = "productsId cannot be null")
    val products: Map<Int, Int>
)
