package com.kotlin.productapi.service

import com.br.order.ProductRequest
import com.br.order.ProductServiceGrpc.ProductServiceImplBase
import com.br.order.ProductsRequest
import com.br.order.ProductsResponse
import com.kotlin.productapi.exeption.InsufficientStock
import com.kotlin.productapi.exeption.ProductNotFoundExeption
import com.kotlin.productapi.model.ProductMapper
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@GrpcService
class ProductServiceGrpc : ProductServiceImplBase(){

    @Autowired
    private lateinit var productService: ProductService

    private val mapper: ProductMapper = ProductMapper.INSTANCE

    override fun getProducts(request: ProductsRequest, responseObserver: StreamObserver<ProductsResponse>){
        runCatching {
            request
                .productsList
                .map { productRequest ->
                    Optional.of(productService.findProductById(productRequest.id.toLong()))
                        .filter { product -> productService.thereIsEnoughStock(product.stock, productRequest.qtd) }
                        .let { product ->
                            if(product.isPresent()) {
                                productService.updateStock(product.get().id, product.get().stock - productRequest.qtd)
                            }
                            product
                        }
                        .orElseThrow { InsufficientStock() }
                }

                .map { product -> mapper.toProductResponse(product)}
                .toMutableList()
        }.onSuccess {
            ProductsResponse.newBuilder()
            .addAllProducts(it)
            .build()
            .let(responseObserver::onNext)

            responseObserver.onCompleted()
        }.onFailure { ex ->
            val status = when(ex) {
                is ProductNotFoundExeption -> Status.NOT_FOUND.withDescription(ex.message)
                is InsufficientStock -> Status.FAILED_PRECONDITION.withDescription(ex.message)
                else -> Status.UNKNOWN.withDescription(ex.message)
            }
            responseObserver.onError(StatusRuntimeException(status))
        }
    }


}