package com.fatec.avalia.dto.pergunta;

import com.fatec.avalia.dto.alternativa.AlternativaResponseDTO;
import com.fatec.avalia.dto.disciplina.DisciplinaSimplesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerguntaResponseDTO {

    private Long id;
    private String enunciado;
    private Long codigoProfessor;

    private DisciplinaSimplesDTO disciplina;

    private List<AlternativaResponseDTO> alternativas;
}