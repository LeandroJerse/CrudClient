package com.example.crudClient.service;

import com.example.crudClient.domain.Client;
import com.example.crudClient.dto.clientDTO.ClientRequestDTO;
import com.example.crudClient.dto.clientDTO.ClientResponseDTO;
import com.example.crudClient.repository.ClientRepositories;
import com.example.crudClient.service.exceptions.DatabaseException;
import com.example.crudClient.service.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepositories clientRepositories;

    @Transactional(readOnly = true)
    public ClientResponseDTO findClientById(UUID id){

        Client client = clientRepositories.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado") );
        return copyEntityToDTOResponse(client);

    }


    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepositories.findAll(pageable);
        return clients.map(this::copyEntityToDTOResponse);
    }

    @Transactional
    public Client createClient (ClientRequestDTO clientRequestDTO) {
        Client client = new Client();
        copyDtoToEntity(clientRequestDTO,client);
        clientRepositories.save(client);
        return client;
    }
    @Transactional
    public ClientRequestDTO  updateClient (UUID id, ClientRequestDTO clientRequestDTO) {
        try{
            Client client =clientRepositories.getReferenceById(id);
            copyDtoToEntity(clientRequestDTO,client);
            clientRepositories.save(client);
            return copyEntityToDTORequest(client);
        }
        catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteClient (UUID id) {
        if(!clientRepositories.existsById(id)){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        try{
            clientRepositories.deleteById(id);
        }
        catch(DataIntegrityViolationException e ){
            throw new DatabaseException("Erro ao deletar cliente");

        }

    }






    private void copyDtoToEntity(ClientRequestDTO clientRequestDTO, Client client){

        client.setName(clientRequestDTO.name());
        client.setCpf(clientRequestDTO.cpf());
        client.setIncome(clientRequestDTO.income());
        client.setBirthDate(clientRequestDTO.birthDate());
        client.setChildren(clientRequestDTO.children());

    }

    private ClientResponseDTO copyEntityToDTOResponse( Client client){
        return new ClientResponseDTO(client.getId(),client.getName(),client.getCpf(),
                client.getIncome(),client.getBirthDate(),client.getChildren());
    }
    private ClientRequestDTO copyEntityToDTORequest( Client client){
        return new ClientRequestDTO( client.getName(),client.getCpf(),
                client.getIncome(),client.getBirthDate(),client.getChildren());
    }

    public @Valid ClientRequestDTO update(UUID id, ClientRequestDTO dto) {

        try {
            Client client = clientRepositories.getReferenceById(id);
            copyDtoToEntity(dto,client);
            clientRepositories.save(client);
            return dto;
        }
        catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id) {
        if(!clientRepositories.existsById(id)){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        try{
            clientRepositories.deleteById(id);
        }
        catch(DataIntegrityViolationException e ){
            throw new DatabaseException("Erro ao deletar cliente");
        }

    }
}
