package com.Cheesedz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("")
    public String greeting() {
        return "Hello";
    }

    record Client(
            Long id,
            String address,
            String name,
            String email,
            String dob
    ) {}
}
