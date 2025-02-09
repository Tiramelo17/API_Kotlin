package com.kotlin.productapi.exeption

import java.time.LocalDate

data class GlobalErrorResponse (
    val status: Int,
    val message: String,
    val timestamp: LocalDate = LocalDate.now()){

}