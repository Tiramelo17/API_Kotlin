package com.br.order.model.response

data class CreateOrderResponse(
    val id: Long,
    val products: MutableMap<Int,Int>,
    val price: Double
)
