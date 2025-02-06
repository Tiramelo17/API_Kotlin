package com.br.order.grpc.client.configuration

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfiguration {

    @Value("\${grpc.server.address}")
    private lateinit var address : String

    @Value("\${grpc.server.port}")
    private lateinit var port : String

    @Bean
    fun channel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(address, port.toInt())
            .usePlaintext()
            .build()
    }
}