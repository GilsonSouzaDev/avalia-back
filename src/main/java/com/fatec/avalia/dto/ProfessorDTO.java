package com.fatec.avalia.dto;

import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.enums.PerfilProfessor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private PerfilProfessor perfilProfessor;
    private Set<Long> disciplinasIds;

    public ProfessorDTO(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.email = professor.getEmail();
        this.senha = professor.getSenha();
        this.perfilProfessor = professor.getPerfilProfessor();

        // Converter lista de disciplinas em IDs (evita loops recursivos JSON)
        this.disciplinasIds = professor.getDisciplinas()
                .stream()
                .map(d -> d.getId())
                .collect(Collectors.toSet());
    }

}
