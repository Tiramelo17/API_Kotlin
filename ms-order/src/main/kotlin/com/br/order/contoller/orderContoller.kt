package com.br.order.contoller

import com.br.order.model.request.CreateOrderRequest
import com.br.order.model.response.CreateOrderResponse
import com.br.order.model.response.FindOrderResponse
import com.br.order.service.OrderService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class orderContoller {

    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping
    fun createNewOrder(@Valid @RequestBody request: CreateOrderRequest): CreateOrderResponse {
        return orderService.createNewOrder(request)
    }

    @GetMapping
    fun findAllOrder(): MutableList<FindOrderResponse> {
        return orderService.findAllOrders()
    }

    @GetMapping("{id}")
    fun findOrderById(@PathVariable id: Long): FindOrderResponse {
        return orderService.findOrderById(id)
    }
}