package com.example.bank.api;

import com.example.bank.model.Client;
import com.example.bank.service.ClientsService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class ClientsApiController implements ClientsApi {

    @Autowired
    private ClientsService clientsService;

    @Override
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        try {
            Client client = clientsService.getClient(id);
            return ResponseEntity.ok(client);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Map> createClient(@ApiParam(value = "client object record") @Valid @RequestBody Client client) {
        Client savedClient = clientsService.createClient(client);
        return ResponseEntity.ok(Collections.singletonMap("id", savedClient.getId().toString()));
    }
}
