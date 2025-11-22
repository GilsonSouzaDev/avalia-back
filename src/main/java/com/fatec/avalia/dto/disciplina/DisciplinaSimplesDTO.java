package com.fatec.avalia.dto.disciplina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaSimplesDTO {
    private Long id;
    private String nome;
    private String cor;
}