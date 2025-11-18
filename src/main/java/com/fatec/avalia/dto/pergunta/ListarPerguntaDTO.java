package com.fatec.avalia.dto.pergunta;

import com.fatec.avalia.dto.alternativa.ListarAlternativaDTO;
import com.fatec.avalia.entity.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListarPerguntaDTO {
    private Long id;
    private String enunciado;
    private String professor;
    private String disciplina;
    private List<ListarAlternativaDTO> alternativas;

    public ListarPerguntaDTO(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.enunciado = pergunta.getEnunciado();
        this.professor = pergunta.getCriadoPor().getNome();
        this.disciplina = pergunta.getDisciplina().getNome();

        this.alternativas = pergunta.getAlternativas()
                .stream()
                .map(alternativa -> new ListarAlternativaDTO(alternativa))
                .collect(Collectors.toList());

    }

}
