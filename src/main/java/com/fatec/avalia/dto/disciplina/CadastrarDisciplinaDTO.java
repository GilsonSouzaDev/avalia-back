package com.fatec.avalia.dto.disciplina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarDisciplinaDTO {

    @NotBlank
    private String nome;

    @Pattern(regexp = "^$|^#([A-Fa-f0-9]{6})$", message = "A cor deve ser vazia ou no formato hexadecimal. Exemplo: #A1B2C3")
    private String cor;

}
