package com.fatec.avalia.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String enunciado;

    @Column(nullable = false, length = 500)
    private Long codigoProfessor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternativa> alternativas = new ArrayList<>();

    public void setAlternativasParaCadastro(List<Alternativa> alternativas) {
        this.alternativas.clear();
        // Adiciona as novas e define a relação bidirecional
        if (alternativas != null) {
            for (Alternativa alt : alternativas) {
                alt.setPergunta(this); // Seta o "pai" na "filha"
                this.alternativas.add(alt);
            }
        }
    }
}
