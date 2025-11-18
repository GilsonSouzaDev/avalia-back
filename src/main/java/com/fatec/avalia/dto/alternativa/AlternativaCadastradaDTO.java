package com.fatec.avalia.dto.alternativa;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlternativaCadastradaDTO {

    private Long id;

    @NotBlank(message = "O texto da alternativa não pode estar vazio")
    private String texto;

    private boolean correta;

}
