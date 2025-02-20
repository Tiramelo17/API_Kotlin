package com.br.order.grpc.client

import com.br.order.*
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

    fun getProducts(products: Map<Int,Int>) : ProductsResponse {
        return runCatching {
            val productList = products
                .map { (id, qtd) ->
                    ProductRequest.newBuilder()
                        .setId(id)
                        .setQtd(qtd)
                        .build()
                }
            client.getProducts(
                ProductsRequest.newBuilder()
                    .addAllProducts(productList)
                    .build()
            )
        }.getOrElse {
            throw it
        }
    }
}