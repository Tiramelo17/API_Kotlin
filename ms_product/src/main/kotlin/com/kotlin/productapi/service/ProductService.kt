package com.kotlin.productapi.service

import com.kotlin.productapi.exeption.ProductNotFoundExeption
import com.kotlin.productapi.model.Product
import com.kotlin.productapi.model.ProductMapper
import com.kotlin.productapi.model.request.ProductRequest
import com.kotlin.productapi.model.request.UpdateProductRequest
import com.kotlin.productapi.model.response.ProductReponse
import com.kotlin.productapi.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    @Autowired
    private lateinit var productRepository : ProductRepository

    private val mapper: ProductMapper = ProductMapper.INSTANCE

    fun createNewProduct(request : ProductRequest): ProductReponse {
       return productRepository.findProductByName(request.name)
           .map { product -> ProductReponse(id = product.id, name = product.name, price = product.price, description = product.description)}
           .orElseGet{
               val productCreate = productRepository.save(mapper.toProduct(request))
               mapper.toProductResponse(productCreate)
           }
    }

    fun updateProduct(productId: Long, request: UpdateProductRequest) : ProductReponse {
        return productRepository.findById(productId)
            .map { product ->
                product.name = request.name ?: product.name
                product.price = request.price ?: product.price
                product.description = request.description ?: product.description
                productRepository.save(product)
            }
            .map { product -> mapper.toProductResponse(product)}
            .orElseThrow{ProductNotFoundExeption(productId)}
    }

    fun findAllProduct(): MutableList<ProductReponse> {
        return productRepository.findAll()
            .map { produc -> ProductReponse(id = produc.id, name = produc.name, price = produc.price, description = produc.description)}
            .toMutableList()
    }

    fun findProductById(productId: Long): ProductReponse {
        return productRepository.findById(productId)
            .map { product -> mapper.toProductResponse(product)}
            .orElseThrow{ProductNotFoundExeption(productId)}
    }

    fun deleteProductById(productId: Long) {
        return this.findProductById(productId)
            .let { productRepository.deleteById(productId) }
    }
}