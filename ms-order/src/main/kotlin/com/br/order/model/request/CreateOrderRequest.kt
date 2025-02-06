package com.br.order.model.request

import org.jetbrains.annotations.NotNull

data class CreateOrderRequest(
    @field:NotNull(value = "productsId cannot be null")
    val products: Map<Int, Int>
)
