package com.fatec.avalia.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.avalia.enums.PerfilProfessor;
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
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(name = "perfil_professor", nullable = false)
    @Enumerated(EnumType.STRING)
    private PerfilProfessor perfilProfessor;

    @ManyToMany
    @JoinTable(
            name = "professor_disciplina",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    @JsonIgnoreProperties("professores")
    private Set<Disciplina> disciplinas = new HashSet<>();

    // Relação direta agora mapeada pelo atributo 'professor' na classe Pergunta
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("professor")
    private Set<Pergunta> perguntas = new HashSet<>();

}