package com.fatec.avalia.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "disciplinas")
    @JsonIgnoreProperties("disciplinas") // 👈 evita recursão inversa
    private Set<Professor> professores = new HashSet<>();

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnoreProperties("disciplina")
    private Set<Pergunta> perguntas = new HashSet<>();
}
