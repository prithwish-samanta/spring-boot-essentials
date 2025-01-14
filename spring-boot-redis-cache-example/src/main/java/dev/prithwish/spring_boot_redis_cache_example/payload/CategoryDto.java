package dev.prithwish.spring_boot_redis_cache_example.payload;

import java.io.Serializable;
import java.util.UUID;

public class CategoryDto implements Serializable {
    private UUID id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(UUID id, String name) {
        this.id = id;
        this.name = name;
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
}
