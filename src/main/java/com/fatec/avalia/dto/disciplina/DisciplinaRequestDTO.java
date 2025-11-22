package com.fatec.avalia.dto.disciplina;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaRequestDTO {

    @NotBlank(message = "O nome da disciplina é obrigatório")
    private String nome;

    // Não colocamos @NotBlank ou @NotNull porque a lógica do seu sistema
    // diz que se vier nulo, o sistema gera uma cor automática.
    private String cor;
}