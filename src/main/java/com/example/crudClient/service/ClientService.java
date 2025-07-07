package com.example.crudClient.service;

import com.example.crudClient.domain.Client;
import com.example.crudClient.dto.clientDTO.ClientResponseDTO;
import com.example.crudClient.repository.ClientRepositories;
import com.example.crudClient.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepositories clientRepositories;

    @Transactional(readOnly = true)
    public ClientResponseDTO findClientById(UUID id){

        Client client = clientRepositories.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado") );
        return new ClientResponseDTO(client.getId(),client.getCpf(), client.getName(),
                client.getIncome(),client.getBirthDate(),client.getChildren());


    }
}
