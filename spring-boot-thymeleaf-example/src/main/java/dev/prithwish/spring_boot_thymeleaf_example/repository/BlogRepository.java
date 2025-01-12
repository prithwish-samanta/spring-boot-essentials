package dev.prithwish.spring_boot_thymeleaf_example.repository;

import dev.prithwish.spring_boot_thymeleaf_example.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {
    List<Blog> findTop3ByOrderByCreatedByDesc();
}
