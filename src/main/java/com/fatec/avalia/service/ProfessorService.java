package com.fatec.avalia.service;

import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<Professor> listarTodos() {
        return repository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

