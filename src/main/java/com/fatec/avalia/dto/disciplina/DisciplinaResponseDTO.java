package com.fatec.avalia.dto.disciplina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaResponseDTO {

    private Long id;
    private String nome;
    private String cor;

    // Lista contendo apenas os IDs das perguntas vinculadas a esta disciplina
    private List<Long> perguntasIds = new ArrayList<>();
}
