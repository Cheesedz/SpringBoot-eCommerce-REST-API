package com.Cheesedz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    record Client(
            Long id,
            String address,
            String name,
            String email,
            String dob
    ) {}
}
