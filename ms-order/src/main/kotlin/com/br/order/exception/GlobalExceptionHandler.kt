package com.br.order.exception

import io.grpc.StatusRuntimeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(StatusRuntimeException::class)
    fun handleStatusRuntimeException(ex: StatusRuntimeException, request: WebRequest): ResponseEntity<GlobalErrorResponse> {
        val errorResponse = GlobalErrorResponse(
            status = ex.status.code.value(),
            message = ex.message ?: ""
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException, request: WebRequest): ResponseEntity<GlobalErrorResponse> {
        val errorResponse = GlobalErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: ""
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
}