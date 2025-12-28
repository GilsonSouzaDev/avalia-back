package com.fatec.avalia.dto.pergunta;

import com.fatec.avalia.dto.alternativa.AlternativaTextoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerguntaRequestDTO {

    @NotBlank(message = "O enunciado da pergunta não pode estar vazio")
    private String enunciado;

    @NotNull(message = "O ID do professor é obrigatório para associar a pergunta")
    private Long professorId;

    @NotNull(message = "O ID da disciplina é obrigatório")
    private Long disciplinaId;

    private String imagem;

    @Valid // Valida cada item da lista
    @NotNull(message = "A pergunta precisa ter alternativas")
    @Size(min = 4, max = 5, message = "A pergunta deve ter pelo menos 4 alternativas e no maximo 5 alternativas")
    private List<AlternativaTextoDTO> alternativas;
}











