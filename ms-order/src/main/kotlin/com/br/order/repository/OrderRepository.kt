package com.br.order.repository

import com.br.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
}