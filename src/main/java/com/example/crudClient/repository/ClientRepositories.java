package com.example.crudClient.repository;

import com.example.crudClient.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepositories extends JpaRepository<Client, UUID> {

}
