package com.Cheesedz;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class eCommerceAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(eCommerceAPIApplication.class, args);
    }

    @GetMapping
    public String greeting() {
        return "index";
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
