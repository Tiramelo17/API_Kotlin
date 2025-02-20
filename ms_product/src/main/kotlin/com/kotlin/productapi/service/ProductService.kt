package com.kotlin.productapi.service

import com.kotlin.productapi.exeption.ProductNotFoundExeption
import com.kotlin.productapi.model.Product
import com.kotlin.productapi.model.ProductMapper
import com.kotlin.productapi.model.request.ProductRequest
import com.kotlin.productapi.model.request.UpdateProductRequest
import com.kotlin.productapi.model.response.ProductResponse
import com.kotlin.productapi.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    @Autowired
    private lateinit var productRepository : ProductRepository

    private val mapper: ProductMapper = ProductMapper.INSTANCE

    fun createNewProduct(request : ProductRequest): ProductResponse {
       return productRepository.findProductByName(request.name)
           .map { product -> ProductResponse(id = product.id, name = product.name, price = product.price, stock = product.stock, description = product.description)}
           .orElseGet{
               val productCreate = productRepository.save(mapper.toProduct(request))
               mapper.toProductResponse(productCreate)
           }
    }

    fun updateProduct(productId: Long, request: UpdateProductRequest) : ProductResponse {
        return productRepository.findById(productId)
            .map { product ->
                product.name = request.name ?: product.name
                product.price = request.price ?: product.price
                product.stock = request.stock ?: product.stock
                product.description = request.description ?: product.description
                productRepository.save(product)
            }
            .map { product -> mapper.toProductResponse(product)}
            .orElseThrow{ProductNotFoundExeption(productId)}
    }

    fun updateStock(productId: Long, quantity: Int) {
        productRepository.updateStockById(productId, quantity)
    }

    fun findAllProduct(): MutableList<ProductResponse> {
        return productRepository.findAll()
            .map { product -> ProductResponse(id = product.id, name = product.name, price = product.price, stock = product.stock, description = product.description)}
            .toMutableList()
    }

    fun findProductById(productId: Long): ProductResponse {
        return productRepository.findById(productId)
            .map { product -> mapper.toProductResponse(product)}
            .orElseThrow{ProductNotFoundExeption(productId)}
    }

    fun deleteProductById(productId: Long) {
        return this.findProductById(productId)
            .let { productRepository.deleteById(productId) }
    }

    val thereIsEnoughStock: (Int, Int) -> Boolean = {stock, order -> stock > order }
}