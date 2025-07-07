package com.example.crudClient.dto.clientDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientRequestDTO(@NotBlank String name, @NotBlank String cpf,
                               Double income, @PastOrPresent LocalDate birthDate, Integer children) {
}
