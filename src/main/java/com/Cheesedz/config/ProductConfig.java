package com.Cheesedz.config;

import com.Cheesedz.model.Product;
import com.Cheesedz.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    private static final Logger logger = LoggerFactory.getLogger(ProductConfig.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            Product A = new Product("Yamaha Exciter 150","Ngon","Yamaha Town",
                    14500L,2340.0,4.9,"Motorbike",1239L, "");
            Product B = new Product("Yamaha Exciter 155 VVA","Ngon","Yamaha Town",
                    14240L,2320.0,4.9,"Motorbike",1223L, "");
            logger.info("inserted record: " + productRepository.save(A));
            logger.info("inserted record: " + productRepository.save(B));
        };
    }
}
