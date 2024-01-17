package com.Cheesedz.config;

import com.Cheesedz.model.Order;
import com.Cheesedz.model.Product;
import com.Cheesedz.repository.OrderRepository;
import com.Cheesedz.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDatabase {
    private static final Logger logger = LoggerFactory.getLogger(ConfigDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepository) {
        return args -> {
//            Order A = new Order(1L,"Cash","Shipped",
//                    "2024-01-10","2024-01-19",1239L);
//            Order B = new Order(1L,"Cash","Shipped",
//                    "2024-01-18","2024-01-19",1219L);
//            logger.info("inserted record: " + orderRepository.save(A));
//            logger.info("inserted record: " + orderRepository.save(B));
        };
    }
}
