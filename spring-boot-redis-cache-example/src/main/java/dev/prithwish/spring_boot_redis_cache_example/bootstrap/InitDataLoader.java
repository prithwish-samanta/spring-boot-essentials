package dev.prithwish.spring_boot_redis_cache_example.bootstrap;

import dev.prithwish.spring_boot_redis_cache_example.entity.Category;
import dev.prithwish.spring_boot_redis_cache_example.entity.Product;
import dev.prithwish.spring_boot_redis_cache_example.repository.CategoryRepository;
import dev.prithwish.spring_boot_redis_cache_example.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(InitDataLoader.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public InitDataLoader(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0 && categoryRepository.count() == 0) {
            log.info("No products and categories were found");
            log.info("START - Creating products and categories");

            Category category1 = new Category();
            category1.setName("Beauty");
            Category savedCategory1 = categoryRepository.save(category1);

            Category category2 = new Category();
            category2.setName("Fragrances");
            Category savedCategory2 = categoryRepository.save(category2);

            Category category3 = new Category();
            category3.setName("Furniture");
            Category savedCategory3 = categoryRepository.save(category3);

            Product product1 = new Product();
            product1.setName("Essence Mascara Lash Princess");
            product1.setDescription("The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.");
            product1.setPrice(9.99);
            product1.setStock(5);
            product1.setCategory(savedCategory1);

            Product product2 = new Product();
            product2.setName("Chanel Coco Noir Eau De");
            product2.setDescription("Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood. Perfect for evening occasions.");
            product2.setPrice(129.99);
            product2.setStock(41);
            product2.setCategory(savedCategory2);

            Product product3 = new Product();
            product3.setName("Annibale Colombo Bed");
            product3.setDescription("The Annibale Colombo Bed is a luxurious and elegant bed frame, crafted with high-quality materials for a comfortable and stylish bedroom.");
            product3.setPrice(1899.99);
            product3.setStock(47);
            product3.setCategory(savedCategory3);

            productRepository.saveAll(List.of(product1, product2, product3));

            log.info("Total categories created: {}", categoryRepository.count());
            log.info("Total products created: {}", productRepository.count());

            log.info("END - Creating products and categories");
        }
    }
}
