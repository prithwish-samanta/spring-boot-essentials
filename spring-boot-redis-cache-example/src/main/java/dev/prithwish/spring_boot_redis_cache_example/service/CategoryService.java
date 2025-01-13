package dev.prithwish.spring_boot_redis_cache_example.service;

import dev.prithwish.spring_boot_redis_cache_example.entity.Category;
import dev.prithwish.spring_boot_redis_cache_example.entity.Product;
import dev.prithwish.spring_boot_redis_cache_example.payload.CategoryDto;
import dev.prithwish.spring_boot_redis_cache_example.payload.ProductDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto dto);

    List<CategoryDto> getAllCategory();

    List<ProductDto> getProductsByCategory(UUID categoryId);
}
