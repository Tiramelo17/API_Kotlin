package com.kotlin.productapi.exeption

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExeptionHandler {

    @ExceptionHandler(ProductNotFoundExeption::class)
    fun handleProductNotFoundExeption(ex: ProductNotFoundExeption, request: WebRequest): ResponseEntity<GlobalErrorResponse> {
        val errorResponse = GlobalErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: ""
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
}