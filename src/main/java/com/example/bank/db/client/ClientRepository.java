package com.example.bank.db.client;

import com.example.bank.db.BaseRepository;
import com.example.bank.model.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {

    Optional<Client> findClientByEmail(String email);
}
