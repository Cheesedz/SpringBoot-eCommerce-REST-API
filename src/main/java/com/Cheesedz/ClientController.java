package com.Cheesedz;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Client> getClient() {
        return service.getClient();
    }

    @PostMapping
    public void registerNewClient(@RequestBody Client client) {
        //service.addNewClient(client);
        service.findClientByEmail(client);
    }

    @DeleteMapping(path = "{clientID}")
    public void deleteClient(@PathVariable("clientID") Long clientID) {
        service.deleteClient(clientID);
    }

    @PutMapping(path = "{clientID}")
    public void updateClient(
            @PathVariable("clientID") Long clientID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String dob,
            @RequestParam(required = false) String phone
    ) {
        service.updateClient(clientID, name, email, dob, phone);
    }
}
