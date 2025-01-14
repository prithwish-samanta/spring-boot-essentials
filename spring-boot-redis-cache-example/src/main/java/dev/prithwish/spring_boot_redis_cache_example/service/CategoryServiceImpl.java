package dev.prithwish.spring_boot_redis_cache_example.service;

import dev.prithwish.spring_boot_redis_cache_example.entity.Category;
import dev.prithwish.spring_boot_redis_cache_example.entity.Product;
import dev.prithwish.spring_boot_redis_cache_example.exception.ItemNotFoundException;
import dev.prithwish.spring_boot_redis_cache_example.mapper.CategoryMapper;
import dev.prithwish.spring_boot_redis_cache_example.mapper.ProductMapper;
import dev.prithwish.spring_boot_redis_cache_example.payload.CategoryDto;
import dev.prithwish.spring_boot_redis_cache_example.payload.ProductDto;
import dev.prithwish.spring_boot_redis_cache_example.repository.CategoryRepository;
import dev.prithwish.spring_boot_redis_cache_example.repository.ProductRepository;
import dev.prithwish.spring_boot_redis_cache_example.utils.Constants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @CacheEvict(value = Constants.CATEGORY_LIST_KEY_PREFIX, allEntries = true)
    public CategoryDto saveCategory(CategoryDto dto) {
        Category category = CategoryMapper.toCategory(dto);
        return CategoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    @Cacheable(value = Constants.CATEGORY_LIST_KEY_PREFIX)
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::toCategoryDto).toList();
    }

    @Override
    @Cacheable(value = Constants.PRODUCT_LIST_KEY_PREFIX, key = "#categoryId")
    public List<ProductDto> getProductsByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ItemNotFoundException("Category not found with id: " + categoryId));
        List<Product> products = productRepository.findByCategory(category);
        return products.stream().map(ProductMapper::mapToProductDto).toList();
    }
}
