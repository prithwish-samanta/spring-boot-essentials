package dev.prithwish.spring_boot_thymeleaf_example.service;

import dev.prithwish.spring_boot_thymeleaf_example.model.Blog;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    List<Blog> getRecentBlogs();

    List<Blog> getAllBlogs();

    Blog getBlogById(UUID id);

    Blog saveBlog(Blog blog);
}
