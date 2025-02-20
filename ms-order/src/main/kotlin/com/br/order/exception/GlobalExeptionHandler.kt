package com.br.order.exception

import io.grpc.StatusRuntimeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExeptionHandler {

    @ExceptionHandler(StatusRuntimeException::class)
    fun handleNoSuchElementException(ex: StatusRuntimeException, request: WebRequest): ResponseEntity<GlobalErrorResponse> {
        val errorResponse = GlobalErrorResponse(
            status = ex.status.code.value(),
            message = ex.message ?: ""
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}