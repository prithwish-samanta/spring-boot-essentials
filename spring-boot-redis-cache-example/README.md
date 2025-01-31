# Spring Boot with Redis Cache Example

This project demonstrates how to integrate Redis caching into a Spring Boot application. Redis is an in-memory data structure store, used as a database, cache, and message broker. This example shows how to use Redis to cache data in a Spring Boot application to improve performance.

## Features
- Spring Boot application setup
- Integration with Redis for caching
- Basic CRUD operations with caching
- Configuration of Redis cache manager

## Requirements
- Java 21
- Maven 3.9.9
- Redis server

## Getting Started
Clone the repository
```
git clone https://github.com/prithwish-samanta/spring-boot-essentials.git
cd spring-boot-essentials/spring-boot-redis-cache-example
```
Build the project
```
mvn clean install
```
Run the application
```
mvn spring-boot:run
```

## Configuration
The application configuration is located in src/main/resources/application.properties. You can configure the Redis server connection details here.
```
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

## Usage
The application provides basic CRUD operations with caching. You can test the endpoints using tools like Postman or cURL.

### Endpoints
- GET /products - Retrieve all products
- GET /products/{id} - Retrieve an product by ID
- POST /products - Create a new product
- PUT /product/{id} - Update an existing product
- PATCH /products/{id}/stock - Update stock count of an product
- DELETE /products/{id} - Delete an product
- GET /products/search - Search Products by name
- GET /categories - Retrieve all categories
- POST /categories - Create a new category
- GET /categories/{id}/products - Retrieve products by category

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

