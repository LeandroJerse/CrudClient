package com.example.crudClient.controller;

import com.example.crudClient.domain.Client;
import com.example.crudClient.dto.clientDTO.ClientRequestDTO;
import com.example.crudClient.dto.clientDTO.ClientResponseDTO;
import com.example.crudClient.service.ClientService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientService clientService;



    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable UUID id){
        ClientResponseDTO client  = clientService.findClientById(id);
        return ResponseEntity.ok(client);

    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> getAllClients(Pageable pageable){
        Page<ClientResponseDTO> clients = clientService.findAll(pageable);
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO){

        Client newClient = clientService.createClient(clientRequestDTO);
        return ResponseEntity.ok(newClient);
        
    }




}
