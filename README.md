# Microservices Project: ms_order and ms_product

## Overview

This repository contains two microservices, `ms_order` and `ms_product`, which integrate using gRPC. The `ms_order` service acts as a gRPC client, while the `ms_product` service functions as a gRPC server. Both services also expose RESTful endpoints for CRUD operations. The primary goal of this project is to study and learn Kotlin by applying advanced concepts and implementing business rules.

## Services

### msOrder

The `ms_order` service is responsible for managing orders. It communicates with the `ms_product` service via gRPC to retrieve product information. The service also provides RESTful endpoints for creating, retrieving, and managing orders.

#### REST Endpoints

- **Create Order**
  - **POST** `/orders`
  - Request Body: `CreateOrderRequest`
  - Response: `CreateOrderResponse`

- **Get All Orders**
  - **GET** `/orders`
  - Response: List of `FindOrderResponse`

- **Get Order by ID**
  - **GET** `/orders/{id}`
  - Path Variable: `id`
  - Response: `FindOrderResponse`

### msProduct

The `ms_product` service manages product information and serves as a gRPC server. It provides product details to the `ms_order` service via gRPC and also exposes RESTful endpoints for CRUD operations on products.

#### REST Endpoints

- **Create Product**
  - **POST** `/products`
  - Request Body: `CreateProductRequest`
  - Response: `CreateProductResponse`

- **Get All Products**
  - **GET** `/products`
  - Response: List of `FindProductResponse`

- **Get Product by ID**
  - **GET** `/products/{id}`
  - Path Variable: `id`
  - Response: `FindProductResponse`

- **Update Product**
  - **PUT** `/products/{id}`
  - Path Variable: `id`
  - Request Body: `UpdateProductRequest`
  - Response: `UpdateProductResponse`


## Technologies Used

- **Kotlin**: The primary programming language used for both services.
- **Spring Boot**: Framework used to build the microservices.
- **gRPC**: Communication protocol used for inter-service communication.
- **Gradle**: Build tool used for managing dependencies and building the project.

## Learning Objectives

The main objective of this repository is to explore and learn Kotlin by implementing advanced concepts and business rules in a microservices architecture. This includes:

- Understanding Kotlin syntax and features.
- Implementing gRPC communication between microservices.
- Exposing RESTful endpoints for CRUD operations.
- Applying business rules and logic within the services.

## Getting Started

To get started with the project, clone the repository and follow the instructions in the respective service directories (`ms_order` and `ms_product`) to set up and run the services.

```sh
git clone <repository-url>
cd ms_Order
./gradlew build
cd ../ms_order
docker-compose -d
./gradlew build
```
## Conclusion
This project serves as a practical example of building and integrating microservices using Kotlin, Spring Boot, and gRPC. It demonstrates how to implement advanced concepts and business rules in a real-world scenario.

