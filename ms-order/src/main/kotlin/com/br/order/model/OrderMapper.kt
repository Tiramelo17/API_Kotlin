package com.br.order.model

import com.br.order.model.response.CreateOrderResponse
import com.br.order.model.response.FindOrderResponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring")
interface OrderMapper {
    companion object {
        val INSTANCE: OrderMapper = Mappers.getMapper(OrderMapper::class.java)
    }

    fun toCreateOrderResponse(order: Order) : CreateOrderResponse
    fun toFindOrderResponse(order: Order) : FindOrderResponse
}