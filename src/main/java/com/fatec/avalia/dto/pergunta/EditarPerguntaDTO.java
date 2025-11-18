package com.fatec.avalia.dto.pergunta;

import com.fatec.avalia.dto.alternativa.AlternativaCadastradaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditarPerguntaDTO {

    @NotBlank
    private String enunciado;
    private Long professorId;

    @NotNull(message = "É necessário informar o ID da disciplina")
    private Long disciplinaId;

    @NotNull
    @Size(min = 4, max = 5, message = "A questão só pode ter 4 ou 5 alternativas")
    @Valid
    private List<AlternativaCadastradaDTO> alternativas;

}
