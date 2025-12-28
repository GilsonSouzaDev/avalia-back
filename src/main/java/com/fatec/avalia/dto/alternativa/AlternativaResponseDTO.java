package com.fatec.avalia.dto.alternativa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativaResponseDTO {

    private Long id;
    private String texto;
    private boolean correta;
    private Long perguntaId;
}