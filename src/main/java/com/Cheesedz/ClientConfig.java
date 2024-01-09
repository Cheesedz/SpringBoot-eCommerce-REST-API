package com.Cheesedz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository repository) {
        return args -> {
            Client cheesedz = new Client(
                    "Ha Noi",
                    "Nguyen Chi Nghia",
                    "22028232@vnu.edu.vn",
                    "2004-06-08"
            );

            Client windbow = new Client(
                    "Ha Noi",
                    "Nguyen Viet Phong",
                    "22028231@vnu.edu.vn",
                    "2004-07-02"
            );

            //repository.saveAll(List.of(cheesedz, windbow));
        };
    }
}
