package dev.prithwish.spring_boot_redis_cache_example.controller;

import dev.prithwish.spring_boot_redis_cache_example.entity.Product;
import dev.prithwish.spring_boot_redis_cache_example.payload.CategoryDto;
import dev.prithwish.spring_boot_redis_cache_example.payload.ProductDto;
import dev.prithwish.spring_boot_redis_cache_example.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
        CategoryDto savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.created(URI.create("/api/v1/categories/" + savedCategory.getId())).body(savedCategory);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDto>> getCategoryProducts(@PathVariable UUID id) {
        List<ProductDto> products = categoryService.getProductsByCategory(id);
        return ResponseEntity.ok(products);
    }
}
