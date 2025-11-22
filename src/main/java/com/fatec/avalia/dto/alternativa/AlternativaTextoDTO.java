package com.fatec.avalia.dto.alternativa;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativaTextoDTO {
    @NotBlank(message = "O texto da alternativa não pode ser vazio")
    private String texto;
}