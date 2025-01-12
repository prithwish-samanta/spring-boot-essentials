package dev.prithwish.spring_boot_thymeleaf_example.bootstrap;

import dev.prithwish.spring_boot_thymeleaf_example.model.Blog;
import dev.prithwish.spring_boot_thymeleaf_example.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(InitDataLoader.class);
    private final BlogRepository blogRepository;


    public InitDataLoader(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (blogRepository.count() == 0) {
            log.info("No blogs found in database");
            log.info("Start - Creating blogs");

            Blog blog1 = new Blog();
            blog1.setTitle("Man must explore, and this is exploration at its greatest");
            blog1.setSubTitle("Problems look mighty small from 150 miles up");
            blog1.setCreatedBy("My Blog");
            blogRepository.save(blog1);

            Blog blog2 = new Blog();
            blog2.setTitle("I believe every human has a finite number of heartbeats. I don't intend to waste any of mine.");
            blog2.setCreatedBy("My Blog");
            blogRepository.save(blog2);

            Blog blog3 = new Blog();
            blog3.setTitle("Science has not yet mastered prophecy");
            blog3.setSubTitle("We predict too much for the next year and yet far too little for the next ten.");
            blog3.setCreatedBy("My Blog");
            blogRepository.save(blog3);

            Blog blog4 = new Blog();
            blog4.setTitle("Failure is not an option");
            blog4.setSubTitle("Many say exploration is part of our destiny, but it’s actually our duty to future generations.");
            blog4.setCreatedBy("My Blog");
            blogRepository.save(blog4);

            log.info("Total blogs created: {}", blogRepository.count());
            log.info("Finish - Creating blogs");
        }
    }
}
