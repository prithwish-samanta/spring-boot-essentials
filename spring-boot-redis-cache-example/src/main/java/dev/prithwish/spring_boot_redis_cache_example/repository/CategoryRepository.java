package dev.prithwish.spring_boot_redis_cache_example.repository;

import dev.prithwish.spring_boot_redis_cache_example.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
