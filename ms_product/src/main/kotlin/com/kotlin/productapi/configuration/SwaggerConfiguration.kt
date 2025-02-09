package com.kotlin.productapi.configuration

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun custonOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                io.swagger.v3.oas.models.info.Info()
                    .title("Product API")
                    .version("1.0.0")
                    .description("This is a demo API documented with Swagger and SpringDoc.")
            )
    }
}