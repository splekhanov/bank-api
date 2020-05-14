package com.example.bank.api;

import com.example.bank.model.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Api(value = "clients")
public interface ClientsApi {

    @ApiOperation(value = "Get client by id", nickname = "getClientById", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gets a client record", response = Client.class)})
    @RequestMapping(value = "/clients/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@ApiParam(value = "", required = true) @PathVariable Long id);

    @ApiOperation(value = "create a client", nickname = "createClient")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "client created"),
            @ApiResponse(code = 400, message = "invalid client"),
            @ApiResponse(code = 409, message = "client already exists")})
    @RequestMapping(value = "/clients",
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Map> createClient(@ApiParam(value = "client object record") @Valid @RequestBody Client body);

}
