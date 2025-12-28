package com.fatec.avalia.repository;

import com.fatec.avalia.entity.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    List<Pergunta> findByDisciplinaId(Long disciplinaId);
    List<Pergunta> findByProfessorId(Long professorId);
    void deleteByDisciplinaId(Long disciplinaId);
    void deleteByProfessorIdAndDisciplinaId(Long professorId, Long disciplinaId);
    void deleteByProfessorId(Long professorId);
}