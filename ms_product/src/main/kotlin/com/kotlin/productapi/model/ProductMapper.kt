package com.kotlin.productapi.model

import com.br.order.ProductResponse
import com.kotlin.productapi.model.request.ProductRequest
import com.kotlin.productapi.model.response.ProductReponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ProductMapper {
    companion object {
        val INSTANCE: ProductMapper = Mappers.getMapper(ProductMapper::class.java)
    }

    fun toProductResponse(product: ProductReponse) : ProductResponse
    fun toProductResponse(product: Product) : ProductReponse
    fun toProduct(product: ProductRequest) : Product
}