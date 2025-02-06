package com.br.order.model.response

data class FindOrderResponse(
    val id: Long,
    val products: MutableMap<Int,Int>,
    val price: Double
)
