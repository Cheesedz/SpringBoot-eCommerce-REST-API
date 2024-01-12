package com.Cheesedz.config;

import com.Cheesedz.entity.Client;
import com.Cheesedz.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository repository) {
        return args -> {
            Client cheesedz = new Client(
                    "Ha Noi",
                    "Nguyen Chi Nghia",
                    "22028232@vnu.edu.vn",
                    "2004-06-08",
                    "0932485923"
            );

            Client windbow = new Client(
                    "Ha Noi",
                    "Nguyen Viet Phong",
                    "22028231@vnu.edu.vn",
                    "2004-07-02",
                    "0932485923"
            );

            //repository.saveAll(List.of(cheesedz, windbow));
        };
    }
}
