package com.Cheesedz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> getClient() {
        return clientRepository.findAll();
    }

    public void addNewClient(Client client) {
        List<Client> cache = clientRepository.findAll();
        if (!cache.contains(client)) {
            clientRepository.save(client);
        } else {
            System.out.println("This client existed");
        }
    }

    public void findClientByEmail(Client client) {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(client.getEmail());
        if (clientOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        System.out.println(client);
        clientRepository.save(client);
    }

    public void deleteClient(Long clientID) {
        boolean existed = clientRepository.existsById(clientID);
        if (!existed) {
            throw new IllegalStateException("The client with ID: " + clientID + " does not exists");
        }
        clientRepository.deleteById(clientID);
    }

    @Transactional
    public void updateClient(Long clientID, String name, String email, String dob, String phone) {
        Client client = clientRepository.findById(clientID).orElseThrow(
                () -> new IllegalStateException("The client with ID: " + clientID + " does not exists")
        );

        if (name != null && name.length() > 0 && !Objects.equals(client.getName(), name)) {
            client.setName(name);
        }

        if (dob != null && dob.length() > 0 && !Objects.equals(client.getDob(), dob)) {
            client.setDob(dob);
        }

        if (phone != null &&  phone.length() > 0 && !Objects.equals(client.getPhone(), phone)) {
            client.setPhone(phone);
        }

        if (email != null && email.length() > 0 && !Objects.equals(client.getEmail(), email)) {
            Optional<Client> clientOptional = clientRepository.findClientByEmail(email);
            if (clientOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            client.setEmail(email);
        }
    }
}
