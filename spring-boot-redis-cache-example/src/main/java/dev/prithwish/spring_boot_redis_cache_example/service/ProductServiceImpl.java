package dev.prithwish.spring_boot_redis_cache_example.service;

import dev.prithwish.spring_boot_redis_cache_example.entity.Category;
import dev.prithwish.spring_boot_redis_cache_example.entity.Product;
import dev.prithwish.spring_boot_redis_cache_example.exception.InvalidUserInputException;
import dev.prithwish.spring_boot_redis_cache_example.exception.ItemNotFoundException;
import dev.prithwish.spring_boot_redis_cache_example.mapper.ProductMapper;
import dev.prithwish.spring_boot_redis_cache_example.payload.ProductDto;
import dev.prithwish.spring_boot_redis_cache_example.repository.CategoryRepository;
import dev.prithwish.spring_boot_redis_cache_example.repository.ProductRepository;
import dev.prithwish.spring_boot_redis_cache_example.utils.Constants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Cacheable(value = Constants.PRODUCT_LIST_KEY_PREFIX)
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::mapToProductDto).toList();
    }

    @Override
    @CacheEvict(value = Constants.PRODUCT_LIST_KEY_PREFIX, allEntries = true)
    public ProductDto saveProduct(ProductDto dto) {
        if (!StringUtils.hasText(dto.getCategory().getId().toString())) {
            throw new InvalidUserInputException("Category id is required");
        }
        Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> new ItemNotFoundException("Category not found with id: " + dto.getCategory().getId()));
        Product product = ProductMapper.mapToProduct(dto);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    @Cacheable(value = Constants.PRODUCT_KEY_PREFIX, key = "#productId")
    public ProductDto getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product not found with id: " + productId));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = Constants.PRODUCT_LIST_KEY_PREFIX, allEntries = true)},
            put = {@CachePut(value = Constants.PRODUCT_KEY_PREFIX, key = "#productId")}
    )
    public ProductDto updateProduct(UUID productId, ProductDto dto) {
        Product oldProduct = productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product not found with id: " + productId));
        if (StringUtils.hasText(dto.getName())) {
            oldProduct.setName(dto.getName());
        }
        if (StringUtils.hasText(dto.getDescription())) {
            oldProduct.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            oldProduct.setPrice(dto.getPrice());
        }
        if (dto.getStock() != null) {
            oldProduct.setStock(dto.getStock());
        }
        if (dto.getCategory() != null && dto.getCategory().getId() != null && StringUtils.hasText(dto.getCategory().getId().toString())) {
            Category category = categoryRepository.findById(dto.getCategory().getId())
                    .orElseThrow(() -> new ItemNotFoundException("Category not found with id: " + dto.getCategory().getId()));
            oldProduct.setCategory(category);
        }
        return ProductMapper.mapToProductDto(productRepository.save(oldProduct));
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = Constants.PRODUCT_LIST_KEY_PREFIX, allEntries = true),
                    @CacheEvict(value = Constants.PRODUCT_KEY_PREFIX, key = "#productId")
            }
    )
    public void deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = Constants.PRODUCT_LIST_KEY_PREFIX, allEntries = true)},
            put = {@CachePut(value = Constants.PRODUCT_KEY_PREFIX, key = "#productId")}
    )
    public void updateInventory(UUID productId, Integer inventory) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product not found with id: " + productId));
        product.setStock(inventory);
        productRepository.save(product);
    }

    @Override
    @Cacheable(value = Constants.PRODUCT_KEY_PREFIX, key = "#keyword")
    public List<ProductDto> searchProduct(String keyword) {
        List<Product> products = productRepository.findByNameLikeIgnoreCase("%" + keyword + "%");
        return products.stream().map(ProductMapper::mapToProductDto).toList();
    }
}
