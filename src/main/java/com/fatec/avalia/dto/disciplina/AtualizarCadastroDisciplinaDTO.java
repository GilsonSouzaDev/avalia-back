package com.fatec.avalia.dto.disciplina;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtualizarCadastroDisciplinaDTO {
    //@NotNull(message = "É necessário informar o ID")
    private Long id;
    private String nome;
    private String cor;
}
