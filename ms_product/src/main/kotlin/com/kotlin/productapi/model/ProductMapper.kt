package com.kotlin.productapi.model

import com.kotlin.productapi.model.request.ProductRequest
import com.kotlin.productapi.model.response.ProductResponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ProductMapper {
    companion object {
        val INSTANCE: ProductMapper = Mappers.getMapper(ProductMapper::class.java)
    }

    fun toProductResponse(product: ProductResponse) : com.br.order.ProductResponse
    fun toProductResponse(product: Product) : ProductResponse
    fun toProduct(product: ProductRequest) : Product
}