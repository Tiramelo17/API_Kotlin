package com.br.order.service

import com.br.order.grpc.client.ProductClientGrpc
import com.br.order.model.Order
import com.br.order.model.OrderMapper
import com.br.order.model.request.CreateOrderRequest
import com.br.order.model.response.CreateOrderResponse
import com.br.order.model.response.FindOrderResponse
import com.br.order.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var productClientGrpc: ProductClientGrpc

    private val mapper : OrderMapper = OrderMapper.INSTANCE

    fun createNewOrder(request: CreateOrderRequest): CreateOrderResponse{
        return Order()
            .apply {
                this.products = request.products
                this.price = calcTotalPrice(request.products)
            }
            .let { orderRepository.save(it) }
            .let { mapper.toCreateOrderResponse(it) }
    }

    fun findAllOrders(): MutableList<FindOrderResponse>{
        return orderRepository.findAll()
            .map { order -> mapper.toFindOrderResponse(order) }
            .toMutableList()
    }
    fun findOrderById(id: Long): FindOrderResponse{
        return orderRepository.findById(id)
            .orElseThrow() { NoSuchElementException("Order not found with id: $id") }
            .let { mapper.toFindOrderResponse(it) }
    }

    fun deleteOrderById(id: Long){
        orderRepository.deleteOrderBy(id)
            .orElseThrow() { NoSuchElementException("Order not found with id: $id") }
    }

    fun calcTotalPrice(products: Map<Int,Int>) : Double {
        return productClientGrpc.getProducts(products.keys.toMutableList())
            .let { it.productsList.map { product -> product.price * products[product.id]!! } }
            .reduce{acc, price -> acc + price}
    }
}