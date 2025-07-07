package com.example.crudClient.dto.clientDTO;

import java.time.LocalDate;

public record ClientRequestDTO(String name,  String cpf,
                               Double income, LocalDate birthDate, Integer children) {
}
