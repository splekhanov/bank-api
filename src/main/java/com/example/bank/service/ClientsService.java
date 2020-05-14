package com.example.bank.service;

import com.example.bank.api.AlreadyExistException;
import com.example.bank.api.NotFoundException;
import com.example.bank.db.client.ClientRepository;
import com.example.bank.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.UUID.randomUUID;

@Service
public class ClientsService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client){
        clientRepository.findClientByEmail(client.getEmail()).ifPresent(e -> {
            throw new AlreadyExistException("Client with email '" + e.getEmail() + "' is already exists");
        });
        return clientRepository.save(client);
    }

    public Client getClient(Long id) throws NotFoundException {
        Optional<Client> clientOpt = clientRepository.findById(id);

        if (!clientOpt.isPresent()) {
            throw new NotFoundException("Client not found");
        }

        return clientOpt.get();
    }
}
