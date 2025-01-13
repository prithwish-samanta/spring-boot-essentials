package dev.prithwish.spring_boot_redis_cache_example.payload;

import java.util.UUID;

public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private CategoryDto category;

    public ProductDto() {
    }

    public ProductDto(UUID id, String name, String description, Double price, Integer stock, CategoryDto category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
