package com.example.crudClient.dto.clientDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClientRequestDTO(@NotBlank @Size(min = 2, max = 50) String name, @NotBlank @Size(min = 11, max  = 11) String cpf,
                               Double income, @PastOrPresent LocalDate birthDate, Integer children) {
}
