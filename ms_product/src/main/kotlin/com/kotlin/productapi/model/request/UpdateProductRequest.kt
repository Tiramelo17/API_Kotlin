package com.kotlin.productapi.model.request

data class UpdateProductRequest(
    var name: String?,
    var price: Double?,
    var description: String?){
}
