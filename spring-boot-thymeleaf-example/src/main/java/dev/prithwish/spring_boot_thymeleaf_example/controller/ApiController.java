package dev.prithwish.spring_boot_thymeleaf_example.controller;

import dev.prithwish.spring_boot_thymeleaf_example.model.Contact;
import dev.prithwish.spring_boot_thymeleaf_example.service.BlogService;
import dev.prithwish.spring_boot_thymeleaf_example.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.UUID;

@Controller("/blog")
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    private final BlogService blogService;
    private final ContactService contactService;

    public ApiController(BlogService blogService, ContactService contactService) {
        this.blogService = blogService;
        this.contactService = contactService;
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
        // Empty contact object to store data
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        model.addAttribute("copyrightYear", LocalDate.now().getYear());
        model.addAttribute("pageHeaderFilename", "contact-bg.jpg");
        model.addAttribute("pageHeaderTitle", "Contact Me");
        model.addAttribute("pageHeaderSubtitle", "Have questions? I have answers.");
        model.addAttribute("isSuccess", null);
        return "contact";
    }

    @PostMapping("/contact/save")
    public String postContact(Model model, @ModelAttribute Contact contact) {
        try {
            Contact savedContact = contactService.saveContact(contact);
            log.info("Contact details saved successfully : {}", savedContact.getId());
            model.addAttribute("contact", new Contact());
            model.addAttribute("isSuccess", Boolean.TRUE);
        } catch (Exception e) {
            model.addAttribute("contact", contact);
            model.addAttribute("isSuccess", Boolean.FALSE);
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("copyrightYear", LocalDate.now().getYear());
        model.addAttribute("pageHeaderFilename", "contact-bg.jpg");
        model.addAttribute("pageHeaderTitle", "Contact Me");
        model.addAttribute("pageHeaderSubtitle", "Have questions? I have answers.");
        return "contact";
    }
}
