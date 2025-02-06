package com.kotlin.productapi.service

import com.br.order.ProductRequest
import com.br.order.ProductServiceGrpc.ProductServiceImplBase
import com.br.order.ProductsResponse
import com.kotlin.productapi.model.ProductMapper
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Autowired

@GrpcService
class ProductServiceGrpc : ProductServiceImplBase(){

    @Autowired
    private lateinit var productService: ProductService

    private val mapper: ProductMapper = ProductMapper.INSTANCE

    override fun getProducts(request: ProductRequest, responseObserver: StreamObserver<ProductsResponse>){
        var products = request
            .idList
            .map { id -> productService.findProductById(id.toLong()) }
            .map { product -> mapper.toProductResponse(product)}
            .toMutableList()

        ProductsResponse.newBuilder()
            .addAllProducts(products)
            .build()
            .let(responseObserver::onNext)

        responseObserver.onCompleted()
    }
}