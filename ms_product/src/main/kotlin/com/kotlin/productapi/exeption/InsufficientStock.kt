package com.kotlin.productapi.exeption

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class InsufficientStock(): RuntimeException("Insufficient Stock"){
}