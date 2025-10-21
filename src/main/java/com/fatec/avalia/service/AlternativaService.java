package com.fatec.avalia.service;

import com.fatec.avalia.entity.Alternativa;
import com.fatec.avalia.repository.AlternativaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlternativaService {

    private final AlternativaRepository repository;

    public AlternativaService(AlternativaRepository repository) {
        this.repository = repository;
    }

    public List<Alternativa> listarTodas() {
        return repository.findAll();
    }

    public Alternativa salvar(Alternativa alternativa) {
        return repository.save(alternativa);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

