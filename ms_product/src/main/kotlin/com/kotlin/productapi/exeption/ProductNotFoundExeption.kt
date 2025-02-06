package com.kotlin.productapi.exeption

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFoundExeption(productId: Long) : RuntimeException("Product not found for ProductId: $productId"){}