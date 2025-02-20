package com.br.order.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "FindOrderResponse", description = "Response for find order")
data class FindOrderResponse(
    @Schema(description = "Order ID", example = "1")
    val id: Long,
    @Schema(description = "Map of product ID and quantity", example = "{\"1\": 2, \"2\": 1}")
    val products: MutableMap<Int,Int>,
    @Schema(description = "Total price of the order", example = "100.0")
    val price: Double
)
