package com.kotlin.productapi.repository

import com.kotlin.productapi.model.Product
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findProductByName(name: String) : Optional<Product>

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.stock = :stock WHERE p.id = :productId")
    fun updateStockById(productId: Long, stock: Int)
}