package dev.prithwish.spring_boot_thymeleaf_example.service;

import dev.prithwish.spring_boot_thymeleaf_example.model.Blog;
import dev.prithwish.spring_boot_thymeleaf_example.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getRecentBlogs() {
        return blogRepository.findTop3ByOrderByCreatedByDesc();
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(UUID id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }
}
