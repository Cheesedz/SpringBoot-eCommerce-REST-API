package com.Cheesedz;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
public class ClientService {

    @GetMapping
    public List<Client> getClient() {
        return List.of(
                new Client(
                        22028232,
                        "Ha Noi",
                        "Nguyen Chi Nghia",
                        "22028232@vnu.edu.vn",
                        "2004-06-08"
                )
        );
    }
}
