package com.kotlin.productapi.grpc.configuration

import com.kotlin.productapi.service.ProductServiceGrpc
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfiguration(private val productServiceGrpc: ProductServiceGrpc) {

    @Bean
    fun grpcServer(): Server {
        return ServerBuilder
            .forPort(9090)
            .addService(ProtoReflectionService.newInstance())
            .addService(productServiceGrpc)
            .build()
    }

    @PostConstruct
    fun startServer() {
        grpcServer().start()
        println("Grpc Server started, listening on 9090")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                grpcServer().shutdown()
                println("*** server shut down")
            }
        )
    }
}