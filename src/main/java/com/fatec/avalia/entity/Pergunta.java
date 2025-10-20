package com.fatec.avalia.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Perguntas")
public class Pergunta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conteudo;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;


}
