package com.fatec.avalia.dto.disciplina;

import com.fatec.avalia.entity.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListarDisciplinaDTO {
    private Long id;
    private String nome;
    private String cor;

    public ListarDisciplinaDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.cor = disciplina.getCor();
    }
}
