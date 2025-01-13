package dev.prithwish.spring_boot_redis_cache_example.service;

import dev.prithwish.spring_boot_redis_cache_example.entity.Product;
import dev.prithwish.spring_boot_redis_cache_example.payload.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto saveProduct(ProductDto dto);

    ProductDto getProductById(UUID productId);

    ProductDto updateProduct(UUID productId, ProductDto dto);

    void deleteProduct(UUID productId);

    void updateInventory(UUID productId, Integer inventory);

    List<ProductDto> searchProduct(String keyword);
}
