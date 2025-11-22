package com.fatec.avalia.repository;

import com.fatec.avalia.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByCodigo(Long codigo);

    Optional<Professor> findByEmail(String email);

    // Útil para validação rápida no cadastro (retorna true/false)
    boolean existsByEmail(String email);
    boolean existsByCodigo(Long codigo);
}