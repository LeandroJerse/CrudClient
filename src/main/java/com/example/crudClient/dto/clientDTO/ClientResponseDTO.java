package com.example.crudClient.dto.clientDTO;

import java.time.LocalDate;
import java.util.UUID;

public record ClientResponseDTO(UUID id, String name, String cpf,
                                Double income, LocalDate birthDate, Integer children) {
}
