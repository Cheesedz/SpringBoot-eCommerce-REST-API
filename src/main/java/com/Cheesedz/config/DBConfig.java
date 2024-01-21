package com.Cheesedz.config;

import com.Cheesedz.model.Shop;
import com.Cheesedz.repository.OrderRepository;
import com.Cheesedz.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);

    @Bean
    CommandLineRunner initDatabase(ShopRepository shopRepository) {
        return args -> {
            Shop A = new Shop("ABC", "Mall", "Clothes", "Clothes shop No1 HN", 10100L, 24L, "2020-10-23", 4.6);
            logger.info("inserted record: " + shopRepository.save(A));
            //logger.info("inserted record: " + orderRepository.save(B));
        };
    }
}
