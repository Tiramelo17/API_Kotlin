package com.br.order.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "CreateOrderResponse", description = "Response object for created order")
data class CreateOrderResponse(
    @Schema(description = "Order ID", example = "1")
    val id: Long,
    @Schema(description = "Map of product IDs and their quantities", example = "{\"1\": 2, \"2\": 1}")
    val products: MutableMap<Int,Int>,
    @Schema(description = "Total price of the order", example = "100.0")
    val price: Double
)
