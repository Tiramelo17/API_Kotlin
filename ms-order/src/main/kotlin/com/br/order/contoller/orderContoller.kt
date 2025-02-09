package com.br.order.contoller

import com.br.order.model.request.CreateOrderRequest
import com.br.order.model.response.CreateOrderResponse
import com.br.order.model.response.FindOrderResponse
import com.br.order.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
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

    @Operation(summary = "Create a new order", description = "Returns a new order created")
    @PostMapping
    fun createNewOrder(@Valid @RequestBody request: CreateOrderRequest): CreateOrderResponse {
        return orderService.createNewOrder(request)
    }

    @Operation(summary = "Get all orders", description = "Returns a list of orders")
    @GetMapping
    fun findAllOrder(): MutableList<FindOrderResponse> {
        return orderService.findAllOrders()
    }

    @Operation(summary = "Get order by id", description = "Returns the change product")
    @GetMapping("{id}")
    fun findOrderById(@PathVariable id: Long): FindOrderResponse {
        return orderService.findOrderById(id)
    }

    @Operation(summary = "Delete order by id", description = "Returns only statusCode")
    @DeleteMapping("{id}")
    fun deleteOderById(@PathVariable id: Long) {
        orderService.deleteOrderById(id)
    }
}