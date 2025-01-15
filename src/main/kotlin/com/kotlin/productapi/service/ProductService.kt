package com.kotlin.productapi.service

import com.kotlin.productapi.exeption.ProductNotFoundExeption
import com.kotlin.productapi.model.Product
import com.kotlin.productapi.model.request.ProductRequest
import com.kotlin.productapi.model.response.ProductReponse
import com.kotlin.productapi.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    @Autowired
    private lateinit var productRepository : ProductRepository

    fun createNewProduct(request : ProductRequest): ProductReponse {
       return productRepository.findProductByName(request.name)
           .map { product -> ProductReponse(id = product.id, name = product.name, price = product.price, description = product.description)}
           .orElseGet{
               val productCreate = productRepository.save(Product(name = request.name, price = request.price, description = request.description))
               ProductReponse(id = productCreate.id, name = productCreate.name, price = productCreate.price, description = productCreate.description)
           }
    }

    fun updateProduct(productId: Long, request: ProductRequest) : ProductReponse {
        return productRepository.findById(productId)
            .map { product ->
                product.name = request.name
                product.price = request.price
                product.description = request.description
                productRepository.save(product)
            }
            .map { product -> ProductReponse(id = product.id, name = product.name, price = product.price, description = product.description)}
            .orElseThrow{ProductNotFoundExeption(productId)}
    }

    fun findAllProduct(): MutableList<ProductReponse> {
        return productRepository.findAll()
            .map { produc -> ProductReponse(id = produc.id, name = produc.name, price = produc.price, description = produc.description)}
            .toMutableList()
    }

    fun findProductById(productId: Long): ProductReponse {
        return productRepository.findById(productId)
            .map { product -> ProductReponse(id = product.id, name = product.name, price = product.price, description = product.description)}
            .orElseThrow{ProductNotFoundExeption(productId)}
    }
}