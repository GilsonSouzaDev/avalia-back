package com.fatec.avalia.dto.alternativa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativaRequestDTO {


    private Long id;

    @NotBlank(message = "O texto da alternativa é obrigatório")
    private String texto;

    @NotNull(message = "O ID da pergunta é obrigatório para vincular a alternativa")
    private Long perguntaId;
}