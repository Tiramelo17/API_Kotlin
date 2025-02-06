package com.br.order.grpc.client

import com.br.order.ProductRequest
import com.br.order.ProductResponse
import com.br.order.ProductServiceGrpc
import com.br.order.ProductsResponse
import io.grpc.ManagedChannel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductClientGrpc {

    @Autowired
    private lateinit var channel: ManagedChannel

    private val client: ProductServiceGrpc.ProductServiceBlockingStub by lazy {
        ProductServiceGrpc.newBlockingStub(channel)
    }

    fun getProducts(ids: MutableList<Int>) : ProductsResponse {
        return client.getProducts(ProductRequest.newBuilder()
            .addAllId(ids)
            .build()
        )
    }
}