package com.fatec.avalia.dto.professor;
import com.fatec.avalia.dto.disciplina.DisciplinaSimplesDTO;
import com.fatec.avalia.enums.PerfilProfessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private PerfilProfessor perfilProfessor;

    // Retorna os objetos simples, sem causar loop infinito
    private List<DisciplinaSimplesDTO> disciplinas;
}