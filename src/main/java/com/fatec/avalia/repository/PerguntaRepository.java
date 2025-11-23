package com.fatec.avalia.repository;

import com.fatec.avalia.entity.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    List<Pergunta> findByCodigoProfessor(Long codigoProfessor);

    List<Pergunta> findByDisciplinaId(Long disciplinaId);

    void deleteByCodigoProfessor(Long codigoProfessor);

    void deleteByDisciplinaId(Long disciplinaId);

    void deleteByCodigoProfessorAndDisciplinaId(Long codigoProfessor, Long disciplinaId);

}