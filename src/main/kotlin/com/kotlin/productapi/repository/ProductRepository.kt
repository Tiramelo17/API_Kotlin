package com.kotlin.productapi.repository

import com.kotlin.productapi.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findProductByName(name: String) : Optional<Product>
}