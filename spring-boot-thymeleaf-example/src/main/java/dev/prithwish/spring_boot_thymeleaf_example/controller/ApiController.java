package dev.prithwish.spring_boot_thymeleaf_example.controller;

import dev.prithwish.spring_boot_thymeleaf_example.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.UUID;

@Controller("/blog")
public class ApiController {
    private final BlogService blogService;

    public ApiController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("blogs", blogService.getRecentBlogs());
        model.addAttribute("copyrightYear", LocalDate.now().getYear());
        model.addAttribute("pageHeaderFilename", "home-bg.jpg");
        model.addAttribute("pageHeaderTitle", "MindLog");
        model.addAttribute("pageHeaderSubtitle", "Stories, Thoughts, and Ideas Worth Sharing");
        return "index";
    }

    @GetMapping("/blogs")
    public String posts(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        model.addAttribute("copyrightYear", LocalDate.now().getYear());
        model.addAttribute("pageHeaderFilename", "home-bg.jpg");
        model.addAttribute("pageHeaderTitle", "My Blogs");
        model.addAttribute("pageHeaderSubtitle", "Please share my blog, if you like");
        return "blogs-list";
    }

    @GetMapping("/blog/{blogId}")
    public String post(@PathVariable UUID blogId, Model model) {
        model.addAttribute("blog", blogService.getBlogById(blogId));
        model.addAttribute("copyrightYear", LocalDate.now().getYear());
        return "blog";
    }

    @GetMapping("/about-me")
    public String aboutMe(Model model) {
        model.addAttribute("copyrightYear", LocalDate.now().getYear());
        model.addAttribute("pageHeaderFilename", "about-bg.jpg");
        model.addAttribute("pageHeaderTitle", "About Me");
        model.addAttribute("pageHeaderSubtitle", "This is what I do.");
        return "about-me";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("copyrightYear", LocalDate.now().getYear());
        model.addAttribute("pageHeaderFilename", "contact-bg.jpg");
        model.addAttribute("pageHeaderTitle", "Contact Me");
        model.addAttribute("pageHeaderSubtitle", "Have questions? I have answers.");
        return "contact";
    }
}
