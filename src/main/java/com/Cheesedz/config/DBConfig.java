package com.Cheesedz.config;

import com.Cheesedz.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);

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
